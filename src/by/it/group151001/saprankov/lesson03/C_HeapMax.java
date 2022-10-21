package by.it.group151001.saprankov.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Lesson 3. C_Heap.
// Задача: построить max-кучу = пирамиду = бинарное сбалансированное дерево на массиве.
// ВАЖНО! НЕЛЬЗЯ ИСПОЛЬЗОВАТЬ НИКАКИЕ КОЛЛЕКЦИИ, КРОМЕ ARRAYLIST (его можно, но только для массива)

//      Проверка проводится по данным файла
//      Первая строка входа содержит число операций 1 ≤ n ≤ 100000.
//      Каждая из последующих nn строк задают операцию одного из следующих двух типов:

//      Insert x, где 0 ≤ x ≤ 1000000000 — целое число;
//      ExtractMax.

//      Первая операция добавляет число x в очередь с приоритетами,
//      вторая — извлекает максимальное число и выводит его.

//      Sample Input:
//      6
//      Insert 200
//      Insert 10
//      ExtractMax
//      Insert 5
//      Insert 500
//      ExtractMax
//
//      Sample Output:
//      200
//      500


public class C_HeapMax {

    private class MaxHeap {
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        private List<Long> heap = new ArrayList<>();


        int siftDown(int i) { //просеивание вверх
            int lCh=2*i+1;
            int rCh=2*i+2;
            int lrgCh=i;
            long tmp=0;

            while(!((heap.size()>rCh&&(heap.get(i)>heap.get(rCh)))&&(heap.size()>lCh&&(heap.get(i)>heap.get(rCh))))){
                if(lCh< heap.size()&&heap.get(lCh)>heap.get(lrgCh)){
                    lrgCh=lCh;
                }
                if(rCh<heap.size()&&heap.get(rCh)>heap.get(lrgCh)){
                    lrgCh=rCh;
                }
                if(lrgCh==i){
                    break;
                }
                tmp=heap.get(lrgCh);
                heap.set(lrgCh,heap.get(i));
                heap.set(i,tmp);
            }
            return i;
        }

        int siftUp(int i) { //просеивание вниз

            return i;
        }

        void insert(Long value) {

            //вставка
            long tmp;
            heap.add(heap.size(),value);
            int i = heap.size()-1;
            int par=(i-1)/2;
            while(i>0&&heap.get(par)<heap.get(i)){
                tmp=heap.get(par);
                heap.set(par,heap.get(i));
                heap.set(i,tmp);
                i=par;
                par=(i-1)/2;
            }
        }

        Long extractMax() { //извлечение и удаление максимума
            Long result = null;
            result = heap.get(0);
            heap.set(0,heap.get(heap.size()-1));
            heap.remove(heap.size()-1);
            siftDown(0);
            return result;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    }

    //эта процедура читает данные из файла, ее можно не менять.
    Long findMaxValue(InputStream stream) {
        Long maxValue=0L;
        MaxHeap heap = new MaxHeap();
        //прочитаем строку для кодирования из тестового файла
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

    // РЕМАРКА. Это задание исключительно учебное.
    // Свои собственные кучи нужны довольно редко.
    // "В реальном бою" все существенно иначе. Изучите и используйте коллекции
    // TreeSet, TreeMap, PriorityQueue и т.д. с нужным CompareTo() для объекта внутри.
}
