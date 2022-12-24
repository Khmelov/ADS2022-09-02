package by.it.group151001.danko.lesson10;

import java.util.*;

public class TaskC<E>  implements NavigableSet<E> {

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
    public TaskC() {
    }
    private int compareTo(E obj1, E obj2) {
        return Integer.compare((int)obj1, (int)obj2);
    }
    @Override
     public boolean add(E e) {
        Node current = root;
        Node parrent = null;
        int result = 0;
        while (current != null){
            parrent = current;
            result = compareTo(current.value, e);
            if (result > 0){
                current = current.left;
            }
            else if (result < 0){
                current = current.right;
            }
            else{
                return false;
            }
        }
        Node additional = new Node(e);
        if (parrent == null)
            root = additional;
        else if (result > 0) {
            parrent.left = additional;
        }
        else {
            parrent.right = additional;
        }
        additional.parent = parrent;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node current = root;
        int temp = 0;
        Node parrent = null;
        while (current != null && ((temp = compareTo(current.value, (E)o)) != 0)) {
            parrent = current;
            if (temp > 0)
                current = current.left;
            else
                current = current.right;
        }
        if (current == null)
            return false;
        if (current.left == null && current.right == null) {
            if (current != root) {
                if (parrent.right == current)
                    parrent.right = null;
                else
                    parrent.left = null;
            } else
                root = null;
        }
        else if (current.left != null && current.right != null){
            Node checked = current.right;
            while (checked.left != null) {
                checked = checked.left;
            }
            remove(checked.value);
            size++;
            current.value = checked.value;
        }
        else{
            Node child  = (current.left != null)? current.left: current.right;
            if (current != root){
                if (current == parrent.left){
                    parrent.left = child;
                    child.parent = parrent;
                }
                else {
                    parrent.right = child;
                    child.parent = parrent;
                }
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
            Node current = root;
            while (current.left != null)
                current = current.left;
            return current.value;
        }
    }

    @Override
    public E last() {
        if (root == null)
            return null;
        else{
            Node current = root;
            while (current.right != null)
                current = current.right;
            return current.value;
        }
    }

    @Override
    public E lower(E e) {
        E value = null;
        for (E temp: this){
            if (compareTo(temp, e) >= 0)
                return value;
            value = temp;
        }
        return null;
    }

    @Override
    public E floor(E e) {
        E value = null;
        for (E temp: this){
            if (compareTo(temp, e) > 0)
                return value;
            value = temp;
        }
        if (value != null && compareTo(value, e) == 0)
            return value;
        return null;
    }

    @Override
    public E ceiling(E e) {
        for (E temp: this){
            if (compareTo(temp, e) >= 0)
                return temp;
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
        if (root == null)
            return null;
        E value = first();
        remove(value);
        return value;
    }

    @Override
    public E pollLast() {
        if (root == null)
            return null;
        E value = last();
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
