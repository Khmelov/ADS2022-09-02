package by.it.group151002.krashevskiy.lesson10;

import java.util.*;

public class TaskC<E extends Comparable<E>>  implements NavigableSet<E> {
    private E item;
    private TaskC<E> left;
    private TaskC<E> right;


    private void setNode(TaskC<E> node){
        this.item = node.item;
        this.right = node.right;
        this.left = node.left;
    }

    public TaskC() {
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
        if(right.item == null) {
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
            this.right = new TaskC<>();
            this.left = new TaskC<>();
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
    private void delete_right(TaskC<E> deleted){
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

    @Override
    public boolean contains(Object o) {
        if(!(o instanceof Comparable)){
            return false;
        }
        if(item == null){
            return false;
        }
        if((item.compareTo((E) o)) < 0){
            return this.right.contains(o);
        }
        if((item.compareTo((E) o)) > 0){
            return this.left.contains(o);
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {
        this.item = null;
        this.right = null;
        this.left = null;
    }

    @Override
    public boolean isEmpty() {
        if(item == null){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        if (this.item == null)
            return 0;
        return 1 + this.left.size() + this.right.size();
    }

    @Override
    public E first() {
        if(this.left.item != null){
            return this.left.first();
        }
        return this.item;
    }

    @Override
    public E last() {
        if(this.right.item != null){
            return this.right.last();
        }
        return this.item;
    }
    private void toArray(ArrayList<E> array){
        if(this.item == null) return;
        this.left.toArray(array);
        array.add(this.item);
        this.right.toArray(array);
    }
    @Override
    public E lower(E e) {
        ArrayList<E> array = new ArrayList<>();
        this.toArray(array);
        int i = -1;
        while(i < array.size() - 1 && ((array.get(i + 1)).compareTo(e) < 0)) {
            i++;
        }
        if(i == -1 || i == array.size()) return null;
        return array.get(i);
    }

    @Override
    public E floor(E e) {
        ArrayList<E> array = new ArrayList<>();
        this.toArray(array);
        int i = -1;
        while(i < array.size() - 1 && (array.get(i + 1)).compareTo(e) <= 0) {
            i++;
        }
        if(i == -1 || i == array.size()) return null;
        return array.get(i);
    }

    @Override
    public E ceiling(E e) {
        ArrayList<E> array = new ArrayList<>();
        this.toArray(array);
        int i = array.size();
        while(i > 0 && (array.get(i - 1)).compareTo(e) >= 0) {
            i--;
        }
        if(i == -1 || i == array.size()) return null;
        return array.get(i);
    }

    @Override
    public E higher(E e) {
        ArrayList<E> array = new ArrayList<>();
        this.toArray(array);
        int i = array.size();
        while(i > 0 && (array.get(i - 1)).compareTo(e) > 0) {
            i--;
        }
        if(i == -1 || i == array.size()) return null;
        return array.get(i);
    }

    @Override
    public E pollFirst() {
        if(this.left.item != null){
            return this.left.pollFirst();
        }
        E tmp = this.item;
        this.remove(tmp);
        return tmp;
    }

    @Override
    public E pollLast() {
        if(this.right.item != null){
            return this.right.pollLast();
        }
        E tmp = this.item;
        this.setNode(this.left);
        return tmp;
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
