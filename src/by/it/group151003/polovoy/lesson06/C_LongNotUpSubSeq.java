package by.it.group151003.polovoy.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) {

        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int n = scanner.nextInt();
        int[] m = new int[n];

        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        int[] LDS_inds = Indexes_Of_LDS(m);

        System.out.println("Array: " + Arrays.toString(m));
        System.out.println("Inds: " + Arrays.toString(LDS_inds));
        System.out.println("Elements: " + Arrays.toString(get_elems(m, LDS_inds)));

        return LDS_inds.length;
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }

    private int[] get_elems(int[] a, int[] b){
        return Arrays.stream(b).map(i -> a[i - 1]).toArray();
    }

    private int[] Indexes_Of_LDS(int[] A){

        int[] D = new int[A.length];
        int Max = 0;

        for (int i = 0; i < A.length; i++){

            D[i] = 1;

            for (int j = 0; j < i; j++){

                if ((A[j] >= A[i]) && (D[j] + 1 > D[i])){

                    D[i] = D[j] + 1;
                }
            }

            if(Max < D[i]){

                Max = D[i];
            }
        }

        int[] inds = new int[Max];
        int ind = Max - 1;

        for(int i = A.length - 1; i >=0; i--){

            if (D[i] == Max){

                inds[ind] = i + 1;
                ind--;
                Max--;
            }
        }

        return inds;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/polovoy/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}