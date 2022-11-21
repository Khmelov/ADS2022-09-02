package by.it.group151004.dubovsky.lesson01;

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


        long[] mass= new long[6*m+1];
        mass[0]=0;
        mass[1]=1;

        if(0==m){
            return 0;
        }else {
            if(0==n){
                return 0;
            }else {
                if(1==n){
                    return 1;
                }
            }
        }
        for(int j=2;j<=n;j++){
            mass[j]=(mass[j-2]+mass[j-1])%m;
            if(1==mass[j] && 0==mass[j-1]){
                return mass[(int)(n%(j-1))];
            }
        }
        return 0L;
    }


}

