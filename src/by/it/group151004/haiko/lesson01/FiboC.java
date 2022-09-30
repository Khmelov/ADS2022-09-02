package by.it.group151004.haiko.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;

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
        if (m==2){
            if (n % 3 ==0){
                return 0;
            }
            else return 1;
        }
        int[] modRes = new int[m*6+2];
        BigInteger First=BigInteger.ZERO, Second = BigInteger.ONE, Res = BigInteger.ZERO;
        modRes[0] = 0; modRes[1] = 1;
        int counter = 2;
        boolean timePer=true;
        while (timePer){

            Res = First.add(Second);
            First = Second;
            Second = Res;
            modRes[counter] = Res.mod(BigInteger.valueOf(m)).intValue();
            counter++;
            if(modRes[counter-1]==1 && modRes[counter-2]==0){
                timePer=false;
                counter-=2;
            }
        }
        return  modRes[(int)n % counter];

    }


}

