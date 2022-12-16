package by.it.group151002.kragel.lesson10;

import java.util.*;

public class TaskA<E>  implements NavigableSet<E> {

    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet

    //Обязательные к реализации методы и конструкторы
    private static final boolean RED   = false;
    private static final boolean BLACK = true;
    private class Node{
        E value;
        Node left, right, parent;
        boolean color;
        Node(E e){
            value = e;
        }
    }
    private class NullNode extends Node{
        NullNode(){
            super(null);
            color = BLACK;
        }
    }
    private Node root;

    public TaskA() {
    }
    private void replaceParents(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else if (parent.right == oldChild) {
            parent.right = newChild;
        }
        if (newChild != null) {
            newChild.parent = parent;
        }
    }
    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.right = node;
        node.parent = leftChild;
        replaceParents(parent, node, leftChild);
    }
    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.left = node;
        node.parent = rightChild;
        replaceParents(parent, node, rightChild);
    }
    private void fixRBPropAfterAdd(Node node) {
        Node parent = node.parent;
        if (parent == null) {
            node.color = BLACK;
            return;
        }
        if (parent.color == BLACK) {
            return;
        }
        Node grandparent = parent.parent;
        Node uncle = grandparent.left == parent ? grandparent.right : (grandparent.right == parent ? grandparent.left  : null);
        if (uncle != null && uncle.color == RED) {
            uncle.color = BLACK;
            parent.color = BLACK;
            grandparent.color = RED;
            fixRBPropAfterAdd(grandparent);
        }
        else if (grandparent.right == parent) {
            if (node == parent.left) {
                rotateRight(parent);
                parent = node;
            }
            rotateLeft(grandparent);
            parent.color = BLACK;
            grandparent.color = RED;

        }
        else {
            if (node == parent.right) {
                rotateLeft(parent);
                parent = node;
            }
            rotateRight(grandparent);
            parent.color = BLACK;
            grandparent.color = RED;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        Node node = root;
        Node parent = null;
        Comparable<? super E> cmp1;
        int res = 0;
        while (node != null) {
            parent = node;
            cmp1 = (Comparable<? super E>) e;
            res = cmp1.compareTo(node.value);
            if (res < 0) {
                node = node.left;
            } else if (res > 0) {
                node = node.right;
            } else {
                return false;
            }
        }
        Node newNode = new Node(e);
        newNode.color = RED;
        if (parent == null) {
            root = newNode;
        } else if (res < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;
        fixRBPropAfterAdd(newNode);
        return true;
    }

    private Node deleteNodeZeroOrOneChild(Node node) {
        if (node.left != null) {
            replaceParents(node.parent, node, node.left);
            return node.left;
        }
        else if (node.right != null) {
            replaceParents(node.parent, node, node.right);
            return node.right;
        }
        else {
            Node newChild = node.color == BLACK ? new NullNode() : null;
            replaceParents(node.parent, node, newChild);
            return newChild;
        }
    }

    private Node findLeft(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    private Node getSibling(Node node) {
        Node parent = node.parent;
        if (node == parent.left) {
            return parent.right;
        } else {
            return parent.left;
        }
    }
    private boolean isBlack(Node node) {
        return node == null || node.color == BLACK;
    }
    private void fixRBPropAfterRemove(Node node) {
        if (node == root) {
            node.color = BLACK;
            return;
        }
        Node sibling = getSibling(node);
        if (sibling.color == RED) {
            sibling.color = BLACK;
            node.parent.color = RED;
            if (node == node.parent.left) {
                rotateLeft(node.parent);
            } else {
                rotateRight(node.parent);
            }
            sibling = getSibling(node);
        }
        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = RED;
            if (node.parent.color == RED) {
                node.parent.color = BLACK;
            }
            else {
                fixRBPropAfterRemove(node.parent);
            }
        }
        else {
            boolean isNodeLeftChild = (node == node.parent.left);
            if (isNodeLeftChild && isBlack(sibling.right)) {
                sibling.left.color = BLACK;
                sibling.color = RED;
                rotateRight(sibling);
                sibling = node.parent.right;
            } else if (!isNodeLeftChild && isBlack(sibling.left)) {
                sibling.right.color = BLACK;
                sibling.color = RED;
                rotateLeft(sibling);
                sibling = node.parent.left;
            }
            sibling.color = node.parent.color;
            node.parent.color = BLACK;
            if (isNodeLeftChild) {
                sibling.right.color = BLACK;
                rotateLeft(node.parent);
            } else {
                sibling.left.color = BLACK;
                rotateRight(node.parent);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object o) {
        Node node = root;
        Comparable<? super E> cmp1 = (Comparable<? super E>) o;
        int res;
        while (node != null && (res = cmp1.compareTo(node.value)) != 0) {
            if (res < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (node == null) {
            return false;
        }
        Node movedUpNode;
        boolean deletedColor;
        if (node.left == null || node.right == null) {
            movedUpNode = deleteNodeZeroOrOneChild(node);
            deletedColor = node.color;
        }
        else {
            Node successor = findLeft(node.right);
            node.value = successor.value;
            movedUpNode = deleteNodeZeroOrOneChild(successor);
            deletedColor = successor.color;
        }
        if (deletedColor == BLACK) {
            fixRBPropAfterRemove(movedUpNode);
            if (movedUpNode instanceof NullNode) {
                replaceParents(movedUpNode.parent, movedUpNode, null);
            }
        }
        return true;
    }
    private String buildString(Node node, String str){
        if (node != null){
            str = str  + buildString(node.left, str) + ", " + node.value.toString()  + buildString(node.right, str);
        }
        return str;
    }
    @Override
    public String toString() {
        String res = "";
        return "[" + (root == null ? res : buildString(root, res).substring(2)) + "]";
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
