package by.it.group151004.buhovets.lesson01;

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
        int n = 55555;
        int m = 96;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    public int countPisano(long n, int m){
        int arrLen=m*m+1;
        long[] a = new long[arrLen];
        fibInitialize(a);
        int i=2;
        int PisanoLen=0;

        while( (!(a[i]%m==0) || !(a[i+1]%m==1)) && i<=arrLen-1){
            i++;
        }
        PisanoLen=i;
        return PisanoLen;
    }

    public void fibInitialize(long[] arr){
        arr[0]=0; arr[1]=1;
        for(int i=2; i<arr.length; i++){
            arr[i]=arr[i-1]+arr[i-2];
        }
    }
    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        if(n<2) return n;


        int pis = countPisano(n,m);//расчет периода пизано
        long x = n%pis; //остаток от номера числа к периоду...то же самое в остатке получается что и в номере n
        long temp,   cur=1, prev=0;

        for(int i=2; i<=x;i++){
            temp=cur;
            cur=cur+prev;
            prev=temp;
        }

        long    result=cur%m; //cur - our Fibo(n%pisano), m-div
        return result;
    }


}

