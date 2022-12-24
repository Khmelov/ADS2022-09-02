package by.it.group151004.tishalovich.lesson10;

import java.util.*;

public class TaskC<E>  implements NavigableSet<E> {

    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet

    AVLTree<E> tree = new AVLTree<>();

    //Обязательные к реализации методы и конструкторы
    public TaskC() {
    }

    @Override
    public boolean add(E e) {
        return null != tree.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return null != tree.delete((E) o);
    }

    @Override
    public boolean contains(Object o) {
        return tree.contains((E) o);
    }

    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    @Override
    public void clear() {tree.clear();}

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public int size() {
        return tree.size();
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
    public E lower(E e) { return tree.lower(e); }

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
    public String toString() {
        return tree.toString();
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
