package by.it.group151003.barilko.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */
import java.util.Vector;

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

        Vector<Long> vec = new Vector<Long>();
        vec.add(0L);
        vec.add(1L); 

        for(int i = 2; i < m*m + 1; ++i)
        {
            Long temp = Long.sum(vec.get(i - 1), vec.get(i - 2)) % m;
            vec.add(temp);

            if (vec.get(i) == 1L && vec.get(i - 1) == 0L)
                return vec.get((int)n % (i - 1));
        }

        return vec.get((int)n);
    }


}

