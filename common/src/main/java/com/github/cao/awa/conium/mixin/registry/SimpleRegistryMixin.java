package com.github.cao.awa.conium.mixin.registry;

import com.github.cao.awa.conium.registry.extend.ConiumDynamicRegistry;
import com.github.cao.awa.sinuatum.manipulate.Manipulate;
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryInfo;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.random.Random;
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
import java.util.function.Supplier;
import java.util.stream.Stream;

@Mixin(SimpleRegistry.class)
@SuppressWarnings("unchecked")
public abstract class SimpleRegistryMixin<T> implements ConiumDynamicRegistry {
    @Unique
    private final Map<T, RegistryEntry.Reference<T>> dynamicIntrusiveValueToEntry = new IdentityHashMap<>();
    @Unique
    private final Map<Identifier, RegistryEntry.Reference<T>> dynamicIdToEntry = CollectionFactor.hashMap();
    @Unique
    private final Map<RegistryKey<T>, RegistryEntry.Reference<T>> dynamicKeyToEntry = CollectionFactor.hashMap();
    @Unique
    private final Reference2IntMap<T> dynamicEntryToRawId = Manipulate.make(new Reference2IntOpenHashMap<>(), map -> {
        map.defaultReturnValue(-1);
    });
    @Unique
    private final Map<T, RegistryEntry.Reference<T>> dynamicValueToEntry = new IdentityHashMap<>();
    @Unique
    private final List<RegistryEntry.Reference<T>> dynamicRawIdToEntry = CollectionFactor.arrayList();
    @Unique
    private final Map<RegistryKey<T>, RegistryEntryInfo> dynamicKeyToEntryInfo = new IdentityHashMap<>();
    @Unique
    private final Map<TagKey<T>, RegistryEntryList.Named<T>> dynamicTags = new IdentityHashMap<>();
    @Shadow
    private boolean frozen;
    @Shadow
    @Final
    private Map<Identifier, RegistryEntry.Reference<T>> idToEntry;
    @Shadow
    @Final
    private Map<RegistryKey<T>, RegistryEntry.Reference<T>> keyToEntry;
    @Shadow
    @Final
    private Reference2IntMap<T> entryToRawId;
    @Shadow
    @Final
    private Map<T, RegistryEntry.Reference<T>> valueToEntry;
    @Shadow
    @Final
    private ObjectList<RegistryEntry.Reference<T>> rawIdToEntry;
    @Shadow
    @Final
    private Map<RegistryKey<T>, RegistryEntryInfo> keyToEntryInfo;
    @Shadow
    private Lifecycle lifecycle;

    @Shadow
    public abstract RegistryKey<? extends Registry<T>> getKey();

    @Shadow
    protected abstract RegistryEntryList.Named<T> createNamedEntryList(TagKey<T> tag);

    @Shadow
    abstract RegistryEntryList.Named<T> getTag(TagKey<T> key);

    @Shadow
    abstract RegistryEntry.Reference<T> getOrCreateEntry(RegistryKey<T> key);

    @Shadow
    public abstract void resetTagEntries();

    @Shadow @Nullable public abstract T get(@Nullable Identifier id);

    @Shadow public abstract Optional<RegistryEntry.Reference<T>> getEntry(Identifier id);

    @Shadow public abstract RegistryEntry<T> getEntry(T value);

    @Shadow public abstract Optional<RegistryKey<T>> getKey(T entry);

