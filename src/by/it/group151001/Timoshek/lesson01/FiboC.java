package by.it.group151001.Timoshek.lesson01;

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
        int n = 999999999;
        int m = 321;

        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n,m), fibo.time());
    }

    long fasterC(long n, int m) {

        long x=1,y=2%m,c,p=1,res;

        while((x!=1)||(y!=1))
        {
        c=x;
        x=y;
        y=(x+c) % m;
        p++;
        }

        n=((n-1)%p)+1;

        x=1;y=1;
        for(int i=3;i<=n;i++)
        {
            c=x;
            x=y;
            y=(x+c) % m;
        }
        res=y;

        if(n<3)
            res=1;

        return res%m;
    }


}

