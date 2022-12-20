package by.it.group151004.ivanenko.lesson09;

import java.util.*;

public class ListA<T> implements List<T> {
    private final static int SIZE = 10;
    private int capacity;
    private Object[] arr = new Object[SIZE];
    private int len;

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

    //Implementation
    private void grow(int size) {
        if (size > capacity) {
            capacity = size + size >> 1;
            arr = Arrays.copyOf(arr, capacity);
        }
        this.len = size;
    }

    @Override
    public boolean add(T e) {
        grow(len + 1);
        arr[len - 1] = e;
        return true;
    }

    @Override
    public T remove(int index) {
        T res = (T) arr[index];
        System.arraycopy(arr, index + 1, arr, index, --len - index);
        return res;
    }

    @Override
    public T get(int index) {
        return (T) arr[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < len - 1; i++) {
            sb.append(arr[i]);
            sb.append(", ");
        }
        sb.append(arr[len - 1]);
        sb.append("]");
        return sb.toString();
    }
}

