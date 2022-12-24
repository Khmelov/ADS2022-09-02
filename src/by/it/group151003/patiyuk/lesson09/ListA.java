package by.it.group151003.patiyuk.lesson09;

import java.util.*;

public class ListA<T> implements List<T> {
    private Node<T> first;
    private Node<T> last;
    private int size = 0;

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<T> node(int index) {
        Node<T> x;
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
        } else {
            x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
        }
        return x;
    }

    private void linkBefore(T element, Node<T> node) {
        final Node<T> prev = node.prev;
        final Node<T> newNode = new Node<>(prev, element, node);
        node.prev = newNode;
        if (prev == null)
            first = newNode;
        else
            prev.next = newNode;
        size++;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean contains(Object o) { return indexOf(o) != -1;}

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = first;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                T item = current.item;
                current = current.next;
                index++;
                return item;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<T> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size)
            return (T1[]) Arrays.copyOf(toArray(), size, a.getClass());

        System.arraycopy(toArray(), 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<>(l, t, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        final Node<T> l = last;
        final Node<T> prev = l.prev;
        final T element = l.item;
        if (Objects.equals(o, element)) {
            final Node<T> next = l.next;
            l.item = null;
            l.next = null;
            last = prev;
            if (prev == null)
                first = null;
            else
                prev.next = next;
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T e : c)
            add(e);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        for (T e : c)
            add(index++, e);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object e : c)
            remove(e);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object e : c)
            if (!c.contains(e))
                remove(e);
        return true;
    }

    @Override
    public void clear() {
        for (Node<T> x = first; x != null; ) {
            Node<T> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return node(index).item;
    }

    @Override
    public T set(int index, T element) {
        Node<T> x = node(index);
        T oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    @Override
    public void add(int index, T element) {
        if (index == size)
            add(element);
        else
            linkBefore(element, node(index));
    }

    @Override
    public T remove(int index) {
        Node<T> x = node(index);
        final T element = x.item;
        final Node<T> next = x.next;
        final Node<T> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<T> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<T> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<T> x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null)
                    return index;
            }
        } else {
            for (Node<T> x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item))
                    return index;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<>() {
            private Node<T> current = node(index);
            private Node<T> lastReturned = null;
            private int nextIndex = index;
            private int prevIndex = index - 1;

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public T next() {
                lastReturned = current;
                current = current.next;
                nextIndex++;
                prevIndex++;
                return lastReturned.item;
            }

            @Override
            public boolean hasPrevious() {
                return prevIndex >= 0;
            }

            @Override
            public T previous() {
                lastReturned = current;
                current = current.prev;
                nextIndex--;
                prevIndex--;
                return lastReturned.item;
            }

            @Override
            public int nextIndex() {
                return nextIndex;
            }

            @Override
            public int previousIndex() {
                return prevIndex;
            }

            @Override
            public void remove() {
                if (lastReturned == null)
                    throw new IllegalStateException();
                ListA.this.remove(lastReturned);
                lastReturned = null;
            }

            @Override
            public void set(T t) {
                if (lastReturned == null)
                    throw new IllegalStateException();
                lastReturned.item = t;
            }

            @Override
            public void add(T t) {
                lastReturned = null;
                if (current == null)
                    ListA.this.add(t);
                else
                    linkBefore(t, current);
                nextIndex++;
                prevIndex++;
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        List<T> subList = new ListA<>();
        for (int i = fromIndex; i < toIndex; i++)
            subList.add(get(i));
        return subList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> x = first;
        for (int i = 0; i < size; i++) {
            sb.append(x.item);
            if (i < size - 1)
                sb.append(", ");
            x = x.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
