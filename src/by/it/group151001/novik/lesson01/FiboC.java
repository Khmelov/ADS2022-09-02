package by.it.group151001.novik.lesson01;

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
        if (m > n) return n;
        ArrayList<Long> Fibo = new ArrayList();
        Fibo.add(0L);
        if (n > 0) Fibo.add(1L);
        int i = 2;
        while ((!((Fibo.get(i - 2) == 0) && (Fibo.get(i - 1) == 1)) || (i <= 2))&&(i <= n)){
            Fibo.add((Fibo.get(i - 2) + Fibo.get(i -1)) % m);
            i++;
        }
        if (i <= n) return Fibo.get((int) (n % (i - 2)));
        else return Fibo.get(i - 1);
        /*long[]FiboArray = new long[(int) (n) + 1];
        FiboArray[0] = 0;
        if (n > 0) FiboArray[1] = 1;
        int i = 2;
        while (((!(FiboArray[i - 2] == 0 && FiboArray[i - 1] == 1))|| (i <= 2))&&(i <= n))
        {
            FiboArray[i] = (FiboArray[i - 2] + FiboArray[i - 1]) % m;
            i++;
        }
        if (i <= n) return FiboArray[(int) ((n) % (i - 2))];
        else return FiboArray[i - 1];*/
    }


}

