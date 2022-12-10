package by.it.group151002.krashevskiy.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<T> implements List<T> {

    private static class Node<T> {
        Node<T> next;
        T item;

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node<T> first;
    private int size = 0;

    public ListA() {
    }

    @Override
    public int size() {
        return size;
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
    public boolean add(T e) {
        Node<T> newNode = new Node<>(e, null);
        if (first == null)
            first = newNode;
        else {
            Node<T> temp = first;
            while (temp.next != null)
                temp = temp.next;

            temp.next = newNode;
        }
        size++;
        return true;
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
    public T remove(int index) {
        T removed = null;
        if (index == 0) {
            removed = first.item;
            first = first.next;
        } else if (index < size) {
            Node<T> temp = first;
            int counter = 0;
            while (counter < index - 1) {
                temp = temp.next;
                counter++;
            }

            removed = temp.next.item;
            if (index == size - 1)
                temp.next = null;
            else
                temp.next = temp.next.next;
        }
        return removed;
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

    public T get(int index) {
        if (index < size) {
            Node<T> temp = first;
            while (index != 0) {
                temp = temp.next;
                index--;
            }
            return temp.item;
        }

        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (first != null) {
            Node<T> temp = first;
            sb.append(temp.item);
            while (temp.next != null) {
                temp = temp.next;
                sb.append(", ").append(temp.item);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
