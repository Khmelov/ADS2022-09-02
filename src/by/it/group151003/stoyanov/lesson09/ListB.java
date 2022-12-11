package by.it.group151003.stoyanov.lesson09;

import java.util.*;

public class ListB<T> implements List<T> {
    private Object elementData[];
    private final static int DEFAULT_CAPACITY = 10;
    private int capacity;
    private int size;

    ListB() {
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
        return indexOf(o) != -1;
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
        int newSize = size + this.size;
        if (newSize > capacity) {
            capacity <<= 1;
            elementData = Arrays.copyOf(elementData, capacity);
        }
        this.size = newSize;
    }

    private void grow() {
        grow(1);
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
        int index = size();
        grow(c.size());
        for (T e : c) {
            set(index++, e);
        }
        return true;
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
        Objects.checkIndex(index, size);
        Object oldValue = elementData[index];
        elementData[index] = element;
        return (T) oldValue;
    }

    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        grow();
        System.arraycopy(elementData, index, elementData, index + 1, size - index - 1);
        elementData[index] = element;
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
