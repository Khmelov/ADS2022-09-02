package by.it.group151002.naftolsky.lesson09;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<T> implements List<T> {

    private class Node {
        T data;
        Node next;

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node headNode = new Node(null, null);
    private int length = 0;

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
    public boolean add(T t) {
        Node newNode = new Node(t, null);

        Node currentNode = headNode;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;

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
        Node currentNode = headNode;

        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }

        int i;
        for (T element : c) {
            Node newNode = new Node(element, null);
            currentNode.next = newNode;
            currentNode = currentNode.next;
        }

        return true;
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
    public T get(int index) {
        Node currentNode = headNode;

        int i = 0;
        while (currentNode.next != null && i < index) {
            currentNode = currentNode.next;
            i++;
        }
        if (i == index) {
            return currentNode.next.data;
        } else {
            return null;
        }
    }

    @Override
    public T set(int index, T element) {
        Node currentNode = headNode;

        int i = 0;
        while (currentNode.next != null && i < index) {
            currentNode = currentNode.next;
            i++;
        }
        if (currentNode.next != null) {
            T tValue = currentNode.next.data;
            currentNode.next.data = element;

            return tValue;
        } else {
            return null;
        }
    }

    @Override
    public void add(int index, T element) {
        Node currentNode = headNode;

        int i = 0;
        while (currentNode.next != null && i < index) {
            currentNode = currentNode.next;
            i++;

        }
        if (i == index) {
            Node tempNode = new Node(element, currentNode.next);
            currentNode.next = tempNode;
        }
    }

    @Override
    public T remove(int index) {
        Node currentNode = headNode;

        int i = 0;
        while (currentNode.next != null && i < index) {
            currentNode = currentNode.next;
            i++;
        }
        if (i == index) {
            Node deleteNode = currentNode.next;
            currentNode.next = deleteNode.next;

            return deleteNode.data;
        } else {
            return null;
        }
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

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        Node currentNode = headNode.next;
        while (currentNode.next != null) {
            str.append(currentNode.data).append(", ");
            currentNode = currentNode.next;
        }
        str.append(currentNode.data).append("]");

        return str.toString();
    }
}
