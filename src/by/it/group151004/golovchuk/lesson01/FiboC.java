package by.it.group151004.golovchuk.lesson01;

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
        int n = 47437473;
        int m = 21;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        long[] pizano = new long[m * 6 + 1];
        pizano[0] = 0;
        pizano[1] = 1;
        if (m == 1)
            return pizano[0];
        if (n <= 1)
            return n;
        else
            for (int i = 2; i <= n; ++i) {
                pizano[i] = (pizano[i - 1] + pizano[i - 2]) % m;
                if (pizano[i - 1] == 0 && pizano[i] == 1)
                    return pizano[(int) (n % (i - 1))];
            }
        return pizano[(int) n];
    }
}





