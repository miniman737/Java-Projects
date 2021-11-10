public class Node<K extends Comparable<K>, V> {
    K    key;
    V    value;

    Node<K,V>    next;

    public Node() {
        this (null,null,null);
    }

    public Node(K key, V value) {
        this(key,value,null);
    }
    public Node(K key, V value, Node<K,V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
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

    public Node<K,V> getNext() {
        return this.next;
    }

    public void setNext(Node<K,V> next) {
        this.next = next;
    }

    public String toString() {
        String s = "\"" + key + ":" + value + "\"";
        return s;
    }
}
