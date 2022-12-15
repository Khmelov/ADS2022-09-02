package by.it.group151002.kragel.lesson09;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<T>  implements List<T>{

    T[] arr;
    int capacity;
    int start, end;
    @SuppressWarnings("unchecked")
    private T[] setCapacity(T[] arr, int capacity){
        T[] array = (T[]) new Object[capacity];
        int newStart = capacity / 3;
        int newEnd = newStart;
        if (arr != null){
            for (int i = start; i < end; i++) {
                array[newEnd++] = arr[i];
            }
        }
        this.start = newStart;
        this.end = newEnd;
        this.capacity = capacity;
        return array;
    }
    ListA() {
        arr = setCapacity(null, 32);
    }

    @Override
    public boolean add(T t) {
        if (end == capacity){
            arr = setCapacity(arr, capacity * 3);
        }
        arr[end++] = t;
        return true;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > end - start - 1)
            throw new IndexOutOfBoundsException("index: %d, when the last index: %d".formatted(index, end - start - 1));
        T res = arr[start + index];
        if (index > ((end - start) / 2)){
            end--;
            for (int i = start + index; i < end; i++) {
                arr[i] = arr[i + 1];
            }
        } else {
            for (int i = start + index; i > start; i--) {
                arr[i] = arr[i - 1];
            }
            start++;
        }
        return res;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > end - start - 1)
            throw new IndexOutOfBoundsException("index: %d, when the last index: %d".formatted(index, end - start - 1));
        return arr[start + index];
    }

    @Override
    public int size() {
        return end - start;
    }

    @Override
    public boolean isEmpty() {
        return end == start;
    }

    @Override
    public void clear() {
        arr = setCapacity(null, 32);
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
    public boolean remove(Object o) {
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
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

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
        String result = "[";
        if (end - start > 0) {
            result += arr[start].toString();
            for (int i = start + 1; i < end; i++) {
                result +=  ", " + arr[i].toString();
            }
        }
        return result + "]";
    }
}
