package by.it.group151002.zaitseva.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    public static long pisano(long m) {
        long prev = 0;
        long curr = 1;
        long res = 0;
        for (int i = 0; i < m * m; i++) {
            long temp = 0;
            temp = curr;
            curr = (prev + curr) % m;
            prev = temp;
            if (prev == 0 && curr == 1)
                res = i + 1;
        }
        return res;
    }

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        long n = 999999999;
        long m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, long m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        long pisanoPeriod = pisano(m);
        n = n % pisanoPeriod;
        long prev = 0;
        long curr = 1;
        for (int i = 0; i < n - 1; i++) {
            long temp = 0;
            temp = curr;
            curr = (prev + curr) % m;
            prev = temp;
        }
        return curr % m;
    }
}

