package by.it.group151003.tolkanitsa.lesson01;

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
        if (n == 1L)
            return 1L;
        int[] prdNums = new int[6*m + 2];
        prdNums[0] = 0;
        prdNums[1] = 1;
        long iter = 0;
        for (int i = 2; i < Math.min(6*m+2, n + 1); ++i) {
            prdNums[i] = (prdNums[i - 1] + prdNums[i - 2]) % m;
            ++iter;
            if ((prdNums[i] == 1) && (prdNums[i - 1] == 0)) break;
        }
        return prdNums[(int)(n % iter)];
    }


}

