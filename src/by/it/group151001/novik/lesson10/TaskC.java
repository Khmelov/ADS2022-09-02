package by.it.group151001.novik.lesson10;

import java.util.*;

public class TaskC<E>  implements NavigableSet<E> {

    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet

    private E value;
    private TaskC<E> left;
    private TaskC<E> right;
    private void SetKey(TaskC<E> elem){
        this.left = elem.left;
        this.right = elem.right;
        this.value = elem.value;
    }
    public TaskC() {
        this.value = null;
        this.left = null;
        this.right = null;

    }

    public static void main(String[] args) {
        TaskC<Integer> tree = new TaskC<>();

        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(9);
        tree.add(6);
        tree.add(0);
        tree.add(7);
        tree.add(8);
        tree.add(10);
        tree.add(12);
        tree.add(14);
        int x = tree.lower(5);
        x = tree.higher(5);
        x = tree.ceiling(7);
        x = tree.floor(4);
    }


    @Override
    public boolean add(E e) {
        if (this.value == null){
            this.value = e;
            this.left = new TaskC<>();
            this.right = new TaskC<>();
            return true;
        }else{
            if (this.value.hashCode() < e.hashCode()){
                return this.right.add(e);
            }
            if (this.value.hashCode() > e.hashCode()){
                return this.left.add(e);
            }
        }
        return false;
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
        // указатель для хранения родителя текущего узла
        TaskC<E> parent = null;

        // начинаем с корневого узла
        TaskC<E> curr = root;
        System.out.println(curr.value);
        // поиск ключа в BST и установка его родительского указателя
        while (curr.value != null && !curr.value.equals(key))
        {
            // обновить родителя до текущего узла
            parent = curr;

            // если заданный ключ меньше текущего узла, переходим в левое поддерево;
            // иначе идем в правое поддерево
            if (curr.value.hashCode() > key.hashCode()) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }

        // возвращаем, если ключ не найден в дереве
        if (curr.value == null) {
            return root;
        }

        // Случай 1: удаляемый узел не имеет дочерних элементов, т. е. является листовым узлом
        if (curr.left.value == null && curr.right.value == null)
        {
            // если удаляемый узел не является корневым узлом, то устанавливаем его
            // родительский левый/правый дочерний элемент в null
            if (curr.value != root.value)
            {
                if (parent.left.value == curr.value) {
                    parent.left.value = null;
                }
                else {
                    parent.right.value = null;
                }
            }
            // если дерево имеет только корневой узел, устанавливаем его в null
            else {
                root.value = null;
            }
        }

        // Случай 2: удаляемый узел имеет двух потомков
        else if (curr.left.value != null && curr.right.value != null)
        {
            // найти его неупорядоченный узел-преемник
            TaskC<E> successor = getMinimumKey(curr.right);

            // сохраняем последующее значение
            E val = successor.value;

            // рекурсивно удаляем преемника. Обратите внимание, что преемник
            // будет иметь не более одного потомка (правого потомка)
            deleteNode(root, successor.value);

            // копируем значение преемника в текущий узел
            curr.value = val;
        }

        // Случай 3: удаляемый узел имеет только одного потомка
        else {
            // выбираем дочерний узел
            TaskC<E> child = (curr.left.value != null)? curr.left: curr.right;

            // если удаляемый узел не является корневым узлом, устанавливаем его родителя
            // своему потомку
            if (curr.value != root.value)
            {
                if (curr.value == parent.left.value) {
                    parent.left = child;
                }
                else {
                    parent.right = child;
                }
            }

            // если удаляемый узел является корневым узлом, то установить корень дочернему
            else {
                root = child;

            }
        }

        return root;
    }
    @Override

    public boolean remove(Object o) {
        TaskC<E> root = deleteNode(this,(E)o);
        this.SetKey( root);
        return root.value == this.value;
    }

    @Override
    public boolean contains(Object o) {
        if (this.value == null){
            return false;
        }
        {
            TaskC<E> curr = this;
            while (curr.value != null) {
                if (curr.value.equals(o)){
                    return true;
                }
                if (curr.value.hashCode() > o.hashCode()) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
            return false;
        }
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
        if(this.value != null){
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
        int s = 0;
        if (this.value == null){
            return s;
        }
        TaskC<E> l = this.left;
        TaskC<E> r = this.right;
        s++;
        if (l.value != null){
            s += l.size();
        }
        if (r.value != null){
            s += r.size();
        }
        return s;
    }

    @Override
    public E first() {
        if (this.value != null) {
            TaskC<E> curr = this;
            while (curr.left.value != null) {
                curr = curr.left;
            }
            return curr.value;
        }else return null;
    }

    @Override
    public E last() {
        if (this.value != null) {
            TaskC<E> curr = this;
            while (curr.right.value != null) {
                curr = curr.right;
            }
            return curr.value;
        }else return null;
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
    public E lower(E e) {
        ArrayList<E> arr = new ArrayList<>();
        arr = inArray(this,arr);
        Collections.sort(arr, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                if (o1.hashCode() > o2.hashCode()){
                    return -1;
                }else if(o1.hashCode() < o2.hashCode()){
                    return 1;
                }else return 0;
            }
        });
        for(int i = 0; i < arr.size();i++){
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
                    return -1;
                }else if(o1.hashCode() < o2.hashCode()){
                    return 1;
                }else return 0;
            }
        });
        for(int i = 0; i < arr.size();i++){
            if (arr.get(i).hashCode() <= e.hashCode()){
                return arr.get(i);
            }
        }
        return null;


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
        E data = this.first();
        if (data != null){
            remove(data);
            return data;
        }
        return null;
    }

    @Override
    public E pollLast() {
        E data = this.last();
        if (data != null){
            remove(data);
            return data;
        }
        return null;
    }



    private void subString(StringBuilder sb) {
        if(this.value == null){
            return;
        }
        this.left.subString(sb);
        sb.append(this.value);
        sb.append(", ");
        this.right.subString(sb);
        return;
    }
    @Override
    public String toString() {
        if (this.value == null){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        left.subString(sb);
        sb.append(this.value);
        sb.append(", ");
        right.subString(sb);
        sb.delete(sb.length() - 2,sb.length());
        sb.append("]");
        return sb.toString();

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
