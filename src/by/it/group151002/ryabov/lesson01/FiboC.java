package by.it.group151002.ryabov.lesson01;

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
        long n = 2334534;
        int m = 6;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        long[] arrL;
        if (m >= 6)
            arrL = new long[6 * m];
        else
            arrL = new long[24];
        arrL[0] = 0;
        arrL[1] = 1;
        int i = 2;
        while ((!(arrL[i - 1] == 1 && arrL[i - 2] == 0)) || (i <= 2)){
            arrL[i] = (arrL[i - 1] + arrL[i - 2]) % m;
            i++;
        }
        return arrL[(int) (n % (i - 2))];
    }


}

