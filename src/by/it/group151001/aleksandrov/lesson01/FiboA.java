package by.it.group151001.aleksandrov.lesson01;

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
        int n = 40;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 0;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }


    public int calc(int n) {
        //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if ((n==0) || (n==1))
        {
            return n;
        }
        if ((n>46) || (n<0))
        {
            throw new IndexOutOfBoundsException();
        }
        int kol=2, p=0, current=1, x;
        while (kol<=n)
        {
            x=current;
            current+=p;
            p=x;
            kol++;
        }
        return current;
    }


    public BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)

        if (n<=1)
        {
            return BigInteger.valueOf(n);
        }
        return BigInteger.valueOf(slowA(n-2).longValue() + slowA(n-1).longValue());
    }



}

