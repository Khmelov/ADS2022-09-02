package by.it.group151002.bybikov.lesson05;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Lesson5Test {
    @Test
    public void A() throws Exception {
        A_QSort instance = new A_QSort();
        instance.isCorrect = true;
        for (int i = 0; i < 100 && instance.isCorrect; i++) {
            String root = System.getProperty("user.dir") + "/src/";
            InputStream input = new FileInputStream(root + "by/it/group151002/bybikov/lesson05/dataA_Test.txt");
            String outputPath = root + "by/it/group151002/bybikov/lesson05/dataA_Test.txt";
            FileMethods fileMethods = new FileMethods();
            fileMethods.createInputFile(outputPath);
            int[] result = instance.getAccessory(input);
        }
        assertTrue("A failed", instance.isCorrect);
    }


    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        boolean ok=Arrays.equals(result,new int[]{2,2,3,9,9});
        assertTrue("B failed", ok);
        stream = new FileInputStream(root + "by/it/group151002/bybikov/lesson05/dataB_Test_1.txt");
        result=instance.countSort(stream);
        ok=Arrays.equals(result,new int[]{2, 2, 2, 2, 3, 3, 3, 4, 5, 5, 6, 7, 7, 9, 9});
        assertTrue("B failed", ok);
    }


    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        boolean ok=Arrays.equals(result,new int[]{1,0,0});
        assertTrue("C failed", ok);
    }

}
