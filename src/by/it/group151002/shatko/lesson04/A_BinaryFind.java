package by.it.group151002.shatko.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_BinaryFind {
    int[] findIndex(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int k = scanner.nextInt();
        int[] result = new int[k];
        boolean isFound;
        for (int i = 0; i < k; i++) {
            isFound = false;
            int value = scanner.nextInt();
            for (int j = 0; j < n; j++) {
                if (arr[j] == value) {
                    result[i] = j + 1;
                    isFound = true;
                }
            }
            if (!isFound) {
                result[i] = -1;
            }
        }

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/shatko/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        int[] result=instance.findIndex(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
