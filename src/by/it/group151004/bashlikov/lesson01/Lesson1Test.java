package by.it.group151004.bashlikov.lesson01;

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
        BigInteger res=fibo.slowA(33);
        boolean ok=res.toString().equals("3524578");
        assertTrue("slowA failed", ok);
    }

    @Test(timeout = 2000)
    public void slowAOne() throws Exception {
        FiboA fibo=new FiboA();
        BigInteger res=fibo.slowA(1);
        boolean ok=res.toString().equals("1");
        assertTrue("slowA failed", ok);
    }

    @Test(timeout = 2000)
    public void slowAZero() throws Exception {
        FiboA fibo=new FiboA();
        BigInteger res=fibo.slowA(0);
        boolean ok=res.toString().equals("0");
        assertTrue("slowA failed", ok);
    }

    @Test(timeout = 2000)
    public void fastBZero() throws Exception {
        FiboB fibo=new FiboB();
        BigInteger res=fibo.fastB(0);
        boolean ok=res.toString().equals("0");
        assertTrue("fastB failed", ok);
    }

    @Test(timeout = 2000)
    public void fastB1() throws Exception {
        FiboB fibo=new FiboB();
        BigInteger res=fibo.fastB(100);
        boolean ok=res.toString().equals("218922995834555169026");
        assertTrue("fastB failed", ok);
    }

    @Test(timeout = 2000)
    public void fastBHun() throws Exception {
        FiboB fibo=new FiboB();
        BigInteger res=fibo.fastB(100);
        boolean ok=res.toString().equals("218922995834555169026");
        assertTrue("fastB failed", ok);
    }

    @Test(timeout = 2000)
    public void fastBOne() throws Exception {
        FiboB fibo=new FiboB();
        BigInteger res=fibo.fastB(1);
        boolean ok=res.toString().equals("1");
        assertTrue("fastB failed", ok);
    }

    @Test(timeout = 2000)
    public void fastB() throws Exception {
        FiboB fibo=new FiboB();
        BigInteger res=fibo.fastB(5555);
        boolean ok=res.toString().equals("233275401838813569895406163156191603333076829004002169115189756657257503015851328785321583286098471222075535644398652951162599373234776772035519654071545075978061852416903218124995811495715291650429746955110652135326873203768276194020329205859053942815120160641048064197933383614852434427614542692569601858270377604875584921911421472165944251547331238089851429509852577108906173131371020801525940961182006585525905503754444724152769143597891818104506051220767991443052122353623150967013108787261728579060305922757303453540868467033989626682688275496982888459651808782735899648800735677033909076629685146663024300847077782914755914527853178815509366664475403958234684866686354551588135764639461974847335247286937909610642906476885730250852895174637764165470969339317854456451616807250478830997852001459980893468246629826498623872598143022838899792774638806390686721357315014378080254813930384556334734128529540834485286137171506446760983054985124305705318231063422908203590605036346797970076967005476125455180120253989242245263200493087336819748848703274235358841105090923167233789108154326231765582944937030516639251406790478912119801147832742678946884306864647");
        assertTrue("fastB failed", ok);
    }

    @Test(timeout = 2000)
    public void slowA1() throws Exception {
        FiboA fibo=new FiboA();
        BigInteger res=fibo.slowA(33);
        boolean ok=res.toString().equals("3524578");
        assertTrue("slowA failed", ok);
    }

    @Test(timeout = 2000)
    public void fasterC() throws Exception {
        FiboC fibo=new FiboC();
        assertTrue("fasterC failed 1", fibo.fasterC(10,2)==1L);
        assertTrue("fasterC failed 2", fibo.fasterC(1,2)==1L);
        assertTrue("fasterC failed 3", fibo.fasterC(999999999,321)==34L);
    }
}
