package by.it.group151001.trybchik.lesson10;

import java.util.*;

public class TaskC<E extends Comparable<E>>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы
    private E value;
    private TaskC<E> left;
    private TaskC<E> right;
    //Обязательные к реализации методы и конструкторы
    public TaskC() {
        this.value=null;
        this.left = null;
        this.right = null;
    }

    private void SetKey(TaskC<E> e){
        this.value = e.value;
        this.left = e.left;
        this.right =e.right;
    }
    public TaskC<E> getMinimumKey(TaskC<E> curr)
    {
        while (curr.left.value != null) {
            curr = curr.left;
        }
        return curr;
    }
    public TaskC<E> deleteNode(TaskC<E> root, E key)
    {

        TaskC<E> parent = null;


        TaskC<E> curr = root;
        System.out.println(curr.value);

        while (curr.value != null && !curr.value.equals(key))
        {

            parent = curr;


            if (curr.value.hashCode() > key.hashCode()) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }


        if (curr.value == null) {
            return root;
        }


        if (curr.left.value == null && curr.right.value == null)
        {

            if (curr.value != root.value)
            {
                if (parent.left.value == curr.value) {
                    parent.left.value = null;
                }
                else {
                    parent.right.value = null;
                }
            }

            else {
                root.value = null;
            }
        }


        else if (curr.left.value != null && curr.right.value != null)
        {

            TaskC<E> successor = getMinimumKey(curr.right);


            E val = successor.value;

            deleteNode(root, successor.value);


            curr.value = val;
        }


        else {

            TaskC<E> child = (curr.left.value != null)? curr.left: curr.right;



            if (curr.value != root.value)
            {
                if (curr.value == parent.left.value) {
                    parent.left = child;
                }
                else {
                    parent.right = child;
                }
            }

            else {
                root = child;

            }
        }

        return root;
    }

    @Override
    public boolean add(E e) {
        if(this.value == null)
        {
            this.value = e;
            this.left = new TaskC<>();
            this.right =  new TaskC<>();
        }else {
            if(this.value.hashCode() < e.hashCode()){
                return this.right.add(e);
            }
            if(this.value.hashCode()> e.hashCode())
            {
                return this.left.add(e);
            }

        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        TaskC<E> root =deleteNode(this,(E)o);
        this.SetKey(root);
        return root.value == this.value;
    }
    private void substr(StringBuilder sb)
    {
        if(this.value ==null)
        {
            return;
        }
        this.left.substr(sb);
        sb.append(this.value);
        sb.append(", ");
        this.right.substr(sb);

    }
    @Override
    public String toString() {
        if (this.value == null)
        {
            return "[]";
        }
        StringBuilder sb= new StringBuilder();
        sb.append("[");
        this.left.substr(sb);
        sb.append(this.value);
        sb.append(", ");
        this.right.substr(sb);
        sb.delete(sb.length()-2,sb.length());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean contains(Object o) {
        if(this.isEmpty()){return false;}
        TaskC<E> curr = this;
        while (curr.value != null)
        {
            if(curr.value.equals(o))
            {
                return true;
            }
            if(curr.value.hashCode()>o.hashCode())
            {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }
        return false;
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

    @Override
    public void clear() {
        if(this.value != null)
        {
            this.left.clear();
            this.right.clear();
            this.value = null;
            this.left = null;
            this.right = null;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.value == null;
    }

    @Override
    public int size() {
        int S = 0;
        if(this.isEmpty())
        {
            return S;
        }
        TaskC<E> left = this.left;
        TaskC<E> right  =this.right;
        S++;
        if(left.value !=null)
        {
            S+=left.size();
        }
        if(right.value != null)
        {
            S+=right.size();
        }
        return S;
    }

    @Override
    public E first() {
        TaskC<E> curr = this;
        while (curr.left.value != null)
        {
            curr = curr.left;
        }
        return curr.value;
    }

    @Override
    public E last() {
        TaskC<E> curr = this;
        while(curr.right.value !=null)
        {
            curr = curr.right;
        }
        return curr.value;
    }
    @Override
    public E lower(E e) {
        ArrayList<E> arr = new ArrayList<>();
        arr = inArray(this,arr);
        Collections.sort(arr, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                if (o1.hashCode() > o2.hashCode()){
                    return 1;
                }else if(o1.hashCode() < o2.hashCode()){
                    return -1;
                }else return 0;
            }
        });
        for(int i = arr.size()-1; i >=0 ;i--){
            if (arr.get(i).hashCode() < e.hashCode()){
                return arr.get(i);
            }
        }
        return null;
    }

    @Override
    public E floor(E e) {
        ArrayList<E> arr = new ArrayList<>();
        arr = inArray(this,arr);
        Collections.sort(arr, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                if (o1.hashCode() > o2.hashCode()){
                    return 1;
                }else if(o1.hashCode() < o2.hashCode()){
                    return -1;
                }else return 0;
            }
        });
        for(int i = arr.size()-1; i >=0 ;i--){
            if (arr.get(i).hashCode() <= e.hashCode()){
                return arr.get(i);
            }
        }
        return null;
    }
    ArrayList<E> inArray(TaskC<E> root, ArrayList<E> arr){
        if (root.value != null) {
            arr.add(root.value);
            arr = inArray(root.left,arr);
            arr = inArray(root.right,arr);
        }
        return arr;

    }
    @Override
    public E ceiling(E e) {
        ArrayList<E> arr = new ArrayList<>();
        arr = inArray(this,arr);
        Collections.sort(arr, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                if (o1.hashCode() > o2.hashCode()){
                    return 1;
                }else if(o1.hashCode() < o2.hashCode()){
                    return -1;
                }else return 0;
            }
        });
        for(int i = 0; i < arr.size();i++){
            if (arr.get(i).hashCode() >= e.hashCode()){
                return arr.get(i);
            }
        }
        return null;
    }

    @Override
    public E higher(E e) {

        ArrayList<E> arr = new ArrayList<>();
        arr = inArray(this,arr);
        Collections.sort(arr, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                if (o1.hashCode() > o2.hashCode()){
                    return 1;
                }else if(o1.hashCode() < o2.hashCode()){
                    return -1;
                }else return 0;
            }
        });
        for(int i = 0; i < arr.size();i++){
            if (arr.get(i).hashCode() > e.hashCode()){
                return arr.get(i);
            }
        }
        return null;
    }

    @Override
    public E pollFirst() {
        if(this.isEmpty()) {return null;}
        E curr = this.first();
        remove(curr);
        return curr;
    }

    @Override
    public E pollLast() {
        if(this.isEmpty()) {return null;}
        E last = this.last();
        remove(last);
        return last;
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
