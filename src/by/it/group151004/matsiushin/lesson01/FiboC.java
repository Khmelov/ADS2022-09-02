package by.it.group151004.matsiushin.lesson01;

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
        if (n == 1) {
            return 1L;
        } else 
        {
            long fib1 = 0L;
            long fib2 = 1L;
            long fib;
            long[] modFib = new long[m*6+2];
            modFib[0] = 0;
            modFib[1] = 1;

            long length = 1;

            int i = 2;
            int ind = 0;

            boolean flag = true;

            while (flag)
            {
                fib = fib1 + fib2;
                modFib[i] = fib % m;
                fib1 = fib2;
                fib2 = fib;
                if (modFib[i] == 1 && modFib[i - 1] == 0) {
                    flag = false;
                }
                i++;
                length++;
            }

            length--;
            ind = (int)(n % length);
            /*if (n % length == 0)
            {
                ind = (int)(length - 1);
            }
            else
            {
                ind = (int)(n % length - 1);
            }*/
            if ( (ind <= m* 6L +1 ) && (ind >= 0) )
                return modFib[ind];
                //return (ind);
            else
                return (50);

        }
    }

}

