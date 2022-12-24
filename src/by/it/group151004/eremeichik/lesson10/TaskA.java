package by.it.group151004.eremeichik.lesson10;

import java.util.*;

public class TaskA<E>  implements NavigableSet<E> {
    private Node<E> root;
    static final boolean RED = false;
    static final boolean BLACK = true;

    public TaskA() {
    }

    private int compareNodes(E node1, E node2){
        int result = 0;
        try{
            result = ((Comparable)node1).compareTo(node2);
        }catch (Exception e){
            result = 0;
        }
        return result;
    }

    public Node<E> searchNode(E key) {
        Node<E>  node = root;
        while (node != null) {
            int compareResult = compareNodes(key,node.data);
            if (compareResult==0) {
                return node;
            } else if (compareResult==-1) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    private void fixRedBlackPropertiesAfterInsert(Node<E>  node) {
        Node<E>  parent = node.parent;
        if (parent == null) {
            return;
        }
        if (parent.color == BLACK) {
            return;
        }
        Node<E>  grandparent = parent.parent;
        if (grandparent == null) {
            parent.color = BLACK;
            return;
        }
        Node<E>  uncle = getUncle(parent);
        if (uncle != null && uncle.color == RED) {
            parent.color = BLACK;
            grandparent.color = RED;
            uncle.color = BLACK;
            fixRedBlackPropertiesAfterInsert(grandparent);
        }
        else if (parent == grandparent.left) {
            if (node == parent.right) {
                rotateLeft(parent);
                parent = node;
            }
            rotateRight(grandparent);
            parent.color = BLACK;
            grandparent.color = RED;
        }

        else {
            if (node == parent.left) {
                rotateRight(parent);
                parent = node;
            }
            rotateLeft(grandparent);
            parent.color = BLACK;
            grandparent.color = RED;
        }
    }

    private Node<E>  getUncle(Node<E>  parent) {
        Node<E>  grandparent = parent.parent;
        if (grandparent.left == parent) {
            return grandparent.right;
        } else if (grandparent.right == parent) {
            return grandparent.left;
        }
        return null;
    }


    private Node<E>  deleteNodeWithZeroOrOneChild(Node<E>  node) {
        if (node.left != null) {
            replaceParentsChild(node.parent, node, node.left);
            return node.left;
        }
        else if (node.right != null) {
            replaceParentsChild(node.parent, node, node.right);
            return node.right;
        }
        else {
            Node<E> newChild = node.color == BLACK ? new Node(null) : null;
            replaceParentsChild(node.parent, node, newChild);
            return newChild;
        }
    }

    private Node<E>  findMinimum(Node<E>  node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private void fixRedBlackPropertiesAfterDelete(Node<E>  node) {
        if (node == root) {
            return;
        }
        Node<E>  sibling = getSibling(node);
        if (sibling.color == RED) {
            handleRedSibling(node, sibling);
            sibling = getSibling(node);
        }
        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = RED;
            if (node.parent.color == RED) {
                node.parent.color = BLACK;
            }
            else {
                fixRedBlackPropertiesAfterDelete(node.parent);
            }
        }
        else {
            handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
        }
    }

    private void handleRedSibling(Node<E>  node, Node<E>  sibling) {
        sibling.color = BLACK;
        node.parent.color = RED;
        if (node == node.parent.left) {
            rotateLeft(node.parent);
        } else {
            rotateRight(node.parent);
        }
    }

    private void handleBlackSiblingWithAtLeastOneRedChild(Node<E>  node, Node<E>  sibling) {
        boolean nodeIsLeftChild = node == node.parent.left;
        if (nodeIsLeftChild && isBlack(sibling.right)) {
            sibling.left.color = BLACK;
            sibling.color = RED;
            rotateRight(sibling);
            sibling = node.parent.right;
        } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
            sibling.right.color = BLACK;
            sibling.color = RED;
            rotateLeft(sibling);
            sibling = node.parent.left;
        }
        sibling.color = node.parent.color;
        node.parent.color = BLACK;
        if (nodeIsLeftChild) {
            sibling.right.color = BLACK;
            rotateLeft(node.parent);
        } else {
            sibling.left.color = BLACK;
            rotateRight(node.parent);
        }
    }

    private Node<E>  getSibling(Node<E>  node) {
        Node<E>  parent = node.parent;
        if (node == parent.left) {
            return parent.right;
        } else if (node == parent.right) {
            return parent.left;
        }
        return null;
    }

    private boolean isBlack(Node<E>  node) {
        return node == null || node.color == BLACK;
    }


    private void rotateRight(Node<E>  node) {
        Node<E>  parent = node.parent;
        Node<E>  leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.right = node;
        node.parent = leftChild;

        replaceParentsChild(parent, node, leftChild);
    }

    private void rotateLeft(Node<E>  node) {
        Node<E>  parent = node.parent;
        Node<E>  rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.left = node;
        node.parent = rightChild;

        replaceParentsChild(parent, node, rightChild);
    }

    private void replaceParentsChild(Node<E>  parent, Node<E>  oldChild, Node<E>  newChild) {
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


    @Override
    public boolean add(E key) {
        Node<E>  node = root;
        Node<E>  parent = null;
        while (node != null) {
            parent = node;
            int compareResult = compareNodes(key,node.data);
            if (compareResult==-1) {
                node = node.left;
            } else if(compareResult==1){
                node = node.right;
            } else{
                return false;
            }
        }
        Node<E>  newNode = new Node(key);
        newNode.color = RED;
        if (parent == null) {
            root = newNode;
        } else if (compareNodes(key,parent.data)==-1) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;

        fixRedBlackPropertiesAfterInsert(newNode);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        E key = (E)o;
        Node<E>  node = root;
        while (node != null && node.data != key) {
            int result = compareNodes(key,node.data);
            if (result==-1) {
                node = node.left;
            } else {
                node = node.right;
            }
        }


        if (node == null) {
            return true;
        }
        Node<E>  movedUpNode;
        boolean deletedNodeColor;

        if (node.left == null || node.right == null) {
            movedUpNode = deleteNodeWithZeroOrOneChild(node);
            deletedNodeColor = node.color;
        }

        else {

            Node<E>  inOrderSuccessor = findMinimum(node.right);

            node.data = inOrderSuccessor.data;
            movedUpNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor);
            deletedNodeColor = inOrderSuccessor.color;
        }

        if (deletedNodeColor == BLACK) {
            fixRedBlackPropertiesAfterDelete(movedUpNode);
            if (movedUpNode.data == null) {
                replaceParentsChild(movedUpNode.parent, movedUpNode, null);
            }
        }
        return true;
    }


    @Override
    public String toString() {
        List<E> list = new ArrayList<>();
        root.addToList(list);
        return list.toString();
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
