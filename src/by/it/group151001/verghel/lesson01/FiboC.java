package by.it.group151001.verghel.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;
import java.util.List;

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
        List<Long> pisanoModule = new ArrayList<Long>();

        long current = 1, pred = 0, pisanoPeriod = 0;

        boolean isFind = false;
        while(!isFind){
            long temp = current;
            current = (current + pred) % m;
            pisanoModule.add(pred);
            pred = temp;
            pisanoPeriod++;

            if (pred == 0 && current == 1) {
                isFind = true;
            }
        }
        n = n % pisanoPeriod;

        return pisanoModule.get((int) n);
    }


}

