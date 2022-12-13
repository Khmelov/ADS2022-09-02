package by.it.group151002.ryabov.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<T> implements List<T> {

    private class Node {
        Node next;
        T data;

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head = new Node(null, null);

    public String toString() {
        StringBuilder result = new StringBuilder();
        Node currNode = head.next;
        if (currNode == null) {
            result.append("List is empty");
        } else {
            result.append("[");
            while (currNode.next != null) {
                result.append(currNode.data + ", ");
                currNode = currNode.next;
            }
        }
        result.append(currNode.data + "]");
        return result.toString();
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
    public boolean add(T t) {
        Node newNode = new Node(t, null);
        if (head.next == null)
            head.next = newNode;
        else {
            Node prevNode = head.next;
            while (prevNode.next != null)
                prevNode = prevNode.next;
            prevNode.next = newNode;
        }
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
        Node currNode = head;
        while (currNode.next != null)
            currNode = currNode.next;
        for (T elem: c){
            currNode.next = new Node(elem, null);
            currNode = currNode.next;
        }
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
    public T get(int index) {
        int currIndex = 0;
        Node currNode = head.next;
        while (currIndex < index && currNode != null) {
            currIndex++;
            currNode = currNode.next;
        }
        if (currNode != null)
            return currNode.data;
        else
            return null;
    }

    @Override
    public T set(int index, T element) {
        int currIndex = 0;
        Node currNode = head.next;
        while (currIndex < index && currNode != null) {
            currIndex++;
            currNode = currNode.next;
        }
        if (currNode != null) {
            T result = currNode.data;
            currNode.data = element;
            return result;
        } else
            return null;
    }

    @Override
    public void add(int index, T element) {
        Node currNode = head.next;
        int currIndex = 0;
        Node prevNode = currNode;
        while (currIndex < index && currNode != null) {
            prevNode = currNode;
            currNode = currNode.next;
            currIndex++;
        }
        if (currNode == head.next)
            head.next = new Node(element, head.next);
        else {
            Node tmp = currNode;
            prevNode.next = new Node(element, tmp);
        }
    }

    @Override
    public T remove(int index) {
        int currIndex = 0;
        Node currNode = head.next;
        Node prev = currNode;
        while (currIndex < index && currNode != null) {
            prev = currNode;
            currNode = currNode.next;
            currIndex++;
        }
        if (currNode != null) {
            if (currNode == head.next) {
                head.next = currNode.next;
            } else {
                prev.next = prev.next.next;
            }
            return currNode.data;
        }
        return null;
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
