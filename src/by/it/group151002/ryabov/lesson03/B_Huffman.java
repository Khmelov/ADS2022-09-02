package by.it.group151002.ryabov.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

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
    abstract class Node implements Comparable<A_Huffman.Node> {
        private final int frequence; //частота символов
        private Node(int frequence) {
            this.frequence = frequence;
        }
    }
    String decode(File file) throws FileNotFoundException {
        StringBuilder result=new StringBuilder();
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        char[] charArr = new char[count];
        ArrayList<String> codeArr = new ArrayList<>();
        String tmp  = "";
        scanner.nextLine();
        for (int i = 0; i < count; i++){
            tmp = scanner.nextLine();
            charArr[i] = tmp.charAt(0);
            codeArr.add(tmp.substring(3, tmp.length()));
        }
        String code = scanner.nextLine();
        int i = 0;
        int r = 1;
        while (i < length){
            tmp = code.substring(i, i + r);
            if (codeArr.contains(tmp)){
                result.append(charArr[codeArr.indexOf(tmp)]);
                i += r;
                r = 1;
            }
            else
                r++;
        }
        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }


}
