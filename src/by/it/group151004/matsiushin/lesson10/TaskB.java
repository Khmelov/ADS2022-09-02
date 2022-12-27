package by.it.group151004.matsiushin.lesson10;

import java.util.*;

public class TaskB<E>  implements NavigableSet<E> {
    public int size = 0;
    private int i=0;
    private Node<E> root;
    public int compareTo(E o, E o1) {
        return Integer.compare((int)o, (int)o1);
    }
    public class Node<E> {
        private E data;
        private Node<E> lChild;
        private Node<E> rChild;
        private Node<E> parent;

        public void setLeftChild(Node<E> newNode) {
            lChild = newNode;
        }

        public void setRightChild(Node<E> newNode) {
            rChild = newNode;
        }

        public Node(E data) {
            this.data = data;
        }
        public Node<E> getLeftChild() {
            return lChild;
        }

        public Node<E> getRightChild() {
            return rChild;
        }

        public E getValue() {
            return data;
        }

        public Node<E> getParent() {
            return parent;
        }
    }
    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet

    //Обязательные к реализации методы и конструкторы
    public TaskB() {
    }

    @Override
    public boolean add(E e) {
        Node<E> current = root;
        Node<E> prev;
        Node<E> newNode = new Node<>(e);
        if(!contains(e)) {
            if (root == null) {
                root = newNode;
                root.parent = null;
                size++;
                return true;
            } else {
                while (true) {
                    prev = current;
                    if (compareTo(current.getValue(), e) > 0) {
                        if (current.lChild == null) {
                            current.setLeftChild(newNode);
                            current.lChild.parent = current;
                            size++;
                            return true;
                        }
                        current = current.getLeftChild();
                    } else {

                        if (current.rChild == null) {
                            current.setRightChild(newNode);
                            current.rChild.parent = current;
                            size++;
                            return true;
                        }
                        current = current.getRightChild();
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> current = root;
        boolean flag=false;
        while(!flag && size >0){
            if(current==null) return false;
            if(current!=null && compareTo(current.getValue(),(E)o)>0){
                current=current.getLeftChild();
            } else
            if(current!=null && compareTo(current.getValue(),(E)o)<0){
                current=current.getRightChild();
            }
            if(current!=null && compareTo(current.getValue(),(E)o)==0){
                flag = true;
            }
        }
        if(current.lChild ==null && current.rChild ==null){
            if(current.parent==null){
                root=null;
            } else
            if(current.parent.rChild ==current){
                current.parent.rChild =null;
            }else {
                current.parent.lChild = null;
            }
            size--;
            return true;
        }
        if(current.lChild ==null || current.rChild ==null){
            if(current.lChild ==null){
                if(current.parent==null){
                    root=current.getRightChild();
                } else
                if(current.parent.lChild ==current){
                    current.rChild.parent=current.parent;
                    current.parent.lChild =current.rChild;
                } else
                if(current.parent.rChild ==current){
                    current.rChild.parent=current.parent;
                    current.parent.rChild = current.rChild;
                }
            }
            if(current.rChild ==null){
                if(current.parent==null){
                    root=current.getLeftChild();
                } else
                if(current.parent!=null && current.parent.rChild ==current){
                    current.lChild.parent=current.parent;
                    current.parent.rChild =current.lChild;
                } else
                if(current.parent!=null && current.parent.lChild ==current){
                    current.lChild.parent=current.parent;
                    current.parent.lChild = current.lChild;
                }
            }
            size--;
            return true;
        }
        if(current.lChild !=null && current.rChild !=null){
            Node<E> tmp;
            tmp=current.getRightChild();
            boolean flag1=false;
            while(tmp.lChild !=null){
                tmp=tmp.getLeftChild();
                flag1=true;
            }
            if(flag1){
                if(tmp.rChild !=null){
                    tmp.rChild.parent=tmp.parent;
                    tmp.parent.lChild =tmp.rChild;
                    current.data=tmp.data;
                }
                if(tmp.rChild ==null){
                    tmp.parent.lChild =null;
                    current.data=tmp.data;
                }
                size--;
                return true;
            }
            else if(!flag1){
                if(tmp.rChild !=null) {
                    tmp.rChild.parent = tmp.parent;
                    tmp.parent.rChild = tmp.rChild;
                    current.data = tmp.data;
                }
                if(tmp.rChild ==null){
                    tmp.parent.rChild =null;
                    current.data=tmp.data;
                }
                size--;
                return true;
            }
            size--;
            return true;
        }
        return false;
    }

    void inOrder(Node root, StringBuilder str){
        if(root==null){return;}
        inOrder(root.lChild,str);
        str.append(root.data);
        str.append(", ");
        i++;
        inOrder(root.rChild,str);
    }

    @Override
    public String toString() {
        i=0;
        StringBuilder s = new StringBuilder();
        s.append('[');
        inOrder(root,s);
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
        boolean flag=false;
        while(!flag && size >0){
            if(current==null) return false;
            if(current!=null && compareTo(current.getValue(),(E)o)>0){
                current=current.getLeftChild();
            }
            if(current!=null && compareTo(current.getValue(),(E)o)<0){
                current=current.getRightChild();
            }
            if(current!=null && compareTo(current.getValue(),(E)o)==0){
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
        size =0;
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return (size ==0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E first() {
        if(size >0){
            Node<E> current = root;
            while(current.lChild !=null){current= current.getLeftChild();}
            return current.data;
        }
        else return null;
    }

    @Override
    public E last() {
        if(size >0){
            Node<E> current = root;
            while(current.rChild !=null){current= current.getRightChild();}
            return current.data;
        }
        else return null;
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
