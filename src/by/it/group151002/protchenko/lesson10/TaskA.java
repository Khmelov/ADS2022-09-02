package by.it.group151002.protchenko.lesson10;

import java.util.*;

public class TaskA<E>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы
    private class Node{
        E value;
        Node left;
        Node right;

        Node(E value){this.value = value;}
    }
    private Node root;
    public TaskA() {
    }
    private int compareTo(E obj1, E obj2) {
        return Integer.compare((int)obj1, (int)obj2);
    }
    @Override
    public boolean add(E e) {
        Node currNode = root;
        Node parent = currNode;
        int result = 0;
        while (currNode != null){
            parent = currNode;
            result = compareTo(currNode.value, e);
            if (result > 0){
                currNode = currNode.left;
            }
            else if (result < 0){
                currNode = currNode.right;
            }
            else{
                return false;
            }
        }
        Node newNode = new Node(e);
        if (parent == null)
            root = newNode;
        else if (result > 0)
            parent.left = newNode;
        else
            parent.right = newNode;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node currNode = root;
        int result = 0;
        Node parent = null;
        while (currNode != null && ((result = compareTo(currNode.value, (E)o)) != 0)) {
            parent = currNode;
            if (result > 0)
                currNode = currNode.left;
            else
                currNode = currNode.right;
        }
        if (currNode == null)
            return false;
        if (currNode.left == null && currNode.right == null) {
            if (currNode != root) {
                if (parent.right == currNode)
                    parent.right = null;
                else
                    parent.left = null;
            } else
                root = null;
        }
        else if (currNode.left != null && currNode.right != null){
            Node minimum = currNode.right;
            while (minimum.left != null) {
                minimum = minimum.left;
            }
            remove(minimum.value);
            currNode.value = minimum.value;
        }
        else{
            Node child  = (currNode.left != null)? currNode.left: currNode.right;
            if (currNode != root){
                if (currNode == parent.left)
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
            res = res  + concatResString(node.left, res) + ", " + node.value.toString()  + concatResString(node.right, res);
        }
        return res;
    }

    @Override
    public String toString() {
        String result = "";
        if (root == null)
            return "[" + result + "]";
        return "[" + concatResString(root, result).substring(2) + "]";
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
