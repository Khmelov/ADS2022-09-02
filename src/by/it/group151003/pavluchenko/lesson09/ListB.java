package by.it.group151003.pavluchenko.lesson09;

import java.util.*;

public class ListB<T> implements List<T> {
    public int defaultSize = 10;
    public Object[] array = new Object[defaultSize];
    public int currentSize = 0;

    @Override
    public boolean add(T e) {
        if (currentSize == array.length-1) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, currentSize);
            array = newArray;
        }
        array[currentSize++] = e;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] modifiedArray = new Object[currentSize + c.size()];
        System.arraycopy(array, 0, modifiedArray, 0, currentSize);
        array = modifiedArray;
        for (Object el : c) {
            array[currentSize++] = el;
        }
        return true;
    }

    @Override
    public T set(int index, T element) {
        T setElement = (T) array[index];
        array[index] = element;
        return setElement;
    }

    @Override
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public void add(int index, T element) {
        Object[] modifiedArray = new Object[currentSize + 1];
        System.arraycopy(array, 0, modifiedArray, 0, currentSize);
        array = modifiedArray;
        for (int i = currentSize; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
        ++currentSize;
    }

    @Override
    public T remove(int index) {
        T element = (T) array[index];
        for (int i = index; i < currentSize; i++) {
            array[i] = array[i + 1];
        }
        array[currentSize--] = null;
        return element;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("[");
        for (int i = 0; i < currentSize; i++) {
            string.append(array[i]);
            string.append(", ");
        }
        string.delete(string.length() - 2, string.length());
        string.append("]");

        return string.toString();
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

