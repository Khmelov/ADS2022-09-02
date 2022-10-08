package by.it.group151001.shlyk.lesson05;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class Lesson5Test {
    public static void sort(int[] sortArray, int iMin, int iMax){
        int i = iMin;
        int j = iMax;
        int x = sortArray[(iMin + iMax) / 2];
        do{
            while(sortArray[i] < x)
                i++;
            while(sortArray[j] > x)
                j--;
            if(i <= j){
                int temp = sortArray[i];
                sortArray[i] = sortArray[j];
                sortArray[j] = temp;
                j--;
                i++;
            }
        }while(i < j);
        if(i < iMax){
            sort(sortArray, i, iMax);
        if(j > iMin)
            sort(sortArray, iMin, j);
        }
    }
    //simple so even tests are not sufficient
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        boolean ok=Arrays.equals(result,new int[]{1,0,0});
        assertTrue("A failed", ok);
    }


    int[] fillArray(Scanner input) {
        int arrSize = input.nextInt();
        int[] result = new int[arrSize];
        for(int i = 0; i < arrSize; i++){
            result[i] = input.nextInt();
        }
        return result;
    }
    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/shlyk/lesson05/dataB.txt");
        InputStream originStream = new FileInputStream(root + "by/it/group151001/shlyk/lesson05/dataB.txt");
        Scanner originInput = new Scanner(originStream);
        Scanner input = new Scanner(stream);
        B_CountSort task2 = new B_CountSort();
        boolean isCorrect = true;
        final int nTest = 5;

        for(int i = 1; i <= nTest && isCorrect; i++){
            int[] origin = fillArray(originInput);
            sort(origin, 0, origin.length - 1);
            isCorrect = Arrays.equals(task2.countSort(input), origin);

        }
        assertTrue("B has failed", isCorrect);
    }


    //using self-testing algorithm
    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";

        C_QSortOptimized task3 = new C_QSortOptimized();
        final int nTest = 1000;
        boolean isCorrect = false;
        int[] result = new int[0];
        int[] response = new int[0];
        for(int i = 1; i <= nTest; i++){
            InputStream stream = new FileInputStream(root + "by/it/group151001/shlyk/lesson05/Values");
            result = task3.getAccessory2(stream);
            response = task3.getResponse();
            for(int j = 0; j < result.length; j++){
                isCorrect = (response[j] != 0 && result[j] != 0) || (response[j] == 0 && result[j] == 0);
                if(!isCorrect)
                    break;
            }
            stream.close();
        }
        if(!isCorrect){
            System.out.println(Arrays.toString(response));
            System.out.println(Arrays.toString(result));
        }
        assertTrue("C failed", isCorrect);
    }

}
