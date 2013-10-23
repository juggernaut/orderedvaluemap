package net.lokare;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author ameya
 *         <p/>
 *         A {@link Map} that returns its entries sorted by values.
 */
public class OrderedMap<K, V extends Integer> implements Map<K, V> {

    /**
     * The input map.
     */
    private final Map<K, V> inputMap;
    /**
     * Internal backing tree map.
     */
    private final TreeMap<K, V> treeMap;

    /**
     * Constructor
     *
     * @param map input map
     */
    public OrderedMap(final Map<K, V> map) {
        this.inputMap = map;
        final Comparator<K> comparator = new ValueComparator<K>();
        this.treeMap = new TreeMap<K, V>(comparator);
        this.treeMap.putAll(map);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public int size() {
        return this.treeMap.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return this.treeMap.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(final Object key) {
        return this.treeMap.containsKey(key);
    }

    /**
     * {@inheritDoc}
     * @param value
     * @return
     */
    @Override
    public boolean containsValue(final Object value) {
        return this.treeMap.containsValue(value);
    }

    /**
     * {@inheritDoc}
     * @param key
     * @return
     */
    @Override
    public V get(final Object key) {
        return this.treeMap.get(key);
    }

    /**
     * {@inheritDoc}
     * @param key
     * @param value
     * @return
     */
    @Override
    public V put(final K key, final V value) {
        return this.put(key, value);
    }

    /**
     * {@inheritDoc}
     * @param key
     * @return
     */
    @Override
    public V remove(final Object key) {
        return this.remove(key);
    }

    /**
     * {@inheritDoc}
     * @param m
     */
    @Override
    public void putAll(final Map<? extends K, ? extends V> m) {
        this.treeMap.putAll(m);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.treeMap.clear();
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public Set<K> keySet() {
        return this.treeMap.keySet();
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public Collection<V> values() {
        return this.treeMap.values();
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.treeMap.entrySet();
    }


    /**
     * A comparator class that will impose an ordering on the keys based on values.
     * @param <K>
     */
    public class ValueComparator<K> implements Comparator<K> {

        @Override
        public int compare(final K o1, final K o2) {
            final V v1 = (V) OrderedMap.this.inputMap.get(o1);
            final V v2 = (V) OrderedMap.this.inputMap.get(o2);
            if (v1.equals(v2)) {
                // Force recognition of duplicate values.
                return -1;
            }
            return v1.compareTo(v2);
        }
    }

    public static void main(final String[] args) {

        final Map<String, Integer> testMap = new HashMap<String, Integer>();
        testMap.put("hello", 2);
        testMap.put("world", 1);
        testMap.put("obama", 1);
        testMap.put("cricket", 1);
        testMap.put("prez", 3);

        OrderedMap<String, Integer> omap = new OrderedMap<String, Integer>(testMap);

        for (final Map.Entry<String, Integer> entry : omap.entrySet()) {
            System.out.println(entry.getKey());
        }

    }


}
