package by.it.group151001.pastukhou.lesson02;

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
        List<Double> starts=instance.calcStartTimes(events,1);
        boolean ok=starts.toString().equals("[1.0, 2.2, 3.7, 5.5, 8.1]");
        assertTrue("A_VideoRegistrator failed 1", ok);
        events = new double[] {};
        starts = instance.calcStartTimes(events,3);
        ok = starts.toString().equals("[]");
        assertTrue("A_VideoRegistrator failed 2", ok);
        events = new double[] {1, 2, 3, 4};
        starts = instance.calcStartTimes(events,0);
        ok = starts.toString().equals("[1.0, 2.0, 3.0, 4.0]");
        assertTrue("A_VideoRegistrator failed 3", ok);
        events = new double[] {4.6, 4.6, 3.6, 2.6, 1.6, 0.6};
        starts = instance.calcStartTimes(events,5);
        ok = starts.toString().equals("[0.6]");
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
        boolean ok = starts.toString().equals("[(0:1), (1:2), (2:3), (3:5), (6:7), (7:9)]");
        assertTrue("B_Sheduler failed 1", ok);
        events = new B_Sheduler.Event[] {new B_Sheduler.Event(8, 10),  new B_Sheduler.Event(9, 11), new B_Sheduler.Event(10, 12), new B_Sheduler.Event(11, 13),
                new B_Sheduler.Event(12, 14)
        };
        starts = instance.calcStartTimes(events, 8, 14);
        ok = starts.toString().equals("[(8:10), (10:12), (12:14)]");
        assertTrue("B_Sheduler failed 2", ok);
        events = new B_Sheduler.Event[] {};
        starts = instance.calcStartTimes(events, 9, 15);
        ok = starts.toString().equals("[]");
        assertTrue("B_Sheduler failed 3", ok);
    }

    @Test
    public void C_GreedyKnapsack() throws Exception {
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/group151001/pastukhou/lesson02/greedyKnapsack1.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        boolean ok=costFinal==200;
        assertTrue("C_GreedyKnapsack failed 1", ok);
        f = new File(root+"by/it/group151001/pastukhou/lesson02/greedyKnapsack2.txt");
        costFinal=new C_GreedyKnapsack().calc(f);
        ok=costFinal==206.25;
        assertTrue("C_GreedyKnapsack failed 2", ok);
        f = new File(root+"by/it/group151001/pastukhou/lesson02/greedyKnapsack3.txt");
        costFinal=new C_GreedyKnapsack().calc(f);
        ok=costFinal==220;
        assertTrue("C_GreedyKnapsack failed 3", ok);
        f = new File(root+"by/it/group151001/pastukhou/lesson02/greedyKnapsack4.txt");
        costFinal=new C_GreedyKnapsack().calc(f);
        ok=costFinal==-1;
        assertTrue("C_GreedyKnapsack failed 4", ok);
    }

}
