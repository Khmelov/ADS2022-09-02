package by.it.group151002.rusakovich.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    int getMaxSum(int n, int[] stairs) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
       int[] sum = new int[n+1];
        int temp = 0;
        sum[0] = 0;
        sum[1] = stairs[0];
        for(int i = 2; i <= n; ++i){
            sum[i] = Math.max(sum[i-1], sum[i-2]) + stairs[i-1];
        }
        return sum[n];

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }

    int[] get_arr(Scanner scanIn, int n){
        int[] stairs = new int[n];
        for(int i = 0; i < n; ++i)
            stairs[i] = scanIn.nextInt();
        return stairs;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson08/dataC.txt");
        C_Stairs instance = new C_Stairs();
        Scanner scanIn = new Scanner(stream);
        int n, res;

        n = scanIn.nextInt();
        res=instance.getMaxSum(n, instance.get_arr(scanIn, n));
        System.out.println("Max amount: " + res);

        n = scanIn.nextInt();
        res=instance.getMaxSum(n, instance.get_arr(scanIn, n));
        System.out.println("Max amount: " + res);
    }

}
