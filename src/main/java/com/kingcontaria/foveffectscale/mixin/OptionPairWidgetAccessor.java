package com.kingcontaria.foveffectscale.mixin;

import net.minecraft.client.gui.widget.OptionPairWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(OptionPairWidget.class)
public interface OptionPairWidgetAccessor {
    @Accessor
    List<OptionPairWidget.Pair> getEntries();

    @Mutable
    @Accessor
    void setEntries(List<OptionPairWidget.Pair> entries);
}
