package by.it.group151002.krumkachev.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.nanoTime();

    private long time() {
        return System.nanoTime() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        long n = 995432096583609731L;
        int m = 79327;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        int[] pisanoPeriod;
        if (m < 6)
            pisanoPeriod = new int[24];
        else
            pisanoPeriod = new int[6 * m];
        pisanoPeriod[0] = 0;
        pisanoPeriod[1] = 1;
        int prev = 0;
        int curr = 1;
        int next = 2;
        boolean periodNotFound = true;
        for (int i = 0; i < n && periodNotFound; i++) {
            int tmp = curr;
            curr = (prev + curr) % m;
            prev = tmp;
            if (curr == 1 && prev == 0)
                periodNotFound = false;
            else
                pisanoPeriod[next++] = curr;
        }
        return pisanoPeriod[(int) (n % (next - 1))];
    }


}

