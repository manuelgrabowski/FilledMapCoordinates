package net.manu_faktur.mapcoordinates.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.component.type.*;
import net.minecraft.nbt.NbtInt;

public class CoordMethods {
    private static final int[] MAP_WIDTH_BY_ZOOM_LEVEL = {128, 256, 512, 1024, 2048};

    public static void createCoordNBT(ItemStack itemStack, PlayerEntity playerEntity){
        NbtCompound tag = itemStack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT).copyNbt();
        tag.put("coordinateX", NbtInt.of((int)Math.floor(playerEntity.getPos().getX())));
        tag.put("coordinateZ", NbtInt.of((int)Math.floor(playerEntity.getPos().getZ())));
        tag.put("coordinateScale", NbtInt.of(0));
        itemStack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(tag));
    }

    public static void increaseScale(ItemStack map) {
        NbtCompound tag = map.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT).copyNbt();
        int zoomLevel = tag.getInt("coordinateScale");
        zoomLevel++;
        tag.put("coordinateScale", NbtInt.of(zoomLevel));
        map.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(tag));
    }

    private static int center(int point, int zoomLevel) {
        int width = MAP_WIDTH_BY_ZOOM_LEVEL[zoomLevel];

        int centerPoint = (int) Math.floor(((double)point + 64.0) / (double)width);

        centerPoint = (centerPoint * width) + (width / 2) - 64;
        return centerPoint;
    }

    public static int topLeftCorner(int p, int zoomLevel) {
        return center(p, zoomLevel) - (MAP_WIDTH_BY_ZOOM_LEVEL[zoomLevel] / 2);
    }

    public static int bottomRightCorner(int p, int zoomLevel) {
        return topLeftCorner(p, zoomLevel) + MAP_WIDTH_BY_ZOOM_LEVEL[zoomLevel] - 1;
    }
}
