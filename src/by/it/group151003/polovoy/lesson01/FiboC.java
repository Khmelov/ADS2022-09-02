package by.it.group151003.polovoy.lesson01;

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

        if (n == 0) 
            return 0;
        else if (n == 1) 
            return 1;
        
        n = n % PisanoPeriod(m);
        
        BigInteger prev = BigInteger.ZERO;
        BigInteger curr = BigInteger.ONE;
        BigInteger tmp;
        
        
        for  (int i=0; i < n-1; i++){

            tmp=prev;
            prev=curr;
            curr=tmp.add(curr);
        }
        
         return  curr.mod(BigInteger.valueOf(m)).longValue();
    }
        

    private int PisanoPeriod(int p){

        int prev = 0;
        int curr = 0;
        int tmp;
        int result = 0;

        for (int i=0;i<p*p; i++) {

            tmp=prev;
            prev=curr;
            curr=(tmp+curr) % p;

            if (prev == 0 && curr == 1) {
                result = i + 1;
            }
        }

        return result;
    }


}

