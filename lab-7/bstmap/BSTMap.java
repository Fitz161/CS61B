package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node<K, V> root;
    private static class Node<K, V> {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node<K, V> left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }

        public int size(){
            return size;
        }
    }

    public BSTMap(){
    }

    public void clear() {
        root = null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public V get(K key) {
        try {
            return get(root, key);
        } catch (Exception e){
            return null;
        }
    }

    public V get(Node<K, V> tree, K key) {
        if (key == null)
            throw new IllegalArgumentException("calls get() with a null key");
        if (tree == null)
            throw new IllegalArgumentException(key + " key not in Map");
        int cmp = key.compareTo(tree.key);
        if (cmp < 0) {
            return get(tree.left, key);
        } else if (cmp > 0){
            return get(tree.right, key);
        } else {
            return tree.val;
        }
    }

    public int size() {

        return size(root);
    }

    public int size(Node<K, V> root){
        if (root == null)
            return 0;
        else
            return root.size;
    }


    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("calls get() with a null key");
        if (value == null);
            //remove(key);
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> root, K key, V val) {
        if (root == null)
            return new Node<K, V>(key, val, 1);
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = put(root.left, key, val);
        } else if (cmp > 0){
            root.right = put(root.right, key, val);
        }
        root.size = 1 + size(root.left) + size(root.right);
        return root;
    }

    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder(){
        printInOrder(root);
    }

    public void printInOrder(Node<K, V> root){
        if (root != null) {
            System.out.println(root.val + "-" + root.val);
            printInOrder(root.left);
            printInOrder(root.right);
        }
    }
}
