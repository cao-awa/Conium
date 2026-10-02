package com.github.cao.awa.conium.mixin.registry;

import com.github.cao.awa.conium.registry.extend.ConiumDynamicRegistry;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Stream;

@Mixin(MappedRegistry.class)
@SuppressWarnings("unchecked")
public abstract class SimpleRegistryMixin<T> implements ConiumDynamicRegistry {
    @Unique
    private final Map<T, Holder.Reference<T>> dynamicIntrusiveValueToEntry = new IdentityHashMap<>();
    @Unique
    private final Map<Identifier, Holder.Reference<T>> dynamicIdToEntry = new HashMap<>();
    @Unique
    private final Map<ResourceKey<T>, Holder.Reference<T>> dynamicKeyToEntry = new HashMap<>();
    @Unique
    private final Reference2IntMap<T> dynamicEntryToRawId = ((Function<Reference2IntOpenHashMap<T>, Reference2IntOpenHashMap<T>>) map -> {
        map.defaultReturnValue(- 1);
        return map;
    }).apply(new Reference2IntOpenHashMap<>());

    @Unique
    private final Map<T, Holder.Reference<T>> dynamicValueToEntry = new IdentityHashMap<>();
    @Unique
    private final List<Holder.Reference<T>> dynamicRawIdToEntry = new ArrayList<>();
    @Unique
    private final Map<ResourceKey<T>, RegistrationInfo> dynamicKeyToEntryInfo = new IdentityHashMap<>();
    @Unique
    private final Map<TagKey<T>, HolderSet.Named<T>> dynamicTags = new java.util.concurrent.ConcurrentHashMap<>();
    @Shadow
    private boolean frozen;
    @Shadow
    @Final
    private Map<Identifier, Holder.Reference<T>> byLocation;
    @Shadow
    @Final
    private Map<ResourceKey<T>, Holder.Reference<T>> byKey;
    @Shadow
    @Final
    private Reference2IntMap<T> toId;
    @Shadow
    @Final
    private Map<T, Holder.Reference<T>> byValue;
    @Shadow
    @Final
    private ObjectList<Holder.Reference<T>> byId;
    @Shadow
    @Final
    private Map<ResourceKey<T>, RegistrationInfo> registrationInfos;
    @Shadow
    private Lifecycle registryLifecycle;

    @Shadow
    public abstract ResourceKey<? extends Registry<T>> key();

    @Shadow
    abstract HolderSet.Named<T> createTag(TagKey<T> tag);

    @Shadow
    public abstract Optional<HolderSet.Named<T>> get(TagKey<T> key);

    @Shadow
    abstract Holder.Reference<T> getOrCreateHolderOrThrow(ResourceKey<T> key);

    @Shadow
    public abstract void bindAllTagsToEmpty();

    @Shadow
    @Nullable
    public abstract T getValue(@Nullable Identifier id);

    @Shadow
    public abstract Optional<Holder.Reference<T>> get(Identifier id);

    @Shadow
    public abstract Holder<T> wrapAsHolder(T value);

    @Shadow
    public abstract Optional<ResourceKey<T>> getResourceKey(T entry);

    @Shadow
    protected abstract HolderSet.Named<T> getOrCreateTagForRegistration(TagKey<T> tagKey);

    @Inject(
            method = "register",
            at = @At("HEAD"),
            cancellable = true
    )
    @SuppressWarnings("unchecked")
    public void register(ResourceKey<T> key, T value, RegistrationInfo info, CallbackInfoReturnable<Holder.Reference<T>> cir) {
        if (this.frozen) {
            Objects.requireNonNull(key);
            Objects.requireNonNull(value);
            if (this.dynamicIdToEntry.containsKey(key.identifier())) {
                Util.pauseInIde(new IllegalStateException("Adding duplicate key '" + key + "' to registry"));
            }

            if (this.dynamicValueToEntry.containsKey(value)) {
                Util.pauseInIde(new IllegalStateException("Adding duplicate value '" + value + "' to registry"));
            }

            Holder.Reference<T> reference;

            reference = this.dynamicIntrusiveValueToEntry.remove(value);
            if (reference == null) {
                String var10002 = String.valueOf(key);
                throw new AssertionError("Missing intrusive holder for " + var10002 + ":" + value);
            }

            ((RegistryEntryReferenceAccessor<T>) reference).registryKey(key);

            this.dynamicKeyToEntry.put(key,
                                       reference
            );
            this.dynamicIdToEntry.put(key.identifier(),
                                      reference
            );
            this.dynamicValueToEntry.put(value,
                                         reference
            );
            int i = this.byId.size() + this.dynamicRawIdToEntry.size();
            this.dynamicRawIdToEntry.add(reference);
            this.dynamicEntryToRawId.put(value,
                                         i
            );
            this.dynamicKeyToEntryInfo.put(key,
                                           info
            );
            this.registryLifecycle = this.registryLifecycle.add(info.lifecycle());

            postChanged();

            cir.setReturnValue(reference);
        }
    }

