package by.it.group151002.zavaliuk.lesson09;

import java.util.*;
import java.util.function.UnaryOperator;

public class ListB<T> implements List<T> {
    private final Node<T> root;
    private int size;

    public ListB() {
        root = new Node<>();
        size = 0;
    }

    private boolean indexOutOfBounds(int index) {
        return index >= size || index < 0;
    }

    public T get(int index) {
        if (indexOutOfBounds(index)) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        Node<T> node = root;
        for (int i = 0; node.next != null; i++) {
            node = node.next;
            if (i == index) {
                return node.data;
            }
        }
        return null;
    }

    public boolean add(T element) {
        Node<T> node = root;
        while (node.next != null) node = node.next;
        node.next = new Node<>(element);
        this.size += 1;
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
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach(this::add);
        return true;
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
    public void replaceAll(UnaryOperator<T> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super T> c) {
        List.super.sort(c);
    }

    @Override
    public void clear() {

    }

    public void add(int index, T element) {
        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        Node<T> node = root;

        if (node.next == null) {
            node.next = new Node<>(element);
            this.size += 1;
            return;
        } else if (index == 0) {
            root.next = new Node<>(element, root.next);
            this.size += 1;
            return;
        }

        int i = 0;
        while (node.next != null) {
            node = node.next;
            if (i == index - 1) {
                break;
            }
            i += 1;
        }
        Node<T> bufEl = node.next;
        node.next = new Node<>(element, bufEl);
        this.size += 1;
    }

    public T set(int index, T element) throws IndexOutOfBoundsException {
        Node<T> node = root;
        int i = 0;
        while (node.next != null) {
            node = node.next;
            if (i == index) {
                T bufEl = node.data;
                node.data = element;
                return bufEl;
            }
            i += 1;
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + 0);
    }

    public T remove(int index) {
        if (indexOutOfBounds(index)) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);

        int i = 0;
        Node<T> node = root;
        while (node.next != null) {
            if (index == i) {
                break;
            }
            node = node.next;
            i += 1;
        }

        if (root.next != null) {
            size -= 1;
            T bufT = node.next.data;
            node.next = node.next.next;
            return bufT;
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

    @Override
    public Spliterator<T> spliterator() {
        return List.super.spliterator();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node<T> node = root;
        str.append('[');
        while (node.next != null) {
            node = node.next;
            str.append(node.data == null ? "null" : node.data.toString());
            if (node.next != null) str.append(", ");
        }
        str.append(']');
        return str.toString();
    }

    public int size() {
        return this.size;
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
}
