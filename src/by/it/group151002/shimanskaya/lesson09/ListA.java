package by.it.group151002.shimanskaya.lesson09;

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
    private int length = 0;

    @Override
    public boolean add(T t) {
        Node temp = head;
        Node newNode = new Node(t, null);
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        length++;
        return true;
    }

    @Override
    public T remove(int index) {
        if (index > length - 1) {
            return null;
        } else {
            int currentIndex = 0;
            Node temp = head.next;
            while (currentIndex < index - 1) {
                temp = temp.next;
                currentIndex++;
            }
            Node nodeForDeletion;
            if (index > 0) {
                nodeForDeletion = temp.next;
                temp.next = temp.next.next;
            } else {
                nodeForDeletion = temp;
                head.next = temp.next;
            }
            T data = nodeForDeletion.data;
            length--;
            return data;
        }
    }

    @Override
    public T get(int index) {
        if (index > length - 1) {
            return null;
        } else {
            int currentIndex = 0;
            Node temp = head.next;
            while (currentIndex != index) {
                temp = temp.next;
                currentIndex++;
            }
            return temp.data;
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        Node temp = head.next;
        while (temp.next != null) {
            str.append(temp.data).append(", ");
            temp = temp.next;
        }
        str.append(temp.data).append("]");
        return str.toString();
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

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
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

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

