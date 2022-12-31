package by.it.group151001.manchik.lesson10;

import java.util.*;

public class TaskC<E>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы
    public static void main(String[] argv){

    }

    private Node root;
    private int size;
    private class Node{
        Node(E value){
            this.value = value;
            this.left = null;
            this.right = null;
        }
        public E value;
        public Node left;
        public Node right;
    }
    //Обязательные к реализации методы и конструкторы

    public TaskC() {
        root = null;
    }

    @Override
    public boolean add(E e) {
        if (!contains(e)) {
            root = Fplacetoadd(root,e);
            size++;
            return true;
        }
        return false;
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object o) {
        Node cur = root, parent = root, nnew;
        Comparable<? super E> value = (Comparable<? super E>) o;
        E temp;
        if (contains(o)) {
            while (cur != null && cur.value != value ){
                parent = cur;
                if (value.compareTo(cur.value) > 0) cur = cur.right;
                else if (value.compareTo(cur.value) < 0) cur = cur.left;
            }
            if (cur != null){
                if (cur.left != null && cur.right != null){
                    nnew = cur.left;
                    if (nnew.right == null){
                        temp = nnew.value;
                        size++;
                        remove(temp);
                        cur.value = temp;
                    } else cur.value = DeleteRight(nnew);
                }
                else if (cur.left != null){
                    if (cur == parent.left) parent.left = cur.left;
                    else if (cur == parent.right) parent.right = cur.left;
                    else root = cur.left;
                }
                else if (cur.right != null){
                    if (cur == parent.left) parent.left = cur.right;
                    else if (cur == parent.right) parent.right = cur.right;
                    else root = cur.right;
                }
                else {
                    if (cur == parent.left) parent.left = null;
                    else if (cur == parent.right) parent.right = null;
                    else root = null;
                }
            }
            this.size--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        if (root == null) return "[]";

        res.append("[");
        res.append(SymmetricOrder(root));
        res.delete(res.length() - 2,res.length());
        res.append("]");
        return res.toString();
    }
    @SuppressWarnings("unchecked")
    private Node Fplacetoadd(Node root, E e){
        if (root == null) {
            root = new Node(e);
        } else {
            Comparable<? super E> val = (Comparable<? super E>) root.value;
            if (val.compareTo(e) > 0) root.left = Fplacetoadd(root.left, e);
            else if (val.compareTo(e) < 0) root.right = Fplacetoadd(root.right, e);
            else return null;
        }
        return root;
    }

    private String SymmetricOrder(Node root){
        StringBuilder res = new StringBuilder();
        if (root != null){
            res.append(SymmetricOrder(root.left));
            res.append(root.value).append(", ");
            res.append(SymmetricOrder(root.right));
        }
        return res.toString();
    }
    @SuppressWarnings("unchecked")
    private boolean SymmetricSearch(Node root, E e){
        if (root != null) {
            Comparable<? super E> val = (Comparable<? super E>) root.value;
            if (root.value == e) return true;
            else if (val.compareTo(e) > 0) return SymmetricSearch(root.left, e);
            else if (val.compareTo(e) < 0) return SymmetricSearch(root.right, e);
        }
        return false;
    }
    private E DeleteRight(Node start){
        Node parent = start;
        E result;

        while (start.right != null){
            parent = start;
            start = start.right;
        }

        result = start.value;
        if (start.left != null) parent.right = start.left;
        else parent.right = null;
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object o) {
        return SymmetricSearch(root, (E)o);
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E first() {
        if (root == null) return null;
        else {
            Node cur = root;
            while (cur.left != null){
                cur = cur.left;
            }
            return cur.value;
        }
    }

    @Override
    public E last() {
        if (root == null) return null;
        else {
            Node cur = root;
            while (cur.right != null){
                cur = cur.right;
            }
            return cur.value;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E lower(E e) {
        Node cur = root;
        Comparable<? super E> max = (Comparable<? super E>) first();
        Comparable<? super E> value = (Comparable<? super E>) e;
        if (max.compareTo(e) >= 0) return null;

        while (cur!= null && cur.value != e){
           if (max.compareTo(cur.value) < 0 && value.compareTo(cur.value) > 0) max = (Comparable<? super E>) cur.value;
           if (value.compareTo(cur.value) > 0) cur = cur.right;
           else if (value.compareTo(cur.value) < 0) cur = cur.left;
        }
        if (cur == null) return (E)max;
        else if (cur.left != null) {
            cur = cur.left;
            while (cur.right != null){
                cur = cur.right;
            }
            return cur.value;
        } else return (E)max;
    }
    @SuppressWarnings("unchecked")
    @Override
    public E floor(E e) {
        Node cur = root;
        Comparable<? super E> max = (Comparable<? super E>) first();
        Comparable<? super E> value = (Comparable<? super E>) e;
        if (value.compareTo(first()) < 0) return null;

        while (cur!= null && cur.value != e){
            if (max.compareTo(cur.value) < 0 && value.compareTo(cur.value) > 0) max = (Comparable<? super E>) cur.value;
            if (value.compareTo(cur.value) > 0) cur = cur.right;
            else if (value.compareTo(cur.value) < 0) cur = cur.left;
        }
        if (cur == null) return (E)max;
        else return cur.value;
    }
    @SuppressWarnings("unchecked")
    @Override
    public E ceiling(E e) {
        Node cur = root;
        Comparable<? super E> max = (Comparable<? super E>) first();
        Comparable<? super E> value = (Comparable<? super E>) e;
        if (value.compareTo(last()) > 0) return null;

        while (cur!= null && cur.value != e){
            if (max.compareTo(cur.value) < 0 && value.compareTo(cur.value) < 0) max = (Comparable<? super E>) cur.value;
            if (value.compareTo(cur.value) > 0) cur = cur.right;
            else if (value.compareTo(cur.value) < 0) cur = cur.left;
        }
        if (cur == null) return (E)max;
        else if (cur == e) return e;
        return cur.value;
    }
    @SuppressWarnings("unchecked")
    @Override
    public E higher(E e) {
        Node cur = root;
        Comparable<? super E> max = (Comparable<? super E>) last();
        Comparable<? super E> value = (Comparable<? super E>) e;
        if (value.compareTo(last()) > 0) return null;

        while (cur!= null && cur.value != e){
            if (max.compareTo(cur.value) > 0 && value.compareTo(cur.value) < 0) max = (Comparable<? super E>) cur.value;
            if (value.compareTo(cur.value) > 0) cur = cur.right;
            else if (value.compareTo(cur.value) < 0) cur = cur.left;
        }
        if (cur == null) return (E)max;
        else if (cur.right != null) {
            cur = cur.right;
            while (cur.left != null){
                cur = cur.left;
            }
            return cur.value;
        }
        return (E)max;
    }

    @Override
    public E pollFirst() {
        if (first() == null) return null;
        else {
            E value = first();
            remove(first());
            return value;
        }
    }

    @Override
    public E pollLast() {
        if (last() == null) return null;
        else {
            E value = last();
            remove(last());
            return value;
        }
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