    @Unique
    @SuppressWarnings("unchecked")
    private void postChanged() {
        this.dynamicValueToEntry.forEach((value, entry) -> ((RegistryEntryReferenceAccessor<T>) entry).value(value));
    }

    @Unique
    public Holder.Reference<T> orEntry(T value) {
        Holder.Reference<T> reference = this.byValue.get(value);
        if (reference == null) {
            reference = this.dynamicValueToEntry.get(value);
        }
        return reference;
    }

    @Redirect(
            method = "wrapAsHolder",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    @SuppressWarnings("unchecked")
    public Object getEntryByValue(Map<?, ?> instance, Object o) {
        return orEntry((T) o);
    }

    @Inject(
            method = "getId(Ljava/lang/Object;)I",
            at = @At("RETURN"),
            cancellable = true
    )
    public void getId(@Nullable T value, CallbackInfoReturnable<Integer> cir) {
        int rawId = cir.getReturnValue();
        if (rawId == - 1) {
            cir.setReturnValue(this.dynamicEntryToRawId.getInt(value));
        }
    }

    @Redirect(
            method = "getResourceKey",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    @SuppressWarnings("unchecked")
    public Object getKey(Map<?, ?> instance, Object o) {
        return orEntry((T) o);
    }

    @Unique
    public RegistrationInfo orEntryInfo(ResourceKey<T> value) {
        RegistrationInfo info = this.registrationInfos.get(value);
        if (info == null) {
            info = this.dynamicKeyToEntryInfo.get(value);
        }
        return info;
    }

    @Redirect(
            method = "registrationInfo",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    @SuppressWarnings("unchecked")
    public Object getEntryInfo(Map<?, ?> instance, Object o) {
        return orEntryInfo((ResourceKey<T>) o);
    }

    @Unique
    public Holder.Reference<T> orEntry(ResourceKey<T> value) {
        Holder.Reference<T> reference = this.byKey.get(value);
        if (reference == null) {
            reference = this.dynamicKeyToEntry.get(value);
        }
        return reference;
    }

    @Redirect(
            method = "getValue(Lnet/minecraft/resources/ResourceKey;)Ljava/lang/Object;", at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    @SuppressWarnings("unchecked")
    public Object getByKey(Map<?, ?> instance, Object o) {
        return orEntry((ResourceKey<T>) o);
    }

    @Inject(
            method = "byId(I)Ljava/lang/Object;",
            at = @At("RETURN"),
            cancellable = true
    )
    public void byId(int index, CallbackInfoReturnable<T> cir) {
        if (cir.getReturnValue() == null) {
            int edge = this.byId.size() + this.dynamicRawIdToEntry.size();
            if (index < 0 || index >= edge) {
                return;
            }
            int realIndex = index - this.byId.size();
            cir.setReturnValue(this.dynamicRawIdToEntry.get(realIndex)
                                                       .value());
        }
    }

    @Inject(
            method = "get(I)Ljava/util/Optional;",
            at = @At("RETURN"),
            cancellable = true
    )
    public void get(int index, CallbackInfoReturnable<Optional<Holder.Reference<T>>> cir) {
        Optional<Holder.Reference<T>> result = cir.getReturnValue();
        cir.setReturnValue(Optional.ofNullable(result.orElseGet(() -> {
            int edge = this.byId.size() + this.dynamicRawIdToEntry.size();
            if (index < 0 || index >= edge) {
                return null;
            }
            int realIndex = index - this.byId.size();
            return this.dynamicRawIdToEntry.get(realIndex);
        })));
    }

    @Inject(
            method = "get(Lnet/minecraft/resources/Identifier;)Ljava/util/Optional;",
            at = @At("RETURN"),
            cancellable = true
    )
    public void get(Identifier id, CallbackInfoReturnable<Optional<Holder.Reference<T>>> cir) {
        Optional<Holder.Reference<T>> result = cir.getReturnValue();
        cir.setReturnValue(Optional.ofNullable(result.orElseGet(() -> this.dynamicIdToEntry.get(id))));
    }

    @Inject(
            method = "iterator",
            at = @At("RETURN"),
            cancellable = true
    )
    public void iterator(CallbackInfoReturnable<Iterator<T>> cir) {
        cir.setReturnValue(Iterators.concat(
                cir.getReturnValue(),
                Iterators.transform(this.dynamicRawIdToEntry.iterator(),
                                    Holder :: value
                )
        ));
    }

    @Unique
    public Holder.Reference<T> orEntry(Identifier value) {
        Holder.Reference<T> reference = this.byLocation.get(value);
        if (reference == null) {
            reference = this.dynamicIdToEntry.get(value);
        }
        return reference;
    }

    @Redirect(
            method = "getValue(Lnet/minecraft/resources/Identifier;)Ljava/lang/Object;",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    public Object getByIdentifier(Map<?, ?> instance, Object o) {
        return orEntry((Identifier) o);
    }

    @Inject(
            method = "keySet",
            at = @At("RETURN"),
            cancellable = true
    )
    public void keySet(CallbackInfoReturnable<Set<Identifier>> cir) {
        Set<Identifier> result = new HashSet<>();
        result.addAll(cir.getReturnValue());
        result.addAll(this.dynamicIdToEntry.keySet());
        cir.setReturnValue(Collections.unmodifiableSet(result));
    }

    @Redirect(
            method = "registryKeySet",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Collections;unmodifiableSet(Ljava/util/Set;)Ljava/util/Set;"
            )
    )
    public Set<ResourceKey<T>> registryKeySet(Set<ResourceKey<T>> s) {
        Set<ResourceKey<T>> keys = new HashSet<>();
        keys.addAll(s);
        keys.addAll(this.dynamicKeyToEntry.keySet());
        return Collections.unmodifiableSet(keys);
    }

    @Redirect(
            method = "entrySet",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Collections;unmodifiableSet(Ljava/util/Set;)Ljava/util/Set;"
            )
    )
    public Set<Map.Entry<ResourceKey<T>, T>> getEntrySet(Set<Map.Entry<ResourceKey<T>, T>> s) {
        Set<Map.Entry<ResourceKey<T>, T>> keys = new HashSet<>();
        keys.addAll(s);
        keys.addAll(Maps.transformValues(this.dynamicKeyToEntry,
                                         Holder :: value
                        )
                        .entrySet());
        return Collections.unmodifiableSet(keys);
    }

    @Inject(
            method = "listElements",
            at = @At("RETURN"),
            cancellable = true
    )
    public void listElements(CallbackInfoReturnable<Stream<Holder.Reference<T>>> cir) {
        cir.setReturnValue(Stream.concat(
                cir.getReturnValue(),
                this.dynamicRawIdToEntry.stream()
        ));
    }

    @Inject(
            method = "listTags",
            at = @At("RETURN"),
            cancellable = true
    )
    public void listTags(CallbackInfoReturnable<Stream<HolderSet.Named<T>>> cir) {
        cir.setReturnValue(Stream.concat(
                cir.getReturnValue(),
                this.dynamicTags.values()
                                .stream()
        ));
    }

    @Inject(
            method = "isEmpty",
            at = @At("RETURN"),
            cancellable = true
    )
    public void isEmpty(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValue() && this.dynamicKeyToEntry.isEmpty());
    }

    @Inject(
            method = "containsKey(Lnet/minecraft/resources/Identifier;)Z",
            at = @At("RETURN"),
            cancellable = true
    )
    public void containsId(Identifier id, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValue() || this.dynamicIdToEntry.containsKey(id));
    }

    @Inject(
            method = "getRandom",
            at = @At("RETURN"),
            cancellable = true
    )
    public void getRandom(RandomSource random, CallbackInfoReturnable<Optional<Holder.Reference<T>>> cir) {
        Optional<Holder.Reference<T>> result = cir.getReturnValue();
        if (result.isEmpty()) {
            cir.setReturnValue(Util.getRandomSafe(this.dynamicRawIdToEntry,
                                                  random
            ));
        }
    }

    @Inject(
            method = "containsKey(Lnet/minecraft/resources/ResourceKey;)Z",
            at = @At("RETURN"),
            cancellable = true
    )
    public void contains(ResourceKey<T> key, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValue() || this.dynamicKeyToEntry.containsKey(key));
    }

