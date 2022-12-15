package by.it.group151002.krashevskiy.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {

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

    public SetC() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object o) {
        if (first == null)
            return false;
        Node<T> temp = first;
        while (temp.next != null) {
            if (temp.item == null || o == null) {
                if (temp.item == o)
                    return true;
            }  else if (temp.item.equals(o))
                return true;
            temp = temp.next;
        }
        if (temp.item == null || o == null) {
            return temp.item == o;
        } else return temp.item.equals(o);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        Node<T> temp = first;
        int index = 0;
        while (temp.next != null) {
            objects[index] = temp.item;
            temp = temp.next;
            index++;
        }
        objects[index] = temp.item;
        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (!contains(t)) {
            Node<T> newNode = new Node<>(t, null);
            if (first != null) {
                Node<T> temp = first;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newNode;
            } else
                first = newNode;
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        boolean isRemoved = false;
        if (first == null)
            return false;
        Node<T> temp = first;
        if (temp.item == null || o == null) {
            if (temp.next.item == o) {
                first = first.next;
                isRemoved = true;
                size--;
            }
        } else if (temp.item.equals(o)) {
            first = temp.next;
            isRemoved = true;
            size--;
        }
        while (temp.next != null && !isRemoved) {
            if (temp.next.item == null || o == null) {
                if (temp.next.item == o) {
                    temp.next = temp.next.next;
                    size--;
                    return true;
                }
            } else if (temp.next.item.equals(o)) {
                temp.next = temp.next.next;
                size--;
                return true;
            }
            temp = temp.next;
        }
        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] objects = c.toArray();
        for (Object o : objects) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] elems = c.toArray();
        for (Object o : elems) {
            add((T) o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] objects = c.toArray();
        for (Object o : objects) {
            remove(o);
        }
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }
}
