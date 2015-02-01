package me.wjwang.notificationlistener;

import java.util.Map;

/**
 * Created by david on 1/31/15.
 */
final public class NLEntry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    public NLEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V pre = this.value;
        this.value = value;
        return pre;
    }
}