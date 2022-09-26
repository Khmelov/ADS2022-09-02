package by.it.group151001.danko.lesson01;

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
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {

        if (n == 0) {
            return 0L;
        } else {
            long current_mod = 1;
            long prev_mod;

            long fib_prev = 0L;
            long fib_current = 1L;
            long fib_sum;
            long len = 1;
            boolean flag = false;

            while (!flag) {
                prev_mod = current_mod;
                fib_sum = fib_prev + fib_current;
                fib_prev = fib_current;
                fib_current = fib_sum;
                current_mod = fib_sum % m;
                if (current_mod == 1 && prev_mod == 0) {
                    flag = true;
                }
                else {
                    len++;
                }

            }
            n = n % len;
            fib_prev = 0L;
            fib_current = 1L;
            fib_sum = 1L;

            for(int i = 2; i <= n; i++) {
                fib_sum = fib_prev + fib_current;
                fib_prev = fib_current;
                fib_current = fib_sum;
            }
            return fib_sum & n;
        }

    }

}

