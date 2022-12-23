package by.it.group151004.danilov.lesson10;

import java.util.*;

public class TaskC<E>  implements NavigableSet<E> {
    private Tree<E> redBlackTree;

    public TaskC() {
        redBlackTree = new Tree<>();
    }

    @Override
    public boolean add(E e) {
        return redBlackTree.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return redBlackTree.remove(o);
    }

    @Override
    public boolean contains(Object o) {
        return redBlackTree.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return redBlackTree.iterator();
    }

    @Override
    public void clear() {
        redBlackTree.clear();
    }

    @Override
    public boolean isEmpty() {
        return redBlackTree.isEmpty();
    }

    @Override
    public int size() {
        return redBlackTree.size();
    }

    @Override
    public E first() {
        return redBlackTree.first();
    }

    @Override
    public E last() {
        return redBlackTree.last();
    }

    @Override
    public E lower(E e) {
        return redBlackTree.lower(e);
    }

    @Override
    public E floor(E e) {
        return redBlackTree.floor(e);
    }

    @Override
    public E ceiling(E e) {
        return redBlackTree.ceiling(e);
    }

    @Override
    public E higher(E e) {
        return redBlackTree.higher(e);
    }

    @Override
    public E pollFirst() {
        return redBlackTree.pollFirst();
    }

    @Override
    public E pollLast() {
        return redBlackTree.pollLast();
    }


    @Override
    public String toString() {
        return redBlackTree.toString();
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
