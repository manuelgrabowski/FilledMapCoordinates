package net.manu_faktur.mapcoordinates.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.manu_faktur.mapcoordinates.util.CoordMethods;
import net.manu_faktur.mapcoordinates.util.ModConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EmptyMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EmptyMapItem.class)
public class EmptyMapItemMixin {

    // Hijack filled map creation
    @ModifyExpressionValue(method = "use", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/item/FilledMapItem;createMap(Lnet/minecraft/world/World;IIBZZ)Lnet/minecraft/item/ItemStack;"))
    private ItemStack addCoordsNBT(ItemStack original, World world, PlayerEntity player, Hand hand){
        // Only store coordinates in component data if mod is enabled
        if (ModConfig.getBooleanValue("enabled")) {
            CoordMethods.createCoordNBT(original, player);
        }

        return original;
    }

}
