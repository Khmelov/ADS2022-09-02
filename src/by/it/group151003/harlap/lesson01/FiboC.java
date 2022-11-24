package by.it.group151003.harlap.lesson01;

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
        int[] mas = new int[m*6+1];
        mas[0]=0;
        mas[1]=1;
        if (n<2)
            return n;
            else
                for(int i=2;i<=n;i++){
                  mas[i]=(mas[i-1]+mas[i-2])%m;
                  if (mas[i]==1 && mas[i-1]==0)
                      return mas[(int)(n%(i-1))];
                }
        return mas[(int) n];
    }


}

