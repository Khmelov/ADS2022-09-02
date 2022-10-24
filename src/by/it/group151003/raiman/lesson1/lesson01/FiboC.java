package by.it.group151003.raiman.lesson1.lesson01;

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

    public long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        if (n == 1) {
            return 1L;
        } else {
            long current = 1;
            long previous = 1;

            int i = 2;
            long a0 = 0L;
            long a1 = 1L;
            long an = 1L;
            long length = 1;
            boolean Found = false;

            while (!Found) {
                previous = current;
                an = a0 + a1;
                current = an % m;
                a0 = a1;
                a1 = an;
                if (current == 1 && previous == 0) { Found = true; }
                i++;
                length++;
            }
            length--;
            n = n % length;
            a0 = 0L;
            a1 = 1L;
            an = 1L;
            for (i = 2; i <= n; i++){
                an = a0 + a1;
                a0 = a1;
                a1 = an;
            }

            return an % m;
        }

    }


}

