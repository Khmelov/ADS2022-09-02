package by.it.group151003.matoshko.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.time.Period;

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
        // Если номер равен единице, то выводим единицу как остаток от деления
        // Массив чисел Пизано, т е остатков от деления чисел Фибоначи на m
        // Они тоже подчиняются своего рода ряду фибоначи, но учитывая, что нужно от суммы
        // двух предыдущих взять еще остаток от дедения на m
        int PisanoNumbers[]= new int [6*m+1];
        // Первые 2 члена одного перида всегда 0 и 1
        PisanoNumbers[0]=0;
        PisanoNumbers[1]=1;
        long Period=0;
        // Колиство чисел в периоде
        if (n <= 1L)
            return 1L;
        else{

            for (int i=2;i<=6*m+1;i++)
            {
                // Считаем числа Пизано, попутно считая их количество в периоде
                PisanoNumbers[i]=(PisanoNumbers[i-1]+PisanoNumbers[i-2])%m;
                Period++;
                // Если мы дошли до последовательности  0 1, то мы нашли один период и выходим
                if((PisanoNumbers[i]==1) && (PisanoNumbers[i-1]==0))
                    break;
            }
        }
        // При n % Period получим индекс элемента массива остатков элемент которого нам и нужен
        // Можно на вики проверить :)
        return PisanoNumbers[(int)(n % Period)];

    }


}

