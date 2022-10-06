package by.it.group151001.hlebanova.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 1000000000;
        int m = 100000;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        long []Arr;
        Arr = new long [6*m];
        Arr[0] = 0;
        Arr[1] = 1;
        Arr[2] = 1;
        long BfrP = 1, NewP = 1, temp;
        int i = 3, Pizano = 3;
        do {
            temp = (BfrP + NewP)%m;
            BfrP = NewP;
            NewP = temp;
            Arr[i] = NewP;
            i++;
            Pizano++;

        } while (!(BfrP == 0 && NewP == 1));
        Pizano -= 2;
        n %= Pizano;
        return Arr[(int) n];
    }


}

