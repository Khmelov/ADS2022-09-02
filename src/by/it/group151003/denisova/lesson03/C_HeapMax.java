package by.it.group151003.denisova.lesson03;

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
        //Двоичная куча представляет собой полное бинарное дерево, для которого выполняется основное свойство кучи:
        // приоритет каждой вершины больше приоритетов её потомков
        //В простейшем случае приоритет каждой вершины можно считать равным её значению. В таком случае
        // структура называется max-куча
        //Двоичную кучу удобно хранить в виде одномерного массива, причем
        //левый потомок вершины с индексом i имеет индекс 2*i+1,
        //правый потомок вершины с индексом i имеет индекс 2*i+2.
        private List<Long> heap = new ArrayList<>();

        int siftDown(int i) { //просеивание вниз
            while (i*2+1<heap.size()){
                int Kid=i*2+1;
                if(Kid+1<heap.size()){
                    if(heap.get(Kid+1)>heap.get(Kid)){
                        Kid++;
                    }
                }
                if(heap.get(Kid)>heap.get(i)){
                    long tmp=heap.get(i);
                    heap.set(i,heap.get(Kid));
                    heap.set(Kid, tmp);
                    i=Kid;
                }
                else break;
            }
            return i;
        }

        int siftUp(int i) { //просеивание вверх
          while(i>0)
          {
              int Predk=(i-1)/2;
              if(heap.get(i)>heap.get(Predk)){
                  long tmp=heap.get(Predk);
                  heap.set(Predk,heap.get(i));
                  heap.set(i, tmp);
                  i=Predk;
              }
              else break;
          }
            return i;
        }

        void insert(Long value) { //вставка нового элемента
            heap.add(value);
            siftUp(heap.size()-1);
        }

        Long extractMax() { //извлечение и удаление максимального элемента из кучи
            Long result = null;
            if(heap.size()>=1)
            {
                result=heap.get(0);
                heap.set(0, heap.get(heap.size()-1)); //хранит элементы в произвольном порядке, но зато быстро ищет
                heap.remove(heap.size()-1); //удаление элемента из набора
                siftDown(0);
            }
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
