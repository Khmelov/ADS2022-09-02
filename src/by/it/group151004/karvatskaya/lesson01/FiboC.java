package by.it.group151004.karvatskaya.lesson01;

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
        int n = 999999999;
        int m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    public static long getperiod(long m) {

        long num1 = 0;
        long num2 = 1;
        long buf = 0;

        int i;
        for (i = 0; i < m * m; i++) {
            long temp;
            temp = num2;
            num2 = (num1 + num2) % m;
            num1 = temp;

        if (num1 == 0 && num2 == 1)
             buf=i+1;
        }
        return buf;
    }

    long fasterC(long n, int m) {
        long periodfinal = getperiod(m);
        n = n % periodfinal;
        long num1 = 0;
        long num2 = 1;
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        int i;
        for (i = 0; i < n - 1; i++) {
            long temp;
            temp = num2;
            num2 = (num1 + num2) % m;
            num1 = temp;
        }
        return num2 % m;

    }
}

