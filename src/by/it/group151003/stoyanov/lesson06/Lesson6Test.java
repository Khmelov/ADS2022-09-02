package by.it.group151003.stoyanov.lesson06;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class Lesson6Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result=instance.getSeqSize(stream);
        boolean ok=(result==3);
        assertTrue("A failed", ok);
    }

    @Test
    public void A2() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/stoyanov/lesson06/dataA3.txt");
        A_LIS instance = new A_LIS();
        int result=instance.getSeqSize(stream);
        boolean ok=(result==6);
        assertTrue("A failed", ok);
    }

    @Test
    public void A_ShouldReturnZeroIfGivenEmptyArray() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/stoyanov/lesson06/dataA2.txt");
        A_LIS instance = new A_LIS();
        int result=instance.getSeqSize(stream);
        boolean ok=(result==0);
        assertTrue("A failed", ok);
    }



    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/stoyanov/lesson06/dataB.txt");
        B_LongDivComSubSeq instance=new B_LongDivComSubSeq();
        int result=instance.getDivSeqSize(stream);
        boolean ok=(result==6);
        assertTrue("B failed", ok);
    }

    @Test(timeout = 1000)
    public void C_Complex() throws Exception {
        int length = 50000;
        Random random = new Random();
        int[] m = new int[length];
        for (int i = 0; i < length; i++)
            m[i] = random.nextInt(1_000_000_000);
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        instance.getNotUpSeqSize(m);
        assertTrue("C_timeout failed", true);
    }

    @Test(timeout = 1000)
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/stoyanov/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();

        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] m = new int[n];
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        int result=instance.getNotUpSeqSize(m);
        boolean ok=(result==4);
        assertTrue("C failed", ok);
    }

}
