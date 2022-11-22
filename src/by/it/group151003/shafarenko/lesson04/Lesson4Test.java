package by.it.group151003.shafarenko.lesson04;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.lang.Math;

import static org.junit.Assert.assertTrue;

public class Lesson4Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/shafarenko/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.findIndex(stream);
        //long finishTime = System.currentTimeMillis();
        StringBuilder sb=new StringBuilder();
        for (int index:result){
            sb.append(index).append(" ");
        }
        boolean ok=sb.toString().trim().equals("3 1 -1 1 -1");
        assertTrue("A failed", ok);
    }


    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/shafarenko/lesson04/dataB1.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        boolean ok = true;
        int[] test=new int[result.length];
        System.arraycopy(result,0,test,0,result.length);
        Arrays.sort(test);
        for (int i = 0; i < result.length; i++) {
            ok=ok && (result[i]==test[i]);
        }
        assertTrue("B failed", ok);

        ok = true;
        stream = new FileInputStream(root + "by/it/group151003/shafarenko/lesson04/dataB2.txt");
        result = instance.getMergeSort(stream);
        test = new int[result.length];
        System.arraycopy(result,0,test,0,result.length);
        Arrays.sort(test);
        for (int i = 0; i < result.length; i++) {
            ok=ok && (result[i]==test[i]);
        }
        assertTrue("B failed", ok);

        ok = true;
        stream = new FileInputStream(root + "by/it/group151003/shafarenko/lesson04/dataB3.txt");
        result = instance.getMergeSort(stream);
        test = new int[result.length];
        System.arraycopy(result,0,test,0,result.length);
        Arrays.sort(test);
        for (int i = 0; i < result.length; i++) {
            ok=ok && (result[i]==test[i]);
        }
        assertTrue("B failed", ok);

        ok = true;
        stream = new FileInputStream(root + "by/it/group151003/shafarenko/lesson04/dataB4.txt");
        result = instance.getMergeSort(stream);
        test = new int[result.length];
        System.arraycopy(result,0,test,0,result.length);
        Arrays.sort(test);
        for (int i = 0; i < result.length; i++) {
            ok=ok && (result[i]==test[i]);
        }
        assertTrue("B failed", ok);

        ok = true;
        stream = new FileInputStream(root + "by/it/group151003/shafarenko/lesson04/dataB5.txt");
        result = instance.getMergeSort(stream);
        test = new int[result.length];
        System.arraycopy(result,0,test,0,result.length);
        Arrays.sort(test);
        for (int i = 0; i < result.length; i++) {
            ok=ok && (result[i]==test[i]);
        }
        assertTrue("B failed", ok);
    }


    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/shafarenko/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int[] testArr = new int[]{2, 3, 9, 2, 9};
        int result = instance.calc(testArr);
        //long finishTime = System.currentTimeMillis();
        boolean ok=(2==result);
        assertTrue("C failed", ok);

        testArr = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        result = instance.calc(testArr);
        //long finishTime = System.currentTimeMillis();
        ok=(45==result);
        assertTrue("C failed", ok);

        testArr = new int[50];
        for (int i = 0; i < 50; i++) {
            testArr[i] = (int)Math.random() * 200 - 100;
        }
        int test = 0;
        for (int i = 0; i < 49; i++) {
            for (int j = i + 1; j < 50; j++) {
                if (testArr[i] < testArr[j])
                    test++;
            }
        }
        result = instance.calc(testArr);
        //long finishTime = System.currentTimeMillis();
        ok=(test==result);
        assertTrue("C failed", ok);
    }

}
