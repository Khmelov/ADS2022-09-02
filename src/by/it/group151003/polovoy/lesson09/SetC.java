package by.it.group151003.polovoy.lesson09;

import java.util.*;
import java.util.stream.IntStream;

public class SetC<T> implements Set<T> {

    private static final int DEFAULT_CAPACITY = 16;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int size;

    private int capacity;

    private ArrayList<LinkedList<T>> buckets;

    public SetC() {
        capacity = DEFAULT_CAPACITY;
        buckets = new ArrayList<>(capacity);
        IntStream.range(0, capacity).forEach(i -> buckets.add(new LinkedList<>()));
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
    public boolean contains(Object o) {
        return buckets.get(hash(o)).contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean add(T t) {
        int index = hash(t);

        if (buckets.get(index).contains(t)) {
            return false;
        }

        buckets.get(index).add(t);

        size++;

        checkCapacity(index);

        return true;
    }

    private int hash(Object o) {
        return o == null ? 0 : (o.hashCode() & (capacity - 1));
    }

    private void checkCapacity(int index) {
        if (buckets.get(index).size() > DEFAULT_LOAD_FACTOR * capacity) {
            rehash();
        }
    }

    private void rehash() {
        capacity <<= 1;

        ArrayList<LinkedList<T>> newBuckets = new ArrayList<>(capacity);

        for (int i = 0; i < capacity; i++) {
            newBuckets.add(new LinkedList<>());
        }

        for (LinkedList<T> bucket : buckets) {
            for (T t : bucket) {
                newBuckets.get(hash(t)).add(t);
            }
        }

        buckets = newBuckets;
    }

    @Override
    public boolean remove(Object o) {
        int index = hash(o);

        if (buckets.get(index).contains(o)) {
            buckets.get(index).remove(o);
            size--;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach(this::add);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        c.forEach(this::remove);
        return true;
    }

    @Override
    public void clear() {
        buckets.forEach(LinkedList::clear);
        size = 0;
    }
}
