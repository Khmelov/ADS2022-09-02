package by.it.group151004.tishalovich.lesson08;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class Lesson8Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        assertEquals("A failed", res, 14);
        stream.close();

        stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson08/dataA2.txt");
        res=instance.getMaxWeight(stream);
        assertEquals("A failed", res, 991);
        stream.close();
    }

    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        assertEquals("B failed", res, 9);
        stream.close();

        stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson08/dataB2.txt");
        res=instance.getMaxWeight(stream);
        assertEquals("B failed", res, 979);
        stream.close();
    }

    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson08/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res=instance.getMaxSum(stream);
        assertEquals("C failed", res, 3);
        stream.close();

        stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson08/dataC2.txt");
        res=instance.getMaxSum(stream);
        assertEquals("C failed", res, 150);
        stream.close();
    }

}
