package by.it.group151002.shatko.lesson03;

import java.io.*;
import java.util.*;

public class C_HeapMax {
    private class MaxHeap {
        private List<Long> heap = new ArrayList<>();

        int siftDown(int i) { //просеивание вниз
            while (2 * i + 1 < heap.size()) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int j = left;
                if (right < heap.size() && heap.get(right) > heap.get(left))
                    j = right;
                if (heap.get(i) >= heap.get(j))
                    break;
                long tmp = heap.get(i);
                heap.set(i, heap.get(j));
                heap.set(j, tmp);
                i = j;
            }
            return i;
        }

        int siftUp(int i) { //просеивание вверх
            while (heap.get(i) > heap.get((i - 1) / 2)) {
                long tmp = heap.get(i);
                heap.set(i, heap.get((i - 1) / 2));
                heap.set((i - 1) / 2, tmp);
                i = (i - 1) / 2;
            }
            return i;
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
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res=heap.extractMax();
                if (res != null && res > maxValue) maxValue = res;
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
        InputStream stream = new FileInputStream(root + "by/it/group151002/shatko/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX=" + instance.findMaxValue(stream));
    }
}
