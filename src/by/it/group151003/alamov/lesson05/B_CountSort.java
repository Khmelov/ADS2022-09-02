package by.it.group151003.alamov.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_CountSort {


    int[] countSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = scanner.nextInt();
        int[] arrOfPoints = new int[n];

        for (int i = 0; i < n; i++) {
            arrOfPoints[i] = scanner.nextInt();
        }
        int maxAmount;
        int minAmount = maxAmount = arrOfPoints[0];

        for (int i = 0; i < n; i++) {
            if (arrOfPoints[i] < minAmount) minAmount = arrOfPoints[i];
            else if (arrOfPoints[i] > maxAmount) maxAmount = arrOfPoints[i];
        }

        int[] amount = new int[maxAmount - minAmount + 1];
        for (int i = 0; i < n; i++) {
            amount[arrOfPoints[i] - minAmount]++;
        }

        int k = 0;
        for (int i = 0; i < amount.length; i++) {
            for (int j = 0; j < amount[i]; j++) {
                arrOfPoints[k++] = minAmount + i;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return arrOfPoints;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
