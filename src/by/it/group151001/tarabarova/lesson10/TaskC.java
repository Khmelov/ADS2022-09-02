package by.it.group151001.tarabarova.lesson10;

import java.util.*;

public class TaskC<E>  implements NavigableSet<E> {

    private class Node{
        Node left = null;
        Node right = null;
        Node parent;
        E value;
        public Node(E value, Node parent){
            this.value = value;
            this.parent = parent;
        }
        public int compareTo(Object o){
            return Integer.compare((int)value ,(int)o);
        }
    }

    private Node root;

    private List<E> nodesList;

    private int size = 0;

    public TaskC() {
        nodesList = new ArrayList<E>();
        root = new Node(null, null);
    }

    private Node findNode(E o){
        Node node = root;
        while(node != null){
            if(node.compareTo(o) > 0)
                node = node.left;
            else if(node.compareTo(o) < 0)
                node = node.right;
            else
                return node;
        }
        return null;
    }
    @Override
    public boolean add(E e) {
        if(size == 0){
            root.value = e;
            root.parent = root;
            size++;
            return true;
        }
        Node node = root;
        while(true){
            if(node.compareTo(e) > 0){
                if(node.left == null){
                    node.left = new Node(e, node);
                    size++;
                    return true;
                }else
                    node = node.left;
            }else if(node.compareTo(e) < 0){
                if(node.right == null){
                    node.right = new Node(e, node);
                    size++;
                    return true;
                }else
                    node = node.right;
            }
            else
                return false;
        }
    }

    private void delete(Node node) {
        if(node.right == null && node.left == null){
            if(node.parent.left == node)
                node.parent.left = null;
            else
                node.parent.right = null;
        }else if(node.right == null){
            node.left.parent = node.parent;
            if(node.parent.left == node){
                node.parent.left = node.left;
            }else{
                node.parent.right = node.left;
            }
            if(node == root){
                root = node.left;
            }
        }
        else if(node.left == null){
            node.right.parent = node.parent;
            if(node.parent.left == node){
                node.parent.left = node.right;
            }else{
                node.parent.right = node.right;
            }
            if(node == root){
                root = node.right;
            }
        }
        else{
            Node temp = node.right;
            while(temp.left != null){
                temp = temp.left;
            }
            node.value = temp.value;
            delete(temp);
        }
    }

    @Override
    public boolean remove(Object o) {
        Node node = findNode((E)o);
        if(node != null){
            delete(node);
            size--;
        }
        return true;
    }

    private void treeToList(Node node){
        if(node != null){
            treeToList(node.left);
            nodesList.add(node.value);
            treeToList(node.right);
        }

    }

    @Override
    public String toString() {
        if(size == 0){
            return "[]";
        }
        nodesList.clear();
        treeToList(root);
        String outputText = "[";
        for(E value : nodesList){
            outputText = outputText + value + ", ";
        }
        outputText = outputText.substring(0, outputText.length() - 2) + ']';
        return outputText;
    }
    @Override
    public boolean contains(Object o) {
        return findNode((E)o) != null;
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

        SetIterator() {
            node = null;
            next = null;
        }

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
        public T next() {
            if (next == null)
                return null;
            node = next;
            return (T) next.value;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new SetIterator<>();
    }

    @Override
    public void clear() {
        root = new Node(null, null);
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return root.value == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E first() {
        Node node = root;
        while(node.left != null){
            node = node.left;
        }
        return node.value;
    }

    @Override
    public E last() {
        Node node = root;
        while(node.right != null){
            node = node.right;
        }
        return node.value;
    }

    @Override
    public E lower(E e) {
        nodesList.clear();
        treeToList(root);
        for(int i = nodesList.size() - 1; i >= 0; i--){
            if((int) nodesList.get(i) < (int)e){
                return nodesList.get(i);
            }
        }
        return null;
    }

    @Override
    public E floor(E e) {
        nodesList.clear();
        treeToList(root);
        for(int i = nodesList.size() - 1; i >= 0; i--){
            if((int) nodesList.get(i) <= (int)e){
                return nodesList.get(i);
            }
        }
        return null;
    }

    @Override
    public E ceiling(E e) {
        nodesList.clear();
        treeToList(root);
        for(E value : nodesList){
            if((int)value >= (int)e){
                return value;
            }
        }
        return null;
    }

    @Override
    public E higher(E e) {
        nodesList.clear();
        treeToList(root);
        for(E value : nodesList){
            if((int)value > (int)e){
                return value;
            }
        }
        return null;
    }

    @Override
    public E pollFirst() {
        Node node = root;
        while(node.left != null){
            node = node.left;
        }
        E result = node.value;
        delete(node);
        size--;
        return result;
    }

    @Override
    public E pollLast() {
        Node node = root;
        while(node.right != null){
            node = node.right;
        }
        E result = node.value;
        delete(node);
        size--;
        return result;
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