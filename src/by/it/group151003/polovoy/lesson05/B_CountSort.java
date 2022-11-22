package by.it.group151003.polovoy.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_CountSort {


    int[] countSort(InputStream stream){

        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int n = scanner.nextInt();
        int[] points=new int[n];

        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }

        int[] nums = new int[11];

        for (int i = 0; i < n; i++){

            nums[points[i]]++;
        }

        int i = 0;
        int k = 0;
        while (i < 11){

            while (k < n) {

                for (int j = 0; j < nums[i]; j++) {
                    points[k++] = i;
                }
                i++;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return points;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/polovoy/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
