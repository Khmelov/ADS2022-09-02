package by.it.group151001.saprankov.lesson01;

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
        long ps=0;
        ps=pisPer(m);

        long rem_1 = n % ps;
        long[] arr = new long[(int)rem_1];
        arr[0]=1;
        if (arr.length>1)
        arr[1]=1;
        for(long i=2;i<arr.length;i++)
        {
            arr[(int) i] = arr[(int) i-1]+arr[(int) i-2];
        }
        long num =arr[arr.length-1];

        return num % m;
    }
    long pisPer(long n){
        long curr=1;
        long prev=0;
        long res=0;
        for(long i=0;i<=6*n;i++){
            long temp=0;
            temp=curr;
            curr=(prev+curr)%n;
            prev=temp;
            if (prev==0 && curr==1){
                res=i+1;
            }
        }
        return res;

    }


}

