package by.it.group151004.stahnov.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }


    private int calc(int n) {
        //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        int a1 = 1;
        int a2 = 1;
        int res = -1;
        if(n == 1){
            res = 0;
        }
        if(n == 2){
            res = 1;
        }
        for(int i = 2;i < n;i++){
            res = a1 + a2;
            a1 = a2;
            a2 = res;
        }
        return res;
    }


    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        BigInteger res = new BigInteger("-1");
        if(n == 1){
            res = BigInteger.ONE;
        }
        if (n == 2){
            res = BigInteger.ONE;
        }
        if(n != 1 && n != 2) {
            res = slowA(n - 1).add(slowA(n - 2));
        }
        return res;
    }



}

