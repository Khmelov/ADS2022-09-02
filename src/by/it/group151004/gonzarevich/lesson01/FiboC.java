package by.it.group151004.gonzarevich.lesson01;

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

    int get_period(int m) {
        int prev = 0;
        int cur = 1;
        int temp = prev + cur, i;
        for (i = 0; i < m * m; i++) {
            temp = (prev + cur) % m;
            prev = cur;
            cur = temp;
            if (prev == 0 && cur == 1) break;
        }
        return i + 1;
    }
    long fasterC(long n, int m) {
        long left = (long) (n % get_period(m));
        long prev = 0;
        long cur = 1;

        long fib_num = left;
        for (int i = 1; i < left; i++) {
            fib_num = prev + cur;
            prev = cur;
            cur = fib_num;
        }

        return fib_num % m;
    }


}

