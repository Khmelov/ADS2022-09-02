package by.it.group151004.burbouski.lesson03;

import java.io.*;
import java.util.*;

public class A_Huffman {
	public abstract class Node implements Comparable<Node> {
		public final int frequence;

		public abstract void fillCodes(String code);

		public Node(int frequence) {
			this.frequence = frequence;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(frequence, o.frequence);
		}
	}

	public class InternalNode extends Node {
		public Node left;
		public Node right;

		public InternalNode(Node left, Node right) {
			super(left.frequence + right.frequence);
			this.left = left;
			this.right = right;
		}

		@Override
		public void fillCodes(String code) {
			left.fillCodes(code + "0");
			right.fillCodes(code + "1");
		}

	}

	public class LeafNode extends Node {
		public char symbol;

		LeafNode(int frequence, char symbol) {
			super(frequence);
			this.symbol = symbol;
		}

		@Override
		public void fillCodes(String code) {
			codes.put(symbol, code);
		}
	}

	public static Map<Character, String> codes = new TreeMap<>();

	public String encode(File file) throws FileNotFoundException {
		try (Scanner scanner = new Scanner(file)) {
			String s = scanner.next();

			Map<Character, Integer> count = new HashMap<>();
			for (int i = 0; i < s.length(); i++) {
				if (count.containsKey(s.charAt(i))) {
					count.put(s.charAt(i), count.get(s.charAt(i)) + 1);
				} else {
					count.put(s.charAt(i), 1);
				}
			}

			PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
			for (Map.Entry<Character, Integer> item : count.entrySet()) {
				priorityQueue.add(new LeafNode(item.getValue(), item.getKey()));
			}

			while (priorityQueue.size() != 1) {
				priorityQueue.add(new InternalNode(priorityQueue.poll(), priorityQueue.poll()));
			}

			StringBuilder sb = new StringBuilder();
			InternalNode root = (InternalNode) priorityQueue.poll();
			root.fillCodes("");
			for (int i = 0; i < s.length(); i++) {
				sb.append(codes.get(s.charAt(i)));
			}
			return sb.toString();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		File f = new File(root + "by/it/group151004/burbouski/lesson03/dataHuffman.txt");
		A_Huffman instance = new A_Huffman();
		System.currentTimeMillis();
		String result = instance.encode(f);
		System.currentTimeMillis();
		System.out.printf("%d %d\n", codes.size(), result.length());
		for (Map.Entry<Character, String> entry : codes.entrySet()) {
			System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
		}
		System.out.println(result);
	}
}