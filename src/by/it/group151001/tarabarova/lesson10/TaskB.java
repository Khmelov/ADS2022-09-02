package by.it.group151001.tarabarova.lesson10;

import java.util.*;

public class TaskB<E>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы
    private class Node{
        E value;
        Node left;
        Node right;
        Node parent;

        Node(E value){this.value = value;}
    }
    private class SetIterator<T> implements Iterator<T> {
        private Node node, next;
        private Node findLeft(Node node) {
            if (node == null)
                return null;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        SetIterator(){
            node = null;
            next = null;
        }
        @Override
        public boolean hasNext() {
            if (node == null) {
                next = findLeft(root);
            } else if (node.right != null) {
                next = findLeft(node.right);
            } else {
                next = node;
                while (next.parent != null && next == next.parent.right) {
                    next = next.parent;
                }
                next = next.parent;
                if (next == null)
                    return false;
            }
            return true;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (next == null)
                return null;
            node = next;
            return (T) next.value;
        }
    }
    private Node root;
    private int size = 0;
    public TaskB() {
    }
    private int compareTo(E obj1, E obj2) {
        return Integer.compare((int)obj1, (int)obj2);
    }
    @Override
    public boolean add(E e) {
        Node cur = root;
        Node parent = cur;
        int result = 0;
        while (cur != null){
            parent = cur;
            result = compareTo(cur.value, e);
            if (result > 0){
                cur = cur.left;
            }
            else if (result < 0){
                cur = cur.right;
            }
            else{
                return false;
            }
        }
        Node new_node = new Node(e);
        if (parent == null)
            root = new_node;
        else if (result > 0) {
            parent.left = new_node;
            parent.left.parent = parent;
        }
        else {
            parent.right = new_node;
            parent.right.parent = parent;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node cur = root;
        int result = 0;
        Node parent = null;
        while (cur != null && ((result = compareTo(cur.value, (E)o)) != 0)) {
            parent = cur;
            if (result > 0)
                cur = cur.left;
            else
                cur = cur.right;
        }
        if (cur == null)
            return false;
        if (cur.left == null && cur.right == null) {
            if (cur != root) {
                if (parent.right == cur)
                    parent.right = null;
                else
                    parent.left = null;
            } else
                root = null;
        }
        else if (cur.left != null && cur.right != null){
            Node min = cur.right;
            while (min.left != null) {
                min = min.left;
            }
            remove(min.value);
            size++;
            cur.value = min.value;
        }
        else{
            Node child  = (cur.left != null)? cur.left: cur.right;
            if (cur != root){
                if (cur == parent.left)
                    parent.left = child;
                else
                    parent.right = child;
            }
            else
                root = child;
        }
        size--;
        return true;
    }
    private String formString(Node node, String res){
        if (node != null){
            res = res  + formString(node.left, res) + ", " + node.value.toString()  + formString(node.right, res);
        }
        return res;
    }

    @Override
    public String toString() {
        String result = "";
        if (root == null)
            return "[" + result + "]";
        return "[" + formString(root, result).substring(2) + "]";
    }
    @Override
    public boolean contains(Object o) {
        Node cur = root;
        int result = 0;
        while (cur != null && ((result = compareTo(cur.value, (E)o)) != 0))
            if (result > 0)
                cur = cur.left;
            else
                cur = cur.right;
        return cur != null;
    }

    @Override
    public Iterator<E> iterator() {
        return new SetIterator<>();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E first() {
        if (root == null)
            return null;
        else{
            Node cur = root;
            while (cur.left != null)
                cur = cur.left;
            return cur.value;
        }
    }

    @Override
    public E last() {
        if (root == null)
            return null;
        else{
            Node cur = root;
            while (cur.right != null)
                cur = cur.right;
            return cur.value;
        }
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


}
