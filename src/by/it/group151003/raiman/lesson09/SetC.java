package by.it.group151003.raiman.lesson09;

import java.util.*;

public class SetC<T> implements Set<T> {
    private final static int DEFAULT_SIZE = 16;
    private final static double LOAD_FACTOR = 0.75;
    private int size;
    private int bucketCount;
    private List<List<T>> buckets;

    public SetC() {
        size = 0;
        bucketCount = DEFAULT_SIZE;
        buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private void ensureBucketCount() {
        T[] setElements = (T[]) toArray();
        bucketCount <<= 1;
        buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++)
            buckets.add(new ArrayList<>());

        addAll(Arrays.asList(setElements));
    }

    private void checkLoading(int index) {
        if (buckets.get(index).size() > bucketCount * LOAD_FACTOR)
            ensureBucketCount();
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
        int index;
        if (o != null)
            index = o.hashCode() & (bucketCount - 1);
        else
            index = 0;
        return buckets.get(index).contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return buckets.stream().flatMap(Collection::stream).toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        int index;
        if (t != null)
            index = t.hashCode() & (bucketCount - 1);
        else
            index = 0;
        checkLoading(index);
        if (buckets.get(index).contains(t))
            return false;
        size++;
        return buckets.get(index).add(t);
    }

    @Override
    public boolean remove(Object o) {
        int index;
        if (o != null)
            index = o.hashCode() & (bucketCount - 1);
        else
            index = 0;
        if (!buckets.get(index).contains(o))
            return false;
        buckets.get(index).remove(o);
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.stream().forEach(this::add);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        c.stream().forEach(this::remove);
        return true;
    }

    @Override
    public void clear() {
        buckets.forEach(List::clear);
        size = 0;
    }

    public String toString() {
        return Arrays.toString(toArray());
    }
}