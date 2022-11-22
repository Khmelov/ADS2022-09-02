package by.it.group151001.beryozkin.lesson09;

import java.util.*;
import java.util.function.UnaryOperator;

public class ListB <T> implements List<T> {
    int arraySize = 10;
    Object[] array = new Object[arraySize];
    int ind = 0;

    public String toString() {
        String result = "[";
        for (int i = 0; i < ind - 1; i++) {
            result += array[i] + ", ";
        }
        result += array[ind - 1] + "]";
        return result;
    }

    @Override
    public int size() {
        return ind;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for (int i=0; i<ind; i++){
            if (array[i] == o) return true;
        }
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
        if (ind == array.length) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, ind);
            array = newArray;
        }
        array[ind++] = t;
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
        Object[] newArray = new Object[array.length + c.size()];
        System.arraycopy(array, 0, newArray, 0, ind);
        array = newArray;

        for (Object elem:c){
            array[ind++] = elem;
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
    public void replaceAll(UnaryOperator<T> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super T> c) {
        List.super.sort(c);
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        T prev = (T) array[index];
        array[index] = element;
        return prev;
    }

    @Override
    public void add(int index, T element) {
      add(element);
      for (int i = ind - 1; i> index; i--){
          array[i]  = array[i-1];
      }
      array[index] = element;
    }

    @Override
    public T remove(int index) {
        T elem = (T) array[index];
        for (int i = index; i < ind - 1; i++) {
            array[i] = array[i + 1];
        }
        array[ind - 1] = null;
        ind--;
        return elem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i=0; i<ind; i++){
            if (array[i] == o) return i;
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

}
