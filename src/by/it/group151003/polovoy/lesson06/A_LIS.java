package by.it.group151003.polovoy.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_LIS {


    int getSeqSize(InputStream stream){

        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int n = scanner.nextInt();
        int[] m = new int[n];

        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        return Length_Of_LIS(m);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }

    public static int Length_Of_LIS(int[] A){

        int[] D = new int[A.length];
        int Max = 0;

        for (int i = 0; i < A.length; i++){

            D[i] = 1;

            for (int j = 0; j < i; j++){

                if ((A[j] < A[i]) && (D[j] +1 > D[i])){

                    D[i] = D[j] + 1;
                }
            }

            if(Max < D[i]){

                Max = D[i];
            }
        }

        return Max;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/polovoy/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        System.out.print(result);
    }
}
