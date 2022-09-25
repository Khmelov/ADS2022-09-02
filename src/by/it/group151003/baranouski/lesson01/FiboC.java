package by.it.group151003.baranouski.lesson01;

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
        long n = 10L;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        if (n == 1L)
            return 1L;
        int[] periodNums = new int[6*m + 2];
        periodNums[0] = 0;
        periodNums[1] = 1;
        long iteration = 0;
        for (int i = 2; i < Math.min(6*m+2, n + 1); ++i) {
            periodNums[i] = (periodNums[i - 1] + periodNums[i - 2]) % m;
            ++iteration;
            if ((periodNums[i] == 1) && (periodNums[i - 1] == 0)) break;
        }
        return periodNums[(int)(n % iteration)];
    }


}

