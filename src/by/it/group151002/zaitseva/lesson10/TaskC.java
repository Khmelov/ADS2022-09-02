package by.it.group151002.zaitseva.lesson10;

import java.util.*;

public class TaskC<E> implements NavigableSet<E> {
    public NavigableMap<E, Object> map;
    public static Object obj = new Object();

    // Обязательные к реализации методы и конструкторы
    public TaskC() {
        this(new TreeMap<E, Object>());
    }

    public TaskC(NavigableMap<E, Object> map) {
        this.map = map;
    }

    @Override
    public boolean add(E e) {
        return map.put(e, obj) == null;
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == obj;
    }

    @Override
    public String toString() {
        return map.keySet().toString();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public Iterator<E> iterator() {
        return map.navigableKeySet().iterator();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public E first() {
        return map.firstKey();
    }

    @Override
    public E last() {
        return map.lastKey();
    }

    @Override
    public E lower(E e) {
        return map.lowerKey(e);

    }

    @Override
    public E floor(E e) {
        return map.floorKey(e);

    }

    @Override
    public E ceiling(E e) {
        return map.ceilingKey(e);
    }

    @Override
    public E higher(E e) {
        return map.higherKey(e);
    }

    @Override
    public E pollFirst() {
        Map.Entry<E, ?> e = map.pollFirstEntry();
        return e == null ? null : e.getKey();
    }

    @Override
    public E pollLast() {
        Map.Entry<E, ?> e = map.pollLastEntry();
        return e == null ? null : e.getKey();
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////// Эти методы реализовывать необязательно ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
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
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super E> comparator() {
        return null;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return null;
    }
}