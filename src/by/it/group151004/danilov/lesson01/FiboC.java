package by.it.a_khmelev.lesson01;

import java.util.ArrayList;

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

        if (n <= 1) return n;
        ArrayList<Long> nums = new ArrayList<Long>();
        nums.add(0L);
        nums.add(1L);

        int period = 0;
        int i = 2;

        while (true) {
            nums.add((nums.get(i-1)+nums.get(i-2))%(Long.valueOf(m)));
            if (nums.get(i) == 1 && nums.get(i-1) == 0) {
                period = i - 1;
                break;
            } 
            i++;
        }

        int ans = (int)n % period;

        return nums.get(ans);
    }


}

