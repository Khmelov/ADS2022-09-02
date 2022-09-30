package by.it.group151004.terebey.lesson01;

/*
 * Given integers 1<=n<=1E18 and 2<=m<=1E5,
 * it is necessary to find the remainder after dividing the n-th Fibonacci number by m.
 * calculation time should be no more than 2 seconds
 */

import java.util.ArrayList;


public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10101010;
        int m = 101;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        // The solution is hard to find intuitively
        //may need additional search for information
        //see Pisano period

        ArrayList<Long> massiv = new ArrayList<>();
        massiv.add((long)0);
        massiv.add((long)1);
        for(int i = 2; i < m*6; i++)
        {
            massiv.add((massiv.get(i-1)+massiv.get(i-2))%m);
            if(massiv.get(i) == 1 && massiv.get(i - 1) == 0)
                break;
        }
        long Pizano = massiv.size() - 2; // period length
        int a = (int)(n%Pizano); // The remainder of division by a period is the same
        return massiv.get(a);       // as that of a number

    }


}

