package by.it.group151001.nesteruk.lesson09;

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
    public boolean add(T element){
        if(head == null){
            head = new Node(element, null);
            return true;
        }
        if (!contains(element)){
            Node temp = head;
            Node new_node = new Node(element,null);


            while (temp.next != null)
                temp = temp.next;
            temp.next = new_node;
            return true;
        } else return false;
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
        while (temp != null) {
            if (temp.data == o) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public int size(){
        if(head == null){
            return 0;
        }
        Node temp = head;
        int result = 0;
        while (temp.next != null){
            result++;
            temp = temp.next;
        }
        return result;
    }

    public boolean isEmpty(){
        if (head == null)
            return true;
        else
            return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        boolean result = true;
        for(T e : c) {
            result = add(e);
        }
        return result;
    }

    public boolean containsAll(Collection<?> c) {
        boolean result = true;
        for(Object e : c) {
            result = contains(e);
        }
        return result ;
    }

    public boolean removeAll(Collection<?> c) {
        boolean result = true;
        for(Object e : c) {
            result = remove(e);
        }
        return result;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('[');

        Node temp = head;
        while (temp != null){
            result.append(temp.data);
            if (temp.next != null)
                result.append(", ");
            temp = temp.next;
        }

        result.append(']');
        return result.toString();
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