package by.it.group151002.kragel.lesson09;


import java.util.*;

public class ListB<T> implements List<T> {

    T[] arr;
    int capacity;
    int start, end;

    private class ListBIterator<E> implements Iterator<E> {

        int pos, del;

        ListBIterator() {
            pos = start;
            del = -1;
        }

        @Override
        public boolean hasNext() {
            return pos != end;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (pos > end)
                throw new NoSuchElementException();
            del = pos - start;
            return (E) arr[pos++];
        }

        @Override
        public void remove() {
            if (del < 0)
                throw new IllegalStateException();
            ListB.this.remove(del);
            del = -1;
        }
    }

    @SuppressWarnings("unchecked")
    private T[] setCapacity(T[] arr, int capacity) {
        T[] array = (T[]) new Object[capacity];
        int newStart = capacity / 3;
        int newEnd = newStart;
        if (arr != null) {
            for (int i = start; i < end; i++) {
                array[newEnd++] = arr[i];
            }
        }
        this.start = newStart;
        this.end = newEnd;
        this.capacity = capacity;
        return array;
    }

    ListB() {
        arr = setCapacity(null, 96);
    }

    @Override
    public boolean add(T t) {
        if (end == capacity) {
            arr = setCapacity(arr, capacity * 3);
        }
        arr[end++] = t;
        return true;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > end - start - 1)
            throw new IndexOutOfBoundsException("index: %d, when the last index: %d".formatted(index, end - start - 1));
        T res = arr[start + index];
        if (index > ((end - start) / 2)) {
            end--;
            for (int i = start + index; i < end; i++) {
                arr[i] = arr[i + 1];
            }
        } else {
            for (int i = start + index; i > start; i--) {
                arr[i] = arr[i - 1];
            }
            start++;
        }
        return res;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > end - start - 1)
            throw new IndexOutOfBoundsException("index: %d, when the last index: %d".formatted(index, end - start - 1));
        return arr[start + index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index > end - start - 1)
            throw new IndexOutOfBoundsException("index: %d, when the last index: %d".formatted(index, end - start - 1));
        T res = arr[start + index];
        arr[start + index] = element;
        return res;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > end - start)
            throw new IndexOutOfBoundsException("index: %d, when the last index: %d".formatted(index, end - start - 1));
        if (index > ((end - start) / 2)) {
            if (end + 1 == capacity) {
                arr = setCapacity(arr, capacity * 3);
            }
            for (int i = end; i > start + index; i--) {
                arr[i] = arr[i - 1];
            }
            end++;
        } else {
            if (start - 1 == -1) {
                arr = setCapacity(arr, capacity * 3);
            }
            start--;
            for (int i = start; i < start + index; i++) {
                arr[i] = arr[i + 1];
            }
        }
        arr[start + index] = element;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0)
            return false;
        if (end + c.size() >= capacity) {
            arr = setCapacity(arr, ((((this.size() + c.size()) * 3 / capacity) * capacity)));
        }
        for (T t : c) {
            arr[end++] = t;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > end - start)
            throw new IndexOutOfBoundsException("index: %d, when the last index: %d".formatted(index, end - start - 1));
        if (c.size() == 0)
            return false;
        if (this.size() + c.size() >= capacity) {
            arr = setCapacity(arr, ((((this.size() + c.size()) * 3 / capacity) * capacity)));
        }
        if (index > ((end - start) / 2)) {
            for (int i = end; i > start + index; i--) {
                arr[i] = arr[i - c.size()];
            }
            end += c.size();
        } else {
            start -= c.size();
            for (int i = start; i < start + index; i++) {
                arr[i] = arr[i + c.size()];
            }
        }
        for (T t : c) {
            arr[start + (index++)] = t;
        }
        return true;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (arr[i] == null)
                    return i - start;
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(arr[i]))
                    return i - start;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = end - 1; i >= start; i--) {
                if (arr[i] == null)
                    return i - start;
            }
        } else {
            for (int i = end - 1; i >= start; i--) {
                if (o.equals(arr[i]))
                    return i - start;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) > -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean res = true;
        Iterator<?> it = c.iterator();
        while (res && it.hasNext()) {
            res = (indexOf(it.next()) > -1);
        }
        return res;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index > -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (Object o : c) {
            while (indexOf(o) > -1)
                res |= remove(o);
        }
        return res;
    }

    @Override
    public int size() {
        return end - start;
    }

    @Override
    public boolean isEmpty() {
        return end == start;
    }

    @Override
    public void clear() {
        arr = setCapacity(null, 96);
    }

    @Override
    public Iterator<T> iterator() {
        return new ListBIterator<>();
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
    public boolean retainAll(Collection<?> c) {
        return false;
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
    public String toString() {
        String result = "[";
        if (end - start > 0) {
            result += (arr[start] == null ? "null" : arr[start].toString());
            for (int i = start + 1; i < end; i++) {
                result += ", " + (arr[i] == null ? "null" : arr[i].toString());
            }
        }
        return result + "]";
    }
}
