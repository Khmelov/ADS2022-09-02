package by.it.group151002.talalaev.lesson10;

import java.util.*;

public class TaskA<E>  implements NavigableSet<E> {
    private final int INITIAL_AMOUNT = 10;
    int curSize = 0;
    private Object[] array = new Object[INITIAL_AMOUNT];

    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet
    //Обязательные к реализации методы и конструкторы
    public boolean isIn(Object o) {
        for (Object el : array)
            if (el == o)
                return true;
        return false;
    }


    public TaskA() {
    }

    private int compareTo(E first, E second) {
        return Integer.compare((int)first, (int)second);
    }

    private void sort() {
        for (int i = 0; i < curSize; i++)
            for (int j = 0; j < curSize - i - 1; j++)
                if (compareTo((E)array[j], (E)array[j+1]) > 0) {
                    E temp = (E) array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
    }

    @Override
    public boolean add(E e) {
        if (!isIn(e)) {
            if (curSize == array.length - 1) {
                Object[] new_arr = new Object[array.length + INITIAL_AMOUNT / 2];
                System.arraycopy(array, 0, new_arr, 0, curSize);
                array = new_arr;
            }
            array[curSize++] = e;
            if (curSize > 1) {
                sort();
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        int remove = -1;
        for (int i = 0; i < curSize; i++)
            if (array[i] == o) {
                remove = i;
                break;
            }
        if (remove == -1)
            return false;
        System.arraycopy(array, remove + 1, array, remove, curSize - remove);
        array[curSize] = null;
        curSize--;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < curSize; i++) {
            str.append(array[i]);
            str.append(", ");
        }
        if (str.length() >= 2)
            str.delete(str.length()-2, str.length());
        str.append("]");
        return str.toString();
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

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

    @Override
    public E first() {
        return null;
    }

    @Override
    public E last() {
        return null;
    }
}
