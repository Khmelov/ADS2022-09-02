package by.it.group151002.redkovskiy.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_BinaryFind {
    int[] findIndex(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++)
            arr[i] = scanner.nextInt();
        int k = scanner.nextInt();
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = -1;
            int value = scanner.nextInt();
            for (int j = 0; j < size; j++) {
                if (arr[j] == value)
                    result[i] = j + 1; //т.к. индексация с 0
            }
        }
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/redkovskiy/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        int[] result=instance.findIndex(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}