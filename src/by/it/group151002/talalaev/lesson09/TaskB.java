package by.it.group151002.talalaev.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TaskB<T> implements List<T> {
    private final int INITIAL_SIZE = 10;
    private Object[] arr = new Object[INITIAL_SIZE];
    private int curSize = 0;

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < curSize; i++) {
            str.append(arr[i]);
            str.append(", ");
        }
        str.delete(str.length() - 2, str.length());
        str.append("]");

        return str.toString();
    }

    @Override
    public boolean add(T t) {
        if (curSize == arr.length - 1) {
            Object[] newArray = new Object[arr.length * 2];
            System.arraycopy(arr, 0, newArray, 0, curSize);
            arr = newArray;
        }
        arr[curSize++] = t;
        return true;
    }

    @Override
    public T remove(int index) {
        T res = (T) arr[index];
        for (int i = index; i < curSize; i++)
            arr[i] = arr[i + 1];
        arr[curSize] = null;
        curSize--;
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
        Object[] newArray = new Object[curSize + 1];
        System.arraycopy(arr, 0, newArray, 0, curSize);
        arr = newArray;
        for (int i = curSize; i > index; i--)
            arr[i] = arr[i - 1];
        arr[index] = element;
        curSize++;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] newArray = new Object[curSize + c.size()];
        System.arraycopy(arr, 0, newArray, 0, curSize);
        arr = newArray;
        for (Object el : c)
            arr[curSize++] = el;
        return true;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < curSize; i++){
            if (arr[i] == o) return i;
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < curSize; i++){
            if (arr[i] == o) return true;
        }
        return false;
    }

    @Override
    public int size() {
        return curSize;
    }

    @Override
    public boolean isEmpty() {
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
