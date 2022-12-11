package by.it.group151003.alamov.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C_HeapMax {

    private class MaxHeap {
        private List<Long> heap = new ArrayList<>();

        int siftDown(int i) {
            while (2 * i + 1 < heap.size()) {
                int leftSide = 2 * i + 1;
                int k = leftSide;
                int rightSide = 2 * i + 2;
                if (rightSide < heap.size() && heap.get(rightSide) > heap.get(leftSide))
                    k = rightSide;
                if (heap.get(i) >= heap.get(k))
                    break;
                long temp = heap.get(i);
                heap.set(i, heap.get(k));
                heap.set(k, temp);
                i = k;
            }
            return i;
        }

        int siftUp(int i) {
            while (heap.get(i) > heap.get((i - 1) / 2)) {
                long temp = heap.get(i);
                heap.set(i, heap.get((i - 1) / 2));
                heap.set((i - 1) / 2, temp);
                i = (i - 1) / 2;
            }
            return i;
        }

        void insert(Long value) {
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        Long extractMax() {
            Long res = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            siftDown(0);
            return res;
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
                Long res = heap.extractMax();
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
        InputStream stream = new FileInputStream(root + "by/it/group151003/alamov/lesson03/heapData-2.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX=" + instance.findMaxValue(stream));
    }
}
