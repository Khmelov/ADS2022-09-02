package by.it.group151001.yankova.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;
import java.util.Arrays;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 999999999;
        int m = 10000;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        if(m <= 0 || n <= 0) return -1L;
        if(m == 1) return 0L;
        long f, s = 1, t = 1;
        ArrayList<Long> sqnc = new ArrayList<>(Arrays.asList(0L));
        do{
            f = s; s = t;
            t = (f+s)%m;
            sqnc.add(f);
        }while (!(f == 0 && s == 1));
        return (sqnc.get((int)(n % (sqnc.size()-1))));
    }


}

