package by.it.group151002.krashevskiy.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<T> implements List<T> {

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

    public ListB() {
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
    public boolean contains(Object e) {
        if (first == null)
            return false;
        Node<T> temp = first;
        while (temp.next != null) {
            if (e == temp.item)
                return true;
            temp = temp.next;
        }
        return temp.item == e;
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
        boolean isRemoved = false;
        if (first != null) {
            if (first.item == o) {
                first = first.next;
                size--;
                isRemoved = true;
            }
            Node<T> temp = first;
            while (temp.next != null && !isRemoved) {
                if (o == temp.next.item) {
                    temp.next = temp.next.next;
                    size--;
                    isRemoved = true;
                }
                temp = temp.next;
            }
        }
        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] elems = c.toArray();
        for (Object o : elems) {
            this.add((T) o);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Object[] elems = c.toArray();
        for (Object o : elems) {
            add(index, (T) o);
            index++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] elems = c.toArray();
        for (Object o : elems) {
            remove(o);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] elems = c.toArray();
        for (Object o : elems) {
            if (!contains(o))
                remove(o);
        }
        return true;
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
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
        T elem = null;
        if (index < size) {
            Node<T> temp = first;
            while (index != 0) {
                temp = temp.next;
                index--;
            }
            elem = temp.item;

            temp.item = element;
            return elem;
        }

        return elem;
    }

    @Override
    public void add(int index, T element) {
        if (index <= size) {
            Node<T> newNode;
            if (index == 0) {
                newNode = new Node<>(element, first);
                first = newNode;
            } else {
                Node<T> temp = first;
                while (index != 1) {
                    temp = temp.next;
                    index--;
                }
                newNode = new Node<>(element, temp.next);
                temp.next = newNode;
            }
            size++;
        }

    }

    @Override
    public T remove(int index) {
        T removed = null;
        if (index == 0) {
            removed = first.item;
            first = first.next;
        } else {
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
        size--;
        return removed;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node<T> temp = first;
        while (temp.next != null) {
            if (o == temp.item)
                return index;
            temp = temp.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        int lastIndex = -1;
        Node<T> temp = first;
        while (temp.next != null) {
            if (temp.item == o)
                lastIndex = index;
            temp = temp.next;
            index++;
        }
        return lastIndex;
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
