package by.it.group151001.matsiushenko.Lesson1;
import java.util.ArrayList;
import java.util.List;
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
        List<Long> PisanoModule=new ArrayList<Long>();
        long cur=1, prev=0, period=2, temp;
        boolean f=false;

        while(!f){
            temp=cur;
            cur=(prev+cur)%m;
            PisanoModule.add(prev);
            prev=temp;
            period=period+1;
            if(prev==0 && cur==1) {
                f=true;
                period=period-2;
            }
        }
        n=n%period;
        return PisanoModule.get((int) n);
    }


}

