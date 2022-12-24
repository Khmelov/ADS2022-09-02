package by.it.group151001.matsiushenko.lesson09;
import java.util.*;

public class ListA<E> implements List<E> {
    private static final int originSize = 25;
    private int actualSize;
    private int dimension;
    private Object[] elements;

    public ListA() {
        actualSize = 0;
        dimension = originSize;
        elements = new Object[dimension];
    }

    private void expand(){
        dimension  = dimension * 3 / 2;
        elements = Arrays.copyOf(elements, dimension);
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
        if (actualSize == dimension) {
            expand();
        }
        elements[actualSize++] = e;
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
        return (E)elements[index];
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        E tmp = (E)elements[index];
        actualSize--;
        System.arraycopy(elements, index + 1, elements, index, actualSize - index);
        return tmp;
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
        StringBuilder str = new StringBuilder();
        str.append("[");
        if (actualSize != 0) {
            for (int i = 0; i < actualSize - 1; i++) {
                str.append(elements[i]);
                str.append(", ");
            }
            str.append(elements[actualSize - 1]);
        }
        str.append("]");
        return str.toString();
    }
}