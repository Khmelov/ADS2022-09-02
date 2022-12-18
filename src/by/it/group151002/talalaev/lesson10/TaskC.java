package by.it.group151002.talalaev.lesson10;

import java.util.*;

public class TaskC<E>  implements NavigableSet<E> {

    private final int AMOUNT = 10;
    int size = 0;
    private Object[] array = new Object[AMOUNT];

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы
    public TaskC() {
    }

    private int compareTo(E first, E second) {
        return Integer.compare((int)first, (int)second);
    }

    private void sorting() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size - i - 1; j++)
                if (compareTo((E)array[j], (E)array[j+1]) > 0) {
                    E temp = (E)array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
    }

    @Override
    public boolean add(E e) {
        if (!contains(e)) {
            if (array.length == 0 || size == array.length - 1) {
                Object[] new_arr = new Object[array.length + AMOUNT / 2];
                System.arraycopy(array, 0, new_arr, 0, size);
                array = new_arr;
            }
            array[size++] = e;
            if (size > 1)
                sorting();
            return true;
        } else
            return false;
    }

    @Override
    public boolean remove(Object o) {
        int remove = -1;
        for (int i = 0; i < size; i++)
            if (array[i] == o) {
                remove = i;
                break;
            }
        if (remove == -1)
            return false;
        System.arraycopy(array, remove + 1, array, remove, size - remove);
        array[size] = null;
        size--;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < size; i++) {
            str.append(array[i]);
            str.append(", ");
        }
        if (str.length() >= 2)
            str.delete(str.length()-2, str.length());
        str.append("]");
        return str.toString();
    }

    @Override
    public boolean contains(Object o) {
        for (Object el : array)
            if (el == o )
                return true;
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = (Iterator<E>) Arrays.stream(array).iterator();
        return iterator;
    }

    @Override
    public void clear() {
        size = 0;
        array = new Object[size];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E first() {
        return (E)array[0];
    }

    @Override
    public E last() {
        return (E)array[size - 1];
    }

    @Override
    public E lower(E e) {
        for (int i = size - 1; i > -1; i--)
            if (compareTo((E)array[i], e) < 0)
                return (E)array[i];
        return null;
    }

    @Override
    public E floor(E e) {
        for (int i = size - 1; i > -1; i--)
            if (compareTo((E)array[i], e) <= 0)
                return (E)array[i];
        return null;
    }

    @Override
    public E ceiling(E e) {
        for (int i = 0; i < size; i++)
            if (compareTo((E)array[i], e) >= 0)
                return (E)array[i];
        return null;
    }

    @Override
    public E higher(E e) {
        for (Object el: array)
            if (compareTo((E)el, e) > 0)
                return (E)el;
        return null;
    }

    @Override
    public E pollFirst() {
        if(isEmpty())
            return null;
        E res = (E)array[0];
        System.arraycopy(array, 1, array, 0, size);
        size--;
        return res;
    }

    @Override
    public E pollLast() {
        if(isEmpty())
            return null;
        E res = (E)array[size -1];
        array[size - 1] = null;
        size--;
        return res;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

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
