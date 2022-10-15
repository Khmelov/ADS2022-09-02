package by.it.group151002.saprin.lesson02;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Lesson2Test {
    /*
    для прохождения тестов создайте JUnit-конфигурацию на свой пакет:
    Поля:
    Name:               Test a_khmelev (тут ваша фамилия)
    Test kind:          All in package
    Package:            by.it.a_khmelev (тут ваша фамилия)
    Search for test:    In whole project
    */


    @Test
    public void A_VideoRegistrator() {
        A_VideoRegistrator instance=new A_VideoRegistrator();
        double[] eventsFirst=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        double[] eventsSecond=new double[]{0.0, 0.6, 2.0, 2.5, 2.7, 3.0, 3.4};

        List<Double> starts=instance.calcStartTimes(eventsFirst,1); //рассчитаем моменты старта, с длинной сеанса 1
        boolean ok=starts.toString().equals("[1.0, 2.2, 3.7, 5.5, 8.1]");
        assertTrue("A_VideoRegistrator failed 1", ok);
        assertEquals("A_VideoRegistrator failed 2", "[0.0, 0.6, 2.0, 2.7, 3.4]",
                instance.calcStartTimes(eventsSecond, 0.5).toString());

    }

    @Test
    public void B_Sheduler() {
        B_Sheduler instance = new B_Sheduler();
        B_Sheduler.Event[] events = {new B_Sheduler.Event(0, 3), new B_Sheduler.Event(0, 1), new B_Sheduler.Event(1, 2), new B_Sheduler.Event(3, 5),
                new B_Sheduler.Event(1, 3), new B_Sheduler.Event(1, 3), new B_Sheduler.Event(1, 3), new B_Sheduler.Event(3, 6),
                new B_Sheduler.Event(2, 7), new B_Sheduler.Event(2, 3), new B_Sheduler.Event(2, 7), new B_Sheduler.Event(7, 9),
                new B_Sheduler.Event(3, 5), new B_Sheduler.Event(2, 4), new B_Sheduler.Event(2, 3), new B_Sheduler.Event(3, 7),
                new B_Sheduler.Event(4, 5), new B_Sheduler.Event(6, 7), new B_Sheduler.Event(6, 9), new B_Sheduler.Event(7, 9),
                new B_Sheduler.Event(8, 9), new B_Sheduler.Event(4, 6), new B_Sheduler.Event(8, 10), new B_Sheduler.Event(7, 10)
        };

        List<B_Sheduler.Event> starts1 = instance.calcStartTimes(events, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        String expected1 = "[(0:1), (1:2), (2:3), (3:5), (6:7), (7:9)]";
        assertEquals(expected1, starts1.toString());
        List<B_Sheduler.Event> starts2 = instance.calcStartTimes(events,2, 8);
        String expected2 = "[(2:3), (3:5), (6:7)]";
        assertEquals(expected2, starts2.toString());
    }
    @Test
    public void C_GreedyKnapsack() throws Exception {
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        boolean ok=costFinal==200;
        assertTrue("B_Sheduler failed", ok);
    }

}
