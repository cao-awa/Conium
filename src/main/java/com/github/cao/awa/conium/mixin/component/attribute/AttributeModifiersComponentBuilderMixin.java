package com.github.cao.awa.conium.mixin.component.attribute;

import com.github.cao.awa.sinuatum.util.collection.CollectionFactor;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AttributeModifiersComponent.Builder.class)
public class AttributeModifiersComponentBuilderMixin {
    @Unique
    private final List<AttributeModifiersComponent.Entry> entryList = CollectionFactor.arrayList();

    @Inject(
            method = "add",
            at = @At("HEAD"),
            cancellable = true
    )
    public void add(RegistryEntry<EntityAttribute> attribute, EntityAttributeModifier modifier, AttributeModifierSlot slot, CallbackInfoReturnable<AttributeModifiersComponent.Builder> cir) {
        this.entryList.add(new AttributeModifiersComponent.Entry(attribute, modifier, slot));
        cir.setReturnValue(getThis());
    }

    @Inject(
            method = "build",
            at = @At("HEAD"),
            cancellable = true
    )
    public void build(CallbackInfoReturnable<AttributeModifiersComponent> cir) {
        cir.setReturnValue(new AttributeModifiersComponent(this.entryList, true));
    }

    @Unique
    private AttributeModifiersComponent.Builder getThis() {
        return (AttributeModifiersComponent.Builder) (Object) this;
    }
}
