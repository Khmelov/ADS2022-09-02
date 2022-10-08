package by.it.group151004.kudrevatykh.lesson01;

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

        int P[]= new int [6*m+1];
        P[0]=0;
        P[1]=1;
        long Period=0;
        for (int i=2;i<=6*m+1;i++)
        {
            P[i]=(P[i-1]+P[i-2])%m;
            Period++;
            if((P[i]==1) && (P[i-1]==0))
                break;
        }
        return P[(int)(n%Period)];
    }

}

