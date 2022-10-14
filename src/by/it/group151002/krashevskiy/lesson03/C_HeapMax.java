package by.it.group151002.krashevskiy.lesson03;

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
        private List<Long> heap = new ArrayList<>();

        int siftDown(int index) { //просеивание вниз
            while (2 * index + 1 < heap.size()) {
                int left = 2 * index + 1;
                int right = 2 * index + 2;
                int j = left;
                if (right < heap.size() && heap.get(right) > heap.get(left))
                    j = right;
                if (heap.get(index) >= heap.get(j))
                    break;
                long tmp = heap.get(index);
                heap.set(index, heap.get(j));
                heap.set(j, tmp);
                index = j;
            }
            return index;
        }

        int siftUp(int index) { //просеивание вверх
            while (heap.get(index) > heap.get((index - 1) / 2)) {
                long temp = heap.get(index);
                heap.set(index, heap.get((index - 1) / 2));
                heap.set((index - 1) / 2, temp);
                index = (index - 1) / 2;
            }
            return index;
        }

        void insert(Long value) { //вставка
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        Long extractMax() { //извлечение и удаление максимума
            Long result = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            siftDown(0);
            return result;
        }
    }

    Long findMaxValue(InputStream stream) {
        Long maxValue = 0L;
        MaxHeap heap = new MaxHeap();
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        for (int i = 0; i < count; ) {
            String str = scanner.nextLine();
            if (str.equalsIgnoreCase("extractMax")) {
                Long res=heap.extractMax();
                if (res != null && res > maxValue) maxValue = res;
                System.out.println();
                i++;
            }
            if (str.contains(" ")) {
                String[] p = str.split(" ");
                if (p[0].equalsIgnoreCase("insert"))
                    heap.insert(Long.parseLong(p[1]));
                i++;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/krashevskiy/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX=" + instance.findMaxValue(stream));
    }
}