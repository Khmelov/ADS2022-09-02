package by.it.group151003.mytnik.lesson03;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C_HeapMax {
    private static class MaxHeap {
        private final List<Long> heap = new ArrayList<>();

        private int leftChild(int pos) {
            return pos * 2;
        }

        private int rightChild(int pos) {
            return pos * 2 + 1;
        }

        private int parent(int pos) {
            return (pos - 1) / 2;
        }

        private void swap(int pos1, int pos2) {
            long temp = heap.get(pos1);
            heap.set(pos1, heap.get(pos2));
            heap.set(pos2, temp);
        }

        private void siftDown(int i) {
            int maxIndex = i;

            int l = leftChild(i);
            if (l < heap.size() && heap.get(l) > heap.get(maxIndex)) {
                maxIndex = l;
            }

            int r = rightChild(i);
            if (r < heap.size() && heap.get(r) > heap.get(maxIndex)) {
                maxIndex = r;
            }

            if (i != maxIndex) {
                swap(i, maxIndex);
                siftDown(maxIndex);
            }
        }

        private void siftUp(int i) {
            while (i > 0 && heap.get(parent(i)) < heap.get(i)) {
                swap(i, parent(i));
                i = parent(i);
            }
        }

        public void insert(long value) {
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        public Long extractMax() {
            if (heap.size() == 0) {
                return null;
            }
            long result = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            siftDown(0);
            return result;
        }
    }

    public Long findMaxValue(InputStream stream) {
        long maxValue = 0L;
        MaxHeap heap = new MaxHeap();

        Scanner scanner = new Scanner(stream);
        int count = scanner.nextInt();

        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();

            if (s.equalsIgnoreCase("extractMax")) {
                Long res = heap.extractMax();
                if (res != null && res > maxValue) maxValue = res;
                i++;
            }

            if (s.contains(" ")) {
                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert")) heap.insert(Long.parseLong(p[1]));
                i++;
            }
        }
        return maxValue;
    }
}
