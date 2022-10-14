package by.it.group151002.bybikov.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] mergeTwoArrays (int[] firstArray, int[] secondArray) {
        int[] result = new int[firstArray.length + secondArray.length];
        int i = 0, j = 0;
        while (i < firstArray.length && j < secondArray.length) {
            if(firstArray[i] < secondArray[j]) {
                result[i + j] = firstArray[i];
                i++;
            }
            else {
                result[i + j] = secondArray[j];
                j++;
            }

        }
        for ( ; i < firstArray.length; i++) {
            result[i + j] = firstArray[i];
        }
        for (; j < secondArray.length; j++) {
            result[i + j] = secondArray[j];
        }
        return result;
    }

    int[] mergeSortRealization (int[] array) {
        if(array == null || array.length < 2)
            return array;
        int beginIndex = 0, endIndex = array.length;
        int dividerIndex = (beginIndex + endIndex) / 2;
        int[] firstPartArray = mergeSortRealization(Arrays.copyOfRange(array, beginIndex, dividerIndex) );
        int[] secondPartArray = mergeSortRealization(Arrays.copyOfRange(array, dividerIndex, endIndex) );
        array = mergeTwoArrays(firstPartArray, secondPartArray);
        return array;
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] inputArray = new int[n];
        for (int i = 0; i < n; i++) {
            inputArray[i] = scanner.nextInt();
        }
        inputArray = mergeSortRealization(inputArray);
        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return inputArray;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
