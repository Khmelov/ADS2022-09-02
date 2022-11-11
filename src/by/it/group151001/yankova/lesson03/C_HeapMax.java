package by.it.group151001.yankova.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

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
        private List<Long> heap = new ArrayList<>();

        void siftDown(int i) {
            int l = 2 * i + 1;
            if (l >= heap.size()) return;
            if (l + 1 < heap.size() && heap.get(l + 1) > heap.get(l)) l++;
            if (heap.get(i) < heap.get(l)) Collections.swap(heap, i, l);
            siftDown(l);
        }

        void siftUp(int i) {
            if (i == 0) return;
            if (heap.get(i) > heap.get(i - 1) / 2) Collections.swap(heap, i, (i - 1) / 2);
            siftUp((i - 1) / 2);
        }

        void insert(Long value) {
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        Long extractMax() {
            Long result = null;
            if (!heap.isEmpty()) {
                result = heap.get(0);
                heap.set(0, heap.get(heap.size() - 1));
                heap.remove(heap.get(heap.size() - 1));
                siftDown(0);
            }
            return result;
        }

    }

    Long findMaxValue(InputStream stream) {
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
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/yankova/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX="+instance.findMaxValue(stream));
    }
}
