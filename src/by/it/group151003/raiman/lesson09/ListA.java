package by.it.group151003.raiman.lesson09;

import java.util.*;

public class ListA<T> implements List<T> {
    private Object[] elementData;
    private final static int DEFAULT_CAPACITY = 10;
    private int capacity;
    private int size;

    ListA() {
        capacity = DEFAULT_CAPACITY;
        elementData = new Object[capacity];
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
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++)
            if (elementData[i].equals(o))
                return true;
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

    private void grow(int size) {
        if (size > capacity) {
            capacity <<= 1;
            elementData = Arrays.copyOf(elementData, capacity);
        }
        this.size = size;
    }

    private void grow() {
        grow(size + 1);
    }

    @Override
    public boolean add(T t) {
        grow();
        elementData[size - 1] = t;
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
        Objects.checkIndex(index, size);
        return (T) elementData[index];
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
        Objects.checkIndex(index, size);
        Object oldValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, --size -  index);
        return (T) oldValue;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size(); i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size(); i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
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

    public String toString() {
        return Arrays.toString(Arrays.stream(elementData).limit(size).toArray());
    }
}