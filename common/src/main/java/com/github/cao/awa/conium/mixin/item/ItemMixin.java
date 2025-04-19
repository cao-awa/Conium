package com.github.cao.awa.conium.mixin.item;

import com.github.cao.awa.conium.registry.extend.ConiumDynamicRegistry;
import com.github.cao.awa.conium.registry.extend.ConiumDynamicRegistryEntryDelegate;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin implements ConiumDynamicRegistryEntryDelegate<Item> {
    @Unique
    private RegistryEntry.Reference<Item> reference = null;

    @WrapOperation(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/registry/DefaultedRegistry;createEntry(Ljava/lang/Object;)Lnet/minecraft/registry/entry/RegistryEntry$Reference;"
            )
    )
    public <T> RegistryEntry.Reference<T> init(DefaultedRegistry<T> instance, Object item, Operation<RegistryEntry.Reference<T>> original) {
        ConiumDynamicRegistry registry = (ConiumDynamicRegistry) instance;

        if (!registry.isReplacing()) {
            return original.call(instance, item);
        }

        return null;
    }

    @Inject(
            method = "getRegistryEntry",
             at = @At("RETURN"),
            cancellable = true
    )
    public void conium$getRegistryEntry(CallbackInfoReturnable<RegistryEntry.Reference<Item>> cir) {
        if (getRegistryReference() != null) {
            cir.setReturnValue(getRegistryReference());
        }
    }

    @Override
    public RegistryEntry.Reference<Item> conium$getRegistryReference() {
        return this.reference;
    }

    @Override
    public void conium$setRegistryReference(RegistryEntry.Reference<Item> reference) {
        this.reference = reference;
    }
}
