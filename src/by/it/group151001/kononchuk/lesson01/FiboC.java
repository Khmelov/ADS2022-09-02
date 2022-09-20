package by.it.group151001.kononchuk.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;
import java.util.List;

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
        List<Long> pisanoModule = new ArrayList<Long>();

        long cur = 1, prev = 0, pisanoPeriod = 2;

        boolean isFinded = false;
        while(!isFinded){
            long temp = cur;
            cur = (cur + prev) % m;
            pisanoModule.add(prev);
            prev = temp;
            pisanoPeriod++;
            if (prev == 0 && cur == 1) {
                isFinded = true;
                pisanoPeriod -= 2;
            }
        }
        n = n % pisanoPeriod;

        return pisanoModule.get((int) n);
    }
}

