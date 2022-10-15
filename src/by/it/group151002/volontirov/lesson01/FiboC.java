package by.it.group151002.volontirov.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        long[] fib = new long[3];
        ArrayList<Long> remainders = new ArrayList<Long>();
        fib[0] = 0l;
        fib[1] = 1l;
        fib[2] = 1l;
        remainders.add(0l);
        remainders.add(1l);
        remainders.add(1l);
        int i = 3;
        while(fib[(i - 1) % 3] % m != 1 || fib[(i - 2) % 3] % m != 0){
            fib[i % 3] = fib[(i+1)%3] + fib[(i+2)%3];
            remainders.add((long)(fib[i % 3] % m));
            i++;
        }
        i -= 2;
        return remainders.get((int) (n % i));
    }


}

