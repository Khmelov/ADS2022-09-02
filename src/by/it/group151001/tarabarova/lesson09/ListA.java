package by.it.group151001.tarabarova.lesson09;

import java.util.*;
import java.util.function.UnaryOperator;


public class ListA<T> implements List<T> {
    private T[] m_elements;
    private int m_size;

    public ListA() {
        m_size = 0;
        m_elements = (T[]) new Object[0];
    }

    public ListA(Collection<? extends T> newElements) {
        Object[] elementsCopy = newElements.toArray();
        m_size = elementsCopy.length;
        m_elements = Arrays.copyOf((T[])elementsCopy, elementsCopy.length);
    }

    public ListA(int initialSize) {
        m_size = initialSize;
        m_elements = (T[]) new Object[initialSize];
    }

    @Override
    public boolean add(T element) {
        if (element != null) {
            m_elements = Arrays.copyOf(m_elements, m_elements.length + 1);
            m_elements[m_size++] = element;
            return true;
        }
        return false;
    }

    @Override
    public void add(int index, T element) {
        m_elements = Arrays.copyOf(m_elements, m_elements.length + 1);
        System.arraycopy(m_elements, index, m_elements, index + 1, m_size - index);
        m_elements[index] = element;
        m_size++;
    }

    @Override
    public T remove(int index) {
        T returnValue = m_elements[index];
        System.arraycopy(m_elements, index + 1, m_elements, index, m_size - index-1);
        m_elements[--m_size] = null;
        return returnValue;
    }

    @Override
    public T get(int index) {
        return m_elements[index];
    }

    @Override
    public T set(int index, T element) {
        T oldValue = m_elements[index];
        m_elements[index] = element;
        return oldValue;
    }

    @Override
    public String toString() {
        StringJoiner txt = new StringJoiner(", ", "[", "]"); // Вывод в формате: [element1, element2, element3, ..., elementN]

       for (int i = 0; i < m_size; i++) {
            if (m_elements[i] != null) {
                txt.add(m_elements[i].toString());
            } else {
                txt.add(null);
            }
        }
        return txt.toString();
    }

    @Override
    public int size() {
        return m_size;
    }

    @Override
    public boolean isEmpty() {
        return m_size == 0;
    }

    @Override
    public void clear() { m_size = 0; }

    @Override
    public void replaceAll(UnaryOperator<T> operator) { }

    @Override
    public void sort(Comparator<? super T> c) { }

    @Override
    public Spliterator<T> spliterator() {
        return null;
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