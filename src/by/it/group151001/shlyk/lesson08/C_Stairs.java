package by.it.group151001.shlyk.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

//I offer to change task: the biggest with lowest num of step
//There are two optimum params: sum and steps
//On each step this condition should be the best
//From up to down solution but this task in NP-fulled unfortunately
//Only brute force method...
//simultaneously
public class C_Stairs {

    int[] buildLadder(int[] arrStepCost){
        int nStepSum;
        int[] arrLadder = new int[arrStepCost.length]; //filled with zeroes
        arrLadder[0] = arrStepCost[0];
        arrLadder[1] = Math.max( arrStepCost[1], arrStepCost[0] + arrStepCost[1]);
        for(int iStep = 2; iStep < arrStepCost.length; iStep++){
            nStepSum = arrStepCost[iStep];
            arrLadder[iStep] = Math.max(nStepSum + arrLadder[iStep - 1], nStepSum + arrLadder[iStep - 2]);
        }
        return arrLadder;
    }
    int getMaxSum(String fName) throws IOException {
        Parser parser = new Parser (fName, Parser.ParserType.Stair);
        int[] stairs = parser.getArray();
        return buildLadder(stairs)[stairs.length - 1];
    }


    public static void main(String[] args) throws IOException {
        String root = System.getProperty("user.dir") + "/src/";
        String fName = root + "by/it/group151001/shlyk/lesson08/dataC.txt";
        C_Stairs instance = new C_Stairs();
        int res = instance.getMaxSum(fName);
        System.out.println(res);
    }

}
