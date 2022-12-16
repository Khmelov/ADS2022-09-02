package lesson09;

class ListEl<T>{
    ListEl next;
    T data;
}

public class ListA<T> {
    private ListEl L_Head;
    private ListEl L_Tail;

    void add(T e){
        ListEl nEl = new ListEl();
        nEl.next = null;
        nEl.data = e;
        if (L_Head ==null){
            L_Head = nEl;
            L_Tail = nEl;
        }else{
            L_Tail.next = nEl;
            L_Tail = nEl;
        }
    }

    int remove(int index){//нумерация с 0
        int codeNum = 0;
        ListEl curr = L_Head;
        if(curr != null){
            int i = 0;
            if (index == 0)
                L_Head = curr.next;
            else{
                while((curr != null)&&(i < index-1)){
                    curr = curr.next;
                    i++;
                }
                if (curr == null || curr.next == null || index < 0)
                    codeNum = -1;
                else
                    curr.next = curr.next.next;
            }
        }else
            codeNum = -1;
        if (codeNum < 0)
            System.out.println("Ошибка удаления!\n");
        curr = L_Head;
        return codeNum;
    }

    T get(int index){
        int i = 0;
        ListEl curr = L_Head;
        while ((i < index)&&(curr != null)){
            curr = curr.next;
            i++;
        }
        if((curr!= null)&&(index > 0)){
            T ret = (T)curr.data;
            curr = L_Head;
            return ret;
        }

        else{
            curr = L_Head;
            return (T)(null);
        }

    }

    @Override
    public String toString() {
        if(L_Head == null)
            return "\n";
        else
            return L_Head.data+"";
    }
}

