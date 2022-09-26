package by.it.group151003.pavluchenko.lesson01;

import java.math.BigInteger;

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        BigInteger[] MyArr = new BigInteger[n + 1];
        MyArr[0] = BigInteger.ZERO;
        MyArr[1] = BigInteger.ONE;
        for ( int i = 2; i <= n; i++)
        {
            MyArr[i] = MyArr[i - 1].add( MyArr[ i - 2]);
        }
        return MyArr[n];
    }

}

