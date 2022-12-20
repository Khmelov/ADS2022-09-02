package by.it.group151001.verghel.lesson10;

import java.util.*;

public class TaskC<E>  implements NavigableSet<E> {

    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet

    //Обязательные к реализации методы и конструкторы
    private transient NavigableMap<E,Object> m;
    private static final Object PRESENT = new Object();
    public TaskC() {
        this(new TreeMap<E,Object>());
    }
    TaskC(NavigableMap<E,Object> m) {
        this.m = m;
    }

    @Override
    public boolean add(E e) {
        return m.put(e, PRESENT)==null;
    }

    @Override
    public boolean remove(Object o) {
        return m.remove(o)==PRESENT;
    }

    @Override
    public boolean contains(Object o) {
        return m.containsKey(o);
    }

    @Override
    public Iterator<E> iterator() {
        return m.navigableKeySet().iterator();
    }

    @Override
    public void clear() {
        m.clear();
    }

    @Override
    public boolean isEmpty() {
        return m.isEmpty();
    }

    @Override
    public int size() {
        return m.size();
    }

    @Override
    public E first() {
        return m.firstKey();
    }

    @Override
    public E last() {
        return m.lastKey();
    }

    @Override
    public E lower(E e) {
        return m.lowerKey(e);

    }

    @Override
    public E floor(E e) {
        return m.floorKey(e);

    }

    @Override
    public E ceiling(E e) {
        return m.ceilingKey(e);
    }

    @Override
    public E higher(E e) {
        return m.higherKey(e);
    }

    @Override
    public E pollFirst() {
        Map.Entry<E,?> e = m.pollFirstEntry();
        return (e == null)? null : e.getKey();
    }

    @Override
    public E pollLast() {
        Map.Entry<E,?> e = m.pollLastEntry();
        return (e == null)? null : e.getKey();
    }


    @Override
    public String toString() {
        return m.keySet().toString();
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
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
