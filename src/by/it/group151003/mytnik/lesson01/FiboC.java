package by.it.group151003.mytnik.lesson01;

import java.math.BigInteger;

public class FiboC {

    private final long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public long fasterC(long n, int m) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;

        n = n % pisanoPeriod(m);

        BigInteger prev = BigInteger.ZERO;
        BigInteger curr = BigInteger.ONE;
        BigInteger temp;

        for (int i = 0; i < n - 1; ++i) {
            temp = prev;
            prev = curr;
            curr = temp.add(curr);
        }

        return curr.mod(BigInteger.valueOf(m)).longValue();
    }

    private long pisanoPeriod(long m) {
        long previous = 0;
        long current = 1;
        long result = 0;
        long tmp;


        for (long i = 0; i < m * m; i++) {
            tmp = previous;
            previous = current;
            current = (tmp + current) % m;

            if (previous == 0 && current == 1) {
                result = i + 1;
            }
        }

        return result;
    }
}

