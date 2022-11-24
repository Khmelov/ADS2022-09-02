package by.it.group151004.tishalovich.lesson06;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class Lesson6Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result=instance.getSeqSize(stream);
        boolean ok=(result==3);
        assertTrue("A failed", ok);
        stream.close();


        InputStream stream2 = new FileInputStream(root + "by/it/group151004/tishalovich/lesson06/dataA2.txt");
        result=instance.getSeqSize(stream2);
        ok=(result==5);
        assertTrue("A failed", ok);
        stream2.close();
    }


    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson06/dataB2.txt");
        B_LongDivComSubSeq instance=new B_LongDivComSubSeq();
        int result=instance.getDivSeqSize(stream);
        boolean ok=(result==3);
        assertTrue("B failed", ok);
        stream.close();

        stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson06/dataB2.txt");
        result = instance.getDivSeqSize(stream);
        ok = (result==3);
        assertTrue("B failed", ok);
        stream.close();
    }

    @Test(timeout = 1000)
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result=instance.getNotUpSeqSize(stream);
        boolean ok=(result==4);
        assertTrue("C failed", ok);
        stream.close();

        stream = new FileInputStream(root + "by/it/group151004/tishalovich/lesson06/dataC2.txt");
        result=instance.getNotUpSeqSize(stream);
        ok=(result==6);
        assertTrue("C failed", ok);
        stream.close();
    }

}
