package by.it.group151001.beryozkin.lesson01;

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
        int n = 0;
        int m = 36547;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    public long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        int k = 6*m+1;
            long [] arr = new long [k];
            arr[0] = 0;
            arr[1] = 1;
            long total = arr[0] +arr[1];
            int i = 1;
            while ((arr[i] != 0 || total % m != 0) && i < k){
                i++;
                arr[i] = arr[i-1]+arr[i-2];
                arr[i] = arr[i] % m;
                total = total + arr[i];
            }
            if (total % m != 0 && arr[i] != 0) return arr[i];
            int j = (int)(n%i);
            return arr[j];
         }
    }



