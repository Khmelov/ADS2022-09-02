package by.it.group151001.nesteruk.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<T> implements List<T> {

    private class Node {
        T data;
        Node next;

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head = new Node(null, null);

    public boolean add(T data) {
        if(head.data == null) {
            head = new Node(data, null);
            return true;
        }
        Node temp = head;
        while(temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(data, null);
        return true;
    }

    private Node findByIndex(int index) {
        if (head.data == null) {
            return null;
        }
        Node temp = head;
        int i = 0;
        while(temp != null){
            if(i == index){
                return temp;
            }
            temp = temp.next;
            i++;
        }
        return null;
    }

    public T remove(int index) {
        if(index == 0) {
            Node temp = head;
            head = head.next;
            return temp.data;
        }
        Node prev = findByIndex(index - 1);
        if(prev != null && prev.next != null) {
            Node temp = prev.next;
            prev.next = prev.next.next;
            return temp.data;
        } else return null;
    }

    public T get(int index) {
        Node temp = findByIndex(index);
        if(temp != null) {
            return temp.data;
        } else return null;
    }
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");

        Node temp = head;
        while(temp != null) {
            result.append(temp.data);
            if(temp.next != null) {
                result.append(", ");
            }
            temp = temp.next;
        }
        result.append("]");
        return result.toString();
    }
    //.................................................................//
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
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
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) { return false; }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}