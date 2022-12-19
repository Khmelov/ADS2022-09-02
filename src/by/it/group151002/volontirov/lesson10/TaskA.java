package by.it.group151002.volontirov.lesson10;

import by.it.group151002.volontirov.lesson09.SetC;

import javax.print.DocFlavor;
import java.util.*;

public class TaskA<E> implements NavigableSet<E> {
    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet
    public static void main(String[] args) {
        TaskA<Integer> tree = new TaskA<>();
        tree.add(5);
        tree.add(10);
        tree.add(4);
        tree.add(7);
        tree.add(8);
        tree.add(6);
        tree.add(9);
        tree.add(11);
        tree.remove(10);
        System.out.println(tree);
    }
    private E value;
    private TaskA<E> left;
    private TaskA<E> right;

    public E getValue() {
        return value;
    }

    public TaskA<E> getLeft() {
        return left;
    }

    public TaskA<E> getRight() {
        return right;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public void setLeft(TaskA<E> left) {
        this.left = left;
    }

    public void setRight(TaskA<E> right) {
        this.right = right;
    }

    public void setNode(TaskA<E> node){
        this.value = node.getValue();
        this.setRight(node.getRight());
        this.setLeft(node.getLeft());
    }

    //Обязательные к реализации методы и конструкторы
    public TaskA() {
        this.value = null;
        this.left = null;
        this.right = null;
    }
    @Override
    public boolean remove(Object o) {
        if(!(o instanceof Comparable)){
            return false;
        }
        if(this.getValue() == null) {
            return false;
        }
        if(((Comparable<E>) o).compareTo(this.getValue()) > 0){
            return this.getRight().remove(o);
        }
        if(((Comparable<E>) o).compareTo(this.getValue()) < 0){
            return this.getLeft().remove(o);
        }
        if(this.getRight().getValue() == null) {
            this.setNode(this.getLeft());
            return true;
        }
        if(this.getLeft().getValue() == null) {
            this.setNode(this.getRight());
            return true;
        }
        this.getLeft().delete_right(this);
        return true;
    }
    @Override
    public boolean add(E e) {
        if(!(e instanceof Comparable)){
            return false;
        }
        if(this.getValue() == null){
            this.setValue(e);
            this.setRight(new TaskA<>());
            this.setLeft(new TaskA<>());
            return true;
        }
        if(((Comparable)this.getValue()).compareTo(e) > 0){
            return this.getLeft().add(e);
        }
        if(((Comparable)this.getValue()).compareTo(e) < 0){
            return this.getRight().add(e);
        }
        return false;
    }
    private void delete_right(TaskA<E> deleted){
        if(this.getRight() != null && this.getRight().getValue() != null){
            this.getRight().delete_right(deleted);
            return;
        }
        deleted.setValue(this.getValue());
        this.setNode(this.getLeft());
    }
    private void subString(StringBuilder builder) {
        if(this.getValue() == null){
            return;
        }
        this.getLeft().subString(builder);
        builder.append(this.getValue());
        builder.append(", ");
        this.getRight().subString(builder);
        return;
    }
    @Override
    public String toString() {
        if(this.getValue() == null){
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        getLeft().subString(builder);
        builder.append(this.getValue());
        builder.append(", ");
        getRight().subString(builder);
        builder.delete(builder.length() - 2, builder.length());
        builder.append("]");
        return builder.toString();
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


    @Override
    public boolean contains(Object o) {
        if(this.getValue() == null){
            return false;
        }
        if(this.getValue().equals(o)){
            return true;
        }
        return getLeft().contains(o) | getRight().contains(o);
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
