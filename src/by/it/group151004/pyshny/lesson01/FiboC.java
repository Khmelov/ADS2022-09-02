package by.it.group151004.pyshny.lesson01;
import java.math.BigDecimal;
import java.math.BigInteger;
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
        int n = 64;
        int m = 56;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        int[] ost = new int[m*m+1];
        boolean flag=true;
        int i=2;
        BigInteger fir=BigInteger.ZERO,sec=BigInteger.ONE,res;
        ost[0]=1;
        ost[1]=1;
        long vsegochasivotonoreshenie;
        if (m != 2) {
            while (flag) {
                res = fir.add(sec);
                fir = sec;
                sec = res;
                ost[i] = (res.mod(BigInteger.valueOf(m))).intValue();
                i++;
                if (ost[i - 1] == 1 && ost[i - 2] == 0) flag = false;
            }
            int num;
            num = (int) (n % (i - 2));
            if ((num-1)<0) num=i;
            vsegochasivotonoreshenie = ost[num];
        }
        else {
            int num;
            num =(int) n % 3;
            ost[0]=1;
            ost[1]=1;
            ost[2]=0;
            if ((num-1)<0) num=3;
            vsegochasivotonoreshenie = ost[num-1];
        }
//        System.out.printf("%d\n",i-2);
        return vsegochasivotonoreshenie;
    }
}

