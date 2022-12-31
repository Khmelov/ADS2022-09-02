package by.it.group151004.belsky.lesson10;

class Node<K extends Comparable<K>, V> implements Comparable<Node<K, V>> {
    private Node<K, V> left = null, right = null;
    private V value = null;
    private K key = null;

    public Node (Node<K, V> left, Node<K, V> right, K key, V value) {
        this.left = left;
        this.right = right;
        this.value = value;
        this.key = key;
    }

    public Node (K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node () {

    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public int compareTo(Node<K, V> o) {
        return key.compareTo(o.key);
    }
}
