package by.it.group151004.prokopchuk.lesson10;

import java.util.*;

public class TaskB<E>  implements NavigableSet<E> {

    private int BASE_SIZE = 10;
    private Object[] array = new Object[BASE_SIZE];
    int currSize = 0;

    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet

    private int compareTo(E first, E second) {
        return Integer.compare((int)first, (int)second);
    }

    //Обязательные к реализации методы и конструкторы
    public TaskB() {
    }

    private void sorting() {
        Object[] arr = array;
        for (int i = 0; i < currSize; i++) {
            for (int j = 0; j < currSize - i - 1; j++) {
                if (compareTo((E)array[j], (E)array[j+1]) > 0) {
                    E temp=(E)array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    @Override
    public boolean add(E e) {
        if (!contains(e)) {
            if (array.length == 0 || currSize == array.length - 1) {
                Object[] new_arr = new Object[array.length + BASE_SIZE / 2];
                System.arraycopy(array, 0, new_arr, 0, currSize);
                array = new_arr;
            }
            array[currSize++] = e;
            if (currSize > 1) {
                sorting();
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        int remove = -1;
        for (int i = 0; i < currSize; i++) {
            if (array[i] == o) {
                remove = i;
                break;
            }
        }
        if (remove == -1)
            return false;
        for (int i = remove; i < currSize; i++) {
            array[i] = array[i+1];
        }
        array[currSize] = null;
        currSize--;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < currSize; i++) {
            str.append(array[i]);
            str.append(", ");
        }
        if (str.length() < 2) {
            str.append("]");
        } else {
            str.delete(str.length()-2, str.length());
            str.append("]");
        }
        return str.toString();
    }

    @Override
    public boolean contains(Object o) {
        for (Object el : array) {
            if (el == o ){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = (Iterator<E>) Arrays.stream(array).iterator();
        return iterator;
    }

    @Override
    public void clear() {
        currSize = 0;
        array = new Object[currSize];
    }

    @Override
    public boolean isEmpty() {
        return currSize == 0;
    }

    @Override
    public int size() {
        return currSize;
    }

    @Override
    public E first() {
        return (E)array[0];
    }

    @Override
    public E last() {
        return (E)array[currSize-1];
    }


    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public E lower(E e) {
        return null;
    }

    @Override
    public E floor(E e) {
        return null;
    }

    @Override
    public E ceiling(E e) {
        return null;
    }

    @Override
    public E higher(E e) {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }


    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super E> comparator() {
        return null;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return null;
    }


}
