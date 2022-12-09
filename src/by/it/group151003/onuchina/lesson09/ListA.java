package by.it.group151003.onuchina.lesson09;

import java.util.*;

public class ListA<T> implements List<T> {
    private T[] elems;
    private int size;

    //Default constructor
    public ListA() {
        size = 0;
        elems = (T[]) new Object[0];
    }

    //Constructor with objects
    public ListA(Collection<? extends T> new_elems) {
        Object[] elems_copy = new_elems.toArray();
        size = elems_copy.length;
        elems = Arrays.copyOf((T[])elems_copy, elems_copy.length);
    }

    //Constructor with initial list size
    public ListA(int required_size) {
        size = required_size;
        elems = (T[]) new Object[required_size];
    }

    @Override
    public boolean add(T elem) {
        if (elem != null) {
            elems = Arrays.copyOf(elems, elems.length + 1);
            elems[size++] = elem;
            return true;
        }
        return false;
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
    public void add(int index, T element) {
        elems = Arrays.copyOf(elems, elems.length + 1);
        System.arraycopy(elems, index, elems, index + 1, size - index); // Copy (size - index - 1) elements from position (index + 1) from arr1 to position (index) from arr2
        elems[index] = element;
        size++;
    }

    @Override
    public T remove(int index) { //Returns value that used to be in that position
        T return_value = elems[index];
        System.arraycopy(elems, index + 1, elems, index, size - index-1); // Copy (size - index - 1) elements from position (index + 1) from arr1 to position (index) from arr2
        elems[--size] = null;
        return return_value;
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
    public T get(int index) {
        return elems[index];
    }

    @Override
    public T set(int index, T element) { //Returns value that used to be in that position
        T old_value = elems[index];
        elems[index] = element;
        return old_value;
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(", ", "[", "]"); //Output in format: [element1, element2, element3, ..., elementN]
        for (int i = 0; i < size; i++) {
            if (elems[i] != null) {
                output.add(elems[i].toString());
            } else {
                output.add(null);
            }
        }
        return output.toString();
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

}
