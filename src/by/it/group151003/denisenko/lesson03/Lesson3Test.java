package by.it.group151003.denisenko.lesson03;


import static org.junit.Assert.assertTrue;

import java.io.*;

import org.junit.Test;

public class Lesson3Test {

    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group151003/denisenko/lesson03/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        String result = instance.encode(f);
        boolean ok = "01001100100111".equals(result);
        assertTrue("A failed", ok);
    }

    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group151003/denisenko/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        boolean ok = "abacabad".equals(result);
        assertTrue("B failed", ok);
    }

    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/denisenko/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        Long res = instance.findMaxValue(stream);
        boolean ok = res == 500;
        assertTrue("C failed", ok);
    }
}