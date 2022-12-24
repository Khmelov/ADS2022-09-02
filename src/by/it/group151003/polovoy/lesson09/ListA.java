package by.it.group151003.polovoy.lesson09;

import java.util.*;

public class ListA<T> implements List<T> {

    private Object[] array;

    private static final int CAPACITY = 10;

    private int size;

    public ListA(){this.array = new Object[CAPACITY];}

    @Override
    public boolean add(T t){
        if (size == array.length){
            grow();
        }

        array[size++] = t;

        return true;
    }

    @Override
    public void add(int index, T element){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("Not correct index: " + index + ", Size: " + size);
        }

        if (size == array.length){
            grow();
        }

        System.arraycopy(array, index, array, index + 1, size - index);

        array[index] = element;

        size++;
    }

    @Override
    public T remove(int index){
        if (index >= size) {
            throw new IndexOutOfBoundsException("Not correct index: " + index + ", Size: " + size);
        }

        T oldValue = (T) array[index];

        fastRemove(index);

        return oldValue;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++) {
                if (array[index] == null) {
                    fastRemove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(array[index])) {
                    fastRemove(index);
                    return true;
                }
            }
        }

        return false;
    }

    private void fastRemove(int index) {
        int numberMoved = size - index - 1;

        if (numberMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numberMoved);
        }

        array[--size] = null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return (T) array[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        T oldValue = (T) array[index];

        array[index] = element;

        return oldValue;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] a = c.toArray();

        int numNew = a.length;

        if (numNew == 0) {
            return false;
        }

        if (numNew > array.length - size) {
            grow(size + numNew);
        }

        System.arraycopy(a, 0, array, size, numNew);

        size += numNew;

        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.stream(array).limit(size).toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(array[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    private void grow() {
        grow(size + 1);
    }

    private void grow(int minCapacity) {
        array = Arrays.copyOf(array, newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = array.length;

        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }

        return newCapacity;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
