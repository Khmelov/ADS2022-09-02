package by.it.group151002.naftolsky.lesson10;

import java.util.*;

public class TaskC<E>  implements NavigableSet<E> {
    private int size = 0;
    private int i = 0;
    private Node<E> root;
    public int compareTo(E o, E nextO) {
        return Integer.compare((int)o, (int)nextO);
    }
    private class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;
        private Node<E> parent;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeftChild() {
            return leftChild;
        }

        public Node<E> getRightChild() {
            return rightChild;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public void setParent(Node<E> parent)
        {
            this.parent = parent;
        }

        public void setLeftChild(Node<E> newNode) {
            leftChild = newNode;
        }

        public void setRightChild(Node<E> newNode) {
            rightChild = newNode;
        }
    }

    @Override
    public boolean add(E inputValue) {
        Node<E> newNode = new Node<>(inputValue);
        Node<E> current = root;
        Node<E> prev;
        boolean isAdd = false;

        if(!contains(inputValue)) {
            if (root == null) {
                root = newNode;
                root.setParent(null);
                size++;
                isAdd = true;
            } else {
                while (!isAdd) {
                    prev = current;
                    if (compareTo(current.getValue(), inputValue) > 0) {
                        if (current.getLeftChild() == null) {
                            current.setLeftChild(newNode);
                            current.getLeftChild().setParent(current);
                            size++;
                            isAdd = true;
                        } else {
                            current = current.getLeftChild();
                        }
                    } else {
                        if (current.getRightChild() == null) {
                            current.setRightChild(newNode);
                            current.getRightChild().setParent(current);
                            size++;
                            isAdd = true;
                        } else {
                            current = current.getRightChild();
                        }
                    }
                }
            }
        }
        return isAdd;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> current = root;
        boolean isFind = false;
        boolean isRemove = false;

        while (!isFind && size > 0 && current != null) {
            if (compareTo(current.getValue(), (E) o) == 0) {
                isFind = true;
            } else if (compareTo(current.getValue(), (E) o) > 0) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }

        if (isFind) {
            if (current.getLeftChild() == null && current.getRightChild() == null) {
                if (current.getParent() == null) {
                    root = null;
                } else if (current.getParent().getRightChild() == current) {
                    current.getParent().setRightChild(null);
                } else {
                    current.getParent().setLeftChild(null);
                }

                size--;
                isRemove = true;
            } else if (current.getLeftChild() == null && current.getRightChild() != null) {
                if (current.getParent() == null) {
                    root = current.getRightChild();
                } else if (current.getParent().getLeftChild() == current) {
                    current.getRightChild().setParent(current.getParent());
                    current.getParent().setLeftChild(current.getRightChild());
                } else if (current.getParent().getRightChild() == current) {
                    current.getRightChild().setParent(current.getParent());
                    current.getParent().setRightChild(current.getRightChild());
                }

                size--;
                isRemove = true;
            } else if (current.getLeftChild() != null && current.getRightChild() == null) {
                if (current.getParent() == null) {
                    root = current.getLeftChild();
                } else if (current.getParent().getRightChild() == current) {
                    current.getLeftChild().setParent(current.getParent());
                    current.getParent().setRightChild(current.getLeftChild());
                } else if (current.getParent().getLeftChild() == current) {
                    current.getLeftChild().setParent(current.getParent());
                    current.getParent().setLeftChild(current.getLeftChild());
                }

                size--;
                isRemove = true;
            } else if (current.getLeftChild() != null && current.getRightChild() != null) {
                Node<E> tempPointer;
                tempPointer = current.getRightChild();
                boolean isLeftFind = false;

                while (tempPointer.getLeftChild() != null) {
                    tempPointer = tempPointer.getLeftChild();
                    isLeftFind = true;
                }

                if (isLeftFind) {
                    if (tempPointer.getRightChild() == null) {
                        tempPointer.getParent().setLeftChild(null);
                        current.setValue(tempPointer.getValue());
                    } else {
                        tempPointer.getRightChild().setParent(tempPointer.getParent());
                        tempPointer.getParent().setLeftChild((tempPointer.getRightChild()));
                        current.setValue(tempPointer.getValue());
                    }

                    size--;
                    isRemove = true;
                } else {
                    if (tempPointer.getRightChild() == null) {
                        tempPointer.getParent().setRightChild(null);
                        current.setValue(tempPointer.getValue());
                    }

                    if (tempPointer.getRightChild() != null) {
                        tempPointer.getRightChild().setParent(tempPointer.getParent());
                        tempPointer.getParent().setRightChild(tempPointer.getRightChild());
                        current.setValue(tempPointer.getValue());
                    }

                    size--;
                    isRemove = true;
                }
            }
        }

        return isRemove;
    }

