package by.it.group151001.shcherba.lesson01;

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
        System.out.printf("fasterC(%d) = %d \n\t time = %d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        {
            long prev = 0;
            long curr = 1;
            long pisanoLength = 0;
            List<Long> pisanoPeriod = new ArrayList<Long>();
            boolean isFind = false;

            while (!isFind)
            {
                long temp = curr;
                curr = (prev + curr) % m;
                pisanoPeriod.add(prev);
                prev = temp;
                pisanoLength++;

                if (prev == 0 && curr == 1)
                    isFind = true;

            }
            n = n % pisanoLength;
            return pisanoPeriod.get((int)n);
        }
    }


}

