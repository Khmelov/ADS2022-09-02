package by.it.group151001.timoshek.lesson09;

import java.util.*;

public class ListA<E> implements List<E> {
    private static final int initSize = 20;
    private int cntElements;
    private int sizeElements;
    private Object[] objects;

    public ListA(){
        cntElements = 0;
        sizeElements = initSize;
        objects = new Object[sizeElements];
    }

    private void realloc(){
        sizeElements = sizeElements * 3 / 2;
        objects = Arrays.copyOf(objects, sizeElements);
    }

    @Override
    public int size() {
        return cntElements;
    }

    @Override
    public boolean isEmpty() {
        return cntElements == 0;
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
    public boolean add(E element) {
        if (sizeElements == cntElements) {
            realloc();
        }
        objects[cntElements++] = element;
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
        return (E)(objects[index]);
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
        E result = (E)objects[index];
        int len = cntElements - index + 1;
        cntElements--;
        System.arraycopy(objects, index + 1, objects, index, len);
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
        StringBuilder ans = new StringBuilder();
        ans.append("[");
        if(cntElements != 0) {
            for (int i = 0; i < cntElements - 1; i++) {
                ans.append(objects[i]);
                ans.append(", ");
            }
            ans.append(objects[cntElements - 1]);
        }
        ans.append("]");
        return ans.toString();
    }
}
