package net.meteoserpent.craftkaisen.items;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.items.custom.FuelItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(CraftKaisen.MODID);

    public static final DeferredItem<Item> SIX_EYES = ITEMS.register("six_eyes",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> SPIRIT_ORB = ITEMS.register("spirit_orb",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.craftkaisen.spirit_orb"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });


//    public static final DeferredItem<Item> CURSED_COW_EGG = ITEMS.register("cursed_cow_egg",
//            () -> new SpawnEggItem(ModEntities.CURSED_COW, 0x303f24, 0xb3b3b3, new Item.Properties()));
//
//    public static final DeferredItem<Item> CURSED_SHEEP_EGG = ITEMS.register("cursed_sheep_egg",
//            () -> new SpawnEggItem(ModEntities.CURSED_SHEEP, 0xD573e6, 0x1d0000, new Item.Properties()));

    public static final DeferredItem<SwordItem> INVERTED_SPEAR_OF_HEAVEN = ITEMS.register("inverted_spear_of_heaven",
            ()-> new SwordItem(ModToolsTier.CURSED_TOOL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolsTier.CURSED_TOOL, 3, -2.4f))));

    public static final DeferredItem<Item> SWORD_OF_EXTERMINATION = ITEMS.register("sword_of_extermination",
            ()-> new SwordItem(ModToolsTier.CURSED_TOOL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolsTier.CURSED_TOOL, 3, -2.4f))));


    public static final DeferredItem<Item> DISASTER_FLAMES = ITEMS.register("disaster_flames",
            ()-> new FuelItem(new Item.Properties().stacksTo(1), Integer.MAX_VALUE));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
