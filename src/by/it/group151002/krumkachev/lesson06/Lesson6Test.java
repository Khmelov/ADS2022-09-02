package by.it.group151002.krumkachev.lesson06;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Lesson6Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        A_LIS instance = new A_LIS();

        FileWriter fileWriter = new FileWriter(root + "by/it/group151002/krumkachev/lesson06/dataA.txt");
        fileWriter.write("1\n2000000000");
        fileWriter.close();
        int minCase = instance.getSeqSize(new FileInputStream(root + "by/it/group151002/krumkachev/lesson06/dataA.txt"));

        fileWriter = new FileWriter(root + "by/it/group151002/krumkachev/lesson06/dataA.txt");
        fileWriter.write("1000\n");
        for (int i = 1000; i >= 1; i--)
            fileWriter.write(i + " ");
        fileWriter.close();
        int maxCase1 = instance.getSeqSize(new FileInputStream(root + "by/it/group151002/krumkachev/lesson06/dataA.txt"));

        fileWriter = new FileWriter(root + "by/it/group151002/krumkachev/lesson06/dataA.txt");
        fileWriter.write("1000\n");
        for (int i = 1; i <= 1000; i++)
            fileWriter.write(i + " ");
        fileWriter.close();
        int maxCase2 = instance.getSeqSize(new FileInputStream(root + "by/it/group151002/krumkachev/lesson06/dataA.txt"));

        fileWriter = new FileWriter(root + "by/it/group151002/krumkachev/lesson06/dataA.txt");
        fileWriter.write("15\n9 8 15 74 19 3 14 42 765 20 2 88 94 28 7");
        fileWriter.close();
        int randomCase = instance.getSeqSize(new FileInputStream(root + "by/it/group151002/krumkachev/lesson06/dataA.txt"));

        assertEquals("A failed min case", 1, minCase);
        assertEquals("A failed max case 1", 1, maxCase1);
        assertEquals("A failed max case 2", 1000, maxCase2);
        assertEquals("A failed random case", 6, randomCase);
    }


    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        Random random = new Random();

        FileWriter fileWriter = new FileWriter(root + "by/it/group151002/krumkachev/lesson06/dataB.txt");
        fileWriter.write("1\n2000000000");
        fileWriter.close();
        int minCase = instance.getDivSeqSize(new FileInputStream(root + "by/it/group151002/krumkachev/lesson06/dataB.txt"));

        fileWriter = new FileWriter(root + "by/it/group151002/krumkachev/lesson06/dataB.txt");
        fileWriter.write("1000\n");
        int randNum = random.nextInt(2000000000) + 1;
        for (int i = 0; i < 1000; i++)
            fileWriter.write(randNum + " ");
        fileWriter.close();
        int maxCase1 = instance.getDivSeqSize(new FileInputStream(root + "by/it/group151002/krumkachev/lesson06/dataB.txt"));

        fileWriter = new FileWriter(root + "by/it/group151002/krumkachev/lesson06/dataB.txt");
        fileWriter.write("1000\n");
        randNum = random.nextInt(1_999_998_000) + 1001;
        for (int i = 0; i < 1000; i++)
            fileWriter.write(randNum + i + " ");
        fileWriter.close();
        int maxCase2 = instance.getDivSeqSize(new FileInputStream(root + "by/it/group151002/krumkachev/lesson06/dataB.txt"));

        fileWriter = new FileWriter(root + "by/it/group151002/krumkachev/lesson06/dataB.txt");
        fileWriter.write("20\n8 19 2 8 1 16 48 64 16 38 76 128 256 90 512 152 304 608 1216 2432");
        fileWriter.close();
        int randomCase = instance.getDivSeqSize(new FileInputStream(root + "by/it/group151002/krumkachev/lesson06/dataB.txt"));

        assertEquals("B failed min case", 1, minCase);
        assertEquals("B failed max case 1", 1000, maxCase1);
        assertEquals("B failed max case 2", 1, maxCase2);
        assertEquals("B failed random case", 8, randomCase);
    }

    @Test(timeout = 1000)
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        Random random = new Random();

        FileWriter fileWriter = new FileWriter(root + "by/it/group151002/krumkachev/lesson06/dataC.txt");
        fileWriter.write("1\n2000000000");
        fileWriter.close();
        String minCase = instance.getNotUpSeq(new FileInputStream(root + "by/it/group151002/krumkachev/lesson06/dataC.txt"));

        fileWriter = new FileWriter(root + "by/it/group151002/krumkachev/lesson06/dataC.txt");
        fileWriter.write("100000\n");
        int randNum = random.nextInt(1_999_900_000) + 1;
        for (int i = 0; i < 100000; i++)
            fileWriter.write(randNum + i + " ");
        fileWriter.close();
        String maxCase1 = instance.getNotUpSeq(new FileInputStream(root + "by/it/group151002/krumkachev/lesson06/dataC.txt"));

        fileWriter = new FileWriter(root + "by/it/group151002/krumkachev/lesson06/dataC.txt");
        fileWriter.write("100000\n");
        randNum = random.nextInt(2000000000) + 1;
        StringBuilder maxCase2Expected = new StringBuilder("100000\n");
        for (int i = 0; i < 100000; i++) {
            fileWriter.write(randNum - i + " ");
            maxCase2Expected.append(i + 1).append(" ");
        }
        fileWriter.close();
        maxCase2Expected.deleteCharAt(maxCase2Expected.length() - 1);
        String maxCase2 = instance.getNotUpSeq(new FileInputStream(root + "by/it/group151002/krumkachev/lesson06/dataC.txt"));

        fileWriter = new FileWriter(root + "by/it/group151002/krumkachev/lesson06/dataC.txt");
        fileWriter.write("16\n0 8 4 12 2 10 6 14 1 5 5 13 3 11 7 15");
        fileWriter.close();
        String randomCase = instance.getNotUpSeq(new FileInputStream(root + "by/it/group151002/krumkachev/lesson06/dataC.txt"));

        assertEquals("C failed min case", "1\n1", minCase);
        assertTrue("C failed max case 1", maxCase1.equals("1\n1") || maxCase1.equals("1\n100000"));
        assertEquals("C failed max case 2", maxCase2Expected.toString(), maxCase2);
        assertEquals("C failed random case", "6\n4 6 7 10 11 13", randomCase);
    }

}
