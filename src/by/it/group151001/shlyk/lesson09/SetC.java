package by.it.group151001.shlyk.lesson09;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SetC<T> implements Set<T> {

    private class HashEntry implements Iterator<T>{

        private int nextIndex;
        HashEntry(){}
        @Override
        public boolean hasNext() {
            return (nextIndex < size);
        }

        @Override
        public T next() {
            T nextValue = table[nextIndex].getValue();
            nextIndex += 1;
            return nextValue;
        }
    }
    private class HashItem<T> {
        private T value;

        void updateValue(T value){
            this.value = value;
        }
        public T getValue(){
            return value;
        }
        HashItem(T value){
            this.value = value;
        }

    }
    final static int DEFAULT_CAPACITY = 100;
    private LinkedList<T>[] hashMap;
    private HashItem<T>[] table;

    private int mapCapacity;
    private int size;

    private static final int MAGIC_NUMBER = 42;
    Integer getHashCode(Object o){
        if(o == null)
            return Math.abs(MAGIC_NUMBER % mapCapacity);
        return Math.abs(o.hashCode() % mapCapacity);
    }
    public SetC(){
        hashMap = new LinkedList[DEFAULT_CAPACITY];
        mapCapacity = DEFAULT_CAPACITY;
        size = 0;
        table = new HashItem[DEFAULT_CAPACITY];
        updateHashMap();
    }

    private void updateHashMap(){
        LinkedList<T>[] hugeMap = new LinkedList[mapCapacity];
        System.arraycopy(hashMap, 0, hugeMap, 0, hashMap.length);
        hashMap = hugeMap;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int hashIndex = getHashCode(o);
        LinkedList<T> ltOverflow;
        if((mapCapacity < hashIndex) || (hashMap[hashIndex] == null))
            return false;
        ltOverflow = hashMap[hashIndex];
        return ltOverflow.contains(o);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new HashEntry();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(table, 0, result, 0, size);
        return result;
    }

    @NotNull
    @Override
    public <S> S[] toArray(@NotNull S[] a) {
        if(a.length < size)
            a = (S[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        Object[] result = a;
        System.arraycopy(table, 0, result, 0, size);
        if(a.length > size)
            a[size] = null;
        return a;
    }


    private void addToTable(T value){
        if(table.length < size)
            table = Arrays.copyOf(table, table.length * 2);
        int lastIndex = size - 1;
        if(table[lastIndex] == null)
            table[lastIndex] = new HashItem<>(value);
        else
            table[lastIndex].updateValue(value);
    }
    private void convertToTable(Object[] source){
        if(source.length == 0)
            throw new NoSuchElementException();
        size = source.length;
        updateTable();
        System.arraycopy(source, 0, table, 0, size);
    }

    //if length of table is reduced -> realloc memory
    private void updateTable(){
        if(table.length == 0)
            table = new HashItem[DEFAULT_CAPACITY];
        if( (table.length >> 2) > size)
            table = Arrays.copyOf(table, size + 1);
    }
    @Override
    public boolean add(T t) {
        int hashIndex = getHashCode(t);
        LinkedList<T> ltOverflow;
        boolean isAdded = false;
        if(hashMap[hashIndex] == null){
            ltOverflow = new LinkedList<>();
            hashMap[hashIndex] = ltOverflow;
        }
        else {
            ltOverflow = hashMap[hashIndex];
        }
        if(!ltOverflow.contains(t)){
            isAdded = true;
            ltOverflow.add(t);
            size += 1;
            addToTable(t);
        }
        return isAdded;
    }

    @Override
    public boolean remove(Object o) {
        LinkedList<T> ltOverflow;
        int hashIndex = getHashCode(o);
        boolean isRemoved;
        if ( (mapCapacity < hashIndex) || (hashMap[hashIndex] == null) )
            return false;

        ltOverflow = hashMap[hashIndex];
        isRemoved = ltOverflow.remove(o);
        size -= (isRemoved) ? 1 : 0;
        updateTable();
        return isRemoved;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        Object[] items = c.toArray();
        for(Object item : items){
            if(!contains(item))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        boolean isChanged = false;
        for(T value : c)
            isChanged = (add(value) || isChanged);
        return isChanged;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        clear();
        T[] source = (T[]) c.toArray();
        return this.addAll(Arrays.asList(source));
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        boolean isRemoved = false;
        for(Object item: c){
            isRemoved = (remove(item) || isRemoved);
        }
        return isRemoved;
    }

    @Override
    public void clear() {
        for (LinkedList<T> ltOverflow : hashMap)
            if(ltOverflow != null)
                ltOverflow.clear();
        size = 0;
        updateTable();
    }
    public String toString(){
        if(size == 0)
            return "[]";
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("[");
        int i;
        for(i = 0; i < (size - 1); i++){
            strBuilder.append(table[i].getValue()).append(", ");
        }
        strBuilder.append(table[i].getValue()).append("]");
        return strBuilder.toString();
    }
}
