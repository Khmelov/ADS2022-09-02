package by.it.group151004.buhovets.lesson09;


import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;



public class ListA<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    transient  Object[] elementData;
    private static final Object[] DEFAULTCAPACITY_EMPTY={};

    public int size;

    public ListA(){
        this.elementData=DEFAULTCAPACITY_EMPTY;
    }

    @Override
    public int size() {
        return size;
    }



    public String toString(){

        String res="[";
        int dataLen;
        if((dataLen= elementData.length)<=0){
            return null;
        }

        for(int i=0; i< dataLen-1;i++){
            res+=elementData[i]+", ";
        }
        res+=elementData[dataLen-1]+"]";
        return res;
    }

    public E remove(int index){

        if(index>= elementData.length)
            return null;

        @SuppressWarnings("unchecked") E oldValue = (E) elementData[index];
        remove(index, elementData, size);
        return oldValue;
    }

    public void remove(int index, Object[] elementData, int s){

        int newSize=s-1;
        for(int i=index+1; i<elementData.length;i++){
            elementData[i-1]=elementData[i];
        }
        elementData[newSize]=null;
        grow(newSize);
        size=s-1;
    }

    public Object[] grow(int newCapacity){
        if(elementData.length==0){
            return elementData = new Object[newCapacity];
        }
        else{
            return elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public void add(E e, Object[] elementData, int s){
        if(s== elementData.length)
            elementData = grow(size+1);
        elementData[s]=e;
        size=s+1;
    }

    public boolean add(E e){
        add(e, elementData, size);
        return true;
    }

    @Override
    public E get(int index) {
        if(index>=elementData.length)
            return null;

        E getValue = (E)elementData[index];
        return getValue;

    }

    public static void  main(String[] args){
        ListA<Integer> a = new ListA<>();
        a.add(10);
        a.add(15);
        System.out.println(a.toString());
        System.out.println(a.remove(0));
        a.add(100);
        a.add(200);
        System.out.println(a.toString());
        System.out.println(a.get(2));
    }
}
