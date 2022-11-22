package by.it.group151002.zavaliuk.lesson05;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Lesson5Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        boolean ok = Arrays.equals(result, new int[]{1, 0, 0});
        assertTrue("A failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson05/dataA1.txt");
        instance = new A_QSort();
        result = instance.getAccessory(stream);
        ok = Arrays.equals(result, new int[]{2, 1, 0 });
        assertTrue("A failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson05/dataA2.txt");
        instance = new A_QSort();
        result = instance.getAccessory(stream);
        ok = Arrays.equals(result, new int[]{2, 2, 2});
        assertTrue("A failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson05/dataA3.txt");
        instance = new A_QSort();
        result = instance.getAccessory(stream);
        ok = Arrays.equals(result, new int[]{0, 0, 0 });
        assertTrue("A failed", ok);
    }


    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        boolean ok = Arrays.equals(result, new int[]{2, 2, 3, 9, 9});
        assertTrue("B failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson05/dataB1.txt");
        instance = new B_CountSort();
        result = instance.countSort(stream);
        ok = Arrays.equals(result, new int[]{9, 9, 9, 9, 9 });
        assertTrue("B failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson05/dataB2.txt");
        instance = new B_CountSort();
        result = instance.countSort(stream);
        ok = Arrays.equals(result, new int[]{1});
        assertTrue("B failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson05/dataB3.txt");
        instance = new B_CountSort();
        result = instance.countSort(stream);
        ok = Arrays.equals(result, new int[]{1, 2, 2, 3, 9, 9, 9 });
        assertTrue("B failed", ok);

    }


    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        boolean ok = Arrays.equals(result, new int[]{1, 0, 0});
        assertTrue("C failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson05/dataC1.txt");
        instance = new C_QSortOptimized();
        result = instance.getAccessory2(stream);
        ok = Arrays.equals(result, new int[]{2, 2, 0});
        assertTrue("C failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson05/dataC2.txt");
        instance = new C_QSortOptimized();
        result = instance.getAccessory2(stream);
        ok = Arrays.equals(result, new int[]{2, 2, 2});
        assertTrue("C failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson05/dataC3.txt");
        instance = new C_QSortOptimized();
        result = instance.getAccessory2(stream);
        ok = Arrays.equals(result, new int[]{0, 0, 0});
        assertTrue("C failed", ok);
    }

}
