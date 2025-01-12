package net.meteoserpent.craftkaisen.items;

import net.meteoserpent.craftkaisen.util.ModTags;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolsTier {
    public static final Tier CURSED_TOOL = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_CURSED_TOOL,
            1400, 8,3,10, ()->null);
}
