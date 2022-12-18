package by.it.group151003.mytnik.lesson10;

import java.util.*;

public class RBTree<E extends Comparable<E>> implements NavigableSet<E> {
    private Node<E> root;
    private int size;

    public RBTree() {
        size = 0;
    }

    @Override
    public boolean add(E e) {
        Node<E> temp = root;
        Node<E> parent = null;

        while (temp != null) {
            parent = temp;

            if (e.equals(temp.data)) {
                return false;
            } else {
                temp = e.compareTo(temp.data) < 0 ? temp.left : temp.right;
            }
        }

        Node<E> newNode = new Node<>(e, Color.RED);

        if (parent == null) {
            root = newNode;
        } else if (e.compareTo(parent.data) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        newNode.parent = parent;
        size++;
        fixAddition(newNode);

        return true;
    }


    @Override
    public boolean remove(Object o) {
        Node<E> node = root;

        while (node != null && !node.data.equals(o)) node = ((Comparable<E>) o).compareTo(node.data) < 0 ? node.left : node.right;

        if (node == null) return false;

        Node<E> movedUpNode;
        Color removedNodeColor;

        if (node.left == null || node.right == null) {
            movedUpNode = removeNodeWithZeroOrOneChild(node);
            removedNodeColor = node.color;
        } else {
            Node<E> inOrderSuccessor = findMinimum(node.right);
            node.data = inOrderSuccessor.data;
            movedUpNode = removeNodeWithZeroOrOneChild(inOrderSuccessor);
            removedNodeColor = inOrderSuccessor.color;
        }

        if (removedNodeColor == Color.BLACK) {
            fixRemoving(movedUpNode);

            if (movedUpNode instanceof NilNode) {
                replaceParentsChild(movedUpNode.parent, movedUpNode, null);
            }
        }

        size--;

        return true;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> temp = root;
        while (temp != null) {
            if (o.equals(temp.data)) {
                return true;
            } else {
                temp = ((Comparable<E>) o).compareTo(temp.data) < 0 ? temp.left : temp.right;
            }
        }

        return false;
    }

    private void fixAddition(Node<E> node) {
        Node<E> parent = node.parent;

        if (parent == null || parent.color == Color.BLACK) return;

        Node<E> grandparent = parent.parent;

        if (grandparent == null) {
            parent.color = Color.BLACK;
            return;
        }

        Node<E> uncle = getUncle(parent);
        if (uncle != null && uncle.color == Color.RED) {
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
            uncle.color = Color.BLACK;
            fixAddition(grandparent);
        } else if (parent == grandparent.left) {
            if (node == parent.right) {
                rotateLeft(parent);
                parent = node;
            }

            rotateRight(grandparent);
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
        } else {
            if (node == parent.left) {
                rotateRight(parent);
                parent = node;
            }

            rotateLeft(grandparent);
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
        }
    }

    private Node<E> getUncle(Node<E> parent) {
        Node<E> grandparent = parent.parent;

        if (grandparent.left == parent) {
            return grandparent.right;
        } else if (grandparent.right == parent) {
            return grandparent.left;
        }

        return null;
    }

    private void rotateRight(Node<E> node) {
        Node<E> parent = node.parent;
        Node<E> left = node.left;

        node.left = left.right;

        if (left.right != null) left.right.parent = node;

        left.right = node;
        node.parent = left;

        replaceParentsChild(parent, node, left);
    }

    private void rotateLeft(Node<E> node) {
        Node<E> parent = node.parent;
        Node<E> right = node.right;

        node.right = right.left;

        if (right.left != null) right.left.parent = node;

        right.left = node;
        node.parent = right;

        replaceParentsChild(parent, node, right);
    }

    private void replaceParentsChild(Node<E> parent, Node<E> oldChild, Node<E> newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else if (parent.right == oldChild) {
            parent.right = newChild;
        }

        if (newChild != null) newChild.parent = parent;
    }

    private void getInOrderTraversal(Node<E> root, List<E> traversal) {
        if (root == null) return;
        getInOrderTraversal(root.left, traversal);
        traversal.add(root.data);
        getInOrderTraversal(root.right, traversal);
    }


    private Node<E> removeNodeWithZeroOrOneChild(Node<E> node) {
        if (node.left != null) {
            replaceParentsChild(node.parent, node, node.left);
            return node.left;
        } else if (node.right != null) {
            replaceParentsChild(node.parent, node, node.right);
            return node.right;
        } else {
            Node<E> newChild = node.color == Color.BLACK ? new NilNode<>() : null;
            replaceParentsChild(node.parent, node, newChild);
            return newChild;
        }
    }

    private Node<E> findMinimum(Node<E> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private void fixRemoving(Node<E> node) {
        if (node == root) return;

        Node<E> sibling = getSibling(node);

        if (sibling.color == Color.RED) {
            handleRedSibling(node, sibling);
            sibling = getSibling(node);
        }

        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = Color.RED;

            if (node.parent.color == Color.RED) {
                node.parent.color = Color.BLACK;
            } else {
                fixRemoving(node.parent);
            }
        } else {
            handleBlackSibling(node, sibling);
        }
    }

    private void handleRedSibling(Node<E> node, Node<E> sibling) {
        sibling.color = Color.BLACK;
        node.parent.color = Color.RED;

        if (node == node.parent.left) {
            rotateLeft(node.parent);
        } else {
            rotateRight(node.parent);
        }
    }

    private void handleBlackSibling(Node<E> node, Node<E> sibling) {
        boolean nodeIsLeftChild = node == node.parent.left;

        if (nodeIsLeftChild && isBlack(sibling.right)) {
            sibling.left.color = Color.BLACK;
            sibling.color = Color.RED;
            rotateRight(sibling);
            sibling = node.parent.right;
        } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
            sibling.right.color = Color.BLACK;
            sibling.color = Color.RED;
            rotateLeft(sibling);
            sibling = node.parent.left;
        }

        sibling.color = node.parent.color;
        node.parent.color = Color.BLACK;

        if (nodeIsLeftChild) {
            sibling.right.color = Color.BLACK;
            rotateLeft(node.parent);
        } else {
            sibling.left.color = Color.BLACK;
            rotateRight(node.parent);
        }
    }

