package by.it.group151002.kuvshinov.lesson10;

import java.util.*;

public class TaskA<E>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы
    private class Node{
        E val;
        Node left;
        Node right;

        Node(E value){this.val = value;}
    }

    private Node root;

    public TaskA() {
    }

    private int compareTo(E obj1, E obj2) {
        return Integer.compare((Integer)obj1, (Integer)obj2);
    }
    @Override
    public boolean add(E e) {
        Node currNode = root;
        Node parent = currNode;
        int res = 0;
        while (currNode != null){
            parent = currNode;
            res = compareTo(currNode.val, e);
            if (res < 0){
                currNode = currNode.right;
            } else if (res > 0){
                currNode = currNode.left;
            } else {
                return false;
            }
        }
        Node newNode = new Node(e);
        if (parent == null)
            root = newNode;
        else if (res <= 0)
            parent.right = newNode;
        else
            parent.left = newNode;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node curr = root;
        int res = 0;
        Node parent = null;
        while (curr != null && ((res = compareTo(curr.val, (E)o)) != 0)) {
            parent = curr;
            if (res > 0)
                curr = curr.left;
            else
                curr = curr.right;
        }
        if (curr == null)
            return false;

        if (curr.left != null && curr.right != null){
            Node min = curr.right;
            while (min.left != null) {
                min = min.left;
            }
            remove(min.val);
            curr.val = min.val;
        } else if (curr.left == null && curr.right == null) {
            if (curr != root) {
                if (parent.right == curr)
                    parent.right = null;
                else
                    parent.left = null;
            } else
                root = null;
        }
        else{
            Node child  = (curr.left != null)? curr.left: curr.right;
            if (curr != root){
                if (curr == parent.left)
                    parent.left = child;
                else
                    parent.right = child;
            }
            else
                root = child;
        }
        return true;
    }
    private String concatResString(Node node, String res){
        if (node != null){
            res = res  + concatResString(node.left, res) + ", " + node.val.toString()  + concatResString(node.right, res);
        }
        return res;
    }

    @Override
    public String toString() {
        String res = "";
        if (root == null)
            return "[" + res + "]";
        return "[" + concatResString(root, res).substring(2) + "]";
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
