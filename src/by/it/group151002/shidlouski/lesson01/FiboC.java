package by.it.group151002.shidlouski.lesson01;

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
        int[] period = new int[m * 6];
        int tmp;
        int current = 1;
        int prev = 0;
        period[0] = 0;
        period[1] = 1;
        int next = 2;
        for (int i = 0; i < n; i++) {
            tmp = current;
            current = (current + prev) % m;
            prev = tmp;
            if (current == 1 && prev == 0) {
                break;
            } else {
                period[next] = current;
                next++;
            }

        }
        return period[(int) (n % (next - 1))];
    }

}

