package by.it.group151004.ivanenko.lesson01;

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
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        long fib[] = new long[6*m + 2];

        if (n < 2) return n;

        fib[0] = 0l;
        fib[1] = 1l;
        int i = 2;
        do
        {
            fib[i] = fib[i - 1] + fib[i - 2];
            i++;
        } while ((fib[i - 1] % m != 1) || (fib[i - 2] % m != 0));


        return fib[(int)n%(i- 2)]%m;
    }


}

