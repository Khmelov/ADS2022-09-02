package by.it.group151004.belsky.lesson01;

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

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        //first fill an array
        long[] arr = new long[m*6*2];
        BigInteger fst = BigInteger.ZERO, scd = BigInteger.ONE, curr;

        arr[0] = 0L;
        arr[1] = 1L % m;
        for (int i = 2;i < arr.length;i++) {
            curr = fst.add(scd);
            fst = scd;
            scd = curr;
            arr[i] = curr.mod(BigInteger.valueOf(m)).longValue();
        }

        int newPerIndex = -1;
        for (int i = 3;i < arr.length;i++) {
            if (arr[i-1] == 0L && arr[i] == 1L) {
                newPerIndex = i-1;
                break;
            }
        }

        return arr[(int) (n % newPerIndex)];
    }


}

