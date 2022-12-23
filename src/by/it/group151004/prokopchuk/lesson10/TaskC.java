package by.it.group151004.prokopchuk.lesson10;

import java.util.*;

public class TaskC<E>  implements NavigableSet<E> {

    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet

    private E value;
    private TaskC<E> left;
    private TaskC<E> right;

    //Обязательные к реализации методы и конструкторы
    public TaskC() {
        this.value = null;
        this.left = null;
        this.right = null;
    }

    public static void main(String[] args) {
        TaskC<Integer> myTree = new TaskC<>();
        myTree.add(0);
        myTree.add(3);
        myTree.add(4);
        myTree.add(5);
        myTree.add(7);
        myTree.add(8);
        myTree.add(9);
        myTree.add(10);
        myTree.add(12);
        myTree.add(14);
        myTree.add(15);
        myTree.add(16);
        System.out.println(myTree);

    }

    public void setNode(TaskC<E> node) {
        this.value = node.getValue();
        this.setLeft(node.getLeft());
        this.setRight(node.getRight());
    }

    public TaskC<E> getMin(TaskC<E> curr) {
        while (curr.left.value != null) {
            curr = curr.left;
        }
        return curr;
    }

    public TaskC<E> deleteNode(TaskC<E> root, E key) {
        TaskC<E> parent = null;
        TaskC<E> curr = root;
        while (curr.value != null && !curr.value.equals(key)) {
            parent = curr;
            if (curr.value.hashCode() > key.hashCode()) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        if (curr.value == null) {return root;}
        if (curr.left.value == null && curr.right.value == null) {
            if (curr.value != root.value) {
                if (parent.left.value == curr.value) {
                    parent.left.value = null;
                } else {
                    parent.right.value = null;
                }
            } else {
                root.value = null;
            }
        } else if (curr.left.value != null && curr.right.value != null) {
            TaskC<E> successor = getMin(curr.right);
            E val = successor.value;
            deleteNode(root, successor.value);
            curr.value = val;
        } else {
            TaskC<E> child = (curr.left.value != null)? curr.left: curr.right;
            if (curr.value != root.value) {
                if (curr.value == parent.left.value) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            } else {
                root = child;
            }
        }
        return root;
    }

    //good
    @Override
    public boolean remove(Object o) {
        TaskC<E> root = deleteNode(this,(E)o);
        this.setNode(root);
        return root.value == this.value;
    }

    public void setLeft(TaskC<E> left) {
        this.left = left;
    }

    public void setRight(TaskC<E> right) {
        this.right = right;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public TaskC<E> getLeft() {
        return left;
    }

    public TaskC<E> getRight() {
        return right;
    }

    //good
    @Override
    public boolean add(E e) {
        if (!(e instanceof Comparable)) {
            return false;
        }
        if (this.getValue() == null) {
            this.setValue(e);
            this.setRight(new TaskC<>());
            this.setLeft(new TaskC<>());
            return true;
        }
        if (((Comparable)this.getValue()).compareTo(e) > 0) {
            return this.getLeft().add(e);
        }
        if (((Comparable)this.getValue()).compareTo(e) < 0){
            return this.getRight().add(e);
        }
        return false;
    }

    //good
    @Override
    public boolean contains(Object o) {
        if (this.getValue() == null) {
            return false;
        }
        if (((Comparable)o).compareTo(this.getValue()) > 0) {
            return this.getRight().contains(o);
        }
        if (((Comparable)o).compareTo(this.getValue()) < 0) {
            return this.getLeft().contains(o);
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        Stack<E> stack = new Stack<>();
        final TaskC<E>[] curr = new TaskC[]{this};
        Iterator<E> iterator = new Iterator<E>() {
            @Override
            public boolean hasNext() {

                return (!stack.isEmpty() || curr[0].value != null);
            }

            @Override
            public E next() {
                while (curr[0].value != null) {
                    stack.push(curr[0].value);
                    curr[0] = curr[0].left;
                }

                curr[0] = (TaskC<E>) stack.pop();
                TaskC<E> node = curr[0];
                curr[0] = curr[0].right;

                return node.value;
            }
        };
        return iterator;
    }

    //good
    @Override
    public void clear() {
        this.setValue(null);
        this.setRight(null);
        this.setLeft(null);
    }

    //good
    @Override
    public boolean isEmpty() {
        return this.getValue() == null;
    }

    //good
    @Override
    public int size() {
        if (this.getValue() == null) {
            return 0;
        }
        int l = this.getLeft().size();
        int r = this.getRight().size();
        return 1 + l + r;
    }

    //good
    @Override
    public E first() {
        if (this.getLeft() != null && this.getLeft().getValue()!= null) {
            return this.getLeft().first();
        }
        return this.getValue();
    }

    //good
    @Override
    public E last() {
        if (this.getRight() != null && this.getRight().getValue()!= null) {
            return this.getRight().last();
        }
        return this.getValue();
    }

    ArrayList<E> inArray(TaskC<E> root, ArrayList<E> arr){
        if (root.value != null) {
            arr = inArray(root.left,arr);
            arr.add(root.value);
            arr = inArray(root.right,arr);
        }
        return arr;

    }

    //good
    @Override
    public E lower(E e) {
        ArrayList<E> arr = new ArrayList<>();
        arr = inArray(this,arr);
        for(int i = 0; i < arr.size();i++){
            if (((Comparable)arr.get(i)).compareTo(e) < 0 && (i == arr.size() - 1 || ((Comparable)arr.get(i + 1)).compareTo(e) >= 0)){
                return arr.get(i);
            }
        }
        return null;
    }

    //good
    @Override
    public E floor(E e) {
        ArrayList<E> arr = new ArrayList<>();
        arr = inArray(this,arr);
        for(int i = 0; i < arr.size();i++){
            if (((Comparable)arr.get(i)).compareTo(e) <= 0 && (i == arr.size() - 1 || ((Comparable)arr.get(i + 1)).compareTo(e) > 0)){
                return arr.get(i);
            }
        }
        return null;
    }

    //good
    @Override
    public E ceiling(E e) {
        ArrayList<E> arr = new ArrayList<>();
        arr = inArray(this,arr);
        for(int i = 0; i < arr.size();i++){
            if (((Comparable)arr.get(i)).compareTo(e) >= 0 && (i == 0 || ((Comparable)arr.get(i - 1)).compareTo(e) < 0)){
                return arr.get(i);
            }
        }
        return null;
    }

    //good
    @Override
    public E higher(E e) {
        ArrayList<E> arr = new ArrayList<>();
        arr = inArray(this,arr);
        for(int i = 0; i < arr.size();i++){
            if (((Comparable)arr.get(i)).compareTo(e) > 0 && (i == 0 || ((Comparable)arr.get(i - 1)).compareTo(e) <= 0)){
                return arr.get(i);
            }
        }
        return null;
    }

    @Override
    public E pollFirst() {
        if (!isEmpty()) {
            E temp = this.first();
            this.remove(this.first());
            return temp;
        }
        return null;
    }

    //good
    @Override
    public E pollLast() {
        if (!isEmpty()) {
            E temp = this.last();
            this.remove(this.last());
            return temp;
        }
        return null;
    }


    private StringBuilder getStr(StringBuilder str) {
        if (this.getLeft() != null) {this.getLeft().getStr(str);}
        if (this.getValue() != null) {
            str.append(this.getValue());
            str.append(", ");
        }
        if (this.getRight() != null) {this.getRight().getStr(str);}
        return str;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        if (!isEmpty()) {
            getStr(str);
            str.replace(str.length()-2, str.length(), "]");
        } else {
            str.append("]");
        }
        return str.toString();
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
