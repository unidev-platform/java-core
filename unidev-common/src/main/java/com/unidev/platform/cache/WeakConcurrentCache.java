package com.unidev.platform.cache;

import lombok.Getter;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Weak concurrent cache
 * @param <K>
 * @param <V>
 */
@Getter
public class WeakConcurrentCache<K, V> {
    private final ConcurrentHashMap<K, WeakReference<V>> cache = new ConcurrentHashMap<>();

    public void put(K key, V value) {
        cache.put(key, new WeakReference<>(value));
    }

    public V get(K key) {
        WeakReference<V> weakRef = cache.get(key);
        return weakRef == null ? null : weakRef.get();
    }
}
