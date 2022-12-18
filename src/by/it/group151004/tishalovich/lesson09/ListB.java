package by.it.group151004.tishalovich.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {
    private static final int START_N_ELEMENTS = 10;
    private int curNElements;
    private int nElements;
    private Object[] elements;

    public ListB(){
        curNElements = 0;
        nElements = START_N_ELEMENTS;
        elements = new Object[nElements];
    }

    private void grow(){
        nElements = nElements * 3 / 2;
        elements = Arrays.copyOf(elements, nElements);
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
    public Iterator<E> iterator() {
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
    public boolean add(E e) {
        if(curNElements == nElements) grow();
        elements[curNElements++] = e;
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
    public boolean addAll(Collection<? extends E> c) {
        for (E element :
                c) {
            this.add(element);
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
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
    public E get(int index) {
        return (E) (elements[index]);
    }

    @Override
    public E set(int index, E element) {
        E result = (E)(elements[index]);
        elements[index] = element;
        return result;
    }

    @Override
    public void add(int index, E element) {
        if(curNElements == nElements) grow();
        System.arraycopy(elements, index, elements, index + 1, curNElements - index);
        elements[index] = element;
        curNElements++;
    }

    @Override
    public E remove(int index) {
        E result = (E)elements[index];
        curNElements--;
        System.arraycopy(elements, index + 1, elements, index, curNElements - index);
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
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if(curNElements != 0) {
            for (int i = 0; i < curNElements - 1; i++) {
                sb.append(elements[i]);
                sb.append(", ");
            }
            sb.append(elements[curNElements - 1]);
        }
        sb.append(']');
        return sb.toString();
    }
}
