package by.it.group151002.zavaliuk.lesson03;

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
        File f = new File(root + "by/it/a_khmelev/lesson03/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        String result = instance.encode(f);
        boolean ok = result.equals("01001100100111");
        assertTrue("A1 failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        f = new File(root + "by/it/group151002/zavaliuk/lesson03/dataHuffman-2.txt");
        instance = new A_Huffman();
        result = instance.encode(f);
        ok = result.equals("0011100000011111110001110011111110011011111011011010101101101001001010100010010001");
        assertTrue("A2 failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        f = new File(root + "by/it/group151002/zavaliuk/lesson03/dataHuffman-1.txt");
        instance = new A_Huffman();
        result = instance.encode(f);
        ok = result.equals("10001001100111010011");
        assertTrue("A3 failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        f = new File(root + "by/it/group151002/zavaliuk/lesson03/dataHuffman-3.txt");
        instance = new A_Huffman();
        result = instance.encode(f);
        ok = result.equals("0100001111010000110111101111001111011101011110011000011010101110011111000000100101");
        assertTrue("A4 failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        f = new File(root + "by/it/group151002/zavaliuk/lesson03/dataHuffman-4.txt");
        instance = new A_Huffman();
        result = instance.encode(f);
        ok = result.equals("00000");
        assertTrue("A5 failed", ok);
    }

    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        boolean ok = result.equals("abacabad");
        assertTrue("B failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        f = new File(root + "by/it/group151002/zavaliuk/lesson03/encodeHuffman-1.txt");
        instance = new B_Huffman();
        result = instance.decode(f);
        ok = result.equals("a");
        assertTrue("B1 failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        f = new File(root + "by/it/group151002/zavaliuk/lesson03/encodeHuffman-2.txt");
        instance = new B_Huffman();
        result = instance.decode(f);
        ok = result.equals("abacab");
        assertTrue("B2 failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        f = new File(root + "by/it/group151002/zavaliuk/lesson03/encodeHuffman-3.txt");
        instance = new B_Huffman();
        result = instance.decode(f);
        ok = result.equals("abab");
        assertTrue("B3 failed", ok);
    }

    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        Long res = instance.findMaxValue(stream);
        boolean ok = (res == 500);
        assertTrue("C failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson03/heapData.txt");
        instance = new C_HeapMax();
        res = instance.findMaxValue(stream);
        ok = (res == 500);
        assertTrue("C1 failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson03/heapData-2.txt");
        instance = new C_HeapMax();
        res = instance.findMaxValue(stream);
        ok = (res == 1000);
        assertTrue("C2 failed", ok);

        root = System.getProperty("user.dir") + "/src/";
        stream = new FileInputStream(root + "by/it/group151002/zavaliuk/lesson03/heapData-3.txt");
        instance = new C_HeapMax();
        res = instance.findMaxValue(stream);
        ok = (res == 5);
        assertTrue("C3 failed", ok);
    }

}
