package by.it.group151003.yagnish.lesson06;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;


public class C_LongNotUpSubSeq {

    int[] getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int[] result = new int[n];
        int d[] = new int [n];
        for (int i = 0; i < n; i++) {
            d[i]=1;
            int tmp=1;
            for (int j = 0; j < i; j++) {
                if ((m[i] <= m[j]) && (d[j] + 1 > d[i])) {
                    result[tmp] = j+1;
                    d[i] = d[j] + 1;
                    tmp++;
                }
            }
            result[i]=i+1;
        }
        for (int i = 0; i < n; i++)
            result[0] = Math.max(result[0],d[i]);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int[] result = instance.getNotUpSeqSize(stream);
        System.out.println(result[0]);
        for (int i = 1; i < result.length; i++)
            System.out.println(result[i]);
    }

}