    @Inject(
            method = "createIntrusiveHolder",
            at = @At("HEAD"),
            cancellable = true
    )
    public void createIntrusiveHolder(T value, CallbackInfoReturnable<Holder.Reference<T>> cir) {
        if (this.frozen) {
            cir.setReturnValue(this.dynamicIntrusiveValueToEntry.computeIfAbsent(value,
                                                                                 (valuex) -> Holder.Reference.createIntrusive(conium$getThis(),
                                                                                                                              valuex
                                                                                 )
            ));
        }
    }

    @Inject(
            method = "get(Lnet/minecraft/tags/TagKey;)Ljava/util/Optional;",
            at = @At("RETURN"),
            cancellable = true
    )
    private void getTag(TagKey<T> key, CallbackInfoReturnable<Optional<HolderSet.Named<T>>> cir) {
        if (cir.getReturnValue().isEmpty() && this.frozen) {
            HolderSet.Named<T> dynamicTag = this.dynamicTags.computeIfAbsent(key, k -> {
                HolderSet.Named<T> created = createTag(k);
                ((NamedRegistryEntryListMixin<T>) created).invokeSetEntries(List.of());
                return created;
            });
            cir.setReturnValue(Optional.of(dynamicTag));
        }
    }

    @Redirect(
            method = "refreshTagsInHolders",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;values()Ljava/util/Collection;"
            )
    )
    private Collection<Holder.Reference<T>> refreshTags(Map<ResourceKey<T>, Holder.Reference<T>> instance) {
        Set<Holder.Reference<T>> allRefs = new HashSet<>();
        allRefs.addAll(instance.values());
        allRefs.addAll(this.dynamicKeyToEntry.values());
        return Collections.unmodifiableCollection(allRefs);
    }

    @Inject(
            method = "bindAllTagsToEmpty",
            at = @At("HEAD"),
            cancellable = true
    )
    @SuppressWarnings("unchecked")
    public void resetTagEntries(CallbackInfo ci) {
        if (this.frozen) {
            for (HolderSet.Named<T> tag : this.dynamicTags.values()) {
                if (tag != null) {
                    ((NamedRegistryEntryListMixin<T>) tag).invokeSetEntries(List.of());
                }
            }
            ci.cancel();
        }
    }

    @Inject(
            method = "get(Lnet/minecraft/resources/ResourceKey;)Ljava/util/Optional;",
            at = @At("HEAD"),
            cancellable = true
    )
    public void getOptional(ResourceKey<T> key, CallbackInfoReturnable<Optional<Holder.Reference<T>>> cir) {
        cir.setReturnValue(conium$orRegistryOptional(key));
    }

    @Redirect(
            method = "createRegistrationLookup",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/core/MappedRegistry;validateWrite()V")
    )
    public void cancelFrozenCheckInCreateRegistrationLookup(MappedRegistry<T> instance) {
        // Do nothing.
    }

    @Inject(
            method = "createRegistrationLookup",
            at = @At("HEAD"),
            cancellable = true
    )
    public void createMutableOrDynamicRegistryLookup(CallbackInfoReturnable<HolderGetter<T>> cir) {
        cir.setReturnValue(new HolderGetter<>() {
            @Override
            public Optional<Holder.Reference<T>> get(ResourceKey<T> key) {
                return Optional.of(conium$getOrCreateDynamicEntry(key));
            }

            @Override
            public Optional<HolderSet.Named<T>> get(TagKey<T> tag) {
                return Optional.of(getOrCreateTagForRegistration(tag));
            }
        });
    }

    @Unique
    private Optional<Holder.Reference<T>> conium$orRegistryOptional(ResourceKey<T> key) {
        return Optional.ofNullable(Optional.ofNullable(this.byKey.get(key))
                                           .orElseGet(() -> this.dynamicKeyToEntry.get(key)));
    }

    @Unique
    private Holder.Reference<T> conium$getOrCreateDynamicEntry(ResourceKey<T> key) {
        Holder.Reference<T> reference;
        if (this.frozen) {
            reference = this.byKey.get(key);
            if (reference == null) {
                reference = this.dynamicKeyToEntry.get(key);
                if (reference == null) {
                    reference = this.dynamicKeyToEntry.computeIfAbsent(key,
                                                                       key2 -> Holder.Reference.createStandAlone(conium$getThis(),
                                                                                                                key2
                                                                       )
                    );

                    postChanged();
                }
            }
        } else {
            reference = getOrCreateHolderOrThrow(key);
        }
        return reference;
    }

    @Unique
    @SuppressWarnings("unchecked")
    private MappedRegistry<T> conium$getThis() {
        return (MappedRegistry<T>) (Object) this;
    }

    @Override
    public void conium$clearDynamic() {
        this.dynamicIntrusiveValueToEntry.clear();
        this.dynamicIdToEntry.clear();
        this.dynamicKeyToEntry.clear();
        this.dynamicEntryToRawId.clear();
        this.dynamicValueToEntry.clear();
        this.dynamicRawIdToEntry.clear();
        this.dynamicKeyToEntryInfo.clear();
        this.dynamicTags.clear();
    }

    @Override
    @Nullable
    public ResourceKey<?> conium$getKey(Identifier identifier) {
        AtomicReference<ResourceKey<?>> result = new AtomicReference<>();

        T val = getValue(identifier);
        if (val != null) {
            getResourceKey(val).ifPresent(result :: set);
        }

        return result.get();
    }

    @Override
    public boolean conium$isPresent(Identifier identifier) {
        return getValue(identifier) != null;
    }
}
