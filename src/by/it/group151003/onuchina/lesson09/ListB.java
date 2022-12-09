package by.it.group151003.onuchina.lesson09;

import java.util.*;
import java.util.function.UnaryOperator;

public class ListB <T> implements List<T> {
    private T[] elems = (T[]) new Object[0];
    private int size;

    //Default constructor
    public ListB() {
        size = 0;
        elems = (T[]) new Object[0];
    }

    //Constructor with objects
    public ListB(Collection<? extends T> new_elems) {
        Object[] elems_copy = new_elems.toArray();
        size = elems_copy.length;
        elems = Arrays.copyOf((T[])elems_copy, elems_copy.length);
    }

    //Constructor with initial list size
    public ListB(int required_size) {
        size = required_size;
        elems = (T[]) new Object[required_size];
    }

    @Override
    public boolean add(T elem) {
        if (elem != null) {
            elems = Arrays.copyOf(elems, elems.length + 1);
            elems[size++] = elem;
            return true;
        }
        return false;
    }

    @Override
    public void add(int index, T elem) {
        elems = Arrays.copyOf(elems, elems.length + 1);
        System.arraycopy(elems, index, elems, index + 1, size - index);
        elems[index] = elem;
        size++;
    }

    @Override
    public T remove(int index) { //Returns value that used to be in that position
        T returnValue = elems[index];
        System.arraycopy(elems, index + 1, elems, index, size - index - 1); // Copy (m_size - index - 1) elements from position (index + 1) from arr1 to position (index) from arr2
        elems[--size] = null;
        return returnValue;
    }

    @Override
    public T get(int index) {
        return elems[index];
    }

    @Override
    public T set(int index, T elem) { //Returns value that used to be in that position
        T old_value = elems[index];
        elems[index] = elem;
        return old_value;
    }

    @Override
    public boolean addAll(Collection<? extends T> elements) {
        Object[] elems_copy = elements.toArray();
        int oldSize = this.size;
        size = size + elems_copy.length;
        Object[] result_copy = Arrays.copyOf(elems, size);
        System.arraycopy(elems_copy, 0, result_copy, oldSize, elems_copy.length);
        elems = Arrays.copyOf((T[]) result_copy, result_copy.length);
        return true;
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            if (elems[i] != null) {
                output.add(elems[i].toString());
            } else {
                output.add(null);
            }
        }
        return output.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int cur = -1;

            @Override
            public boolean hasNext() {
                return cur + 1 < size;
            }

            @Override
            public T next() {
                return elems[++cur];
            }

            @Override
            public void remove() {
                by.it.group151003.onuchina.lesson09.ListB.this.remove(elems[cur]);
            }

        };
    }

    @Override
    public boolean contains(Object object) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            if (object == null) {
                if (iterator.next() == object) {
                    return true;
                }
            } else {
                if (object.equals(iterator.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object object) {
        Iterator<T> iterator = iterator();
        int index = 0;
        while (iterator.hasNext()) {
            if (object == null) {
                if (iterator.next() == object) {
                    return index;
                }
            } else {
                if (object.equals(iterator.next())) {
                    return index;
                }
            }
            ++index;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() { size = 0; }

    @Override
    public void replaceAll(UnaryOperator<T> operator) { }

    @Override
    public void sort(Comparator<? super T> c) { }

    @Override
    public Spliterator<T> spliterator() {
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
