package by.it.group151001.kononchuk.lesson09;

public class Node <T> {
    public T data;
    public  Node<T> prev, next;


    public Node(T data, Node<T> prev, Node<T> next)
    {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public Node(T data)
    {
        this(data, null, null);
    }

    public Node()
    {
        this(null, null, null);
    }

    public String toString()
    {
        if (data == null){
            return "null";
        }
        else {
            return this.data.toString();
        }
    }
}
