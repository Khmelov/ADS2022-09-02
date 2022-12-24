package by.it.group151001.tarabarova.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 16;
    private Object[] array;
    int size = 0;


    public ListB() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean add(T t) {
        if (size == array.length) {
            Object[] array2 = new Object[array.length * 2];
            System.arraycopy(array, 0, array2, 0, array.length);
            array = array2;
        }
        array[size++] = t;
        return true;
    }

    @Override
    public T remove(int index) {
        T tmp = (T) array[index];
        if (array.length - 1 - index >= 0) System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
        array[size - 1] = null;
        size--;
        return tmp;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++)
            result.append(array[i]).append(", ");
        result.append(array[size - 1]).append("]");
        return result.toString();
    }

    @Override
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        T res = (T) array[index];
        array[index] = element;
        return res;
    }

    @Override
    public void add(int index, T element) {

        System.arraycopy(array, index,
                array, index + 1, size - index);
        array[index] = element;
        size++;

    }


    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] array2 = new Object[array.length + c.size()];
        System.arraycopy(array, 0, array2, 0, size);
        array = array2;
        for (Object elem : c)
            array[size++] = elem;
        return true;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < array.length; i++)
            if (o == array[i])
                return i;
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
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
    public void clear() { }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}

