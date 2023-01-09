package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;

    private Set<K> keys;
    // You should probably define some more!
    private int initialSize = 16;
    private double loadFactor = 0.75;

    private int size;

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(initialSize);
        keys = new HashSet<>();
        size = 0;
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        buckets = createTable(initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        this.loadFactor = maxLoad;
        buckets = createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        /*
        * When creating a new Collection<Node>[] to store in our buckets variable,
        * be aware that in Java, you cannot create an array of parameterized type.
        * Collection<Node> is a parameterized type, because we parameterize the
        * Collection class with the Node class. Therefore, the expression new
        * Collection<Node>[size] is illegal, for any given size. To get around this,
        * you should instead create a new Collection[size], where size is the desired size.
        * The elements of a Collection[] can be a collection of any type, like a
        * Collection<Integer> or a Collection<Node>. For our purposes, we will only add
        * elements of type Collection<Node> to our Collection[].
        */
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i].clear();
        }
        keys.clear();
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (keys.contains(key)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public V get(K key) {
        if (containsKey(key)) {
            int hashCode = Math.floorMod(key.hashCode(), initialSize);
            for (Node node : buckets[hashCode]) {
                if (node == null) {
                    continue;
                } else if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)){
            for (Collection<Node> bucket : buckets) {
                for (Node node : bucket) {
                    if (node == null) {
                        continue;
                    }
                    else if (node.key == key){
                        node.value = value;
                        return;
                    }
                }
            }
        }
        else {
            double loadFactor = (size() + 1) / initialSize;
            if (loadFactor > this.loadFactor) {
                initialSize *= 2;
                Collection<Node>[] newBuckets = createTable(initialSize);
                for (Collection<Node> bucket : buckets) {
                    for (Node node : bucket) {
                        if (node == null) {
                            continue;
                        }
                        int hashCode = Math.floorMod(node.key.hashCode(), initialSize);
                        newBuckets[hashCode].add(node);
                        buckets = newBuckets;
                    }
                }
            }
            int hashCode = Math.floorMod(key.hashCode(), initialSize);
            buckets[hashCode].add(createNode(key, value));
            keys.add(key);
            size += 1;
        }
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
//        return new Iterator<K>() {
//            @Override
//            public boolean hasNext();
//
//            @Override
//            public K next();
//        };
        return keys.iterator();
    }

}
