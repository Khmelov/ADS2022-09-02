package by.it.group151004.belsky.lesson09;

import java.util.*;
import java.util.function.UnaryOperator;

public class ListA<T> implements List<T> {

    private final ListEl<T> rootEl;
    private int size;

    public ListA() {
        rootEl = new ListEl<>();
        size = 0;
    }

    private boolean indexOutOfBounds(int index) {
        return index >= size || index < 0;
    }

    public T get(int index) {
        if (indexOutOfBounds(index)) throw new IndexOutOfBoundsException("Index: "+index+", Size: "+this.size);
        ListEl<T> loopEl = rootEl;
        int i = 0;
        while (loopEl.next != null) {
            loopEl = loopEl.next;
            if (i == index) {
                return loopEl.data;
            }

            i+=1;
        }
        return null;
    }

    public boolean add(T element) {
        ListEl<T> loopEl = rootEl;
        while (loopEl.next != null) loopEl = loopEl.next;
        loopEl.next = new ListEl<>(element);
        this.size+=1;
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

    public void add(int index, T element) {
        if (index < 0 || index > this.size) throw new IndexOutOfBoundsException("Index: "+index+", Size: "+this.size);
        ListEl<T> loopEl = rootEl;

        if (loopEl.next == null) {
            loopEl.next = new ListEl<>(element);
            this.size+=1;
            return;
        } else if (index == 0) {
            rootEl.next = new ListEl<>(element, rootEl.next);
            this.size+=1;
            return;
        }

        int i = 0;
        while(loopEl.next != null) {
            loopEl = loopEl.next;
            if (i == index-1) {
                break;
            }
            i+=1;
        }
        ListEl<T> bufEl = loopEl.next;
        loopEl.next = new ListEl<>(element, bufEl);
        this.size+=1;
    }

    public boolean addAll(List<T> pList) {
        pList.forEach(this::add);
        return true;
    }

    public T set(int index, T element) throws IndexOutOfBoundsException {
        ListEl<T> loopEl = rootEl;
        int i = 0;
        while (loopEl.next != null) {
            loopEl = loopEl.next;
            if (i == index) {
                T bufEl = loopEl.data;
                loopEl.data = element;
                return bufEl;
            }
            i+=1;
        }
        throw new IndexOutOfBoundsException("Index: "+index+", Size: "+0);
    }

    public T remove(int index) {
        if (indexOutOfBounds(index)) throw new IndexOutOfBoundsException("Index: "+index+", Size: "+this.size);

        int i = 0;
        boolean selected = false;
        ListEl<T> loopEl = rootEl;
        while (loopEl.next != null) {
            if (index == i) {
                break;
            }
            loopEl = loopEl.next;
            i+=1;
        }

        if (rootEl.next != null) {
            size-=1;
            T bufT = loopEl.next.data;
            loopEl.next = loopEl.next.next;
            return bufT;
        }
        return null;
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
    public Spliterator<T> spliterator() {
        return List.super.spliterator();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        ListEl<T> loopEl = rootEl;
        str.append('[');
        while (loopEl.next != null) {
            loopEl = loopEl.next;
            str.append(loopEl.data.toString());
            if (loopEl.next != null) str.append(", ");
        }
        str.append(']');
        return str.toString();
    }

    public int size() {
        return this.size;
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
}
