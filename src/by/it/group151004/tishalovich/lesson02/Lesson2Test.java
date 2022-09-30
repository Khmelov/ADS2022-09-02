package by.it.group151004.tishalovich.lesson02;

import org.junit.Test;

import java.io.File;
import java.util.List;

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
        double[] events=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts=instance.calcStartTimes(events,1); //рассчитаем моменты старта, с длинной сеанса 1
        boolean ok=starts.toString().equals("[1.0, 2.2, 3.7, 5.5, 8.1]");
        assertTrue("slowA failed", ok);

        List<Double> test2 = instance.calcStartTimes(null,1);
        assertTrue("A_VideoRegistrator failed", test2 == null);

        List<Double> test3 = instance.calcStartTimes(events,4);
        ok=test3.toString().equals("[1.0, 5.5]");
        assertTrue("A_VideoRegistrator failed", ok);
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

        List<B_Sheduler.Event> starts = instance.calcStartTimes(events, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        boolean ok=starts.toString().equals("[(0:1), (1:2), (2:3), (3:5), (6:7), (7:9)]");
        assertTrue("B_Sheduler failed", ok);

        List<B_Sheduler.Event> test2 = instance.calcStartTimes(null, 0, 10);
        assertTrue("B_Sheduler failed", test2 == null);

        List<B_Sheduler.Event> test3 = instance.calcStartTimes(events, 0, 3);  //рассчитаем оптимальное заполнение аудитории
        ok=test3.toString().equals("[(0:1), (1:2), (2:3)]");
        assertTrue("B_Sheduler failed", ok);
    }
    @Test
    public void C_GreedyKnapsack() throws Exception {
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        boolean ok=costFinal==200;
        assertTrue("B_Sheduler failed", ok);

        File f2=new File(root + "by/it/group151004/tishalovich/lesson02/greedyKnapsack2.txt");
        costFinal=new C_GreedyKnapsack().calc(f2);
        assertTrue("C_GreedyKnapsack failed", costFinal == 10000);
    }

}
