package by.it.group151002.kragel.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Lesson 3. B_Huffman.
// Восстановите строку по её коду и беспрефиксному коду символов.

// В первой строке входного файла заданы два целых числа
// kk и ll через пробел — количество различных букв, встречающихся в строке,
// и размер получившейся закодированной строки, соответственно.
//
// В следующих kk строках записаны коды букв в формате "letter: code".
// Ни один код не является префиксом другого.
// Буквы могут быть перечислены в любом порядке.
// В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
// каждая из этих букв встречается в строке хотя бы один раз.
// Наконец, в последней строке записана закодированная строка.
// Исходная строка и коды всех букв непусты.
// Заданный код таков, что закодированная строка имеет минимальный возможный размер.
//
//        Sample Input 1:
//        1 1
//        a: 0
//        0

//        Sample Output 1:
//        a


//        Sample Input 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111

//        Sample Output 2:
//        abacabad

public class B_Huffman {

    abstract class Node implements Comparable<Node> {
        private final String code;
        abstract void fillChars();
        private Node(String code) {
            this.code = code;
        }
        @Override
        public int compareTo(Node o) {;
            if (o.code.length() == code.length()) {
                for (int k = 0; k < code.length(); k++) {
                    if (o.code.charAt(k) != code.charAt(k)) {
                        return o.code.charAt(k) - code.charAt(k);
                    }
                }
            }
            return o.code.length() - code.length();
        }
    }
    private class InternalNode extends Node {
        Node left;
        Node right;
        InternalNode(Node right, Node left) {
            super(right.code.substring(0, right.code.length() - 1));
            this.left = left;
            this.right = right;
        }
        @Override
        void fillChars() {
            if (codeStr.startsWith("0")) {
                codeStr = codeStr.substring(1);
                left.fillChars();
            }
            else {
                codeStr = codeStr.substring(1);
                right.fillChars();
            }
        }

    }

    private class LeafNode extends Node {
        char symbol;
        LeafNode(char symbol, String code) {
            super(code);
            this.symbol = symbol;
        }
        @Override
        void fillChars() {
            result.append(symbol);
        }
    }
    static StringBuilder result = new StringBuilder();
    static String codeStr;
    String decode(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        Map<Character, String> codes = new HashMap<>();
        scanner.nextLine();
        for (int i = 0; i < count; i++) {
            String[] pair  = scanner.nextLine().split(":");
            codes.put(pair[0].trim().charAt(0), pair[1].trim());
        }
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, String> pair : codes.entrySet())
            priorityQueue.add(new LeafNode(pair.getKey(), pair.getValue()));
        while(priorityQueue.size() > 1)
            priorityQueue.add(new InternalNode(priorityQueue.poll(), priorityQueue.poll()));
        codeStr = scanner.nextLine();
        Node head = priorityQueue.poll();
        while (codeStr.length() > 0){
            head.fillChars();
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group151002/kragel/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }


}
