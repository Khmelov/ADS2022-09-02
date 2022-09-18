package by.it.group151002.vakar.lesson01;

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

    long findLengthOfPisanoPeriod (int m) {
        long ans = 0L;
        long prevNum = 0L;
        long currNum = 1L;
        long tempNum;
        for (int i = 0; i < m*m; i++) {
            tempNum = currNum;
            currNum = (prevNum + currNum) % m;
            prevNum = tempNum;
            if (prevNum == 0 && currNum == 1) {
                ans = i + 1;
            }
        }
        return ans;
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        long lengthOfPisanoPeriod = findLengthOfPisanoPeriod(m);
        n %= lengthOfPisanoPeriod;

        if (n < 2) {
            return n;
        } else {
            long prevNum = 0;
            long currNum = 1;
            long tempNum;
            for (int i = 0; i < n - 1; i++) {
                tempNum = currNum;
                currNum = (prevNum + currNum) % m;
                prevNum = tempNum;
            }
            return currNum % m;
        }
    }


}

