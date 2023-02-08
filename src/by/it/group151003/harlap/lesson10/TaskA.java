package by.it.group151003.harlap.lesson10;

import java.util.*;

public class TaskA<E>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    private Node<E> root;

    private static class Node<E> {
        private E value;
        private Node<E> left;
        private Node<E> right;

        Node(E value) {
            this.value = value;
        }

        E getValue() {
            return value;
        }

        void setValue(E value) {
            this.value = value;
        }

        Node<E> getLeft() {
            return left;
        }

        void setLeft(Node<E> left) {
            this.left = left;
        }

        Node<E> getRight() {
            return right;
        }

        void setRight(Node<E> right) {
            this.right = right;
        }
    }

    //Обязательные к реализации методы и конструкторы
    public TaskA() {
    }
    private int compareTo(E obj1, E obj2) {
        return Integer.compare((int)obj1, (int)obj2);
    }
    @Override
    public boolean add(E e) {
        Node<E> current = root;
        Node<E> parent = current;
        int result = 0;
        while (current != null){
            parent = current;
            result = compareTo(current.value,e);
            if (result > 0){
                current = current.left;
            } else if (result < 0){
                current = current.right;
            } else {
                return false;
            }
        }

        final Node<E> newNode = new Node<>(e);
        if (parent == null) {
            root = newNode;
        } else if (result > 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> current = root;
        Node<E> parent = null;

        int result;
        while (current != null && (result = compareTo(current.value,(E) o)) != 0) {
            parent = current;
            if (result > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return false;
        }

        if (current.left == null && current.right == null) {
            if (current != root) {
                if (parent.right == current) {
                    parent.right = null;
                } else {
                    parent.left = null;
                }
            } else {
                root = null;
            }
        } else if (current.left != null && current.right != null) {
            Node<E> min = current.right;
            while (min.left != null) {
                min = min.left;
            }
            remove(min.value);
            current.value = min.value;
        } else {
            final Node<E> child  = (current.left != null)
                    ? current.left
                    : current.right;

            if (current != root){
                if (current == parent.left) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            } else {
                root = child;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        if (root == null) return "[]";

        trace(root, stringBuilder);

        return stringBuilder.append("]").toString().replace(", ]", "]");
    }

    private void trace(Node<E> node, StringBuilder builder) {
        if (node == null) return;

        trace(node.getLeft(), builder);
        builder.append(node.getValue()).append(", ");
        trace(node.getRight(), builder);
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