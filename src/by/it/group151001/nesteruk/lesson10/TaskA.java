package by.it.group151001.nesteruk.lesson10;
import java.util.*;

public class TaskA<E>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы

    private class Node {
        E data;
        Node left;
        Node right;

        Node(E data) {
            this.data = data;
        }
    }

    private Node root = null;
    public TaskA() {
    }
    private int compare(E obj1, E obj2) {
        return Integer.compare((int)obj1, (int)obj2);
    }
    @Override
    public boolean add(E e) {
        Node cur = root;
        Node parent = cur;
        int result = 0;
        while (cur != null){
            parent = cur;
            result = compare(cur.data, e);
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
        else if (result > 0)
            parent.left = new_node;
        else
            parent.right = new_node;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node cur = root;
        int result = 0;
        Node parent = null;
        while (cur != null && ((result = compare(cur.data, (E)o)) != 0)) {
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
            remove(min.data);
            cur.data = min.data;
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
        return true;
    }

    private String makeString(Node node, String res){
        if (node != null){
            res = res  + makeString(node.left, res) + ", " + node.data.toString()  + makeString(node.right, res);
        }
        return res;
    }
    @Override
    public String toString() {
        String result = "";
        if (root == null)
            return "[" + result + "]";
        return "[" + makeString(root, result).substring(2) + "]";
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
