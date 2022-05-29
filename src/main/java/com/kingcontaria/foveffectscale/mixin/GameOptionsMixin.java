package com.kingcontaria.foveffectscale.mixin;

import com.kingcontaria.foveffectscale.FovEffectScale;
import net.minecraft.client.options.GameOptions;
import org.lwjgl.Sys;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

@Mixin(GameOptions.class)
public class GameOptionsMixin {

    @Shadow
    @Final
    private File optionsFile;

    @Inject(method = "load", at = @At("TAIL"))
    private void loadFovEffectsScale(CallbackInfo ci){
        try {
            if (!this.optionsFile.exists()) {
                return;
            }

            try (Scanner scanner = new Scanner(optionsFile)) {
                String string;
                while ((string = scanner.nextLine()) != null) {
                    String[] string_split = string.split(":");
                    if(string_split[0].equals("fovEffectScale")){
                        FovEffectScale.fovEffectScale = Float.parseFloat(string_split[1]);
                    }
                }
            } catch (FileNotFoundException ignored) {
            }
        } catch (RuntimeException ignored) {
        }
    }

    @Inject(method = "save", at = @At("TAIL"))
    private void saveFovEffectsScale(CallbackInfo ci){
        try{
            Files.write(Paths.get("options.txt"),("fovEffectScale:"+ FovEffectScale.fovEffectScale + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
