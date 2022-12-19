package lesson09;

import java.util.Iterator;
import java.util.List;

class lEl<T>{
    T data;
    lEl next;
}
public class ListB<T>{
    lEl l_hd;
//numeration always begins with zero
    public void add(T dt){
        lEl curr = l_hd;
        if(curr == null){
            curr = new lEl();
            curr.data = dt;
            curr.next = null;
        }
        else{
            while(curr.next != null)
                curr = curr.next;
            curr.next = new lEl();
            curr.next.data = dt;
            curr.next.next = null;
        }
    }

    public int add(int index, T dt){
        lEl curr = new lEl();
        int codeNum = -1;
        curr = l_hd;
        int i = 0;
        while (curr != null && curr.next != null && i < index-1){
            curr = curr.next;
            i++;
        }
        if (curr != null && i == index-1){
            lEl nEl = new lEl();
            nEl.data = dt;
            codeNum = 0;
            if (curr.next == null)
                nEl.next = null;
            else
                nEl.next = curr.next;
            curr.next = nEl;
        }
        return codeNum;
    }

    public int remove(int index){
        int codeNum = -1;
        lEl curr = new lEl();
        curr = l_hd;
        int i =0;
        while (curr != null && i < index-1 && curr.next != null)
            curr = curr.next;
        if(curr!=null&&curr.next!=null&&i==index-1){
            codeNum = 0;
            curr.next=curr.next.next;
        }
        return codeNum;
    }

    public T get(int index){
        T dt = null;
        lEl curr = new lEl();
        curr = l_hd;
        int i = 0;
        while(curr != null && i < index){
            curr =curr.next;
            i++;
        }
        if(curr != null && i==index)
            dt = (T)curr.data;
        return dt;
    }

    public int set(int index, T e){
        int codeNum = -1;
        lEl curr;
        curr = l_hd;
        int i = 0;
        while(curr != null && i < index){
            curr = curr.next;
            i++;
        }
        if(curr != null && i==index){
            curr.data = e;
            codeNum = 0;
        }
        return codeNum;
    }

    public void addAll(List<? extends T> c){
        for(int i = 0; i < c.size(); i++){
            T data = c.get(i);
            add(data);
        }
    }

    public boolean contains(T dt){
        lEl curr = l_hd;
        boolean isNotFound = true;
        while((curr != null)&&isNotFound){
            isNotFound = curr.data == dt;
            curr = curr.next;
        }
        return !isNotFound;
    }

    public int size(){
        int i=0;
        lEl curr = l_hd;
        while(curr!=null){
            curr =curr.next;
            i++;
        }
        return i;
    }

    @Override
    public String toString() {
        if(l_hd == null)
            return "\n";
        else
            return l_hd.data+"";
    }
}
