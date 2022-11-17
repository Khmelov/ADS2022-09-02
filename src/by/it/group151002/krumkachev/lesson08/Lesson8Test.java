package by.it.group151002.krumkachev.lesson08;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class Lesson8Test {
    @Test
    public void A() throws Exception {
        String path = System.getProperty("user.dir") + "/src/by/it/group151002/krumkachev/lesson08/dataA.txt";
        A_Knapsack instance = new A_Knapsack();

        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write("15 3\n2 8 16");
        fileWriter.close();
        assertEquals("A failed1", 14, instance.getMaxWeight(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("10 3\n1 4 8");
        fileWriter.close();
        assertEquals("A failed2", 10, instance.getMaxWeight(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("100000 3\n99999 0 100000");
        fileWriter.close();
        assertEquals("A failed3", 100000, instance.getMaxWeight(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("2 5\n3 4 5 6 7");
        fileWriter.close();
        assertEquals("A failed4", 0, instance.getMaxWeight(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("100000 4\n0 0 0 0");
        fileWriter.close();
        assertEquals("A failed5", 0, instance.getMaxWeight(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("10 4\n9 7 6 5");
        fileWriter.close();
        assertEquals("A failed5", 10, instance.getMaxWeight(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("100000 300\n");
        for (int i = 99999; i >= 0; i -= 333)
            fileWriter.write(i + " ");
        fileWriter.close();
        assertEquals("A failed6", 99999, instance.getMaxWeight(new FileInputStream(path)));
    }


    @Test
    public void B() throws Exception {
        B_Knapsack instance = new B_Knapsack();
        String path = System.getProperty("user.dir") + "/src/by/it/group151002/krumkachev/lesson08/dataB.txt";

        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write("15 3\n2 8 16");
        fileWriter.close();
        assertEquals("B failed1", 10, instance.getMaxWeight(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("10 3\n1 4 8");
        fileWriter.close();
        assertEquals("B failed2", 9, instance.getMaxWeight(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("100000 3\n99999 0 100000");
        fileWriter.close();
        assertEquals("B failed3", 100000, instance.getMaxWeight(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("2 5\n3 4 5 6 7");
        fileWriter.close();
        assertEquals("B failed4", 0, instance.getMaxWeight(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("100000 4\n0 0 0 0");
        fileWriter.close();
        assertEquals("B failed5", 0, instance.getMaxWeight(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("10 4\n9 7 6 5");
        fileWriter.close();
        assertEquals("B failed5", 9, instance.getMaxWeight(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("15 5\n9 7 1 10 3");
        fileWriter.close();
        assertEquals("B failed6", 14, instance.getMaxWeight(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("100000 300\n");
        for (int i = 99999; i >= 0; i -= 333)
            fileWriter.write(i + " ");
        fileWriter.close();
        assertEquals("B failed7", 99999, instance.getMaxWeight(new FileInputStream(path)));
    }

    @Test
    public void C() throws Exception {
        C_Stairs instance = new C_Stairs();
        String path = System.getProperty("user.dir") + "/src/by/it/group151002/krumkachev/lesson08/dataC.txt";
        Random random = new Random();

        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write("2\n1 2");
        fileWriter.close();
        assertEquals("C failed1", 3, instance.getMaxSum(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("2\n2 -1");
        fileWriter.close();
        assertEquals("C failed2", 1, instance.getMaxSum(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("3\n-1 2 1");
        fileWriter.close();
        assertEquals("C failed3", 3, instance.getMaxSum(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("6\n-2000 4000 1000 7000 -10000 10000");
        fileWriter.close();
        assertEquals("C failed4", 22000, instance.getMaxSum(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("9\n0 -15 -14 -10 -16 -19 0 -5 -10");
        fileWriter.close();
        assertEquals("C failed5", -40, instance.getMaxSum(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        int num = random.nextInt(20000) - 10000;
        fileWriter.write("1\n" + num);
        fileWriter.close();
        assertEquals("C failed5", num, instance.getMaxSum(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("7\n-1 2 -3 -5 -3 0 -1");
        fileWriter.close();
        assertEquals("C failed6", -4, instance.getMaxSum(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("100\n");
        num = random.nextInt(10000);
        for (int i = 0; i < 100; i++)
            fileWriter.write(num + " ");
        fileWriter.close();
        assertEquals("C failed7", num * 100, instance.getMaxSum(new FileInputStream(path)));

        fileWriter = new FileWriter(path);
        fileWriter.write("100\n");
        num = random.nextInt(10000) - 10000;
        for (int i = 0; i < 100; i++)
            fileWriter.write(num + " ");
        fileWriter.close();
        assertEquals("C failed7", num * 50, instance.getMaxSum(new FileInputStream(path)));
    }

}
