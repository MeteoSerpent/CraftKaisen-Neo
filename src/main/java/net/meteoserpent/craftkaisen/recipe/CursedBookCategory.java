package net.meteoserpent.craftkaisen.recipe;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import java.util.function.IntFunction;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

public enum CursedBookCategory implements StringRepresentable {
    TECHNIQUE("technique", 0),
    BINDING_VOW("binding_vow", 1),
    UPGRADE("upgrade", 2);

    public static final Codec<CursedBookCategory> CODEC = StringRepresentable.fromEnum(CursedBookCategory::values);
    public static final IntFunction<CursedBookCategory> BY_ID = ByIdMap.continuous(CursedBookCategory::id, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StreamCodec<ByteBuf, CursedBookCategory> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, CursedBookCategory::id);
    private final String name;
    private final int id;

    private CursedBookCategory(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    private int id() {
        return this.id;
    }
}
