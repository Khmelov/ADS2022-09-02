package by.it.group151004.eremeichik.lesson10;

import java.util.List;

public class Node<T>{

    public T data;
    public boolean color;
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;

    public void addToList(List<T> list){
        if(left!=null && left.data!=null){
            left.addToList(list);
        }
        list.add(data);
        if(right!=null && right.data !=null){
            right.addToList(list);
        }
    }


    public Node(T data) {
        this.data = data;
    }
}
