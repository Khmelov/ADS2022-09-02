package by.it.group151003.shafarenko.lesson06;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class Lesson6Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/by/it/a_khmelev/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result=instance.getSeqSize(stream);
        boolean ok=(result==3);
        assertTrue("A failed", ok);
    }


    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/by/it/a_khmelev/lesson06/dataB.txt");
        B_LongDivComSubSeq instance=new B_LongDivComSubSeq();
        int result=instance.getDivSeqSize(stream);
        boolean ok=(result==3);
        assertTrue("B failed", ok);
    }

    @Test(timeout = 1000)
    public void C() throws Exception {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result=instance.getNotUpSeqSize(stream);
        boolean ok=(result==4);
        assertTrue("C failed", ok);

        OutputStream stream1 = new FileOutputStream(root + "/by/it/a_khmelev/lesson06/dataC2.txt", true);
        stream1.write(10000);
        for (int i = 1; i <= 10000; i++) {
            stream1.write((int)(Math.random() * 1000));
        }
        stream = new FileInputStream(root + "/by/it/a_khmelev/lesson06/dataC2.txt");
        result=instance.getNotUpSeqSize(stream);
        ok = true;
        assertTrue("C failed", ok);
        System.out.println();
    }

}
