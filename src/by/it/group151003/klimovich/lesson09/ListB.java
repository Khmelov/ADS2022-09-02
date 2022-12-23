package by.it.group151003.klimovich.lesson09;

import java.util.*;

public class ListB<T> implements List<T> {
    private T[] elems;

    private int size;

    public ListB() {
        size = 0;
        elems = (T[]) new Object[0];
    }

    @Override
    public int size() {
        return size;
    };

    @Override
    public boolean isEmpty() { return size==0 ? false: true; };

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
    public void add(int index, T element) {
        elems = Arrays.copyOf(elems, elems.length + 1);
        System.arraycopy(elems, index, elems, index+1, size-index);
        elems[index] = element;
        size++;
    }

    @Override
    public boolean add(T element) {
        elems = Arrays.copyOf(elems, elems.length + 1);
        elems[size] = element;
        size++;
        return true;
    }

    @Override
    public T get(int index) { return elems[index];}

    @Override
    public T remove(int index) {
        T retval = elems[index];
        System.arraycopy(elems, index + 1, elems, index, size - index - 1);
        size--;
        elems[size] = null;
        return retval;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        str.append(elems[0]);
        for(int i=1; i<size; i++){
            str.append(", ");
            str.append(elems[i]);
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public T set(int index, T element) {
        T retval = elems[index];
        elems[index] = element;
        return retval;
    }

    @Override
    public boolean remove(Object o) { return false; }

    @Override
    public boolean containsAll(Collection<?> c) { return false; }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] added_arr = c.toArray();
        int befSize = size;
        size += added_arr.length;
        System.arraycopy((T[]) added_arr, 0, elems, befSize, added_arr.length);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) { return false; }

    @Override
    public boolean removeAll(Collection<?> c) { return false; }

    @Override
    public boolean retainAll(Collection<?> c) { return false; }

    @Override
    public void clear() { }

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
