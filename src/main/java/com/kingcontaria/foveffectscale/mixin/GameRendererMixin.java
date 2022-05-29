package com.kingcontaria.foveffectscale.mixin;

import com.kingcontaria.foveffectscale.FovEffectScale;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Shadow private float movementFovMultiplier;

    @Inject(method = "updateMovementFovMultiplier", at = @At("TAIL"))
    private void applyFovEffectScale(CallbackInfo ci){
        this.movementFovMultiplier = 1- FovEffectScale.fovEffectScale + this.movementFovMultiplier* FovEffectScale.fovEffectScale;
    }

}
