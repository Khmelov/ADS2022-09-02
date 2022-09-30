package by.it.group151003.mytnik.lesson03;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class Lesson3Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group151003/mytnik/lesson03/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        String result = instance.encode(f);
        boolean ok = result.equals("101010101010101010101010101010101010101010101010101010101010101010110110110110110110110110110110110110110110110110110110111011101110111011101110111011101110111011101110111011101111011110111101111011110111101111101111101111101111101111110111111011111101111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000");
        assertTrue("A failed", ok);
    }

    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group151003/mytnik/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        boolean ok = result.equals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbccccccccccccccddddddeeeexxxmms1111111111111111111111111111111111111111111111111111111111111111111111");
        assertTrue("B failed", ok);
    }

    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/mytnik/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        Long res = instance.findMaxValue(stream);
        boolean ok = (res == 111111111);
        assertTrue("C failed", ok);
    }
}