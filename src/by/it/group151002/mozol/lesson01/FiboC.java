package by.it.group151002.mozol.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;
import java.util.function.LongToIntFunction;

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
        ArrayList<Long> arr = new ArrayList<>();
        long prev1 = 0L;
        long num = 0L;
        int period = 2;
        long prev2 = 1L;
        long fibNum = 0L;
        arr.add(0L);
        arr.add(1L);
        for (int i = 2; i < n + 1; i++){
            fibNum = prev1 + prev2;
            arr.add(fibNum % m);
            period++;
            prev1 = prev2;
            prev2 = fibNum;
            if (arr.get(i) == 1 && arr.get(i - 1) == 0) {
                period = period - 2;
                break;
            }
        }
        num = n % period;
        if (num == 0)
            num = period;
        return arr.get((int)num);
    }


}

