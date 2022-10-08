package by.it.group151001.novik.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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

    Character getindex(String val){
        for( Map.Entry<Character,Integer> pair: codes.entrySet()){
            if (val.equals(Integer.toString(pair.getValue()))) {
                return pair.getKey();
            }
        }
        return 'f';
    }
    /*Character getstr(String val, String line, int i){
        if (val == ""){
            if(line.charAt(i) == '0'){
                return getindex(line.substring(i,i + 1));
            } else{
                if(line.charAt(i) == '1'){
                    val = line.substring(i, i + 1);
                    line = line.substring(i + 1);
                    i++;
                    getstr(val,line,i);
                }
            }
        }else{

        }
    }*/
    static private Map<Character,Integer> codes = new TreeMap<>();
    String decode(File file) throws FileNotFoundException {
        StringBuilder result=new StringBuilder();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //тут запишите ваше решение

        for (int i = 0; i < count; i++){
            codes.put(scanner.next().charAt(0),scanner.nextInt());
        }
        String line = scanner.next();
        String tmp ="";
        int i = 0;
        int cnt = 0;
        while( i < line.length()){
            while((line.charAt(i) != '0')&&(cnt < count - 2)){
                cnt++;
                i++;
            }
            result.append(getindex(line.substring(0,i + 1)));
            line = line.substring(i + 1);
            cnt = 0;
            i = 0;
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
