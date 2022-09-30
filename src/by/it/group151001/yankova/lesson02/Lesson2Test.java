package by.it.group151001.yankova.lesson02;

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
        assertTrue("A_VideoRegistrator №1 failed", ok);

        events=new double[]{0, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        starts=instance.calcStartTimes(events,1);
        ok=starts.toString().equals("[0.0, 1.1, 2.2, 3.7, 5.5, 8.1]");
        assertTrue("A_VideoRegistrator №2 failed", ok);

        events=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        starts=instance.calcStartTimes(events,-1); //рассчитаем моменты старта, с длинной сеанса 1
        ok=starts.toString().equals("[]");
        assertTrue("A_VideoRegistrator №3 failed", ok);
    }

    @Test
    public void B_Sheduler() {
        B_Sheduler instance;
        instance = new B_Sheduler();
        B_Sheduler.Event[] events;
        events = new B_Sheduler.Event[]{new B_Sheduler.Event(0, 3), new B_Sheduler.Event(0, 1), new B_Sheduler.Event(1, 2), new B_Sheduler.Event(3, 5),
                new B_Sheduler.Event(1, 3), new B_Sheduler.Event(1, 3), new B_Sheduler.Event(1, 3), new B_Sheduler.Event(3, 6),
                new B_Sheduler.Event(2, 7), new B_Sheduler.Event(2, 3), new B_Sheduler.Event(2, 7), new B_Sheduler.Event(7, 9),
                new B_Sheduler.Event(3, 5), new B_Sheduler.Event(2, 4), new B_Sheduler.Event(2, 3), new B_Sheduler.Event(3, 7),
                new B_Sheduler.Event(4, 5), new B_Sheduler.Event(6, 7), new B_Sheduler.Event(6, 9), new B_Sheduler.Event(7, 9),
                new B_Sheduler.Event(8, 9), new B_Sheduler.Event(4, 6), new B_Sheduler.Event(8, 10), new B_Sheduler.Event(7, 10)
        };

        List<B_Sheduler.Event> starts = instance.calcStartTimes(events, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        boolean ok=starts.toString().equals("[(0:1), (1:2), (2:3), (3:5), (6:7), (7:9)]");
        assertTrue("B_Sheduler №1 failed", ok);

        events = new B_Sheduler.Event[]{new B_Sheduler.Event(5, 6), new B_Sheduler.Event(8, 13), new B_Sheduler.Event(10, 15),
                new B_Sheduler.Event(21, 25), new B_Sheduler.Event(13, 15), new B_Sheduler.Event(16, 19),
                new B_Sheduler.Event(11, 17), new B_Sheduler.Event(16, 18), new B_Sheduler.Event(18, 20)
        };
        starts = instance.calcStartTimes(events, 8, 20);  //рассчитаем оптимальное заполнение аудитории
        ok=starts.toString().equals("[(8:13), (13:15), (16:18), (18:20)]");
        assertTrue("B_Sheduler №2 failed", ok);

        events = new B_Sheduler.Event[]{};
        starts = instance.calcStartTimes(events, 8, 20);  //рассчитаем оптимальное заполнение аудитории
        ok=starts.toString().equals("[]");
        assertTrue("B_Sheduler №3 failed", ok);

        events = new B_Sheduler.Event[]{new B_Sheduler.Event(8, 10), new B_Sheduler.Event(9, 11), new B_Sheduler.Event(12, 14)};
        starts = instance.calcStartTimes(events, 40, 20);  //рассчитаем оптимальное заполнение аудитории
        ok=starts.toString().equals("[]");
        assertTrue("B_Sheduler №4 failed", ok);

    }
    @Test
    public void C_GreedyKnapsack() throws Exception {
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/group151001/yankova/lesson02/test№1Knapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        boolean ok=costFinal==42;
        assertTrue("C_GreedyKnapsack() №1 failed", ok);

        f=new File(root+"by/it/group151001/yankova/lesson02/test№2Knapsack.txt");
        costFinal=new C_GreedyKnapsack().calc(f);
        ok=costFinal== 3500;
        assertTrue("C_GreedyKnapsack() №2 failed", ok);

        f=new File(root+"by/it/group151001/yankova/lesson02/greedyKnapsack.txt");
        costFinal=new C_GreedyKnapsack().calc(f);
        ok=costFinal==200;
        assertTrue("C_GreedyKnapsack() №3 failed", ok);
    }

}
