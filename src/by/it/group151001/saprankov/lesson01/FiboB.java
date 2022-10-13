package by.it.group151001.saprankov.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());

        fibo = new FiboB();
        n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.ACLU(n), fibo.time());


        }

    private int ACLU(int n){
        int[] mas = new int[n];
        mas[0]=1;
        mas[1]=1;
        for(int i=2;i<mas.length;i++)
        {
           mas[i] = mas[i-1]+mas[i-2];
        }



        return mas[mas.length-1];
    }
    BigInteger fastB(Integer n) {
        BigInteger[] arr = new BigInteger[n];
        arr[0]=BigInteger.ONE;
        arr[1]=BigInteger.ONE;
        for(int i=2;i<arr.length;i++)
        {
            arr[i] = arr[i-1].add(arr[i-2]);
        }
        return arr[arr.length-1];
    }

}

