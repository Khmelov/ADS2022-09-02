package by.it.group151003.radionov.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

    String decode(File file) throws FileNotFoundException {
        StringBuilder result=new StringBuilder();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //тут запишите ваше решение
        Map<Integer, Character> map = new HashMap<>();
        String s;
        char[] chars;
        s = scanner.nextLine();
        for (int i=0; i<count; i++)
        {
            s = scanner.nextLine();
            chars = s.toCharArray();
            map.put(Integer.parseInt(new String (Arrays.copyOfRange(chars,3,chars.length)),2),chars[0]);
        }
        s = scanner.nextLine();
        chars = s.toCharArray();
        int i = 0;
        int kol;
        boolean f;
        while (i!=chars.length)
        {
            kol=1;
            f=false;
            while (i+kol<chars.length && !((map.containsKey (Integer.parseInt(new String(Arrays.copyOfRange(chars,i,i+kol)),2)))))
            {
                kol++;
            }
            String oo = new String(Arrays.copyOfRange(chars,i,i+kol));
            Character co = map.get(Integer.parseInt(oo,2));
            result.append(co);
            i=i+kol;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        return result.toString(); //01001100100111
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }


}
