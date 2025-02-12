package net.meteoserpent.craftkaisen.screen;

import net.meteoserpent.craftkaisen.blocks.ModBlocks;
import net.meteoserpent.craftkaisen.recipe.CursedRecipe;
import net.meteoserpent.craftkaisen.recipe.CursedShapeless;
import net.meteoserpent.craftkaisen.recipe.ModRecipes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class CursedMenu extends AbstractContainerMenu {

    //public final CursedTechniqueCrafterBlockEntity blockEntity;
    private final Level level;

    private final CraftingContainer craftSlots = new TransientCraftingContainer(this, 3,3);
    private final ResultContainer resultSlot = new ResultContainer();
    private final ContainerLevelAccess access;
    private final Player player;


    public CursedMenu(int pContainerId, Inventory inv, ContainerLevelAccess access) {
        super(ModMenuTypes.CURSED_MENU.get(), pContainerId);
        checkContainerSize(inv, 10);
        //this.blockEntity = ((CursedTechniqueCrafterBlockEntity) blockEntity);
        this.level = inv.player.level();
        this.access = access;
        this.player = inv.player;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    this.addSlot(new Slot(this.craftSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
                }
            }
            this.addSlot(new CursedSlot(this, this.resultSlot, 0, 124, 35));
            //new ResultSlot(inv.player, this.craftSlots, this.resultSlot, 0, 124, 35)
    }

    public CursedMenu(int i, Inventory inventory, RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        this(i, inventory, ContainerLevelAccess.NULL);
    }

    @Override
    public void removed(Player pPlayer) {
        clearContainer(pPlayer, craftSlots);
        super.removed(pPlayer);
    }

    @Override
    public void slotsChanged(Container pContainer) {
        this.access.execute((level, pos) -> {
            logic(pContainer);
        });
    }

    private void logic(Container pContainer) {
        if (level.isClientSide()) {
            return;
        }

        for (int i = 0; i < pContainer.getContainerSize(); i++) {
            System.out.println(pContainer.getItem(i));
        }

        if (hasRecipe()) {
            craftItem();
        }
        else if (craftSlots.isEmpty()) {
            resultSlot.clearContent();
        }
    }

    private void consumeIngredients() {
        for (int i = 0; i < craftSlots.getContainerSize(); i++) {
            if (!craftSlots.getItem(i).isEmpty()) {
                craftSlots.removeItem(i, 1);
            }
        }
    }

    private void craftItem() {
        Optional<RecipeHolder<CursedRecipe>> recipeShaped = getCurrentRecipe();
        Optional<RecipeHolder<CursedShapeless>> recipeShapeless = getCurrentRecipeShapeless();

        ServerPlayer serverplayer = (ServerPlayer) player;
        ItemStack result;

        if (recipeShaped.isEmpty()) {
            if (recipeShapeless.isEmpty()) {
                result = null;
            }
            else {
                result = recipeShapeless.get().value().assemble(craftSlots.asCraftInput(), level.registryAccess());
            }
        } else {
            result = recipeShaped.get().value().assemble(craftSlots.asCraftInput(), level.registryAccess());;
        }

        resultSlot.setItem(0, result);
        setRemoteSlot(0, result);
        serverplayer.connection.send(new ClientboundContainerSetSlotPacket(containerId, incrementStateId(), 0, result));
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<CursedRecipe>> recipeShaped = getCurrentRecipe();
        Optional<RecipeHolder<CursedShapeless>> recipeShapeless = getCurrentRecipeShapeless();

        System.out.printf("%b %b %n",recipeShaped.isEmpty(), recipeShapeless.isEmpty());

        if (recipeShaped.isEmpty()) {
            if (recipeShapeless.isEmpty()) {
                return false;
            }
            else {
                resultSlot.setRecipeUsed((ServerPlayer) player, recipeShapeless.get());
            }
        } else {
            resultSlot.setRecipeUsed((ServerPlayer) player, recipeShaped.get());
        }



        return outputSlotIsEmpty();
    }

    private Optional<RecipeHolder<CursedShapeless>> getCurrentRecipeShapeless() {

        System.out.println(this.level.getServer().getRecipeManager().getRecipeFor(CursedShapeless.Type.INSTANCE, craftSlots.asCraftInput(), level));

        return this.level.getServer().getRecipeManager().getRecipeFor(CursedShapeless.Type.INSTANCE, craftSlots.asCraftInput(), level);
    }

    private Optional<RecipeHolder<CursedRecipe>> getCurrentRecipe() {

        System.out.println(craftSlots.asCraftInput().isEmpty());

        return this.level.getServer().getRecipeManager().getRecipeFor(ModRecipes.CURSED_RECIPE.get(), craftSlots.asCraftInput(), level);
    }

    private boolean outputSlotIsEmpty() {
        return resultSlot.isEmpty();
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 10;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.access,
                pPlayer, ModBlocks.CURSED_TECHNIQUE_CRAFTER.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public static class CursedSlot extends Slot {

        private final CursedMenu menu;

        public CursedSlot(CursedMenu menu, Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
            this.menu = menu;
        }

        @Override
        public void onTake(Player pPlayer, ItemStack pStack) {
            menu.consumeIngredients();
        }
    }
}
