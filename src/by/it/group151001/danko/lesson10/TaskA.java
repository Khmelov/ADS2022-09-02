package by.it.group151001.danko.lesson10;

import java.util.*;

public class TaskA<E>  implements NavigableSet<E> {


    class Node{
        public E value;
        public Node right, left;

        Node(E value) {
            this.value = value;
            right = null;
            left = null;
        }
    }

    private int compareTo(E one, E two)
    {
        if(Integer.compare((int) one, (int) two) > 0) return 1;
        else if(Integer.compare((int) one, (int) two) < 0) return -1;
        else return 0;
    }

    private Node root;
    private int size;

    public TaskA() {
        size = 0;
        root = null;
    }

    @Override
    public boolean add(E e) {
        Node current = root;
        Node parent = current;
        int temp = 0;
        while(current != null)
        {
            parent = current;
            temp = compareTo(current.value, e);
            switch (temp)
            {
                case (1):
                    current = current.left;
                    break;

                case (-1):
                    current = current.right;
                    break;

                case(0):
                    return false;
            }
        }
        Node additional = new Node(e);
        if(parent == null)
            root = additional;
        else if (temp == 1) {
            parent.left = additional;
        }
        else
            parent.right = additional;
        return true;
    }

    public boolean remove(Object o) {
        Node current = root;
        int temp;
        Node parent = null;
        while(current != null && ((temp = compareTo(current.value, (E)o)) != 0))
        {
            parent = current;
            if(temp == 1)
                current = current.left;
            else
                current = current.right;
        }
        if(current == null) return false;
        if (current.left == null && current.right == null) {
            if (current != root) {
                if (parent.right == current)
                    parent.right = null;
                else
                    parent.left = null;
            } else
                root = null;
        }
        else if (current.left != null && current.right != null){
            Node min = current.right;
            while (min.left != null) {
                min = min.left;
            }
            remove(min.value);
            current.value = min.value;
        }
        else{
            Node child  = (current.left != null)? current.left: current.right;
            if (current != root){
                if (current == parent.left)
                    parent.left = child;
                else
                    parent.right = child;
            }
            else
                root = child;
        }
        return true;
    }
    private String formString(Node node, String resultString){
        if (node != null){
            resultString = resultString  + formString(node.left, resultString) + ", " + node.value.toString()  + formString(node.right, resultString);
        }
        return resultString;
    }

    @Override
    public String toString() {
        String result = "";
        if (root == null)
            return "[" + result + "]";
        return "[" + formString(root, result).substring(2) + "]";
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
