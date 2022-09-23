package by.it.group151004.stahnov.lesson01;

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
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        int[] cache = new int[100000];
        cache[0] = 1;
        cache[1] = 1;
        long res = -1;
        int i = 2;
        boolean flag = true;
        while (flag){
            cache[i] = (cache[i-1] + cache[i-2]);
            if(cache[i] >= m){
                cache[i] = cache[i] - m;
            }
            if(cache[i] == 1 && cache[i-1] == 1){
                flag = false;
            }
            else {
                i++;
            }
        }
        res = cache[(int)n % (i-1) - 1];
        return res;
    }
}

