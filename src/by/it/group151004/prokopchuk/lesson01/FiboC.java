package by.it.group151004.prokopchuk.lesson01;

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
        long n = 999999999;
        long m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    private long findPisanoSize(long m)
    {
        long prev = 0;
        long curr = 1;
        long res = 0;

        for(int i = 0; i < m * m; i++)
        {
            long temp = 0;
            temp = curr;
            curr = (prev + curr) % m;
            prev = temp;

            if (prev == 0 && curr == 1)
                res = i + 1;//find a pisano period size
        }
        return res;
    }

    long fasterC(long n, long m) {
        ArrayList<Long> list;
        if (n == 1 || n == 2) {
            return 1 % m;
        } else {
            n %= findPisanoSize(m);
            list = new ArrayList<>();
            long[] arr = {0, 1, 1};//start array
            list.add(0L);
            list.add(arr[1] % m);
            list.add(arr[2] % m);
            for (int i = 2; i < n; i++) {
                arr[0] = arr[1];
                arr[1] = arr[2];
                arr[2] = (arr[0] + arr[1])%m;
                list.add(arr[2]);
            }
        }
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        return list.get((int) n);
    }

}
