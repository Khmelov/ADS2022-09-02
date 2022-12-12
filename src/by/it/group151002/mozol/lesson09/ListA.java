package by.it.group151002.mozol.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<T> implements List<T> {
    private final int DEFAULT_LENGTH = 10;
    private Object[] arr = new Object[DEFAULT_LENGTH];
    private int listSize = 0;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < listSize; i++) {
            str.append(arr[i]);
            str.append(", ");
        }
        str.delete(str.length() - 2, str.length());
        str.append("]");

        return str.toString();
    }

    @Override
    public boolean add(T t) {
        if (listSize == arr.length - 1) {
            Object[] newArray = new Object[arr.length * 2];
            System.arraycopy(arr, 0, newArray, 0, listSize);
            arr = newArray;
        }
        arr[listSize++] = t;
        return true;
    }

    @Override
    public T remove(int index) {
        T res = (T) arr[index];
        for (int i = index; i < listSize; i++) {
            arr[i] = arr[i + 1];
        }
        arr[listSize] = null;
        listSize--;
        return res;
    }

    @Override
    public T get(int index) {
        if(index >= listSize || index < 0)
            return null;
        return (T) arr[index];
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
}


