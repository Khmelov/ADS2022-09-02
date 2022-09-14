package by.it.group151001.milkovskaya.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */
import java.util.ArrayList;
import java.util.List;

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
        if (n <= 2) return 1;
        if (m > n) return n;
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        ArrayList<Long> pisPeriod = new ArrayList<>();
         long pred = 0, curr = 1, temp;
         boolean end = false;
         pred = 0;
         curr =1;
         while (!end){
             pisPeriod.add(pred);
            temp = curr;
             curr= (curr + pred) % m;
             pred = temp;
           // ++lenPis;
            if (curr == 1 && pred ==0){
                end = true;
            }
         }
         n = n % (pisPeriod.size());
        return pisPeriod.get((int)n);
    }


}

