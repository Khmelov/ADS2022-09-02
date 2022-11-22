package by.it.group151001.kononova.lesson01;

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
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    public long fasterC(long n, int m) {
        BigInteger[] ost = new BigInteger[6*m+1];
        ost[0] = BigInteger.ZERO;
        ost[1] = BigInteger.ONE;
        long sum = ost[0].add(ost[1]).longValue();
        int i = 1;
        while ((sum % m != 0 || ost[i].longValue() != 0) && i <= 6*m){
            ++i;
            ost[i] = BigInteger.valueOf(ost[i-1].add(ost[i-2]).longValue()%m);
            sum = sum + ost[i].longValue();
        }
        if (sum % m != 0 && ost[i].longValue() != 0){
            return ost[i].longValue();
        }
        return ost[(int) (n % i)].longValue();
    }
}

