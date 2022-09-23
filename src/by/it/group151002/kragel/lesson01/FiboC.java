package by.it.group151002.kragel.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;

public class FiboC {
    private long startTime = System.currentTimeMillis();
    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        long n = 999999999;
        int m = 98989;

        System.out.printf("fasterC(%d, %d)=%d \n\t time=%d \n\n", n, m, fibo.fasterC(n, m), fibo.time());
    }
    long fasterC(long n, int m) {
        if (n == 1 || n == 2)
            return 1L;
        int[] modArr = new int[6 * m];
        modArr[0] = 0;
        modArr[1] = 1;
        BigInteger[] fibArr = new BigInteger[]{BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE};
        BigInteger bm = BigInteger.valueOf(m);
        int per = 1;
        for(int i = 2; i <= 6 * m; i++) {
            fibArr[0] = fibArr[1];
            fibArr[1] = fibArr[2];
            fibArr[2] = fibArr[1].add(fibArr[0]);
            modArr[i] = fibArr[2].mod(bm).intValue();
            if (modArr[i - 1] == modArr[0] && modArr[i] == modArr[1]) {
                per = i - 1;
                break;
            }
        }
        System.out.println(per);
        return modArr[(int) (n % per)];
    }
}