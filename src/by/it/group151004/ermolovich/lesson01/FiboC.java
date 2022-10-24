package by.it.group151004.ermolovich.lesson01;

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

        long[] period = new long [6 * m + 1];
        period[0] = 0;
        period[1] = 1;

        if (m == 0)
            return period[0];
        if (n == 0)
            return period[0];
        if (n == 1)
            return period[1];

        for (int i = 2; i <= n; i++){
            period[i] = (period[i-2] + period[i-1]) % m;
            if (period[i] == 1 && period[i-1] == 0)
                return period[(int) (n % (i - 1))];
        }

        return period[(int) n];
    }


}

