package by.it.group151004.golovchuk.lesson09;

import java.util.*;

public class ListB<T> implements List<T> {
    public int DEFAULT_CAPACITY = 10;
    public Object[] arr = new Object[DEFAULT_CAPACITY];
    public int currSize = 0;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < currSize; i++) {
            str.append(arr[i]);
            str.append(", ");
        }
        str.delete(str.length() - 2, str.length());
        str.append("]");

        return str.toString();
    }

    @Override
    public boolean add(T t) {
        if (currSize == arr.length - 1) {
            Object[] newArray = new Object[arr.length * 2];
            System.arraycopy(arr, 0, newArray, 0, currSize);
            arr = newArray;
        }
        arr[currSize++] = t;
        return true;
    }

    @Override
    public T remove(int index) {
        T res = (T) arr[index];
        for (int i = index; i < currSize; i++) {
            arr[i] = arr[i + 1];
        }
        arr[currSize] = null;
        currSize--;
        return res;
    }

    @Override
    public T get(int index) {
        return (T) arr[index];
    }

    @Override
    public T set(int index, T element) {
        T res = (T) arr[index];
        arr[index] = element;
        return res;
    }

    @Override
    public void add(int index, T element) {
        Object[] newArray = new Object[currSize + 1];
        System.arraycopy(arr, 0, newArray, 0, currSize);
        arr = newArray;
        for (int i = currSize; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = element;
        currSize++;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] newArray = new Object[currSize + c.size()];
        System.arraycopy(arr, 0, newArray, 0, currSize);
        arr = newArray;
        for (Object el : c) {
            arr[currSize++] = el;
        }

        return true;
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
        return null;
    }

    @Override
    public <T0> T0[] toArray(T0[] a) {
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
