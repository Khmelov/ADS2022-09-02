package by.it.group151001.pastukhou.lesson01;

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
        long n = 0;
        int m = 3;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        if (m == 0 || n < 0) return -1L;
        if (m == 1) return 0L;

        ArrayList<Long> arr = new ArrayList<>();
        arr.add(0L);
        arr.add(1L);
        long f0 = 0, f1 = 1, f2;
        do {
            f2 = (f0 + f1) % m;
            f0 = f1;
            f1 = f2;
            arr.add(f1 % m);
        } while (!((arr.get(arr.size() - 1).equals(arr.get(1))) && (arr.get(arr.size() - 2).equals(arr.get(0)))));
        int period = arr.size() - 2;
        return arr.get((int)(n % period));
    }


}

