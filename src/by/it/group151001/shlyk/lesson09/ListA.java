package by.it.group151001.shlyk.lesson09;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ListA<T> implements List<T> {
    private class Item {
        Item next;
        Item prev;
        T value;
        private Item(T value){
            this.value = value;
        }
    }
    Item firstItem;
    Item lastItem;
    private int size;

    public ListA(){
    }
    private boolean linkLast(T value){
        if(contains(value))
            return false;
        Item addable = new Item(value);
        lastItem.next = addable;
        addable.prev = lastItem;
        lastItem = addable;
        this.size++;
        return true;
    }
    private void linkClose(Item item){
        //this item is not null
        if(item.next == null){
            lastItem = item.prev;
        }
        else {
            item.next.prev = item.prev;
        }
        if(item.prev == null){
            firstItem = item.next;
        }
        else {
            item.prev.next = item.next;
        }
        size -= 1;

    }
    void checkIndex(int index){
        if(size <= index || index < 0)
            throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        Item item = firstItem;
        boolean isFound = false;
        while(item != null && !o.equals(item.value))
            item = item.next;
        isFound = (item != null) && o.equals(item.value);
        return isFound;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NotNull
    @Override
    public <T1> T1[] toArray(@NotNull T1[] a) {
        return null;
    }

    //using stack to add
    public boolean add(T value){
        if(firstItem == null) {
            firstItem = new Item(value);
            lastItem = firstItem;
            size = 1;
        }
        else {
            return linkLast(value);
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    public T remove (int index){
        checkIndex(index);
        Item deletable = getItem(index);
        linkClose(deletable);
        return deletable.value;
        //dynamic memory management

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @NotNull
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private Item getItem(int index){
        //not check index)
        Item item;
        if(index > (size >> 1) ){
         item = lastItem;
         int nIndex = size - 1;
         while(nIndex != index){
             item = item.prev;
             nIndex -= 1;
         }
        }
        else {
         item = firstItem;
         int nIndex = 0;
         while(nIndex != index){
             item = item.next;
             nIndex += 1;
         }
        }
        return item;
    }
    public T get(int index){
        checkIndex(index);
        return getItem(index).value;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    public String toString(){
        if(size == 0)
            return "[]";
        StringBuilder strOutput = new StringBuilder();
        Item item = firstItem;
        strOutput.append('[');
        while(item != null){
            strOutput.append(item.value).append(", ");
            item = item.next;
        }
        strOutput.delete(strOutput.length() - 2, strOutput.length() );
        strOutput.append(']');
        return strOutput.toString();
    }
}
