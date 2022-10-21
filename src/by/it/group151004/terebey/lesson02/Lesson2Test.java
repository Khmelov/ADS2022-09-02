package by.it.group151004.terebey.lesson02;

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
        assertTrue("A_VideoRegistrator failed 1", ok);
        events = new double[]{5, 2.2, 3, 7, 1, 9.1, 8, 9, 1.1, 2.8, 7.8, 3.1};
        starts = instance.calcStartTimes(events,2);
        ok = starts.toString().equals("[1.0, 3.1, 7.0, 9.1]");
        assertTrue("A_VideoRegistrator failed 2", ok);
        events = new double[] {7.5, 6.5, 5.5, 4.6, 3.6, 1.6};
        starts = instance.calcStartTimes(events,5);
        ok = starts.toString().equals("[1.6, 7.5]");
        assertTrue("A_VideoRegistrator failed 3", ok);
        events = new double[] {};
        starts = instance.calcStartTimes(events,1);
        ok = starts.toString().equals("[]");
        assertTrue("A_VideoRegistrator failed 4", ok);

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
        assertTrue("B_Sheduler failed 1", ok);

        events = new B_Sheduler.Event[] {};
        starts = instance.calcStartTimes(events, 1, 10);
        ok = starts.toString().equals("[]");
        assertTrue("B_Sheduler failed 2", ok);

        events = new B_Sheduler.Event[] {new B_Sheduler.Event(6, 12),  new B_Sheduler.Event(7, 8), new B_Sheduler.Event(8, 11), new B_Sheduler.Event(11, 13),
                new B_Sheduler.Event(12, 14)
        };

        starts = instance.calcStartTimes(events, 8, 14);
        ok = starts.toString().equals("[(8:11), (11:13)]");
        assertTrue("B_Sheduler failed 3", ok);


    }
    @Test
    public void C_GreedyKnapsack() throws Exception {
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/group151004/terebey/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        boolean ok=costFinal==200;
        assertTrue("B_Sheduler failed 1", ok);

        f=new File(root+"by/it/group151004/terebey/lesson02/greedyKnapsack2.txt");
        costFinal=new C_GreedyKnapsack().calc(f);
        ok=costFinal == 118.5;
        assertTrue("B_Sheduler failed 2", ok);

        f=new File(root+"by/it/group151004/terebey/lesson02/greedyKnapsack3.txt");
        costFinal=new C_GreedyKnapsack().calc(f);
        ok=costFinal == 106.25;
        assertTrue("B_Sheduler failed 3", ok);

        f=new File(root+"by/it/group151004/terebey/lesson02/greedyKnapsack4.txt");
        costFinal=new C_GreedyKnapsack().calc(f);
        ok=costFinal == 100;
        assertTrue("B_Sheduler failed 4", ok);
    }

}