    @Inject(
            method = "add",
            at = @At("HEAD"),
            cancellable = true
    )
    @SuppressWarnings("unchecked")
    public void add(RegistryKey<T> key, T value, RegistryEntryInfo info, CallbackInfoReturnable<RegistryEntry.Reference<T>> cir) {
        if (this.frozen) {
            Objects.requireNonNull(key);
            Objects.requireNonNull(value);
            if (this.dynamicIdToEntry.containsKey(key.getValue())) {
                Util.getFatalOrPause(new IllegalStateException("Adding duplicate key '" + key + "' to registry"));
            }

            if (this.dynamicValueToEntry.containsKey(value)) {
                Util.getFatalOrPause(new IllegalStateException("Adding duplicate value '" + value + "' to registry"));
            }

            RegistryEntry.Reference<T> reference;
            reference = this.dynamicIntrusiveValueToEntry.remove(value);
            if (reference == null) {
                String var10002 = String.valueOf(key);
                throw new AssertionError("Missing intrusive holder for " + var10002 + ":" + value);
            }

            ((RegistryEntryReferenceAccessor<T>) reference).registryKey(key);

            this.dynamicKeyToEntry.put(key, reference);
            this.dynamicIdToEntry.put(key.getValue(), reference);
            this.dynamicValueToEntry.put(value, reference);
            int i = this.rawIdToEntry.size() + this.dynamicRawIdToEntry.size();
            this.dynamicRawIdToEntry.add(reference);
            this.dynamicEntryToRawId.put(value, i);
            this.dynamicKeyToEntryInfo.put(key, info);
            this.lifecycle = this.lifecycle.add(info.lifecycle());

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
    public RegistryEntry.Reference<T> orEntry(T value) {
        RegistryEntry.Reference<T> reference = this.valueToEntry.get(value);
        if (reference == null) {
            reference = this.dynamicValueToEntry.get(value);
        }
        return reference;
    }

    @Redirect(
            method = "getEntry(Ljava/lang/Object;)Lnet/minecraft/registry/entry/RegistryEntry;",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    @SuppressWarnings("unchecked")
    public Object getEntryByValue(Map<?, ?> instance, Object o) {
        return orEntry((T) o);
    }

    @Redirect(
            method = "getId",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    @SuppressWarnings("unchecked")
    public Object getId(Map<?, ?> instance, Object o) {
        return orEntry((T) o);
    }

    @Redirect(
            method = "getKey(Ljava/lang/Object;)Ljava/util/Optional;",
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
    public RegistryEntryInfo orEntryInfo(RegistryKey<T> value) {
        RegistryEntryInfo info = this.keyToEntryInfo.get(value);
        if (info == null) {
            info = this.dynamicKeyToEntryInfo.get(value);
        }
        return info;
    }

    @Redirect(
            method = "getEntryInfo",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    @SuppressWarnings("unchecked")
    public Object getEntryInfo(Map<?, ?> instance, Object o) {
        return orEntryInfo((RegistryKey<T>) o);
    }

    @Inject(
            method = "getRawId",
            at = @At("RETURN"),
            cancellable = true
    )
    public void getRawId(@Nullable T value, CallbackInfoReturnable<Integer> cir) {
        int rawId = cir.getReturnValue();
        if (rawId == -1) {
            cir.setReturnValue(this.dynamicEntryToRawId.getInt(value));
        }
    }

    @Unique
    public RegistryEntry.Reference<T> orEntry(RegistryKey<T> value) {
        RegistryEntry.Reference<T> reference = this.keyToEntry.get(value);
        if (reference == null) {
            reference = this.dynamicKeyToEntry.get(value);
        }
        return reference;
    }

    @Redirect(
            method = "get(Lnet/minecraft/registry/RegistryKey;)Ljava/lang/Object;",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    @SuppressWarnings("unchecked")
    public Object getByKey(Map<?, ?> instance, Object o) {
        return orEntry((RegistryKey<T>) o);
    }

    @Inject(
            method = "get(I)Ljava/lang/Object;",
            at = @At("RETURN"),
            cancellable = true
    )
    public void get(int index, CallbackInfoReturnable<T> cir) {
        if (cir.getReturnValue() == null) {
            int edge = this.rawIdToEntry.size() + this.dynamicRawIdToEntry.size();
            if (index < 0 || index >= edge) {
                return;
            }
            int realIndex = index - this.rawIdToEntry.size();
            cir.setReturnValue(this.dynamicRawIdToEntry.get(realIndex).value());
        }
    }

    @Inject(
            method = "getEntry(I)Ljava/util/Optional;",
            at = @At("RETURN"),
            cancellable = true
    )
    public void getEntry(int index, CallbackInfoReturnable<Optional<RegistryEntry.Reference<T>>> cir) {
        Optional<RegistryEntry.Reference<T>> result = cir.getReturnValue();
        cir.setReturnValue(Optional.ofNullable(result.orElseGet(() -> {
            int edge = this.rawIdToEntry.size() + this.dynamicRawIdToEntry.size();
            if (index < 0 || index >= edge) {
                return null;
            }
            int realIndex = index - this.rawIdToEntry.size();
            return this.dynamicRawIdToEntry.get(realIndex);
        })));
    }

    @Inject(
            method = "getEntry(Lnet/minecraft/util/Identifier;)Ljava/util/Optional;",
            at = @At("RETURN"),
            cancellable = true
    )
    public void getEntry(Identifier id, CallbackInfoReturnable<Optional<RegistryEntry.Reference<T>>> cir) {
        Optional<RegistryEntry.Reference<T>> result = cir.getReturnValue();
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
                Iterators.transform(this.dynamicRawIdToEntry.iterator(), RegistryEntry::value)
        ));
    }

    @Unique
    public RegistryEntry.Reference<T> orEntry(Identifier value) {
        RegistryEntry.Reference<T> reference = this.idToEntry.get(value);
        if (reference == null) {
            reference = this.dynamicIdToEntry.get(value);
        }
        return reference;
    }

    @Redirect(
            method = "get(Lnet/minecraft/util/Identifier;)Ljava/lang/Object;",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    public Object getByIdentifier(Map<?, ?> instance, Object o) {
        return orEntry((Identifier) o);
    }

    @Inject(
            method = "getIds",
            at = @At("RETURN"),
            cancellable = true
    )
    public void getIds(CallbackInfoReturnable<Set<Identifier>> cir) {
        Set<Identifier> result = CollectionFactor.hashSet();
        result.addAll(cir.getReturnValue());
        result.addAll(this.dynamicIdToEntry.keySet());
        cir.setReturnValue(Collections.unmodifiableSet(result));
    }

    @Redirect(
            method = "getKeys",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Collections;unmodifiableSet(Ljava/util/Set;)Ljava/util/Set;"
            )
    )
    public Set<RegistryKey<T>> getKeys(Set<RegistryKey<T>> s) {
        Set<RegistryKey<T>> keys = CollectionFactor.hashSet();
        keys.addAll(s);
        keys.addAll(this.dynamicKeyToEntry.keySet());
        return Collections.unmodifiableSet(keys);
    }

    @Redirect(
            method = "getEntrySet",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Collections;unmodifiableSet(Ljava/util/Set;)Ljava/util/Set;"
            )
    )
    public Set<Map.Entry<RegistryKey<T>, T>> getEntrySet(Set<Map.Entry<RegistryKey<T>, T>> s) {
        Set<Map.Entry<RegistryKey<T>, T>> keys = CollectionFactor.hashSet();
        keys.addAll(s);
        keys.addAll(Maps.transformValues(this.dynamicKeyToEntry, RegistryEntry::value).entrySet());
        return Collections.unmodifiableSet(keys);
    }

    @Inject(
            method = "streamEntries",
            at = @At("RETURN"),
            cancellable = true
    )
    public void streamEntries(CallbackInfoReturnable<Stream<RegistryEntry.Reference<T>>> cir) {
        cir.setReturnValue(Stream.concat(
                cir.getReturnValue(),
                this.dynamicRawIdToEntry.stream()
        ));
    }

    @Inject(
            method = "streamTags",
            at = @At("RETURN"),
            cancellable = true
    )
    public void streamTags(CallbackInfoReturnable<Stream<RegistryEntryList.Named<T>>> cir) {
        cir.setReturnValue(Stream.concat(
                cir.getReturnValue(),
                this.dynamicTags.values().stream()
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
            method = "containsId",
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
    public void getRandom(Random random, CallbackInfoReturnable<Optional<RegistryEntry.Reference<T>>> cir) {
        Optional<RegistryEntry.Reference<T>> result = cir.getReturnValue();
        if (result.isEmpty()) {
            cir.setReturnValue(Util.getRandomOrEmpty(this.dynamicRawIdToEntry, random));
        }
    }

    @Inject(
            method = "contains",
            at = @At("RETURN"),
            cancellable = true
    )
    public void contains(RegistryKey<T> key, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValue() || this.dynamicKeyToEntry.containsKey(key));
    }


    @Inject(
            method = "createEntry",
            at = @At("HEAD"),
            cancellable = true
    )
    public void createEntry(T value, CallbackInfoReturnable<RegistryEntry.Reference<T>> cir) {
        if (this.frozen) {
            cir.setReturnValue(this.dynamicIntrusiveValueToEntry.computeIfAbsent(value, (valuex) -> RegistryEntry.Reference.intrusive(getThis(), valuex)));
        }
    }

    @Unique
    public RegistryEntryList.Named<T> orTag(TagKey<T> value) {
        return getTag(value);
    }

    @Inject(
            method = "getTag",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getTag(TagKey<T> key, CallbackInfoReturnable<RegistryEntryList.Named<T>> cir) {
        if (this.frozen) {
            cir.setReturnValue(this.dynamicTags.computeIfAbsent(key, this::createNamedEntryList));
            resetTagEntries();
        }
    }

    @Redirect(
            method = "refreshTags",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;values()Ljava/util/Collection;"
            )
    )
    private Collection<RegistryEntry.Reference<T>> refreshTags(Map<RegistryKey<T>, RegistryEntry.Reference<T>> instance) {
        Set<RegistryEntry.Reference<T>> allRefs = CollectionFactor.hashSet();
        allRefs.addAll(instance.values());
        allRefs.addAll(this.dynamicKeyToEntry.values());
        return Collections.unmodifiableCollection(allRefs);
    }

    @Inject(
            method = "resetTagEntries",
            at = @At("HEAD"),
            cancellable = true
    )
    @SuppressWarnings("unchecked")
    public void resetTagEntries(CallbackInfo ci) {
        if (this.frozen) {
            this.dynamicTags.values().forEach(tag -> ((NamedRegistryEntryListMixin<T>) tag).invokeSetEntries(List.of()));
            ci.cancel();
        }
    }

    @Inject(
            method = "getOptional(Lnet/minecraft/registry/RegistryKey;)Ljava/util/Optional;",
            at = @At("HEAD"),
            cancellable = true
    )
    public void getOptional(RegistryKey<T> key, CallbackInfoReturnable<Optional<RegistryEntry.Reference<T>>> cir) {
        cir.setReturnValue(orRegistryOptional(key));
    }

    @Unique
    private Optional<RegistryEntry.Reference<T>> orRegistryOptional(RegistryKey<T> key) {
        return Optional.ofNullable(Optional.ofNullable(this.keyToEntry.get(key)).orElseGet(() -> this.dynamicKeyToEntry.get(key)));
    }

    @Unique
    private RegistryEntry.Reference<T> getOrCreateDynamicEntry(RegistryKey<T> key) {
        RegistryEntry.Reference<T> reference;
        if (this.frozen) {
            reference = this.keyToEntry.get(key);
            if (reference == null) {
                reference = this.dynamicKeyToEntry.get(key);
                if (reference == null) {
                    reference = this.dynamicKeyToEntry.computeIfAbsent(key, key2 -> RegistryEntry.Reference.standAlone(getThis(), key2));

                    postChanged();
                }
            }
        } else {
            reference = getOrCreateEntry(key);
        }
        return reference;
    }

    @Redirect(
            method = "createMutableRegistryLookup",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/SimpleRegistry;assertNotFrozen()V")
    )
    public void cancelFrozenCheckInCreateMutableRegistryLookup(SimpleRegistry<T> instance) {
        // Do nothing.
    }

    @Inject(
            method = "createMutableRegistryLookup",
            at = @At("HEAD"),
            cancellable = true
    )
    public void createMutableOrDynamicRegistryLookup(CallbackInfoReturnable<RegistryEntryLookup<T>> cir) {
        cir.setReturnValue(new RegistryEntryLookup<>() {
            @Override
            public Optional<RegistryEntry.Reference<T>> getOptional(RegistryKey<T> key) {
                return Optional.of(getOrThrow(key));
            }

            @Override
            public RegistryEntry.Reference<T> getOrThrow(RegistryKey<T> key) {
                return getOrCreateDynamicEntry(key);
            }

            @Override
            public Optional<RegistryEntryList.Named<T>> getOptional(TagKey<T> tag) {
                return Optional.of(getOrThrow(tag));
            }

            @Override
            public RegistryEntryList.Named<T> getOrThrow(TagKey<T> tag) {
                return orTag(tag);
            }
        });
    }

    @Unique
    @SuppressWarnings("unchecked")
    private SimpleRegistry<T> getThis() {
        return (SimpleRegistry<T>) (Object) this;
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
    public RegistryKey<?> conium$getKey(Identifier identifier) {
        AtomicReference<RegistryKey<?>> result = new AtomicReference<>();

        getKey(get(identifier)).ifPresent(result::set);

        return result.get();
    }

    @Override
    public boolean conium$isPresent(Identifier identifier) {
        return get(identifier) != null;
    }
}
