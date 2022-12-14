package by.it.group151001.manchik.lesson01;

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
        long[] PizArray = new long[6*m];
        PizArray[0] = 0;
        PizArray[1] = 1;
        PizArray[2] = 1;
        int i = 3;
        while (!(PizArray[i-2] == 0 && PizArray[i-1] == 1)){
            PizArray[i] = (PizArray[i-2] + PizArray[i-1])%m;
            i++;
        }
        return PizArray[(int)(n%(i-2))];
    }


}

