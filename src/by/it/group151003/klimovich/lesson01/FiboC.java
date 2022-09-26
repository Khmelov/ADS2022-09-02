package by.it.group151003.klimovich.lesson01;

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
        int n = 235336;
        int m = 234;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        long[] arrfib = new long[6*m+1];
        int i=1;
        arrfib[0]=0;
        arrfib[1]=1;
        long res=arrfib[0]+arrfib[1];

        while (((arrfib[i]!=0) || (res % m !=0)) && (i<(6*m+1))){
            ++i;
            arrfib[i]=arrfib[i-1]+arrfib[i-2];
            arrfib[i]=arrfib[i] % m;
            res=res + arrfib[i];
        }

        if((res % m!=0) && (arrfib[i]!=0))
            return arrfib[i];
        else {
            int exep = (int) (n % i);
            return arrfib[exep];
        }
    }
}

