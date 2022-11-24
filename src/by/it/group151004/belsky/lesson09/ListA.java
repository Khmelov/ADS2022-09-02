package by.it.group151004.belsky.lesson09;

import java.util.ArrayList;
import java.util.List;

class ListAEl<T> {
    T data;
    ListAEl<T> next;

    public ListAEl() {
        this.data = null;
        this.next = null;
    }

    public ListAEl(T data) {
        this.data = data;
        this.next = null;
    }

    public ListAEl(T data, ListAEl<T> next) {
        this.data = data;
        this.next = next;
    }
}

public class ListA<T> {

    private final ListAEl<T> rootEl;
    private int size;

    public ListA() {
        rootEl = new ListAEl<>();
        size = 0;
    }

    private boolean indexOutOfBounds(int index) {
        return index >= size || index < 0;
    }

    public T get(int index) {
        if (indexOutOfBounds(index)) throw new IndexOutOfBoundsException("Index: "+index+", Size: "+this.size);
        ListAEl<T> loopEl = rootEl;
        int i = 0;
        while (loopEl.next != null) {
            loopEl = loopEl.next;

            if (i == index) {
                return loopEl.data;
            }

            i+=1;
        }
        return null;
    }

    public void add(T element) {
        ListAEl<T> loopEl = rootEl;
        while (loopEl.next != null) loopEl = loopEl.next;
        loopEl.next = new ListAEl<>(element);
        this.size+=1;
    }

    public void add(int index, T element) {
        if (index < 0 || index > this.size) throw new IndexOutOfBoundsException("Index: "+index+", Size: "+this.size);
        ListAEl<T> loopEl = rootEl;

        if (loopEl.next == null) {
            loopEl.next = new ListAEl<>(element);
            this.size+=1;
            return;
        } else if (index == 0) {
            rootEl.next = new ListAEl<>(element, rootEl.next);
            this.size+=1;
            return;
        }

        int i = 0;
        while(loopEl.next != null) {
            loopEl = loopEl.next;
            if (i == index-1) {
                break;
            }
            i+=1;
        }
        ListAEl<T> bufEl = loopEl.next;
        loopEl.next = new ListAEl<>(element, bufEl);
        this.size+=1;
    }

    public boolean addAll(List<T> pList) {
        pList.forEach(this::add);
        return true;
    }

    public T set(int index, T element) throws IndexOutOfBoundsException {
        ListAEl<T> loopEl = rootEl;
        int i = 0;
        while (loopEl.next != null) {
            loopEl = loopEl.next;
            if (i == index) {
                T bufEl = loopEl.data;
                loopEl.data = element;
                return bufEl;
            }
            i+=1;
        }
        throw new IndexOutOfBoundsException("Index: "+index+", Size: "+0);
    }

    public T remove(int index) {
        if (indexOutOfBounds(index)) throw new IndexOutOfBoundsException("Index: "+index+", Size: "+this.size);

        int i = 0;
        boolean selected = false;
        ListAEl<T> loopEl = rootEl;
        while (loopEl.next != null) {
            if (index == i) {
                break;
            }
            loopEl = loopEl.next;
            i+=1;
        }

        if (rootEl.next != null) {
            size-=1;
            T bufT = loopEl.next.data;
            loopEl.next = loopEl.next.next;
            return bufT;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        ListAEl<T> loopEl = rootEl;
        str.append('[');
        while (loopEl.next != null) {
            loopEl = loopEl.next;
            str.append(loopEl.data.toString());
            if (loopEl.next != null) str.append(", ");
        }
        str.append(']');
        return str.toString();
    }

    public int size() {
        return this.size;
    }
}
