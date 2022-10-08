package by.it.group151002.redkovskiy.lesson01;
/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {
    public static final int MAX_PIZANO_PERIOD = 6;
    //период Пизано числа m может быть не больше чем в 6 раз больше самого числа m
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
        int i = 1;
        int position;
        long multiplier;
        long[] arr = new long[MAX_PIZANO_PERIOD*m];
        boolean isPeriodFound = false;
        if(n <= 1)
            return n % m;
        arr[0] = 0; arr[1] = 1;
        while (!isPeriodFound && (i < n)){
            i++;
            arr[i] = arr[i-1] + arr[i-2];
            if(arr[i-1] % m == 0 && arr[i] % m == 1)
                isPeriodFound = true;
        }
        multiplier = n / (i-1);
        position = (int)(n - multiplier*(i-1));
        return arr[position] % m ;
    }


}

