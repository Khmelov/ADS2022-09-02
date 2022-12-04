package by.it.group151001.milkovskaya.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {

    private class Node {
        Object data;
        Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head = new Node(null, null);

    @Override
    public boolean add(T e){
        if(head == null){
            head = new Node(e, null);
            return true;
        }
        if (!contains(e)){
            Node temp = head;
            Node new_node = new Node(e,null);


            while (temp.next != null)
                temp = temp.next;
            temp.next = new_node;
            return true;
        }
        else
            return false;
    }

    public boolean remove(Object o) {
        Node temp = head;
        while (temp.next != null && temp.next.data != o)
            temp = temp.next;
        if (temp.next == null)
            return false;

        temp.next = temp.next.next;
            return true;

    }

    public boolean contains(Object o) {
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data == o) {
                return true;
            }
            if (o != null && temp.next.data != null) {
                if (temp.next.data.equals(o)){
                    return true;
                }
            }
            temp = temp.next;
        }
        return false;
    }

    public int size(){
        int Res = 0;
        Node temp = head;
        if(temp == null){
            return 0;
        }
        while (temp.next != null){
            Res++;
            temp = temp.next;
        }
        return Res;
    }

    @Override
    public boolean isEmpty(){
        if (head == null)
            return true;
        else
            return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean Res = true;
        for(T e : c) {
            Res = add(e);
        }
        return Res;
    }

    public boolean containsAll(Collection<?> c) {
        boolean Res = true;
        for(Object e : c) {
            Res = contains(e);
        }
        return Res;
    }

    public boolean removeAll(Collection<?> c) {
        boolean Res = true;
        for(Object e : c) {
            Res = remove(e);
        }
        return Res;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');

        Node temp = head;
        while (temp != null){
            res.append(temp.data);
            if (temp.next != null)
                res.append(", ");
            temp = temp.next;
        }

        res.append(']');

        return res.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
    }

}
