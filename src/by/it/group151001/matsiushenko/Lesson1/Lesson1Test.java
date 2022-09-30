package by.it.group151001.matsiushenko.Lesson1;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("all")
public class Lesson1Test {
    /*
    для прохождения тестов создайте JUnit-конфигурацию на свой пакет:
    Поля:
    Name:               Test a_khmelev (тут ваша фамилия)
    Test kind:          All in package
    Package:            by.it.группа.a_khmelev (тут ваша фамилия)
    Search for test:    In whole project
    */

    @Test(timeout = 2000)
    public void slowA() throws Exception {
        FiboA fibo=new FiboA();
        BigInteger res=fibo.slowA(23);
        boolean ok=res.toString().equals("28657");
        assertTrue("slowA failed", ok);
    }

    @Test(timeout = 2000)
    public void fastB() throws Exception {
        FiboB fibo=new FiboB();
        BigInteger res=fibo.fastB(666);
        boolean ok=res.toString().equals("6859356963880484413875401302176431788073214234535725264860437720157972142108894511264898366145528622543082646626140527097739556699078708088");
        assertTrue("fastB failed", ok);
    }


    @Test(timeout = 2000)
    public void fasterC() throws Exception {
        FiboC fibo=new FiboC();
        assertTrue("fasterC failed 1", fibo.fasterC(16,4)==3L);
        assertTrue("fasterC failed 2", fibo.fasterC(25,24)==1L);
        assertTrue("fasterC failed 3", fibo.fasterC(696969,13)==0L);
    }
}
