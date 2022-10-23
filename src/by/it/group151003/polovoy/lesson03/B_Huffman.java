package by.it.group151003.polovoy.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B_Huffman {

    String decode(File file) throws FileNotFoundException {
        StringBuilder result=new StringBuilder();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        int count = scanner.nextInt();
        int length = scanner.nextInt();
        scanner.nextLine();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

        Map <String, Character> codes = new HashMap<>();

        for (int i = 0; i < count; i++){
            String l = scanner.nextLine();
            char symbol = l.charAt(0);
            String code = l.substring(3);
            codes.put(code, symbol);
        }

        String to_decode = scanner.next();

        for (int i = 0; i < length; i++){

            String key = "";

            for (int j = i; i < to_decode.length(); j++ ){

                key+=to_decode.charAt(j);
                if (codes.containsKey(key)){

                    i = j;
                    result.append(codes.get(key));
                    break;
                }
            }

        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        return result.toString(); //01001100100111
    }

}
