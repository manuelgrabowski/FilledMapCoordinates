package net.manu_faktur.mapcoordinates.mixin;

import net.manu_faktur.mapcoordinates.util.ModConfig;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.manu_faktur.mapcoordinates.util.CoordMethods;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

@Mixin(FilledMapItem.class)
public class FilledMapItemMixin {
    // When the player zooms out a map, increase scale level in component data
    @Inject(method = "scale", at = @At("HEAD"))
    private static void scale(ItemStack map, World world, CallbackInfo info) {
        if (ModConfig.getBooleanValue("enabled")) {
            CoordMethods.increaseScale(map);
        }
    }

    // Show coordinates in map tooltip
    @Inject(method = "appendTooltip", at = @At("TAIL"))
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo info) {
        Text coordsTooltip = getCoordTooltip(stack);
        if(coordsTooltip != null && ModConfig.getBooleanValue("enabled")) {
            tooltip.add(coordsTooltip);
        }
    }

    // Calculate map coverage area based on coordinates and zoom level in component data if present
    private static Text getCoordTooltip(ItemStack itemStack){
        Style LORE_STYLE = Style.EMPTY.withColor(Formatting.GRAY).withItalic(false);

        NbtCompound tag = itemStack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT).copyNbt();
        int coordinateX = tag.getInt("coordinateX");
        int coordinateZ = tag.getInt("coordinateZ");
        int zoomLevel = tag.getInt("coordinateScale");
        if(tag.getKeys().containsAll(Arrays.asList("coordinateX","coordinateZ","coordinateScale"))) {
            String topLeft = CoordMethods.topLeftCorner(coordinateX, zoomLevel) + ", " + CoordMethods.topLeftCorner(coordinateZ, zoomLevel);
            String bottomRight = CoordMethods.bottomRightCorner(coordinateX, zoomLevel) + ", " + CoordMethods.bottomRightCorner(coordinateZ, zoomLevel);

            return Text.translatable("net.manu_faktur.mapcoordinates.coords", topLeft, bottomRight).setStyle(LORE_STYLE);
        }
        return null;
    }
}