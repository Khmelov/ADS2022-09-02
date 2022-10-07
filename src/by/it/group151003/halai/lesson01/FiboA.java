package by.it.group151003.halai.lesson01;

import java.math.BigInteger;

public class FiboA      {
    public long startTime = System.currentTimeMillis();

    public long time()   {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int num = 17;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", num, fibo.calc(num), fibo.time());

        fibo = new FiboA();
        num = 17;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", num, fibo.slowA(num), fibo.time());
    }

    public int    calc(int num        ) {
        if (num < 2      ) {
            return     num;
        }
        return calc(   num - 1) + calc(   num - 2);
    }

    public BigInteger slowA(Integer num     ) {
        switch (num) {
            case 0:
                return BigInteger.ZERO;
            case 1:
                return BigInteger.ONE;
        }
        return slowA(num - 1).add(slowA(num - 2));
    }
}


