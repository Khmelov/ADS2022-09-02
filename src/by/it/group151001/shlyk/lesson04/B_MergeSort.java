package by.it.group151001.shlyk.lesson04;

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

    public boolean isOrdered(int[] sortArray){
        for(int i = 1; i < sortArray.length; i++){
            if(sortArray[i -1] > sortArray[i])
                return false;
        }
        return true;
    }
    private void mergeSort(int[] sortArray, int offset, int arrSize){
        if (arrSize == 1 || arrSize == 0)
            return;
        if (arrSize > 2)
        {
            int rightSize = arrSize / 2;
            int leftSize = arrSize - rightSize;
            mergeSort(sortArray, offset, rightSize);
            mergeSort(sortArray, offset + rightSize, leftSize);
            int iTemp = 0, iRight = offset, iLeft = offset + rightSize;
            int[] temp = new int[arrSize];
            while (iRight < rightSize + offset && iLeft < arrSize + offset){
                if (sortArray[iRight] < sortArray[iLeft])
                {
                    temp[iTemp] = sortArray[iRight];
                    iRight++;
                } else
                {
                    temp[iTemp] = sortArray[iLeft];
                    iLeft++;
                }
                iTemp++;
            }
            boolean isRight = iLeft != arrSize + offset;
            int nIteration = isRight ? (arrSize + offset - iLeft) : (rightSize  + offset - iRight);
            int iSource = isRight ? iLeft : iRight;
            for (int i = 0; i < nIteration; i++){
                temp[iTemp] = sortArray[iSource + i];
                iTemp++;
            }
            iTemp = 0;
            for(int i = offset; iTemp < temp.length; i++, iTemp++){
                sortArray[i] = temp[iTemp];
            }
            return;
        }
        if (sortArray[offset] > sortArray[offset + 1]){
            int temp = sortArray[offset];
            sortArray[offset] = sortArray[offset + 1];
            sortArray[offset + 1] = temp;
        } //otherwise array is sorted
    }
    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a=new int[n];
        int j = 10;
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            // System.out.println(a[i]);
        }
        mergeSort(a, 0, n);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/shlyk/lesson04/Values");
        B_MergeSort instance = new B_MergeSort();
        long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime);
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
