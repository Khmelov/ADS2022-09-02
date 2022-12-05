package by.it.group151001.shlyk.lesson09;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ListB<T> implements List<T> {
    private class Item{
        Item next, prev;
        T value;
        private Item(T value){
            this.value = value;
        }
        private Item(T value, Item prev, Item next){
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private class ListItr implements ListIterator<T> {

        private Item next;
        private Item lastReturned;
        private int nextIndex;
        public ListItr(int index){
            next = (index == size) ? null : getItem(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return (nextIndex < size);
        }

        @Override
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException();
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.value;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public T previous() {
            if(!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? lastItem : next.prev;
            nextIndex -= 1;
            return lastReturned.value;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            if(lastReturned == null)
                throw new NoSuchElementException();
            Item lastNext = lastReturned.next;
            linkClose(lastReturned);

            //was on previous
            if(next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null; //we have returned nothing
        }

        @Override
        public void set(T t) {
            if(lastReturned == null)
                throw new NoSuchElementException();
            lastItem.value = t;
        }

        //from the iterator point view
        @Override
        public void add(T t) {
            lastReturned = null;
            if(next == null)
                linkLast(t);
            else
                linkBefore(next, t);
            nextIndex += 1;
        }
    }
    int size;
    Item firstItem;
    Item lastItem;

    void checkIndex(int index){
        if(size <= index || index < 0)
            throw new NoSuchElementException();
    }

    //Assume: last item is not empty
    private void linkFirst(T value){
        Item addable = new Item(value);
        addable.next = firstItem.next;
        addable.prev = firstItem;
        firstItem.next.prev = addable;
        firstItem.next = addable;
        this.size++;
    }
    private boolean linkLast(T value){
        Item addable = new Item(value);
        lastItem.next = addable;
        addable.prev = lastItem;
        lastItem = addable;
        this.size++;
        return true;
    }

    private boolean linkBefore(Item item, T value){
        Item addable = new Item(value);
        if(item.prev != null)
            item.prev.next = addable;
        else
            firstItem = addable;
        addable.prev = item.prev;
        addable.next = item;
        item.prev = addable;
        this.size++;
        return true;
    }
    //item is deletable
    private void linkClose(Item item){
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


    Item getItem(int index){
        //not check limits
        int nItem;
        Item item;
        if( index < (size >> 1 ) ){
            nItem =0;
            item = firstItem;
            while(nItem != index){
                item = item.next;
                nItem += 1;
            }
            return item;
        }
        else {
             item = lastItem;
             nItem = size - 1;
             while(nItem != index){
                 item = item.prev;
                 nItem -= 1;
             }
             return item;
        }
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
        Item item = firstItem;
        boolean isFound;
        if(o == null){
            while(item != null && (item.value != null))
                item = item.next;
        }
        else {
            while(item != null && !o.equals(item.value))
                item = item.next;
        }
        isFound = (item != null);
        return isFound;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new ListItr(0);
    }

    @NotNull
    @Override
    public Object[] toArray() {
        Object[] arrResult = new Object[size];
        int iItem = 0;
        for(Item item = firstItem; item != null; item = item.next){
            arrResult[iItem] = item;
            iItem += 1;
        }
        return arrResult;
    }

    @NotNull
    @Override
    public <S> S[] toArray(@NotNull S[] a) {
        if(size > a.length)
            a = (S[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        int iItem = 0;
        Item item = firstItem;
        Object[] result = a;
        while(item != null){
            result[iItem++] = item.value;
            item = item.next;
        }
        if(a.length > size)
            a[size] = null; //conventional note)

        return a;
    }

    @Override
    public boolean add(T t) {
        if(firstItem == null){
            firstItem = new Item(t);
            lastItem = firstItem;
            size = 1;
        }
        else {
            return linkLast(t);
        }
        return true;
    }


    @Override
    public boolean remove(Object o) {
        if(o == null){
            for(Item item = firstItem; item != null; item = item.next){
                if(item.value == null){
                    linkClose(item);
                    return true;
                }
            }
        }
        else {
            for(Item item = firstItem; item != null; item = item.next){
                if (o.equals(item)) {
                    linkClose(item);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        Object[] arrElements = c.toArray();
        for(Object obj : arrElements){
            if(!contains(obj))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends T> c) {
        if(index != size) //append to the end
            checkIndex(index);
        if(c.size() == 0)
            return false;

        Item first;
        first = new Item(c.iterator().next()); // dummy value
        Item last = first;
        //the idea -> not used condition inside cycle
        for(T value : c){
                last.next = new Item(value, last, null);
                last = last.next;
        }
        first = first.next;
        first.prev.next = null;

        if(index == size){
            lastItem.next = first;
            first.prev = lastItem;
            lastItem = last;
        }
        else {
           Item item = getItem(index);
           first.prev = item.prev;
           item.prev.next = first;
           last.next = item;
           item.prev = last;
        }
        size += c.size();
        return true;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {

        return true;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        if(firstItem == null)
            return;
        if(firstItem.next != null){
            for (Item item = firstItem.next; item != null; item = item.next) {
                item.prev.next = null;
                item.prev = null;
            }
        }
        firstItem = null;
        lastItem = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return getItem(index).value;
    }

    @Override
    public T set(int index, T element) {
            checkIndex(index);
            Item item = getItem(index);
            T oldValue = item.value;
            item.value = element;
            return oldValue;
    }

    @Override
    public void add(int index, T element) {
        if(index == size){
            linkLast(element);
        }
        else {
            checkIndex(index);
            Item item = getItem(index);
            linkBefore(item, element);
        }

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Item item = getItem(index);
        linkClose(item);
        return item.value;
    }

    @Override
    public int indexOf(Object o) {
        Item item = firstItem;
        int nItem = 0;
        if(o == null){
            while(item != null){
                if(item.value == null)
                    return nItem;
                nItem += 1;
                item = item.next;
            }
        }
        else {
            while(item != null){
                if(o.equals(item.value))
                    return nItem;
                nItem += 1;
                item = item.next;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Item item = lastItem;
        int nItem = 0;
        if(o == null){
            while(item != null){
                if(item.value == null)
                    return nItem;
                item = item.prev;
                nItem += 1;
            }
        }
        else {
            while(item != null){

                if(o.equals(item.value))
                    return nItem;
                item = item.prev;
                nItem += 1;
            }
        }
        return -1;
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListItr(index);
    }

    @NotNull
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        checkIndex(fromIndex);
        checkIndex(toIndex);
        if(fromIndex > toIndex){
            int hugeIndex = fromIndex;
            fromIndex = toIndex;
            toIndex = hugeIndex;
        }
        Item item = firstItem;
        List<T> smallList = new ListB<>();
        int nItem = 0;
        while(nItem != fromIndex){
            item = item.next;
            nItem += 1;
        }
        while(nItem <= toIndex){
            smallList.add(item.value);
            item = item.next;
            nItem += 1;
        }
        return smallList;
    }
    public String toString(){
        if(size == 0)
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Item item = firstItem;
        while(item.next != null){
            sb.append(item.value).append(", ");
            item = item.next;
        }
        sb.append(item.value).append("]");
        return sb.toString();

    }
}