    void fillValuesNodesBuilder(Node root, StringBuilder str){
        if(root != null) {
            fillValuesNodesBuilder(root.leftChild, str);
            str.append(root.value);
            str.append(", ");
            i++;
            fillValuesNodesBuilder(root.rightChild, str);
        }
    }

    @Override
    public String toString() {
        i=0;
        StringBuilder s = new StringBuilder();
        s.append('[');
        fillValuesNodesBuilder(root,s);
        if (s.length() < 2) {
            s.append("]");
        } else {
            s.delete(s.length()-2, s.length());
            s.append("]");
        }
        return s.toString();
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = root;
        boolean isFind = false;

        while(!isFind && current != null) {
            if(compareTo(current.getValue(),(E)o) == 0){
                isFind = true;
            }
            else if(compareTo(current.getValue(),(E)o) > 0){
                current = current.getLeftChild();
            }
            else {
                current = current.getRightChild();
            }
        }

        return isFind;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        boolean isEmptyFlag = false;
        if (size == 0)
        {
            isEmptyFlag = true;
        }

        return isEmptyFlag;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E first() {
        if(size == 0) {
            return null;
        }
        else {
            Node<E> current = root;

            while(current.leftChild != null){
                current= current.getLeftChild();
            }

            return current.value;
        }
    }

    @Override
    public E last() {
        if(size == 0) {
            return null;
        }
        else {
            Node<E> current = root;

            while(current.rightChild != null){
                current= current.getRightChild();
            }

            return current.value;
        }
    }

    void fillArrObject(Node root, Object arrObject[]) {
        if (root != null) {
            fillArrObject(root.leftChild, arrObject);

            arrObject[i] = (int) root.value;
            i++;

            fillArrObject(root.rightChild, arrObject);
        }
    }

    @Override
    public E lower(E e) { // < e
        Object[] arrObject = new Object[size];
        i = 0;

        fillArrObject(root, arrObject);
        Arrays.sort(arrObject);

        int j = 0;
        while (j < size && (int)arrObject[j] < (int)e)
        {
            j++;
        }

        if (j != size && j != 0)
        {
            return (E)arrObject[j - 1];
        }
        else {
            return null;
        }
    }

    @Override
    public E floor(E e) { // <= e
        Object[] arrObject = new Object[size];
        Object resultObject = null;
        i = 0;

        fillArrObject(root, arrObject);
        Arrays.sort(arrObject);

        int j = 0;
        boolean isFind = false;
        while (j < size && !isFind) {
            if ((int) arrObject[j] == (int)e) {
                resultObject = arrObject[j];
                isFind = true;
            } else if ((int) arrObject[j] > (int)e) {
                resultObject = arrObject[j - 1];
                isFind = true;
            } else {
                j++;
            }
        }

        if (isFind) {
            return (E) resultObject;
        } else {
            return null;
        }
    }

    @Override
    public E ceiling(E e) { // >=
        Object[] arrObject = new Object[size];
        i = 0;

        fillArrObject(root, arrObject);
        Arrays.sort(arrObject);

        int j = 0;
        while (j < size && (int)arrObject[j] < (int)e)
        {
            j++;
        }

        if (j != size)
        {
            return (E)arrObject[j];
        }
        else {
            return null;
        }
    }

    @Override
    public E higher(E e) { // >
        Object[] arrObject = new Object[size];
        i = 0;

        fillArrObject(root, arrObject);
        Arrays.sort(arrObject);

        int j = 0;
        while (j < size && (int)arrObject[j] <= (int)e)
        {
            j++;
        }

        if (j < size - 1)
        {
            return (E)arrObject[j];
        }
        else {
            return null;
        }
    }

    @Override
    public E pollFirst() {
        if (size == 0) {
            return null;
        } else {
            Node<E> tempPointer = root;

            while (tempPointer.leftChild != null)
                tempPointer = tempPointer.getLeftChild();

            E value = tempPointer.value;
            remove(tempPointer.value);

            return value;
        }
    }

    @Override
    public E pollLast() {
        if (size == 0) {
            return null;
        } else {
            Node<E> tempPointer = root;

            while (tempPointer.rightChild != null)
                tempPointer = tempPointer.getRightChild();

            E value = tempPointer.value;
            remove(tempPointer.value);

            return value;
        }
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