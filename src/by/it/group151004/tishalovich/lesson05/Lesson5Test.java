package by.it.group151004.tishalovich.lesson05;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Lesson5Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        boolean ok=Arrays.equals(result,new int[]{1,0,0});
        assertTrue("A failed", ok);

        stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson05/dataA2.txt");
        result = instance.getAccessory(stream);
        ok = Arrays.equals(result,new int[]{2,2,2,2,3,1});
        assertTrue("A failed", ok);

    }


    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        boolean ok=Arrays.equals(result,new int[]{2,2,3,9,9});
        assertTrue("B failed", ok);
        stream.close();

        stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson05/dataB2.txt");
        result=instance.countSort(stream);
        ok=Arrays.equals(result,new int[]{1,1,1,1, 2,2,2,2, 3,3,3,3, 4,4,4,4, 5,5,5,5});
        assertTrue("B failed", ok);
        stream.close();
    }


    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        boolean ok=Arrays.equals(result,new int[]{1,0,0});
        assertTrue("C failed", ok);
        stream.close();

        stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson05/dataC2.txt");
        result = instance.getAccessory2(stream);
        ok = Arrays.equals(result,new int[]{2,2,2,2,3,1});
        assertTrue("C failed", ok);
        stream.close();
    }
}