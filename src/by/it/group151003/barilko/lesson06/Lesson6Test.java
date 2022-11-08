package by.it.group151003.barilko.lesson06;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class Lesson6Test {
    String root = "C:/Users/ilyav/source/Java/AISD/ADS2022-09-02/src/";

    @Test
    public void A() throws Exception {

        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result=instance.getSeqSize(stream);
        boolean ok=(result==3);
        assertTrue("A failed", ok);
    }


    @Test
    public void B() throws Exception {
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataB.txt");
        B_LongDivComSubSeq instance=new B_LongDivComSubSeq();
        int result=instance.getDivSeqSize(stream);
        boolean ok=(result==3);
        assertTrue("B failed", ok);
    }

    @Test(timeout = 1000)
    public void C() throws Exception {

        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result=instance.getNotUpSeqSize(stream);
        boolean ok=(result==4);
        assertTrue("C failed", ok);
    }

}
