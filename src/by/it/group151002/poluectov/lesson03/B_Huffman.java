package by.it.group151002.poluectov.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyStore;
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

    private class Node {

        private Node left;
        private Node right;

        private Character symbol;

        Node(Node left, Node right){
            this.left = left;
            this.right = right;
            this.symbol = 0;
        }

        Node(Character symbol) {
            this.symbol = symbol;
            this.left = null;
            this.right = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public  void setSymbol(Character symbol) {
            this.symbol = symbol;
        }

        public Character getSymbol() {
            return symbol;
        }
    }

    String decode(File file) throws FileNotFoundException {
        StringBuilder result=new StringBuilder();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        scanner.nextLine();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //тут запишите ваше решение
        List<Character> codes = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            Character ch = line.charAt(0);
            codes.add(ch);
        }
        String toDecode = scanner.nextLine();
        Node head = huffmanTree(codes);
        result = decode(toDecode, head);
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        return result.toString(); //01001100100111
    }

    private Node huffmanTree(List<Character> codes) {
        Node prev = new Node(null, null);
        Node head = prev;
        Node curr;
        for (int i = 0; i < codes.size() - 1; i++) {
            Character ch = codes.get(i);
            curr = new Node(ch);
            prev.setLeft(curr);
            prev.setRight(new Node(null, null));
            prev = prev.getRight();
        }
        prev.setSymbol(codes.get(codes.size() - 1));
        return head;
    }

    private StringBuilder decode(String strToDecode, Node huffmanTree) {
        Node curr = huffmanTree;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strToDecode.length(); i++) {
            if (curr.getLeft() != null) {
                Character ch = strToDecode.charAt(i);
                if (ch.equals('0')) curr = curr.getLeft();
                else curr = curr.getRight();
            } else {
                result.append(curr.getSymbol());
                curr = huffmanTree;
                i--;
            }
        }
        result.append(curr.getSymbol());
        return result;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }


}
