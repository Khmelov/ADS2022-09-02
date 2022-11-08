package by.it.group151001.shlyk.lesson08;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class Lesson8Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        String fName = root + "by/it/group151001/shlyk/lesson08/dataA.txt";
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(fName);
        assertEquals("A failed", res, 14);
    }

    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        String fName = root  + "by/it/group151001/shlyk/lesson08/dataB.txt";
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(fName);
        assertEquals("B failed", res, 9);
    }

    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        String fName = root + "by/it/group151001/shlyk/lesson08/dataC.txt";
        C_Stairs instance = new C_Stairs();
        int res=instance.getMaxSum(fName);
        assertEquals("C failed", res, 10);
    }

}
