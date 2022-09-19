package by.it.group151001.aleksandrov.lesson01;

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

    public long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        long[] array = new long [6*m+1];
        array[0]=0;
        array[1]=1;
        long res = array[0]+array[1];
        int i=1;
        while (((array[i]!=0) || (res%m !=0)) && (i<(6*m+1)))
        {
            i++;
            array[i]=array[i-1]+array[i-2];
            array[i]%=m;
            res+=array[i];
        }
        if ((res%m !=0) && (array[i]!=0))
        {
            return array[i];
        }
        int ex = (int)(n%i);
        return array[ex];
    }


}

