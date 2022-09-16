package by.it.group151002.saprin.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 999999999;
        int m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        ArrayList<Long> arr = new ArrayList<>();
        long prev = 0;
        long curr = 1;
        arr.add(0L);
        arr.add(1L);
        int i = 0;
        boolean flag = true;
        while (i < m*m && flag){
            if (i > 2 && arr.get(i) == 1 && arr.get(i - 1) == 0){
                arr.remove(i);
                arr.remove(i - 1);
                flag = false;
            } else {
                long a = prev;
                prev = curr;
                curr += a;
                arr.add(curr % m);
                i++;
            }
        }
        return arr.get((int)(n % arr.size()));
    }


}

