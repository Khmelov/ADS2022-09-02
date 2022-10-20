package by.it.group151003.polovoy.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C_HeapMax {

    private class MaxHeap {
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

        private List<Long> heap = new ArrayList<>();

        private int leftChild (int pos){ return 2*pos; }

        private int rightChild (int pos){ return 2*pos + 1; }

        private int parent (int pos){ return (pos-1) / 2; }

        private void swap (int pos1, int pos2){

            long temp = heap.get(pos1);
            heap.set(pos1, heap.get(pos2));
            heap.set(pos2 ,temp);
        }

        private int siftUp(int i){

            while(i > 0 && (heap.get(parent(i)) < heap.get(i)))
            {
                swap(parent(i), i);
                i = parent(i);
            }
            return  i;
        }

        void siftDown(int i) { //просеивание вверх

            int max = i;

            int left = leftChild(i);
            if (left < heap.size() && heap.get(left) > heap.get(max))
            {
                max = left;
            }

            int right = rightChild(i);
            if (right < heap.size() && heap.get(right) > heap.get(max))
            {
                max = right;
            }

            if (i != max)
            {
                swap(max, i);
                siftDown(i);
            }
        }

        void insert(Long value) {
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        Long extractMax() { //извлечение и удаление максимума
            Long result = null;
            if (heap.size() == 0) {return result;}

            result = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            siftDown(0);

            return result;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    }

    //эта процедура читает данные из файла, ее можно не менять.
    public Long findMaxValue(InputStream stream) {
        Long maxValue=0L;
        MaxHeap heap = new MaxHeap();

        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res=heap.extractMax();
                if (res!=null && res>maxValue) maxValue=res;
                System.out.println();
                i++;
            }
            if (s.contains(" ")) {
                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert"))
                    heap.insert(Long.parseLong(p[1]));
                i++;
            //System.out.println(heap); //debug
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX="+instance.findMaxValue(stream));
    }

}
