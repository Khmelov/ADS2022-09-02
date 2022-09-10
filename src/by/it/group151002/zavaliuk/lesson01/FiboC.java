package by.it.group151002.zavaliuk.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        long n = 999999999;
        int m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    private static long findPeriodPisano(int m) {
        long a = 0, b = 1, res = 0;

        for (int i = 0; i < m * m; i++) {
            long c = b;
            b = (a + b) % m;
            a = c;

            if (a == 0 && b == 1)
                res = i + 1;
        }
        return res;
    }

    long fasterC(long n, int m) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        
        n = n % findPeriodPisano(m);

        long a = 0, b = 1;
        for (int i = 0; i < n - 1; i++) {
            long c;
            c = b;
            b = (a + b) % m;
            a = c;
        }
        return b % m;
    }


}

