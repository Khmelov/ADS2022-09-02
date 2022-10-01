package by.it.group151003.mytnik.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class A_Huffman {
    private abstract static class Node implements Comparable<Node> {
        private final int frequency;

        public abstract void fillCodes(String code);

        public Node(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(frequency, o.frequency);
        }
    }

    private static class InternalNode extends Node {
        private final Node left;

        private final Node right;

        public InternalNode(Node left, Node right) {
            super(left.frequency + right.frequency);
            this.left = left;
            this.right = right;
        }

        @Override
        public void fillCodes(String code) {
            left.fillCodes(code + "0");
            right.fillCodes(code + "1");
        }
    }

    private static class LeafNode extends Node {
        private final char symbol;

        public LeafNode(int frequency, char symbol) {
            super(frequency);
            this.symbol = symbol;
        }

        @Override
        public void fillCodes(String code) {
            codes.put(this.symbol, code);
        }
    }

    private static final Map<Character, String> codes = new HashMap<>();

    public String encode(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String s = scanner.next();

        Map<Character, Integer> count = new HashMap<>();
        Arrays.stream(s.split("")).forEach(code -> count.merge(code.charAt(0), 1, Integer::sum));

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        count.forEach((key, value) -> priorityQueue.add(new LeafNode(value, key)));

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            priorityQueue.add(new InternalNode(left, right));
        }

        priorityQueue.poll().fillCodes("");

        StringBuilder result = new StringBuilder();
        Arrays.stream(s.split("")).forEach(c -> result.append(codes.get(c.charAt(0))));
        return result.toString();
    }
}
