package by.it.group151001.maliauka.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private final long startTime = System.currentTimeMillis();

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

        final int PISANO_PERIOD = 6;

        long[] fibArr = new long[m * PISANO_PERIOD];

        fibArr[0] = 0L;
        fibArr[1] = 1L;

        for (int i = 2; i < fibArr.length; i++) {
            fibArr[i] = (fibArr[i - 1] + fibArr[i - 2]) % m;

            if (fibArr[i - 1] == 0 && fibArr[i] == 1) {
                return fibArr[(int)(n % (i - 1))];
            }
        }

        return fibArr[(int)n];
    }


}

