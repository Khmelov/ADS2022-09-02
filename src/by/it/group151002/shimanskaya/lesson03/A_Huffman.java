package by.it.group151002.shimanskaya.lesson03;

import by.it.group151002.shimanskaya.lesson03.A_Huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//Lesson 3. A_Huffman.
//Разработайте метод encode(File file) для кодирования строки (код Хаффмана)

// По данным файла (непустой строке ss длины не более 104104),
// состоящей из строчных букв латинского алфавита,
// постройте оптимальный по суммарной длине беспрефиксный код.

// Используйте Алгоритм Хаффмана — жадный алгоритм оптимального
// безпрефиксного кодирования алфавита с минимальной избыточностью.

// В первой строке выведите количество различных букв kk,
// встречающихся в строке, и размер получившейся закодированной строки.
// В следующих kk строках запишите коды букв в формате "letter: code".
// В последней строке выведите закодированную строку. Примеры ниже

//        Sample Input 1:
//        a
//
//        Sample Output 1:
//        1 1
//        a: 0
//        0

//        Sample Input 2:
//        abacabad
//
//        Sample Output 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111
public class A_Huffman {

abstract class Node implements Comparable<A_Huffman.Node> {
    private final int frequence;

    abstract void fillCodes(String code);

    private Node(int frequence) {
        this.frequence = frequence;
    }

    @Override
    public int compareTo(A_Huffman.Node o) {
        return Integer.compare(frequence, o.frequence);
    }
}

private class InternalNode extends A_Huffman.Node {
    A_Huffman.Node left;
    A_Huffman.Node right;

    InternalNode(A_Huffman.Node left, A_Huffman.Node right) {
        super(left.frequence + right.frequence);
        this.left = left;
        this.right = right;
    }

    @Override
    void fillCodes(String code) {
        left.fillCodes(code + "0");
        right.fillCodes(code + "1");
    }

}

private class LeafNode extends A_Huffman.Node {
    char symbol;

    LeafNode(int frequence, char symbol) {
        super(frequence);
        this.symbol = symbol;
    }

    @Override
    void fillCodes(String code) {
        codes.put(this.symbol, code);
    }
}

    static private Map<Character, String> codes = new TreeMap<>();

    String encode(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String s = scanner.next();
        scanner.close();
        char[] strOfChars = s.toCharArray();
        Map<Character, Integer> amount = new HashMap<>();

        for (char c : strOfChars) {
            if (amount.containsKey(c)) {
                amount.put(c, amount.get(c) + 1);
            } else {
                amount.put(c, 1);
            }
        }

        PriorityQueue<A_Huffman.Node> priorityQueue = new PriorityQueue<>();
        int i = 0;
        while (i < strOfChars.length) {
            if (amount.containsKey(strOfChars[i])) {
                A_Huffman.LeafNode currNode = new A_Huffman.LeafNode(amount.get(strOfChars[i]), strOfChars[i]);
                priorityQueue.add(currNode);
                amount.remove(strOfChars[i]);
            }
            i++;
        }

        int last = priorityQueue.size() - 1;
        for (i = 0; i < last; i++) {
            A_Huffman.Node left = priorityQueue.poll();
            A_Huffman.Node right = priorityQueue.poll();
            assert left != null;
            assert right != null;
            A_Huffman.InternalNode parentNode = new A_Huffman.InternalNode(left, right);
            priorityQueue.add(parentNode);
        }

        if (last == 0) {
            A_Huffman.Node left = priorityQueue.poll();
            A_Huffman.LeafNode right = new A_Huffman.LeafNode(0, '0');
            A_Huffman.InternalNode parentNode = new A_Huffman.InternalNode(left, right);
            priorityQueue.add(parentNode);
        }

        StringBuilder encryptedString = new StringBuilder();
        A_Huffman.Node root = priorityQueue.poll();
        assert root != null;
        root.fillCodes("");

        if (last == 0) codes.remove('0');

        for (i = 0; i < strOfChars.length; i++) {
            encryptedString.append(codes.get(strOfChars[i]));
        }

        return encryptedString.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group151002/shimanskaya/lesson03/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        long startTime = System.currentTimeMillis();
        String result = instance.encode(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("%d %d\n", codes.size(), result.length());
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
        }
        System.out.println(result);
    }

}
