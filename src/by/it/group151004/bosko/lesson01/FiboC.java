package by.it.group151004.bosko.lesson01;

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
        ArrayList<Long> fibMod = new ArrayList<Long>();

        fibMod.add(0L);
        fibMod.add(1L);

        int i = 2;
        while(fibMod.get(i-2) != 0 || fibMod.get(i-1) != 1 || i <= 2) {
            fibMod.add((fibMod.get(i - 1) + fibMod.get(i - 2)) % m);
            i++;
        }
        return fibMod.get((int) (n % (i-2)));
    }


}

