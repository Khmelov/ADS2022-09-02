package by.it;

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

    public static long fibo(long m)
    {
        long prev = 0;
        long cur = 1;
        long reslt = 0;

        for(int i = 0; i < m * m; i++)
        {
            long tmp = cur;
            cur = (prev + cur) % m;
            prev = tmp;
            if (prev == 0 && cur == 1)
                reslt = i + 1;
        }
        return reslt;
    }


    long fasterC(long n, int m) {
        long ans = fibo(m);
        long prev = 0;
        long cur = 1;
        n %= ans;

        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;

        for (int i = 0; i < n - 1; i++) {
            long tmp = cur;
            cur = (prev + cur) % m;
            prev = tmp;
        }
        return cur % m;
    }

}

