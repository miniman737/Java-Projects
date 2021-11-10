public class BSTNode<K extends Comparable<K>, V> {
    K    key;
    V    value;

    BSTNode<K,V>    left;
    BSTNode<K,V>    right;

    public BSTNode() {
        this (null,null);
    }
    public BSTNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public BSTNode<K,V> getLeft() {
        return this.left;
    }

    public void setLeft(BSTNode<K,V> left) {
        this.left = left;
    }

    public BSTNode<K,V> getRight() {
        return this.right;
    }

    public void setRight(BSTNode<K,V> right) {
        this.right = right;
    }

    public String toString() {
        String s = "\"" + key + ":" + value + "\"";
        return s;
    }
}
