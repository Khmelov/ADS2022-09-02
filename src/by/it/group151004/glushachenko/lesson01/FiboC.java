package by.it.group151004.glushachenko.lesson01;

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
        System.out.printf("\nfasterC(%d)=%d \n\t", n, fibo.fasterC(n, m));
    }

    long fasterC(long n, int m) {
        long[] arr = new long[m * 6 + 2];
        arr[0] = 0;
        arr[1] = 1;

        if (n < 2)
            return n;

        int i = 2;
        do{
            arr[i] = (arr[i - 1] + arr[i - 2]) % m;
            i++;
        } while(!(arr[i - 1] == 1 && arr[i - 2] == 0));

        return arr[(int) (n % (i - 2))];
    }

}

