package by.it.group151004.sakovsky.lesson01;

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
        int n = 1234567899;
        int m = 117;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        int[] ost = new int[m*m+1];
        boolean flag = true;
        int i=2;
        BigInteger first = BigInteger.ZERO,second = BigInteger.ONE, res;
        ost[0]=1;
        ost[1]=1;
        long result;
        if (m != 2) {
            while (flag) {
                res = first.add(second);
                first = second;
                second = res;
                ost[i] = (res.mod(BigInteger.valueOf(m))).intValue();
                i++;
                if (ost[i - 1] == 1 && ost[i - 2] == 0) {
                    flag = false;
                }
            }
            int num;
            num = (int) (n % (i - 2));
            if ((num - 1) < 0){
                num = i;
            }
            result = ost[num];
        }
        else {
            int num;
            num = (int) n % 3;
            result = ost[num - 1];
        }
            return result;
    }
}

