package by.it.group151004.haiko.lesson10;

import java.util.*;

public class TaskC<E>  implements NavigableSet<E> {
    public int amount = 0;
    private int i=0;
    private Node<E> root;
    public int compareTo(E o, E o1) {
        return Integer.compare((int)o, (int)o1);
    }
    public class Node<E> {
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

        public E getvalue() {
            return data;
        }

        public Node<E> getparent() {
            return parent;
        }
    }
    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet

    //Обязательные к реализации методы и конструкторы
    public TaskC() {
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
                amount++;
                return true;
            } else {
                while (true) {
                    prev = current;
                    if (compareTo(current.getvalue(), e) > 0) {
                        if (current.leftChild == null) {
                            current.setLeftChild(newNode);
                            current.leftChild.parent = current;
                            amount++;
                            return true;
                        }
                        current = current.getLeftChild();
                    } else {

                        if (current.rightChild == null) {
                            current.setRightChild(newNode);
                            current.rightChild.parent = current;
                            amount++;
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
        while(!flag && amount>0){
            if(current==null) return false;
            if(current!=null && compareTo(current.getvalue(),(E)o)>0){
                current=current.getLeftChild();
            } else
            if(current!=null && compareTo(current.getvalue(),(E)o)<0){
                current=current.getRightChild();
            }
            if(current!=null && compareTo(current.getvalue(),(E)o)==0){
                flag = true;
            }
        }
        if(current.leftChild==null && current.rightChild==null){
            if(current.parent==null){
                root=null;
            } else
            if(current.parent.rightChild==current){
                current.parent.rightChild=null;
            }else {
                current.parent.leftChild = null;
            }
            amount--;
            return true;
        }
        if(current.leftChild==null || current.rightChild==null){
            if(current.leftChild==null){
                if(current.parent==null){
                    root=current.getRightChild();
                } else
                if(current.parent.leftChild==current){
                    current.rightChild.parent=current.parent;
                    current.parent.leftChild=current.rightChild;
                } else
                if(current.parent.rightChild==current){
                    current.rightChild.parent=current.parent;
                    current.parent.rightChild = current.rightChild;
                }
            }
            if(current.rightChild==null){
                if(current.parent==null){
                    root=current.getLeftChild();
                } else
                if(current.parent!=null && current.parent.rightChild==current){
                    current.leftChild.parent=current.parent;
                    current.parent.rightChild=current.leftChild;
                } else
                if(current.parent!=null && current.parent.leftChild==current){
                    current.leftChild.parent=current.parent;
                    current.parent.leftChild = current.leftChild;
                }
            }
            amount--;
            return true;
        }
        if(current.leftChild!=null && current.rightChild!=null){
            Node<E> tmp;
            tmp=current.getRightChild();
            boolean flag1=false;
            while(tmp.leftChild!=null){
                tmp=tmp.getLeftChild();
                flag1=true;
            }
            if(flag1){
                if(tmp.rightChild!=null){
                    tmp.rightChild.parent=tmp.parent;
                    tmp.parent.leftChild=tmp.rightChild;
                    current.data=tmp.data;
                }
                if(tmp.rightChild==null){
                    tmp.parent.leftChild=null;
                    current.data=tmp.data;
                }
                amount--;
                return true;
            }
            else if(!flag1){
                if(tmp.rightChild!=null) {
                    tmp.rightChild.parent = tmp.parent;
                    tmp.parent.rightChild = tmp.rightChild;
                    current.data = tmp.data;
                }
                if(tmp.rightChild==null){
                    tmp.parent.rightChild=null;
                    current.data=tmp.data;
                }
                amount--;
                return true;
            }
            amount--;
            return true;
        }
        return false;
    }

    void inOrder(Node root, StringBuilder str){
        if(root==null){return;}
        inOrder(root.leftChild,str);
        str.append(root.data);
        str.append(", ");
        i++;
        inOrder(root.rightChild,str);
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
        while(!flag && amount>0){
            if(current==null) return false;
            if(current!=null && compareTo(current.getvalue(),(E)o)>0){
                current=current.getLeftChild();
            }
            if(current!=null && compareTo(current.getvalue(),(E)o)<0){
                current=current.getRightChild();
            }
            if(current!=null && compareTo(current.getvalue(),(E)o)==0){
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
        amount=0;
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return (amount==0);
    }

    @Override
    public int size() {
        return amount;
    }

    @Override
    public E first() {
        if(amount>0){
            Node<E> current = root;
            while(current.leftChild!=null){current= current.getLeftChild();}
            return current.data;
        }
        else return null;
    }

    @Override
    public E last() {
        if(amount>0){
            Node<E> current = root;
            while(current.rightChild!=null){current= current.getRightChild();}
            return current.data;
        }
        else return null;
    }

    void inArray(Node root, Object arr[]){
        if(root==null){return;}
        inArray(root.leftChild,arr);
        arr[i]=(int)root.data;
        i++;
        inArray(root.rightChild,arr);
    }

    @Override
    public E lower(E e) {
        Object[] A = new Object[amount];
        i=0;
        inArray(root,A);
        Arrays.sort(A);
        int ind=-1;
        for(int j=0;j<amount;j++){
            if((int)A[j]>=(int)e) {
                ind = j;
                break;
            }
        }
        if(ind==-1 || ind == 0) return null;
        return (E)A[ind-1];
    }

    @Override
    public E floor(E e) {
        Object[] A = new Object[amount];
        i=0;
        inArray(root,A);
        Arrays.sort(A);
        int ind=-1;
        for(int j=0;j<amount;j++){
            if((int)A[j]>=(int)e) {
                ind = j;
                break;
            }
        }
        if(ind==-1) return null;
        if((int)A[ind]==(int)e) return (E)A[ind];
        if((int)A[ind]>(int)e) return (E)A[ind-1];
        return (E)A[ind];
    }

    @Override
    public E ceiling(E e) {
        Object[] A = new Object[amount];
        i=0;
        inArray(root,A);
        Arrays.sort(A);
        int ind=-1;
        for(int j=0;j<amount;j++){
            if((int)A[j]>=(int)e) {
                ind = j;
                break;
            }
        }
        if(ind==-1) return null;
        return (E)A[ind];
    }

    @Override
    public E higher(E e) {
        Object[] A = new Object[amount];
        i=0;
        inArray(root,A);
        Arrays.sort(A);
        int ind=-1;
        for(int j=0;j<amount;j++){
            if((int)A[j]>(int)e) {
                ind = j;
                break;
            }
        }
        if(ind==-1 || ind == amount-1) return null;
        return (E)A[ind];
    }

    @Override
    public E pollFirst() {
        if(amount>0){
            Node<E> current = root;
            while(current.leftChild!=null){current= current.getLeftChild();}
            E el = current.data;
            remove(current.data);
            return el;
        }
        return null;
    }

    @Override
    public E pollLast() {
        if(amount>0){
            Node<E> current = root;
            while(current.rightChild!=null){current= current.getRightChild();}
            E el = current.data;
            remove(current.data);
            return el;
        }
        return null;
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
