package by.it.group151004.tishalovich.lesson01;

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
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        int maxPeriod = 6 * m;
        long[] arr = new long[maxPeriod];

        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 1;

        int i = 3;
        while(!(arr[i-1] == 1 && arr[i-2] == 0)){
            arr[i] = (arr[i - 1] + arr[i - 2])%m;
            i++;
        }

        int period = i - 2;

        return arr[(int)(n % period)] % m;
    }


}

