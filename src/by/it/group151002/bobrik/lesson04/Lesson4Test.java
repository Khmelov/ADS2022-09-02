package by.it.group151002.bobrik.lesson04;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Lesson4Test {
    @Test (timeout = 1000)
    public void A1() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/bobrik/lesson04/dataA1.txt");
        A_BinaryFind instance = new A_BinaryFind();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.findIndex(stream);
        //long finishTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int index:result){
            sb.append(index).append(" ");
        }
        boolean ok = sb.toString().trim().equals("3 1 -1 1 -1");
        assertTrue("A1 failed", ok);
    }

    @Test (timeout = 1000)
    public void A2() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/bobrik/lesson04/dataA2.txt");
        A_BinaryFind instance = new A_BinaryFind();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.findIndex(stream);
        //long finishTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int index:result){
            sb.append(index).append(" ");
        }
        boolean ok = sb.toString().trim().equals("-1 -1 -1 -1 -1 -1 -1 -1 -1 -1");
        assertTrue("A2 failed", ok);
    }

    @Test (timeout = 1000)
    public void A3() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/bobrik/lesson04/dataA3.txt");
        A_BinaryFind instance = new A_BinaryFind();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.findIndex(stream);
        //long finishTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int index:result){
            sb.append(index).append(" ");
        }
        boolean ok = sb.toString().trim().equals("1 2 3 4 5 6 7 8 9 10");
        assertTrue("A3 failed", ok);
    }



    @Test
    public void B1() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/bobrik/lesson04/dataB1.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        boolean ok = result.length > 3;
        int test[] = new int[result.length];
        System.arraycopy(result,0,test,0,result.length);
        Arrays.sort(test);
        for (int i = 0; i < result.length; i++) {
            ok = ok && (result[i] == test[i]);
        }
        assertTrue("B1 failed", ok);
    }

    @Test (timeout = 1000)
    public void B2() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/bobrik/lesson04/dataB2.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        boolean ok = result.length > 3;
        int test[] = new int[result.length];
        System.arraycopy(result,0,test,0,result.length);
        Arrays.sort(test);
        for (int i = 0; i < result.length; i++) {
            ok = ok && (result[i] == test[i]);
        }
        assertTrue("B2 failed", ok);
    }

    @Test
    public void C1() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/bobrik/lesson04/dataC1.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        boolean ok=(2==result);
        assertTrue("C1 failed", ok);
    }

    @Test
    public void C2() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/bobrik/lesson04/dataC2.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        boolean ok=(6==result);
        assertTrue("C2 failed", ok);
    }

    @Test
    public void C3() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/bobrik/lesson04/dataC3.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        boolean ok=(5==result);
        assertTrue("C3 failed", ok);
    }

    @Test
    public void C4() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/bobrik/lesson04/dataC4.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        boolean ok=(0==result);
        assertTrue("C4 failed", ok);
    }
}
