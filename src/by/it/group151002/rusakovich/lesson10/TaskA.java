package by.it.group151002.rusakovich.lesson10;

import java.util.*;

public class TaskA<E extends Comparable<E>>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.
    public class Node{
        Node left;
        Node right;
        E value;
        int height;
        public int compareTo(Node c){
            return this.value.compareTo(c.value);
        }
        Node(E val){
            this.value = val;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    }
    private Node r = null;
    private boolean nullInTree = false;
    private int nodeAmount = 0;
    //Обязательные к реализации методы и конструкторы
    public TaskA() {

    }
    public TaskA(E value){
        r = new Node(value);
    }

    private Node rightRotate(Node t){
        Node left = t.left;
        t.left = left.right;
        left.right = t;
        t.height = Math.max(getHeight(t.left), getHeight(t.right)) + 1;
        left.height = Math.max(getHeight(left.left), t.height) + 1;
        return left;
    }

    private Node leftRotate(Node t){
        Node right = t.right;
        t.right = right.left;
        right.left = t;
        t.height = Math.max(getHeight(t.left), getHeight(t.right)) + 1;
        right.height = Math.max(getHeight(right.right), t.height) + 1;
        return right;
    }
    private Node leftRightRotate(Node t){
        t.left = leftRotate(t.left);
        return rightRotate(t);
    }
    private Node rightLeftRotate(Node t){
        t.right = rightRotate(t.right);
        return leftRotate(t);
    }
    private int getHeight(Node n){return n == null ? -1 : n.height;}
    private boolean nullWork(){
        if (nullInTree)
            return false;
        else {
            nullInTree = true;
            nodeAmount++;
            return true;
        }
    }
    private Node insert(Node n, Node in){
        if(n==null)
            return in;
        if(n.compareTo(in) > 0){
            n.left = insert(n.left, in);
            if(getHeight(n.left) - getHeight(n.right) == 2){
                if(in.compareTo(n.left) < 0)
                    n = rightRotate(n);
                else
                    n = leftRightRotate(n);
            }
        } else {
            if(n.compareTo(in) < 0){
                n.right = insert(n.right, in);
                if(getHeight(n.right) - getHeight(n.left) == 2){
                    if(in.compareTo(n.right) > 0)
                        n = leftRotate(n);
                    else
                        n = rightLeftRotate(n);
                }
            }
        }
        n.height = Math.max(getHeight(n.right), getHeight(n.left)) + 1;
        return n;
    }
    @Override
    public boolean add(E e) {
        if(e == null) {
            return nullWork();
        }
        if(containsNode(r, e))
            return false;
        Node n = new Node(e);
        nodeAmount++;
        r = insert(r, n);
        return true;
    }
    private int bFactor(Node n){return getHeight(n.right) - getHeight(n.left);}
    private Node balance(Node n)
    {
        n.height = Math.max(getHeight(n.right), getHeight(n.left)) + 1;
        if(bFactor(n) == 2) {
            if (bFactor(n.right) < 0)
                n.right = rightRotate(n.right);
            return leftRotate(n);
        }
        if(bFactor(n) == -2) {
            if (bFactor(n.left) > 0)
                n.left = leftRotate(n.left);
            return rightRotate(n);
        }
        return n;
    }

    private Node findMin(Node n){
        return n.left != null ? findMin(n.left):n;
    }
    private Node removeMin(Node n){
        if(n.left == null)
            return n.right;
        n.left = removeMin(n.left);
        return balance(n);
    }
    private Node delete(Node n, Object o) {
        if(n==null)
            return null;
        if(n.value.compareTo((E)o) > 0)
            n.left = delete(n.left, o);
        else if(n.value.compareTo((E)o) < 0)
            n.right = delete(n.right, o);
        else{
            Node q = n.left;
            Node right = n.right;
            if(right == null) return q;
            Node min = findMin(right);
            min.right = removeMin(right);
            min.left = q;
            return balance(min);
        }
        return balance(n);
    }

    @Override
    public boolean remove(Object o) {
        if(o == null){
            if(nullInTree){
                nodeAmount--;
                nullInTree = false;
                return true;
            } else {
                return false;
            }
        }
        if(!containsNode(r, o))
            return false;
        r = delete(r, o);
        nodeAmount--;
        return true;
    }

    void traverse(StringBuilder str, Node n){
        if(n==null)
            return;
        traverse(str, n.left);
        str.append(n.value.toString()).append(", ");
        traverse(str, n.right);
    }

    @Override
    public String toString() {
        if(r == null)
            return "[]";
        StringBuilder str = new StringBuilder();
        str.append('[');
        if(nullInTree)
            str.append("null, ");
        traverse(str, r);
        str.delete(str.length()-2, str.length());
        str.append(']');
        return str.toString();
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


    private boolean containsNode(Node n, Object o){
        if(n == null)
            return false;
        if(n.value.compareTo((E)o) > 0)
            return containsNode(n.left, o);
        else
        if(n.value.compareTo((E)o) < 0)
            return containsNode(n.right, o);
        else
            return true;
    }
    @Override
    public boolean contains(Object o) {
        if(o == null)
            return nullInTree;
        return containsNode(r, o);
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
        if(nodeAmount == 0)
            return true;
        return false;
    }

    @Override
    public int size() {
        return nodeAmount;
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
