package by.it.group151003.onuchina.lesson01;

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
        //см. период Пизано
        int max_period_test = 6 * m + 2;
        int[] div_rems = new int[max_period_test + 1];
        if (n<=1) return (int)n;
        div_rems[0] = 0;
        div_rems[1] = 1;

        int i = 2;
        for (; i <= max_period_test; i++){
            div_rems[i] = (div_rems[i - 1] + div_rems[i - 2]) % m;
            if (div_rems[i] == 1 && div_rems[(i - 1)] == 0) break;
        }
        return div_rems[(int)(n % (i - 1))];
    }


}

