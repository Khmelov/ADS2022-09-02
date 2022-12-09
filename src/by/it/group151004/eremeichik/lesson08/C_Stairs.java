package by.it.group151004.eremeichik.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Даны число 1<=n<=100 ступенек лестницы и
целые числа −10000<=a[1],…,a[n]<=10000, которыми помечены ступеньки.
Найдите максимальную сумму, которую можно получить, идя по лестнице
снизу вверх (от нулевой до n-й ступеньки), каждый раз поднимаясь на
одну или на две ступеньки.

Sample Input 1:
2
1 2
Sample Output 1:
3

Sample Input 2:
2
2 -1
Sample Output 2:
1

Sample Input 3:
3
-1 2 1
Sample Output 3:
3

*/

public class C_Stairs {

    int getMaxSum(InputStream stream ) {
        Scanner scanner = new Scanner(stream);
        int n=scanner.nextInt();
        int stairs[]=new int[n];
        for (int i = 0; i < n; i++) {
            stairs[i]=scanner.nextInt();
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return calculateMaxScore(stairs,2);
    }

    private int calculateMaxScore(int[] stairs, int maxOneMoveSteps){
        int[] subproblems = new int[stairs.length+maxOneMoveSteps];
        System.arraycopy(stairs,0,subproblems,maxOneMoveSteps,stairs.length);
        for(int i = maxOneMoveSteps;i < subproblems.length;i++){
            subproblems[i] = findMax(subproblems,i-maxOneMoveSteps,maxOneMoveSteps) + subproblems[i];
        }
        return subproblems[subproblems.length-1];
    }

    private int findMax(int[] arr,int start, int length){
        int max = arr[start];
        for(int i = start+1;i<start+length;i++){
            if(arr[i]>max){
                max = arr[i];
            }
        }
        return max;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res=instance.getMaxSum(stream);
        System.out.println(res);
    }

}
