package by.it.group151003.denisova.lesson09;

import java.util.*;

public class SetC<T> implements Set<T> {

    private static final int DEFAULT_CAPACITY = 16;
    private Object[] array;
    int size=0;

    public SetC(){
        size=0;
        this.array=new Object[DEFAULT_CAPACITY];
    }

    @Override
    public String toString() {
        if (size < 1) return "[]";
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            result.append(array[i]).append(", ");
        }
        result.append(array[size - 1]).append("]");
        return result.toString();
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object element : array)
            if (element == o) return true;
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
    public boolean add(T t) {
        if (contains(t)) return false;
        if (size == array.length) {
            Object[] array2 = new Object[array.length * 2];
            System.arraycopy(array, 0, array2, 0, array.length);
            array = array2;
        }
        array[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) return false;
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] == o)
                j = i;
        }
        if (j == 0) return false;
        if (size - 1 - j >= 0) System.arraycopy(array, j + 1, array, j, size - 1 - j);
        array[size--] = null;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c)
            if (!contains(element))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean temp = false;
        for (T element : c)
            if (add(element))
                temp = true;
        return temp;
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean temp = false;
        for (Object element : c)
            if (remove(element))
                temp = true;
        return temp;
    }

    @Override
    public void clear() {
        array = new Object[0];
        size = 0;

    }
}
