package by.it.group151004.bashlikov.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<T> implements List<T> {
    public Object[] A = new Object[10];
    public int amount = 0;

    @Override
    public boolean add(T t) {
        if (amount == A.length - 2) {
            Object[] newA = new Object[A.length * 2];
            System.arraycopy(A, 0, newA, 0, amount);
            A = newA;
        }
        A[amount] = t;
        amount++;
        return true;
    }

    @Override
    public T remove(int index) {
        T res = (T) A[index];
        for (int i = index; i < amount; i++) {
            A[i] = A[i + 1];
        }
        A[amount] = null;
        amount--;
        return res;
    }

    @Override
    public T get(int index) {
        return (T) A[index];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < amount; i++) {
            s.append(A[i]);
            s.append(", ");
        }
        s.delete(s.length() - 2, s.length());
        s.append("]");
        return s.toString();
    }

    @Override
    public int size() {
        return 0;
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
}
