package by.it.group151001.loziuk.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;
import java.util.ArrayList;

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
        ArrayList<Integer> MyArr = new ArrayList<Integer>();
        MyArr.add(0);
        MyArr.add(1);
        MyArr.add(1);
        int pred = 1;
        int now = 1;
        int temp;
        int lengthOfPeriod = 3;
        do{
            temp = (pred + now) % m;
            pred = now;
            now = temp;
            MyArr.add(now);
            lengthOfPeriod++;
        }
        while (!(pred == 0 && now == 1));
        lengthOfPeriod -= 2;
        n %= lengthOfPeriod;

        return MyArr.get((int)n);
    }


}

