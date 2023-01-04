package by.it.group151002.krashevskiy.lesson10;

import java.util.*;

public class TaskA<E extends Comparable<E>>  implements NavigableSet<E> {

    private E item;
    private TaskA<E> left;
    private TaskA<E> right;


    private void setNode(TaskA<E> node){
        this.item = node.item;
        this.right = node.right;
        this.left = node.left;
    }

    public TaskA() {
        this.item = null;
        this.left = null;
        this.right = null;
    }
    @Override
    public boolean remove(Object o) {
        if(!(o instanceof Comparable)){
            return false;
        }
        if(item == null) {
            return false;
        }
        if(item.compareTo((E) o) < 0) {
            return this.right.remove(o);
        }
        if(item.compareTo((E) o) > 0){
            return this.left.remove(o);
        }
        if(this.right.item == null) {
            this.setNode(this.left);
        } else if(left.item == null) {
            this.setNode(this.right);
        } else
            this.left.delete_right(this);
        return true;
    }
    @Override
    public boolean add(E e) {
        if(this.item == null){
            this.item = e;
            this.right = new TaskA<>();
            this.left = new TaskA<>();
            return true;
        }
        if(item.compareTo(e) > 0){
            return this.left.add(e);
        }
        if(item.compareTo(e) < 0){
            return this.right.add(e);
        }
        return false;
    }
    private void delete_right(TaskA<E> deleted){
        if(this.right != null && this.right.item != null){
            this.right.delete_right(deleted);
            return;
        }
        deleted.item = this.item;
        this.setNode(this.left);
    }
    private void subString(StringBuilder sb) {
        if(this.item == null){
            return;
        }
        this.left.subString(sb);
        sb.append(this.item);
        sb.append(", ");
        this.right.subString(sb);
    }
    @Override
    public String toString() {
        if(this.item == null){
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        left.subString(sb);
        sb.append(item);
        sb.append(", ");
        right.subString(sb);
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public E lower(E e) {
        return null;
    }

    @Override
    public E floor(E e) {
        return null;
    }

    @Override
    public E ceiling(E e) {
        return null;
    }

    @Override
    public E higher(E e) {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

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

    @Override
    public E first() {
        return null;
    }

    @Override
    public E last() {
        return null;
    }
}
