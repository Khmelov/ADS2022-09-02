package by.it.group151002.bybikov.lesson09;

import java.util.*;

public class List_A<T> implements List<T> {
    private Object[] list_Field;
    private static final int BEGIN_CAPACITY = 10;
    private int capacity;
    private int length;

    private void setCapacity(int capacity) {
        Object[] new_Field = new Object[capacity];
        for (int i = 0; i < this.capacity; i++) {
            new_Field[i] = this.list_Field[i];
        }
        this.capacity = capacity;
        this.list_Field = new_Field;
    }

    List_A () {
        this.setCapacity(BEGIN_CAPACITY);
        this.length = 0;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
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
    public boolean add(T t) {
        if (this.length == this.capacity) {
            setCapacity(this.capacity * 2);
        }
        this.list_Field[length++] = t;
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
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException("index: %d, when the list size: %d".formatted(index, length));
        }
        T result = (T)this.list_Field[index];
        return result;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("index: %d, when the list size: %d".formatted(index, length));
        }
        if (this.length == this.capacity){
            this.setCapacity(this.capacity * 2);
        }
        for (int i = this.length - 1; i >= index; i--){
            this.list_Field[i + 1] = this.list_Field[i];
        }
        this.list_Field[index] = element;
        length++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException("index: %d, when the list size: %d".formatted(index, length));
        }
        T result = (T)this.list_Field[index];
        this.length--;
        for (int i = index; i < this.length; i++) {
            this.list_Field[i] = this.list_Field[i + 1];
        }
        return result;
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
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('[');
        if (this.length > 0) {
            result.append(this.list_Field[0].toString());
            for (int i = 1; i < this.length; i++) {
                result.append(", ");
                result.append(this.list_Field[i].toString());
            }
        }
        result.append(']');
        return result.toString();
    }
}
