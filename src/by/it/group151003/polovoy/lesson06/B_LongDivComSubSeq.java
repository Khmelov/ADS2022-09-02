package by.it.group151003.polovoy.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_LongDivComSubSeq {


    int getDivSeqSize(InputStream stream){

        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int n = scanner.nextInt();
        int[] m = new int[n];

        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        return Length_Of_DIV_LIS(m);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }

    public static int Length_Of_DIV_LIS(int[] A){

        int[] D = new int[A.length];
        int Max = 0;

        for (int i = 0; i < A.length; i++){

            D[i] = 1;

            for (int j = 0; j < i; j++){

                if ((A[j] % D[i] == 0) && (D[j] +1 > D[i])){

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
        InputStream stream = new FileInputStream(root + "by/it/group151003/polovoy/lesson06/dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }
}
