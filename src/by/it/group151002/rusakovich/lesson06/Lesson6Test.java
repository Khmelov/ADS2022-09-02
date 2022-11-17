package by.it.group151002.rusakovich.lesson06;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class Lesson6Test {
    @Test
    public void A1() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson06/dataA1.txt");
        A_LIS instance = new A_LIS();
        int result=instance.getSeqSize(stream);
        boolean ok=(result==5);
        assertTrue("A failed", ok);
    }

    @Test
    public void A2() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson06/dataA2.txt");
        A_LIS instance = new A_LIS();
        int result=instance.getSeqSize(stream);
        boolean ok=(result==4);
        assertTrue("A failed", ok);
    }


    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson06/dataB.txt");
        B_LongDivComSubSeq instance=new B_LongDivComSubSeq();
        int result=instance.getDivSeqSize(stream);
        boolean ok=(result==3);
        assertTrue("B failed", ok);
    }

    @Test(timeout = 1000)
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        instance.getNotUpSeqSize(stream);
        int result = C_LongNotUpSubSeq.res_l;
        boolean ok=(result==4);
        assertTrue("C failed", ok);
    }

    @Test
    public void C1() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson06/dataC1.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        instance.getNotUpSeqSize(stream);
        int result = C_LongNotUpSubSeq.res_l;
        boolean ok=(result==7);
        assertTrue("C failed", ok);
    }
}
