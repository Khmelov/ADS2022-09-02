package by.it.group151002.ravodin.lesson10;

import java.util.*;

public class TaskA<E extends Comparable<E>>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы

    private class Node{
        E data;
        Node left;
        Node right;

        Node(E Data) {
            data = Data;
        }
    }

    private int compareTo(E o1, E o2) {
        return o1.compareTo(o2);
    }

    private Node first;

    @Override
    public boolean add(E newData) {
        Node currentNode = first;
        Node par = currentNode;
        int result = 0;
        while (currentNode != null){
            par = currentNode;
            result = compareTo(currentNode.data, newData);
            if (result > 0){
                currentNode = currentNode.left;
            }
            else if (result < 0){
                currentNode = currentNode.right;
            }
            else{
                return false;
            }
        }
        Node node = new Node(newData);
        if (par == null)
            first = node;
        else if (result > 0)
            par.left = node;
        else
            par.right = node;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node currNode = first;
        int result = 0;
        Node parent = null;
        while (currNode != null && ((result = compareTo(currNode.data, (E)o)) != 0)) {
            parent = currNode;
            if (result > 0)
                currNode = currNode.left;
            else
                currNode = currNode.right;
        }
        if (currNode == null)
            return false;
        if (currNode.left == null && currNode.right == null) {
            if (currNode != first) {
                if (parent.right == currNode)
                    parent.right = null;
                else
                    parent.left = null;
            } else
                first = null;
        }
        else if (currNode.left != null && currNode.right != null){
            Node minimum = currNode.right;
            while (minimum.left != null) {
                minimum = minimum.left;
            }
            remove(minimum.data);
            currNode.data = minimum.data;
        }
        else{
            Node child  = (currNode.left != null)? currNode.left: currNode.right;
            if (currNode != first){
                if (currNode == parent.left)
                    parent.left = child;
                else
                    parent.right = child;
            }
            else
                first = child;
        }
        return true;
    }

    private String concatResString(Node node, String res){
        if (node != null){
            res += concatResString(node.left, res) + ", " + node.data.toString()
                    + concatResString(node.right, res);
        }
        return res;
    }

    @Override
    public String toString() {
        String str = "";
        if (first == null)
            return "[" + str + "]";
        return "[" + concatResString(first, str).substring(2) + "]";
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
