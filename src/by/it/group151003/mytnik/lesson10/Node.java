package by.it.group151003.mytnik.lesson10;

public class Node<T> {
    public T data;
    public Node<T> left;
    public Node<T> right;
    public Color color;
    public Node<T> parent;

    public Node(T data, Color color, Node<T> parent) {
        this.data = data;
        this.color = color;
        this.parent = parent;
    }

    public Node(T data, Color color) {
        this(data, color, null);
    }
}