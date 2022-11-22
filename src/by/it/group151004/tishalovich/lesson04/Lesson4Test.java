package by.it.group151004.tishalovich.lesson04;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Lesson4Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson04/dataA.txt");
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
        stream.close();

        stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson04/dataA2.txt");
        result = instance.findIndex(stream);
        sb.setLength(0);
        for (int index:result){
            sb.append(index).append(" ");
        }
        ok = sb.toString().trim().equals("-1 -1 6 6 6");
        assertTrue("A failed", ok);
        stream.close();

    }


    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson04/dataB.txt");
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
        stream.close();

        stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson04/dataB2.txt");
        result = instance.getMergeSort(stream);
        ok = result.length > 3;
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
        InputStream stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        boolean ok=(2==result);
        assertTrue("C failed", ok);
        stream.close();

        stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson04/dataC2.txt");
        result = instance.calc(stream);
        ok = (0 == result);
        assertTrue("C failed", ok);
        stream.close();
    }
}
