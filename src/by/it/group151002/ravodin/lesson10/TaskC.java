package by.it.group151002.ravodin.lesson10;

import java.util.*;

public class TaskC<E extends Comparable<E>>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы
    private class Node{
        E data;
        Node left;
        Node right;
        Node parent;

        Node(E value){this.data = value;}
    }
    private class SetIterator<T> implements Iterator<T> {

        private Node findLeft(Node node) {
            if (node == null)
                return null;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        private Node currentNode, nextNode;
        SetIterator(){
            currentNode = null;
            nextNode = null;
        }
        @Override
        public boolean hasNext() {
            if (currentNode == null) {
                nextNode = findLeft(first);
            } else if (currentNode.right != null) {
                nextNode = findLeft(currentNode.right);
            } else {
                nextNode = currentNode;
                while (nextNode.parent != null && nextNode == nextNode.parent.right) {
                    nextNode = nextNode.parent;
                }
                nextNode = nextNode.parent;
                if (nextNode == null)
                    return false;
            }
            return true;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (nextNode == null)
                return null;
            currentNode = nextNode;
            return (T) nextNode.data;
        }
    }
    private Node first;
    private int count = 0;
    public TaskC() {
    }
    private int compareTo(E o1, E o2) {
        return o1.compareTo(o2);
    }
    @Override
    public boolean add(E e) {
        Node parent = null;
        Node currNode = first;

        int result = 0;
        while (currNode != null){
            parent = currNode;
            result = compareTo(currNode.data, e);
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
            first = newNode;
        else if (result > 0) {
            parent.left = newNode;
        }
        else {
            parent.right = newNode;
        }
        newNode.parent = parent;
        count++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node currentNode = first;
    int res = 0;
    Node parentNode = null;
        while (currentNode != null && ((res = compareTo(currentNode.data, (E)o)) != 0)) {
        parentNode = currentNode;
        if (res > 0)
            currentNode = currentNode.left;
        else
            currentNode = currentNode.right;
    }
        if (currentNode == null)
            return false;
        if (currentNode.left == null && currentNode.right == null) {
        if (currentNode != first) {
            if (parentNode.right == currentNode)
                parentNode.right = null;
            else
                parentNode.left = null;
        } else
            first = null;
    }
        else if (currentNode.left != null && currentNode.right != null){
        Node minNode = currentNode.right;
        while (minNode.left != null) {
            minNode = minNode.left;
        }
        remove(minNode.data);
        count++;
        currentNode.data = minNode.data;
    }
        else{
        Node childNode  = (currentNode.left != null)? currentNode.left: currentNode.right;
        if (currentNode != first){
            if (currentNode == parentNode.left){
                parentNode.left = childNode;
                childNode.parent = parentNode;
            }
            else {
                parentNode.right = childNode;
                childNode.parent = parentNode;
            }
        }
        else
            first = childNode;
    }
    count--;
        return true;
}
    private String concatResString(Node node, String res){
        if (node != null){
            res += concatResString(node.left, res) + ", " + node.data.toString()  +
                    concatResString(node.right, res);
        }
        return res;
    }

    @Override
    public String toString() {
        String res = "";
        if (first == null)
            return "[" + res + "]";
        return "[" + concatResString(first, res).substring(2) + "]";
    }
    @Override
    public boolean contains(Object o) {
        Node currentNode = first;
        int result = 0;
        while (currentNode != null && ((result = compareTo(currentNode.data, (E)o)) != 0))
            if (result > 0)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        return currentNode != null;
    }

    @Override
    public Iterator<E> iterator() {
        return new SetIterator<>();
    }

    @Override
    public void clear() {
        first = null;
        count = 0;
    }

    @Override
    public E lower(E e) {
        E value = null;
        for (E tmp: this){
            if (compareTo(tmp, e) >= 0)
                return value;
            value = tmp;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public E first() {
        if (first == null)
            return null;
        else{
            Node currentNode = first;
            while (currentNode.left != null)
                currentNode = currentNode.left;
            return currentNode.data;
        }
    }

    @Override
    public E last() {
        if (first == null)
            return null;
        else{
            Node currentNode = first;
            while (currentNode.right != null)
                currentNode = currentNode.right;
            return currentNode.data;
        }
    }

    @Override
    public E floor(E e) {
        E val = null;
        for (E tmp: this){
            if (compareTo(tmp, e) > 0)
                return val;
            val = tmp;
        }
        if (val != null && compareTo(val, e) == 0)
            return val;
        return null;
    }

    @Override
    public E pollLast() {
        if (first == null)
            return null;
        E val = last();
        remove(val);
        return val;
    }

    @Override
    public E ceiling(E e) {
        for (E tmp: this){
            if (compareTo(tmp, e) >= 0)
                return tmp;
        }
        return null;
    }

    @Override
    public E higher(E e) {
        for (E temp: this){
            if (compareTo(temp, e) > 0)
                return temp;
        }
        return null;
    }

    @Override
    public E pollFirst() {
        if (first == null)
            return null;
        E value = first();
        remove(value);
        return value;
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
