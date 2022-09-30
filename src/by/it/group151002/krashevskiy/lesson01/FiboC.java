package by.it.group151002.krashevskiy.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        long n = 999999999L;
        int m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        int period = 1;
        long[] array = {0, 1, 1};

        int curr = 1;
        int next = 1;

        ArrayList<Integer> periodNums = new ArrayList<>();
        periodNums.add(0);

        int i = 2;
        do {
            periodNums.add(curr);
            period++;
            array[0] = array[1];
            array[1] = array[2];
            array[2] = array[0] + array[1];

            curr = next;
            next = (int)(array[2] % m);

            i++;
        } while (!(curr == 0 && next == 1) && i < n);

        return periodNums.get((int)(n % period));
    }

}