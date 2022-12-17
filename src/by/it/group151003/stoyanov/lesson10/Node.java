package by.it.group151003.stoyanov.lesson10;

public class Node<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;
    private Color color;
    private Node<T> parent;

    public Node(T data, Color color, Node<T> parent) {
        this.data = data;
        this.color = color;
        this.parent = parent;
    }

    public Node(T data, Color color) {
        this(data, color, null);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }
}
