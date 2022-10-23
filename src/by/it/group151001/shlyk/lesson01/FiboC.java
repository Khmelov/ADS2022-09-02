package by.it.group151001.shlyk.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;
import java.util.ArrayList;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        long n = 1000_000_000_000_000_000L;
        int m = 100_000;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    final int AVAILABLE_MISTAKE = 100;
    long fasterC(long n, int m) {
        if (n <= 2)
            return 1;
        ArrayList<Long> pizSeq = new ArrayList<>();
        ArrayList<Long> checkSeq = new ArrayList<>();

        int pizPeriod = 2;
        int tempPeriod = pizPeriod;
        pizSeq.add(0L);
        pizSeq.add(1L);

        boolean isCompleted = false;
        boolean isTriggered = false;

        long lastRest = 0;
        long currRest = 1;
        long temp;
        while(!isCompleted){
            temp = currRest;
            currRest = (currRest + lastRest) % m;
            lastRest = temp;

            if (isTriggered){
                checkSeq.add(currRest);
                isTriggered = currRest == pizSeq.get(tempPeriod - pizPeriod);
                tempPeriod++;
                isCompleted = tempPeriod >= (pizPeriod + AVAILABLE_MISTAKE) || (tempPeriod >= 2 * pizPeriod);
            } else{
                if (checkSeq.size() > 0) {
                    pizSeq.addAll(checkSeq);
                    checkSeq.clear();
                    pizPeriod = tempPeriod;
                }
                tempPeriod++;
                isTriggered = currRest == 0;
                if (isTriggered)
                    checkSeq.add(currRest);
                else{
                    pizPeriod = tempPeriod;
                    pizSeq.add(currRest);
                }
            }

        }
        long index =  (n % pizPeriod);
        return pizSeq.get((int) index );
    }


}

