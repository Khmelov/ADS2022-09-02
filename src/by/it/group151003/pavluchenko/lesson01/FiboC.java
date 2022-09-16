package by.it.group151003.pavluchenko.lesson01;

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
        int [] MyArr = new int [m*6];
        MyArr[0] = 0;
        MyArr[1] = 1;
        if (n <= 1)
            return n;
        for (int i = 2; i <= n; i++)
        {
            MyArr[i] = (MyArr[i - 1] + MyArr[ i - 2]) % m;
            if ( MyArr[i] == 1 && MyArr[ i - 1] == 0)
            {
                return MyArr[(int) (n % (i - 1))];
            }
        }
        return MyArr[ (int) n];
    }


}

