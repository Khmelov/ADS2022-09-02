package by.it.group151002.naftolsky.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {

    private class Node {
        Object data;
        Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node headNode = new Node(null, null);

    @Override
    public int size() {
        Node currentNode = headNode;

        int counter = 0;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            counter++;
        }

        return counter;
    }

    @Override
    public boolean isEmpty() {
        if (headNode.next == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(Object o) {
        Node currentNode = headNode;
        boolean isContain = false;

        while (currentNode.next != null && !isContain) {
            if (currentNode.next.data == o) {
                isContain = true;
            } else if (o != null && currentNode.next.data != null) {
                 if (currentNode.next.data.equals(o)) {
                     isContain = true;
                 }
            }

            currentNode = currentNode.next;
        }

        return isContain;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (!contains(t)) {
            Node currentNode = headNode;

            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }

            Node newNode = new Node(t, null);
            currentNode.next = newNode;

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        Node currentNode = headNode;

        while (currentNode.next != null && currentNode.next.data != o) {
            currentNode = currentNode.next;
        }
        if (currentNode.next != null) {
            if (currentNode.next.data == o) {
                currentNode.next = currentNode.next.next;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object element : c) {
            boolean isChanged = contains(element);

            if (!isChanged){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isChanged = false;

        for (T element : c) {
            isChanged = add(element);
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;

        for (Object element : c) {
            isChanged = remove(element);
        }

        return isChanged;
    }

    @Override
    public void clear() {
        headNode.next = null;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");

        Node currentNode = headNode.next;
        while (currentNode != null) {
            str.append(currentNode.data).append(", ");
            currentNode = currentNode.next;
        }

        if (str.length() > 2) {
            str.delete(str.length() - 2, str.length());
        }
        str.append("]");

        return str.toString();
    }
}

//

//
//    @Override
//    public int size() {

//
//
//    @Override
//    public boolean contains(Object o) {
//        Node temp = head;
//        while (temp.next != null) {
//            if (temp.next.data == o) {
//                return true;
//            }
//            if (o != null && temp.next.data != null) {
//                if (temp.next.data.equals(o)){
//                    return true;
//                }
//            }
//            temp = temp.next;
//        }
//        return false;
//    }