    private Node<E> getSibling(Node<E> node) {
        Node<E> parent = node.parent;
        if (node == parent.left) {
            return parent.right;
        } else if (node == parent.right) {
            return parent.left;
        }

        return null;
    }

    private boolean isBlack(Node<E> node) {
        return node == null || node.color == Color.BLACK;
    }

    @Override
    public E lower(E e) {
        Node<E> temp = root;
        while (temp != null) {
            int cmp = e.compareTo(temp.data);
            if (cmp > 0) {
                if (temp.right != null) {
                    temp = temp.right;
                } else {
                    return temp.data;
                }
            } else {
                if (temp.left != null) {
                    temp = temp.left;
                } else {
                    Node<E> parent = temp.parent;
                    Node<E> ch = temp;

                    while (parent != null && ch == parent.left) {
                        ch = parent;
                        parent = parent.parent;
                    }

                    return parent == null ? null : parent.data;
                }
            }
        }
        return null;
    }

    @Override
    public E floor(E e) {
        Node<E> temp = root;
        while (temp != null) {
            int cmp = e.compareTo(temp.data);

            if (cmp > 0) {
                if (temp.right != null) {
                    temp = temp.right;
                } else {
                    return temp.data;
                }
            } else if (cmp < 0) {
                if (temp.left != null) {
                    temp = temp.left;
                } else {
                    Node<E> parent = temp.parent;
                    Node<E> ch = temp;

                    while (parent != null && ch == parent.left) {
                        ch = parent;
                        parent = parent.parent;
                    }

                    return parent == null ? null : parent.data;
                }
            } else {
                return temp.data;
            }
        }
        return null;
    }

    @Override
    public E ceiling(E e) {
        Node<E> temp = root;
        while (temp != null) {
            int cmp = e.compareTo(temp.data);

            if (cmp < 0) {
                if (temp.left != null) {
                    temp = temp.left;
                } else {
                    return temp.data;
                }
            } else if (cmp > 0) {
                if (temp.right != null) {
                    temp = temp.right;
                } else {
                    Node<E> parent = temp.parent;
                    Node<E> ch = temp;

                    while (parent != null && ch == parent.right) {
                        ch = parent;
                        parent = parent.parent;
                    }

                    return parent == null ? null : parent.data;
                }
            } else {
                return temp.data;
            }
        }
        return null;
    }

    @Override
    public E higher(E e) {
        Node<E> temp = root;
        while (temp != null) {
            int cmp = e.compareTo(temp.data);

            if (cmp < 0) {
                if (temp.left != null) {
                    temp = temp.left;
                } else {
                    return temp.data;
                }
            } else {
                if (temp.right != null) {
                    temp = temp.right;
                } else {
                    Node<E> parent = temp.parent;
                    Node<E> ch = temp;

                    while (parent != null && ch == parent.right) {
                        ch = parent;
                        parent = parent.parent;
                    }

                    if (parent == null) {
                        return null;
                    }

                    return parent.data;
                }
            }
        }

        return null;
    }

    @Override
    public E pollFirst() {
        Node<E> firstNode = getFirstNode();

        if (firstNode == null) {
            return null;
        }

        E data = firstNode.data;
        remove(firstNode.data);
        return data;
    }

    @Override
    public E pollLast() {
        Node<E> lastNode = getLastNode();

        if (lastNode == null) {
            return null;
        }

        E data = lastNode.data;
        remove(lastNode.data);
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
    public E first() {
        Node<E> firstNode = getFirstNode();
        return firstNode == null ? null : firstNode.data;
    }

    private Node<E> getLastNode() {
        Node<E> temp = root;

        while (temp != null) {
            if (temp.right != null) {
                temp = temp.right;
            } else {
                return temp;
            }
        }

        return null;
    }

    private Node<E> getFirstNode() {
        Node<E> temp = root;

        while (temp != null) {
            if (temp.left != null) {
                temp = temp.left;
            } else {
                return temp;
            }
        }

        return null;
    }

    @Override
    public E last() {
        Node<E> lastNode = getLastNode();
        return lastNode == null ? null : lastNode.data;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
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
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public NavigableSet<E> descendingSet() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Comparator<? super E> comparator() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String toString() {
        List<E> traversal = new LinkedList<>();
        getInOrderTraversal(root, traversal);
        return traversal.toString();
    }
}