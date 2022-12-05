package by.it.group151004.buhovets.lesson09;


import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;

public class ListB<E> extends ListA<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {



    private static final Object[] DEFAULTCAPACITY_EMPTY={};

    public ListB(){
        this.elementData=DEFAULTCAPACITY_EMPTY;
    }

    public E set(int index, E e){
        if(index>=elementData.length)
            return null;
        E OldValue = (E)elementData[index];
        elementData[index]=e;
        return OldValue;
    }

    public void add(int index, E e){
        if(index> elementData.length)
            return;
        size++;
        System.out.println(size);
        elementData = grow(size);
        System.arraycopy(elementData, index, elementData, index+1, size-1-index);
        elementData[index]=e;
    }

    public boolean addAll(List<? extends E> c){
        Object[] a=c.toArray();
        int IncSize=a.length;
        if(IncSize==0)
            return false;
        int lastID= elementData.length-1;
        elementData = grow(size+IncSize);

        for(int i=0;i<IncSize;i++){
            lastID++;
            elementData[lastID]=a[i];
        }
        return true;
    }



    public static void  main(String[] args){
        ListB<Integer> a = new ListB<>();
        a.add(10);
        a.add(15);
        System.out.println(a.toString());
        System.out.println(a.remove(0));
        a.add(100);
        a.add(200);
        System.out.println(a.toString());
        System.out.println(a.set(2,-100));
        a.add(1,1000);
        a.add(100,1);

        System.out.println(a.toString());
    }

}
