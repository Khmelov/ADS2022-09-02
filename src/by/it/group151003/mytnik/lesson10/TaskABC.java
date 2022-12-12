package by.it.group151003.mytnik.lesson10;

import java.util.*;

public class TaskABC<E extends Comparable<E>> implements NavigableSet<E> {

    private final RBTree<E> tree;

    public TaskABC() {
        tree = new RBTree<>();
    }

    @Override
    public E lower(E e) {
        return tree.lower(e);
    }

    @Override
    public E floor(E e) {
        return tree.floor(e);
    }

    @Override
    public E ceiling(E e) {
        return tree.ceiling(e);
    }

    @Override
    public E higher(E e) {
        return tree.higher(e);
    }

    @Override
    public E pollFirst() {
        return tree.pollFirst();
    }

    @Override
    public E pollLast() {
        return tree.pollLast();
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return tree.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    @Override
    public Object[] toArray() {
        return tree.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return tree.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return tree.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return tree.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return tree.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return tree.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return tree.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return tree.removeAll(c);
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return tree.descendingSet();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return tree.descendingIterator();
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return tree.subSet(fromElement, fromInclusive, toElement, toInclusive);
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return tree.headSet(toElement, inclusive);
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return tree.tailSet(fromElement, inclusive);
    }

    @Override
    public Comparator<? super E> comparator() {
        return tree.comparator();
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return tree.subSet(fromElement, toElement);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return tree.headSet(toElement);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return tree.tailSet(fromElement);
    }

    @Override
    public E first() {
        return tree.first();
    }

    @Override
    public E last() {
        return tree.last();
    }

    @Override
    public String toString() {
        return tree.toString();
    }
}