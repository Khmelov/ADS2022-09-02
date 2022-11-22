package by.it.group151003.shafarenko.lesson03;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class Lesson3Test {
    /*
    для прохождения тестов создайте JUnit-конфигурацию на свой пакет:
    Поля:
    Name:               Test a_khmelev (тут ваша фамилия)
    Test kind:          All in package
    Package:            by.it.a_khmelev (тут ваша фамилия)
    Search for test:    In whole project
    */


    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";

        File f = new File(root + "by/it/group151003/shafarenko/lesson03/dataHuffman1.txt");
        A_Huffman instance = new A_Huffman();
        String result = instance.encode(f);
        boolean ok=result.equals("01001100100111");
        assertTrue("A1 failed", ok);

        f = new File(root + "by/it/group151003/shafarenko/lesson03/dataHuffman2.txt");
        result = instance.encode(f);
        ok = result.equals("11000110000111000010010111110010110001001011011111000110");
        assertTrue("A2 failed", ok);
    }

    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group151003/shafarenko/lesson03/encodeHuffman1.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        boolean ok=result.equals("abacabad");
        assertTrue("B1 failed", ok);

        f = new File(root + "by/it/group151003/shafarenko/lesson03/encodeHuffman2.txt");
        result = instance.decode(f);
        ok=result.equals("dadacabad");
        assertTrue("B2 failed", ok);
    }
    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        Long res=instance.findMaxValue(stream);
        boolean ok=(res==500);
        assertTrue("C failed", ok);
    }

}
