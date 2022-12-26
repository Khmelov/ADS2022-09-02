package by.it.group151004.danilov.lesson10;

import java.util.*;

class Node<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;
    private Color color;
    private Node<T> parent;

    public Node(T data, Color color, Node<T> parent) {
        this.data = data;
        this.color = color;
        this.parent = parent;
    }

    public Node(T data, Color color) {
        this(data, color, null);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }
}

enum Color {
    RED, BLACK
}

class NilNode extends Node {
    public NilNode() {
        super(null, Color.BLACK);
    }
}

public class Tree<E> implements NavigableSet<E> {
    private Node<E> root;
    private int size;

    public Tree() {
        size = 0;
    }



    @Override
    public boolean add(E e) {
        Node<E> temp = root;
        Node<E> parent = null;

        while (temp != null) {
            parent = temp;
            if (e.equals(temp.getData())) {
                return false;
            } else if (((Comparable<E>)e).compareTo(temp.getData()) < 0) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }

        Node<E> newNode = new Node<>(e, Color.RED);
        if (parent == null) {
            root = newNode;
        } else if (((Comparable<E>)e).compareTo(parent.getData()) < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

        newNode.setParent(parent);
        size++;
        fixAddition(newNode);
        return true;
    }


    @Override
    public boolean remove(Object o) {
        Node<E> node = root;
        while (node != null && !node.getData().equals(o)) {
            if (((Comparable<E>) o).compareTo(node.getData()) < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }

        if (node == null) return false;

        Node<E> movedUpNode;
        Color removedNodeColor;

        if (node.getLeft() == null || node.getRight() == null) {
            movedUpNode = removeNodeWithZeroOrOneChild(node);
            removedNodeColor = node.getColor();
        } else {
            Node<E> inOrderSuccessor = findMinimum(node.getRight());
            node.setData(inOrderSuccessor.getData());
            movedUpNode = removeNodeWithZeroOrOneChild(inOrderSuccessor);
            removedNodeColor = inOrderSuccessor.getColor();
        }

        if (removedNodeColor == Color.BLACK) {
            fixRemoving(movedUpNode);
            if (movedUpNode instanceof NilNode) {
                replaceParentsChild(movedUpNode.getParent(), movedUpNode, null);
            }
        }

        size--;
        return true;
    }

    @Override
    public String toString() {
        List<E> traversal = new LinkedList<>();
        getInOrderTraversal(root, traversal);
        return traversal.toString();
    }

    @Override
    public boolean contains(Object o) {
        Node<E> temp = root;
        while (temp != null) {
            if (o.equals(temp.getData())) {
                return true;
            } else if (((Comparable<E>)o).compareTo(temp.getData()) < 0) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }

        return false;
    }

    private void fixAddition(Node<E> node) {
        Node<E> parent = node.getParent();
        if (parent == null || parent.getColor() == Color.BLACK) {
            return;
        }

        Node<E> grandparent = parent.getParent();
        if (grandparent == null) {
            parent.setColor(Color.BLACK);
            return;
        }
        Node<E> uncle = getUncle(parent);
        if (uncle != null && uncle.getColor() == Color.RED) {
            parent.setColor(Color.BLACK);
            grandparent.setColor(Color.RED);
            uncle.setColor(Color.BLACK);
            fixAddition(grandparent);
        } else if (parent == grandparent.getLeft()) {
            if (node == parent.getRight()) {
                rotateLeft(parent);
                parent = node;
            }
            rotateRight(grandparent);
            parent.setColor(Color.BLACK);
            grandparent.setColor(Color.RED);
        } else {
            if (node == parent.getLeft()) {
                rotateRight(parent);
                parent = node;
            }
            rotateLeft(grandparent);
            parent.setColor(Color.BLACK);
            grandparent.setColor(Color.RED);
        }
    }

    private Node<E> getUncle(Node<E> parent) {
        Node<E> grandparent = parent.getParent();
        if (grandparent.getLeft() == parent) {
            return grandparent.getRight();
        } else if (grandparent.getRight() == parent) {
            return grandparent.getLeft();
        }

        return null;
    }

    private void rotateRight(Node<E> node) {
        Node<E> parent = node.getParent();
        Node<E> left = node.getLeft();

        node.setLeft(left.getRight());
        if (left.getRight() != null) {
            left.getRight().setParent(node);
        }

        left.setRight(node);
        node.setParent(left);

        replaceParentsChild(parent, node, left);
    }

    private void rotateLeft(Node<E> node) {
        Node<E> parent = node.getParent();
        Node<E> right = node.getRight();

        node.setRight(right.getLeft());
        if (right.getLeft() != null) {
            right.getLeft().setParent(node);
        }

        right.setLeft(node);
        node.setParent(right);

        replaceParentsChild(parent, node, right);
    }

    private void replaceParentsChild(Node<E> parent, Node<E> oldChild, Node<E> newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.getLeft() == oldChild) {
            parent.setLeft(newChild);
        } else if (parent.getRight() == oldChild) {
            parent.setRight(newChild);
        }

        if (newChild != null) {
            newChild.setParent(parent);
        }
    }

    private void getInOrderTraversal(Node<E> root, List<E> traversal) {
        if (root == null) return;
        getInOrderTraversal(root.getLeft(), traversal);
        traversal.add(root.getData());
        getInOrderTraversal(root.getRight(), traversal);
    }


    private Node<E> removeNodeWithZeroOrOneChild(Node<E> node) {
        if (node.getLeft() != null) {
            replaceParentsChild(node.getParent(), node, node.getLeft());
            return node.getLeft();
        } else if (node.getRight() != null) {
            replaceParentsChild(node.getParent(), node, node.getRight());
            return node.getRight();
        } else {
            Node<E> newChild = node.getColor() == Color.BLACK ? new NilNode() : null;
            replaceParentsChild(node.getParent(), node, newChild);
            return newChild;
        }
    }

    private Node<E> findMinimum(Node<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private void fixRemoving(Node<E> node) {
        if (node == root) return;

        Node<E> sibling = getSibling(node);

        if (sibling.getColor() == Color.RED) {
            handleRedSibling(node, sibling);
            sibling = getSibling(node);
        }

        if (isBlack(sibling.getLeft()) && isBlack(sibling.getRight())) {
            sibling.setColor(Color.RED);
            if (node.getParent().getColor() == Color.RED) {
                node.getParent().setColor(Color.BLACK);
            } else {
                fixRemoving(node.getParent());
            }
        } else {
            handleBlackSibling(node, sibling);
        }
    }

    private void handleRedSibling(Node<E> node, Node<E> sibling) {
        sibling.setColor(Color.BLACK);
        node.getParent().setColor(Color.RED);

        if (node == node.getParent().getLeft()) {
            rotateLeft(node.getParent());
        } else {
            rotateRight(node.getParent());
        }
    }

    private void handleBlackSibling(Node<E> node, Node<E> sibling) {
        boolean nodeIsLeftChild = node == node.getParent().getLeft();
        if (nodeIsLeftChild && isBlack(sibling.getRight())) {
            sibling.getLeft().setColor(Color.BLACK);
            sibling.setColor(Color.RED);
            rotateRight(sibling);
            sibling = node.getParent().getRight();
        } else if (!nodeIsLeftChild && isBlack(sibling.getLeft())) {
            sibling.getRight().setColor(Color.BLACK);
            sibling.setColor(Color.RED);
            rotateLeft(sibling);
            sibling = node.getParent().getLeft();
        }

        sibling.setColor(node.getParent().getColor());
        node.getParent().setColor(Color.BLACK);
        if (nodeIsLeftChild) {
            sibling.getRight().setColor(Color.BLACK);
            rotateLeft(node.getParent());
        } else {
            sibling.getLeft().setColor(Color.BLACK);
            rotateRight(node.getParent());
        }
    }

    private Node<E> getSibling(Node<E> node) {
        Node<E> parent = node.getParent();
        if (node == parent.getLeft()) {
            return parent.getRight();
        } else if (node == parent.getRight()) {
            return parent.getLeft();
        }

        return null;
    }

    private boolean isBlack(Node<E> node) {
        return node == null || node.getColor() == Color.BLACK;
    }

    @Override
    public E lower(E e) {
        Node<E> temp = root;
        while (temp != null) {
            int cmp = ((Comparable<E>)e).compareTo(temp.getData());
            if (cmp > 0) {
                if (temp.getRight() != null)
                    temp = temp.getRight();
                else
                    return temp.getData();
            } else {
                if (temp.getLeft() != null) {
                    temp = temp.getLeft();
                } else {
                    Node<E> parent = temp.getParent();
                    Node<E> ch = temp;
                    while (parent != null && ch == parent.getLeft()) {
                        ch = parent;
                        parent = parent.getParent();
                    }
                    if (parent == null)
                        return null;
                    return parent.getData();
                }
            }
        }
        return null;
    }

    @Override
    public E floor(E e) {
        Node<E> temp = root;
        while (temp != null) {
            int cmp = ((Comparable<E>)e).compareTo(temp.getData());
            if (cmp > 0) {
                if (temp.getRight() != null)
                    temp = temp.getRight();
                else
                    return temp.getData();
            } else if (cmp < 0) {
                if (temp.getLeft() != null) {
                    temp = temp.getLeft();
                } else {
                    Node<E> parent = temp.getParent();
                    Node<E> ch = temp;
                    while (parent != null && ch == parent.getLeft()) {
                        ch = parent;
                        parent = parent.getParent();
                    }
                    if (parent == null)
                        return null;
                    return parent.getData();
                }
            } else
                return temp.getData();

        }
        return null;
    }

    @Override
    public E ceiling(E e) {
        Node<E> temp = root;
        while (temp != null) {
            int cmp = ((Comparable<E>)e).compareTo(temp.getData());
            if (cmp < 0) {
                if (temp.getLeft() != null) {
                    temp = temp.getLeft();
                } else {
                    return temp.getData();
                }
            } else if (cmp > 0) {
                if (temp.getRight() != null) {
                    temp = temp.getRight();
                } else {
                    Node<E> parent = temp.getParent();
                    Node<E> ch = temp;
                    while (parent != null && ch == parent.getRight()) {
                        ch = parent;
                        parent = parent.getParent();
                    }
                    if (parent == null)
                        return null;
                    return parent.getData();
                }
            } else {
                return temp.getData();
            }
        }
        return null;
    }

    @Override
    public E higher(E e) {
        Node<E> temp = root;
        while (temp != null) {
            int cmp = ((Comparable<E>)e).compareTo(temp.getData());
            if (cmp < 0) {
                if (temp.getLeft() != null)
                    temp = temp.getLeft();
                else
                    return temp.getData();
            } else {
                if (temp.getRight() != null) {
                    temp = temp.getRight();
                } else {
                    Node<E> parent = temp.getParent();
                    Node<E> ch = temp;
                    while (parent != null && ch == parent.getRight()) {
                        ch = parent;
                        parent = parent.getParent();
                    }
                    if (parent == null)
                        return null;
                    return parent.getData();
                }
            }
        }
        return null;
    }

    @Override
    public E pollFirst() {
        Node<E> firstNode = getFirstNode();
        if (firstNode == null)
            return null;
        E data = firstNode.getData();
        remove(firstNode.getData());
        return data;
    }

    @Override
    public E pollLast() {
        Node<E> lastNode = getLastNode();
        if (lastNode == null)
            return null;
        E data = lastNode.getData();
        remove(lastNode.getData());
        return data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        List<E> traversal = new LinkedList<>();
        getInOrderTraversal(root, traversal);
        return traversal.iterator();
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
    public void clear() {
        root = null;
        size = 0;
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
        Node<E> firstNode = getFirstNode();
        if (firstNode == null)
            return null;
        return firstNode.getData();
    }

    private Node<E> getLastNode() {
        Node<E> temp = root;
        while (temp != null) {
            if (temp.getRight() != null) {
                temp = temp.getRight();
            } else {
                return temp;
            }
        }

        return null;
    }

    private Node<E> getFirstNode() {
        Node<E> temp = root;
        while (temp != null) {
            if (temp.getLeft() != null) {
                temp = temp.getLeft();
            } else {
                return temp;
            }
        }

        return null;
    }

    @Override
    public E last() {
        Node<E> lastNode = getLastNode();
        if (lastNode == null)
            return null;
        return lastNode.getData();
    }
}