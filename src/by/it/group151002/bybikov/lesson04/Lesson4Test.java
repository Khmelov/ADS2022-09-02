package by.it.group151002.bybikov.lesson04;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Lesson4Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataA.txt");
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
        stream = new FileInputStream(root + "by/it/group151002/bybikov/lesson04/dataA_Test_1.txt");
        result=instance.findIndex(stream);
        sb = new StringBuilder();
        for (int index:result){
            sb.append(index).append(" ");
        }
        ok = sb.toString().trim().equals("4 10 3 -1 1 -1 7 -1 5 9 8 1 -1");
        assertTrue("A failed", ok);
    }


    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        boolean ok=result.length>3;
        int test[]=new int[result.length];
        System.arraycopy(result,0,test,0,result.length);
        Arrays.sort(test);
        for (int i = 0; i < result.length; i++) {
            ok=ok && (result[i]==test[i]);
        }
        assertTrue("B failed", ok);
        stream = new FileInputStream(root + "by/it/group151002/bybikov/lesson04/dataB_Test_1.txt");
        result=instance.getMergeSort(stream);
        ok=result.length>3;
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
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        boolean ok=(2==result);
        assertTrue("C failed", ok);

        stream = new FileInputStream(root + "by/it/group151002/bybikov/lesson04/dataC_Test_1.txt");
        result = instance.calc(stream);
        ok = (35 == result);
        assertTrue("C failed", ok);

    }

}
