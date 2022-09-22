package by.it.group151002.bobrik.lesson01;

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
        long n = 999999999;
        int m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        if (n == 1) {
            return 1L;
        }

        long curr = 1; //остаток от деления текущего числа на m
        long prev = 1; //остаток от деления предыдущего числа на m

        int i = 2;
        long a0 = 0L;
        long a1 = 1L;
        long an = 1L;
        long length = 1;
        boolean isFound = false;

        while (!isFound) {
            prev = curr;
            an = a0 + a1;
            curr = an % m;
            a0 = a1;
            a1 = an;
            if (curr == 1 && prev == 0) {
                isFound = true;
            }
            i++;
            length++;
        }

        length--;
        n = n % length;
        a0 = 0L;
        a1 = 1L;
        an = 1L;
        for (i = 2; i <= n; i++) {
            an = a0 + a1;
            a0 = a1;
            a1 = an;
        }

        return an % m;
    }

}

