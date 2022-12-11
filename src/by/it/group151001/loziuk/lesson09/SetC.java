package by.it.group151001.loziuk.lesson09;

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
        if (!contains(e)){
            Node Cur = head;
            Node new_node = new Node(e,null);


            while (Cur.next != null)
                Cur = Cur.next;
            Cur.next = new_node;
            return true;
        }
        else
            return false;
    }

    public boolean remove(Object o) {
        Node Cur = head;
        while (Cur.next != null && Cur.next.data != o)
            Cur = Cur.next;
        if (Cur.next == null)
            return false;
        else{
            Cur.next = Cur.next.next;
            return true;
        }
    }

    public boolean contains(Object o) {
        Node Cur = head;
        while (Cur.next != null) {
            if (Cur.next.data == o) {
                return true;
            }
            if (o != null && Cur.next.data != null) {
                if (Cur.next.data.equals(o)){
                    return true;
                }
            }
            Cur = Cur.next;
        }
        return false;
    }

    public int size(){
        int Res = 0;
        Node Cur = head;
        while (Cur.next != null){
            Res++;
            Cur = Cur.next;
        }
        return Res;
    }

    @Override
    public boolean isEmpty(){
        if (head.next == null)
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

        Node temp = head.next;
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
        head.next = null;
    }

}