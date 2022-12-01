package by.it.group151004.prokopchuk.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<T> implements List<T> {
    private final int START_SIZE = 10;
    private Object[] array = new Object[5];
    private int currSize = 0;

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < currSize; i++) {
            if (array[i] != null && array[i].equals(o)) {
                return i;
            } else if (array[i] == null && o == null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < currSize; i++) {
            if (array[i] != null && array[i].equals(o)) {
                return true;
            } else if (array[i] == null && o == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] new_arr = new Object[currSize + c.size()];
        System.arraycopy(array,0,new_arr,0,currSize);
        array = new_arr;
        for (Object el : c) {
            array[currSize++] = el;
        }
        return true;
    }

    @Override
    public void add(int index, T element) {
        Object[] new_arr = new Object[currSize + 1];
        System.arraycopy(array,0,new_arr,0,currSize);
        array = new_arr;
        for (int i = currSize; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = element;
        currSize++;
    }

    @Override
    public T set(int index, T element) {
        T res = (T)array[index];
        array[index] = element;
        return res;
    }

    @Override
    public String toString() {
        StringBuilder new_str = new StringBuilder();
        new_str.append("[");
        for (int i = 0; i < currSize; i++) {
            new_str.append(array[i]);
            new_str.append(", ");
        }
        new_str.delete(new_str.length()-2, new_str.length());
        new_str.append("]");
        return new_str.toString();
    }

    @Override
    public boolean add(T e) {
        if (currSize == array.length - 1) {
            Object[] new_arr = new Object[array.length + START_SIZE/2];
            System.arraycopy(array,0,new_arr,0,currSize);
            array = new_arr;
        }
        array[currSize++] = e;
        return true;
    }

    @Override
    public T remove(int index) {
        T res = (T) array[index];
        for (int i = index; i < currSize; i++) {
            array[i] = array[i + 1];
        }
        array[currSize] = null;
        currSize--;
        return res;
    }

    @Override
    public T get(int index) {
        if (index < currSize && index > -1) {
            return (T)array[index];
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return currSize;
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
