package by.it.group151002.kuvshinov.lesson01;



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
        long[] fib = new long[m * m + 1];
        fib[0] = 0;
        fib[1] = 1;
        long b = 0;
        int i = 2;
        while (i < fib.length) {
            fib[i] = (fib[i - 1] + (fib[i - 2])) % m;
            b++;
            if ((fib[i] == 1) && (fib[i - 1] == 0)) break;
            i++;
        }
        return fib[(int) (n % b)];
    }


}
