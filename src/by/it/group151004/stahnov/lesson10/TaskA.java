package by.it.group151004.stahnov.lesson10;

import java.util.*;

public class TaskA<E>  implements NavigableSet<E> {

    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet

    int size;
    class Node{
        E value;
        Node right,left;

        Node(E value) {
            this.value = value;
            right = null;
            left = null;
        }
    }

    Node root;
    //Обязательные к реализации методы и конструкторы
    public TaskA() {
        size = 0;
        root = null;
    }

    @Override
    public boolean add(E e) {
        if(root == null) {
            root = new Node(e);
            return true;
        }
        else{
            Node tmp = root;
            while(true){
                if(tmp.value.equals(e))
                    return false;
                if((int)tmp.value - (int)e < 0){
                //if(tmp.value.getClass().toString().compareTo(e.toString())<=0){
                    if(tmp.right == null) {
                        tmp.right = new Node(e);
                        size++;
                        return true;
                    }
                    else
                        tmp = tmp.right;
                }
                else
                    if(tmp.left == null) {
                        tmp.left = new Node(e);
                        size++;
                        return true;
                    }
                    else
                        tmp = tmp.left;
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        if(root != null){
            int flag = 0;
            Node tmp1 = null, tmp = root;
            while(true){
                if(tmp.value.equals(o)) {
                    if(tmp1 != null){
                        if(flag == 1){
                            tmp1.right = null;
                        }
                        else{
                            tmp1.left = null;
                        }
                    }
                    if(tmp.right != null)
                        RABadd(tmp.right);
                    if(tmp.left != null)
                        RABadd(tmp.left);
                return true;
                }
                if((int)tmp.value - (int)o < 0){
                //if(tmp.value.toString().compareTo(o.toString())<0){
                    if(tmp.right == null) {
                        return false;
                    }
                    else{
                        tmp1 = tmp;
                        flag = 1;
                        tmp = tmp.right;
                    }

                }
                else
                if(tmp.left == null) {
                    return false;
                }
                else{
                    flag = 0;
                    tmp1 = tmp;
                    tmp = tmp.left;
                }
            }
        }
        return false;
    }

    void RABadd(Node node){
        if(node.left != null){
            RABadd(node.left);
        }
        if(node.right != null){
            RABadd(node.right);
        }
        add(node.value);
    }
    StringBuilder str;
    @Override
    public String toString() {
        str = new StringBuilder();
        str.append("[");
        if(size != 0) {
            RAB(root);
            str.deleteCharAt(str.length() - 1);
            str.deleteCharAt(str.length() - 1);
        }
        str.append("]");
        return str.toString();
    }

    private void RAB(Node node){
        if(node.left != null){
            RAB(node.left);
        }
        str.append(node.value);
        str.append(", ");
        if(node.right != null){
            RAB(node.right);
        }
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
