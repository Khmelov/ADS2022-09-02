package by.it.group151003.polovoy.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_GetInversions {

    long calc(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = scanner.nextInt();
            System.out.print(a[i]+" ");
        }

        System.out.println();

        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!

        return Inversions_Count(a, a.length);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }

    public static long Inversions_Count(int[] Arr, int n){

        if (n < 2)
            return 0;

        int mid = n / 2; long result = 0;

        int[] left = Arrays.copyOf(Arr, mid);
        int[] right = Arrays.copyOfRange(Arr, mid, n);

        result += Inversions_Count(left, mid);
        result += Inversions_Count(right, n-mid);
        result += merge(Arr, left, right, mid, n-mid);
        return result;
    }

    public static long merge(int[] arr, int[] l, int[] r, int left, int right){

        long count = 0;
        int i = 0, j = 0, k = 0;

        while (i < left && j < right){

            if (l[i] <= r[j]){

                arr[k++] = l[i++];
            }
            else {

                count += left - i;
                arr[k++] = r[j++];
            }
        }

        while (i < left){
            arr[k++] = l[i++];
        }

        while (j < right){
            arr[k++] = r[j++];
        }

        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/polovoy/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        long startTime = System.currentTimeMillis();
        long result = instance.calc(stream);
        long finishTime = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(finishTime - startTime);

    }
}
