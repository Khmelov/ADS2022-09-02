package by.it.group151002.saprin.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;

    private int capacity = 0;
    private Object[] elements;

    public ListA() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("[");
        for (int i = 0; i < this.size; ++i) {
            b.append(this.elements[i].toString());
            if (i != this.size - 1)
                b.append(", ");
        }
        b.append("]");
        return b.toString();
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
    public boolean add(T elem) {
        if (this.size + 1 == this.capacity) {
            this.capacity = (int) (this.capacity * 1.5);
            Object[] temp = new Object[this.capacity];
            System.arraycopy(this.elements, 0, temp, 0, this.size);
            this.elements = temp;
        }
        elements[this.size] = elem;
        this.size++;
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
    public T get(int index) {
        return (T) this.elements[index];
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        Object[] newArr = new Object[this.size - 1];
        Object returnElem = this.elements[index];
        if (index > 0) {
            System.arraycopy(this.elements, 0, newArr, 0, index);
        }
        System.arraycopy(this.elements, index + 1, newArr, index, this.size - index - 1);
        this.elements = newArr;
        this.size--;
        return (T) returnElem;
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
