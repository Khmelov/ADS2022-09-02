package by.it.group151004.glushachenko.lesson10;

import java.util.*;

public class TaskB<E extends Comparable<E>>  implements NavigableSet<E> {

    public int size = 0;
    private Node<E> rootNode;
    public int compareTo(E o, E o1) {
        return o.compareTo(o1);
    }
    public static class Node<E> {
        private E data;
        private Node<E> leftChild;
        private Node<E> rightChild;
        private Node<E> parent;

        public void setLeftChild(Node<E> newNode) {
            leftChild = newNode;
        }

        public void setRightChild(Node<E> newNode) {
            rightChild = newNode;
        }

        public Node(E data) {
            this.data = data;
        }
        public Node<E> getLeftChild() {
            return leftChild;
        }

        public Node<E> getRightChild() {
            return rightChild;
        }

        public E getValue() {
            return data;
        }

        public Node<E> getParent() {
            return parent;
        }
    }

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы
    public TaskB() {
    }

    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.data = e;
        if (rootNode == null) {
            rootNode = newNode;
            size++;
        } else {
            Node<E> currentNode = rootNode;
            Node<E> parentNode;
            while (true) {
                parentNode = currentNode;
                if(e == currentNode.getValue()) {
                    return false;
                }
                else  if (compareTo(currentNode.getValue(), e) > 0) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null){
                        newNode.parent = parentNode;
                        parentNode.setLeftChild(newNode);
                        size++;
                        return true;
                    }
                } else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        newNode.parent = parentNode;
                        parentNode.setRightChild(newNode);
                        size++;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> current = rootNode;
        boolean flag = false;
        E value = (E)o;

        while(!flag && size >0){
            if(current==null) {
                return false;
            }
            if(compareTo(current.getValue(),value)>0){
                current=current.getLeftChild();
            } else if(compareTo(current.getValue(),value)<0){
                current=current.getRightChild();
            }
            if(compareTo(current.getValue(), value) == 0){
                flag = true;
            }
        }
        if(current.getRightChild() == null && current.getLeftChild() == null){
            if(current.getParent() == null){
                rootNode=null;
            } else if(current.getParent().getRightChild() == current){
                current.getParent().rightChild = null;
            }else {
                current.getParent().leftChild = null;
            }
            size--;
            return true;
        }
        if(current.getLeftChild() == null || current.getRightChild() == null){
            if(current.getLeftChild() == null){
                if(current.getParent() == null){
                    rootNode = current.getRightChild();
                } else if(current.getParent().getLeftChild() == current){
                    current.getRightChild().parent = current.getParent();
                    current.getParent().leftChild = current.getRightChild();
                } else if (current.getParent().getRightChild() == current){
                    current.getRightChild().parent = current.getParent();
                    current.getParent().rightChild = current.getRightChild();
                }
            }
            if(current.getRightChild() == null){
                if(current.getParent() == null){
                    rootNode = current.getLeftChild();
                } else if(current.getParent() != null && current.getParent().getRightChild() == current){
                    current.getLeftChild().parent = current.getParent();
                    current.getParent().rightChild = current.getLeftChild();
                } else
                if(current.getParent() != null && current.getParent().getLeftChild() == current){
                    current.getLeftChild().parent = current.getParent();
                    current.getParent().leftChild = current.getLeftChild();
                }
            }
            size--;
            return true;
        }
        if(current.getLeftChild() != null && current.getRightChild() != null){
            Node<E> tmp = current.getRightChild();
            flag = false;
            while(tmp.leftChild != null){
                tmp=tmp.getLeftChild();
                flag=true;
            }
            if(flag){
                if(tmp.getRightChild() != null){
                    tmp.getRightChild().parent = tmp.getParent();
                    tmp.getParent().leftChild = tmp.getRightChild();
                    current.data = tmp.getValue();
                }
                if(tmp.getRightChild() == null){
                    tmp.getParent().leftChild = null;
                    current.data = tmp.getValue();
                }
            } else{
                if(tmp.getRightChild() != null) {
                    tmp.getRightChild().parent = tmp.getParent();
                    tmp.getParent().rightChild = tmp.getRightChild();
                    current.data = tmp.getValue();
                }
                if(tmp.getRightChild() == null){
                    tmp.getParent().rightChild = null;
                    current.data = tmp.getValue();
                }
            }
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = rootNode;
        boolean flag = false;
        E value = (E)o;

        while(!flag && size > 0){
            if(current == null){
                return false;
            }
            if(compareTo(current.getValue(),value) > 0){
                current=current.getLeftChild();
            }
            if(compareTo(current.getValue(),value) < 0){
                current=current.getRightChild();
            }
            if(compareTo(current.getValue(),value) == 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {
        size = 0;
        rootNode = null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E first() {
        if(size > 0){
            TaskB.Node<E> current = rootNode;
            while(current.getLeftChild() != null){
                current = current.getLeftChild();
            }
            return current.data;
        }
        else {
            return null;
        }
    }

    @Override
    public E last() {
        if(size > 0){
            TaskB.Node<E> current = rootNode;
            while(current.getRightChild() != null){
                current = current.getRightChild();
            }
            return current.data;
        }
        else {
            return null;
        }
    }


    void inOrder(Node<E> root, StringBuilder stb){
        if(root == null) {
            return;
        }
        inOrder(root.leftChild,stb);
        stb.append(root.data);
        stb.append(", ");
        inOrder(root.rightChild,stb);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        inOrder(rootNode,stringBuilder);
        if (stringBuilder.length() <= 1) {
            stringBuilder.append("]");
        } else {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append("]");
        }
        return stringBuilder.toString();
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
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


}
