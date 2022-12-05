package by.it.group151002.rusakovich.lesson08;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class Lesson8Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        assertEquals("A failed", res, 10);
    }

    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        assertEquals("B failed", res, 9);
    }

    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson08/dataC.txt");
        C_Stairs instance = new C_Stairs();
        Scanner scanIn = new Scanner(stream);
        int n = scanIn.nextInt();
        int res=instance.getMaxSum(n, instance.get_arr(scanIn, n));
        assertEquals("C0 failed", res, 1);
        n = scanIn.nextInt();
        res=instance.getMaxSum(n, instance.get_arr(scanIn, n));
        assertEquals("C1 failed", res, -6);


    }

}
