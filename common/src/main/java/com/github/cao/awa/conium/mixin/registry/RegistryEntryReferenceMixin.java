package com.github.cao.awa.conium.mixin.registry;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Mixin(RegistryEntry.Reference.class)
public class RegistryEntryReferenceMixin<T> {
//    @Inject(
//            method = "getTags",
//            at = @At("RETURN"),
//            cancellable = true
//    )
//    public void getTagsNotNull(CallbackInfoReturnable<Set<TagKey<T>>> cir) {
//        if (cir.getReturnValue() == null) {
//            cir.setReturnValue(Collections.emptySet());
//        }
//    }
}
