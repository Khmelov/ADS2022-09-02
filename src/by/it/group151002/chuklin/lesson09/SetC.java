package by.it.group151002.chuklin.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {

    ListB<T> set = new ListB<>();

    public SetC(){

    }

    public String toString() {
        return set.toString();
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i < set.size(); i++){
            if(o == null){
                if(set.get(i) == null) {
                    return true;
                }
            }else{
                if(o.equals(set.get(i))){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if(!contains(t)) {
            set.add(t);
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(!contains(o)){
            return false;
        }
        for(int i = 0; i < set.size(); i++){
            if(o == null){
                if(set.get(i) == null) {
                    set.remove(i);
                }
            }else{
                if(o.equals(set.get(i))){
                    set.remove(i);
                }
            }
        }
        return true;

    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object object : c){
            if(!contains(object)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for(Object object : c){
            if(!contains(object)){
                add((T)object);
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object object : c){
            remove(object);
        }
        return true;
    }

    @Override
    public void clear() {
        set.clear();

    }
}
