package com.kingcontaria.foveffectscale.mixin;

import com.kingcontaria.foveffectscale.FovEffectScale;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.VideoOptionsScreen;
import net.minecraft.client.gui.widget.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VideoOptionsScreen.class)
public class VideoOptionsScreenMixin extends Screen {

    @Shadow private EntryListWidget list;

    @Inject(method = "init", at = @At("TAIL"))
    private void addFovEffectScale(CallbackInfo ci){
        SliderWidget fovEffectScale = new SliderWidget(new PagedEntryListWidget.Listener() {
            @Override
            public void setBooleanValue(int id, boolean value) {

            }

            @Override
            public void setFloatValue(int id, float value) {
                if(id == 28743856){
                    FovEffectScale.fovEffectScale = value/100;
                }
            }

            @Override
            public void setStringValue(int id, String text) {

            }
        }, 28743856, this.width/2-155, 200, "FOV Effects", 0, 100, FovEffectScale.fovEffectScale*100, new SliderWidget.LabelSupplier() {
            @Override
            public String getLabel(int i, String string, float sliderValue) {
                if(FovEffectScale.fovEffectScale == 0){
                    return "FOV Effects: OFF";
                }
                return "FOV Effects: "+(int)(FovEffectScale.fovEffectScale*100)+"%";
            }
        });
        OptionPairWidget.Pair newEntry = new OptionPairWidget.Pair(fovEffectScale,null);
        ((OptionPairWidgetAccessor)this.list).getEntries().add(newEntry);
    }

}
