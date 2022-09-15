package by.it.group151002.zavaliuk.lesson02;

import org.junit.Test;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
        A_VideoRegistrator instance = new A_VideoRegistrator();
        double[] events = new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts = instance.calcStartTimes(events, 1); //рассчитаем моменты старта, с длинной сеанса 1
        boolean ok = starts.toString().equals("[1.0, 2.2, 3.7, 5.5, 8.1]");
        assertTrue("A_VideoRegistrator failed with test {1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7}", ok);

        instance = new A_VideoRegistrator();
        events = new double[]{1, 1.1, 1.6, 1.7, 1.8, 1.9, 2.3, 2.4, 2.7, 2.5, 3.9, 8.1, 9.1, 5.5, 3.7};
        starts = instance.calcStartTimes(events, 1); //рассчитаем моменты старта, с длинной сеанса 1
        ok = starts.toString().equals("[1.0, 2.3, 3.7, 5.5, 8.1]");
        assertTrue("A_VideoRegistrator failed with test {1, 1.1, 1.6, 1.7, 1.8, 1.9, 2.3, 2.4, 2.7, 2.5, 3.9, 8.1, 9.1, 5.5, 3.7}", ok);

        instance = new A_VideoRegistrator();
        events = new double[]{1, 2, 3, 4, 5, 6, 7};
        starts = instance.calcStartTimes(events, 1); //рассчитаем моменты старта, с длинной сеанса 1
        ok = starts.toString().equals("[1.0, 3.0, 5.0, 7.0]");
        assertTrue("A_VideoRegistrator failed with test {1, 2, 3, 4, 5, 6, 7}", ok);

        instance = new A_VideoRegistrator();
        events = new double[]{9, 8, 7, 6, 6, 4, 4, 2};
        starts = instance.calcStartTimes(events, 1); //рассчитаем моменты старта, с длинной сеанса 1
        ok = starts.toString().equals("[2.0, 4.0, 6.0, 8.0]");
        assertTrue("A_VideoRegistrator failed with test {9, 8, 7, 6, 6, 4, 4, 2}", ok);

        instance = new A_VideoRegistrator();
        events = new double[]{0.1, 0.2, 0.3, 1.05, 1.2, 1, 1.1, 2.2, 2.3};
        starts = instance.calcStartTimes(events, 1); //рассчитаем моменты старта, с длинной сеанса 1
        ok = starts.toString().equals("[0.1, 1.2, 2.3]");
        assertTrue("A_VideoRegistrator failed with test {0.1, 0.2, 0.3, 1.05, 1.2, 1, 1.1, 2.2, 2.3}", ok);

        instance = new A_VideoRegistrator();
        events = new double[]{1, 10, 17};
        starts = instance.calcStartTimes(events, 1); //рассчитаем моменты старта, с длинной сеанса 1
        ok = starts.toString().equals("[1.0, 10.0, 17.0]");
        assertTrue("A_VideoRegistrator failed with test {1, 10, 17}", ok);
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
        assertTrue("B_Sheduler failed with test 1", ok);

        instance = new B_Sheduler();
        B_Sheduler.Event[] event = {new B_Sheduler.Event(1, 10), new B_Sheduler.Event(1, 19), new B_Sheduler.Event(1, 2), new B_Sheduler.Event(3, 5),
                new B_Sheduler.Event(1, 3), new B_Sheduler.Event(1, 3), new B_Sheduler.Event(1, 3), new B_Sheduler.Event(3, 6),
                new B_Sheduler.Event(2, 7), new B_Sheduler.Event(2, 3), new B_Sheduler.Event(2, 7), new B_Sheduler.Event(7, 11),
                new B_Sheduler.Event(3, 5), new B_Sheduler.Event(2, 4), new B_Sheduler.Event(2, 3), new B_Sheduler.Event(3, 7),
                new B_Sheduler.Event(4, 5), new B_Sheduler.Event(6, 7), new B_Sheduler.Event(6, 9), new B_Sheduler.Event(7, 11),
                new B_Sheduler.Event(8, 10), new B_Sheduler.Event(4, 6), new B_Sheduler.Event(8, 10), new B_Sheduler.Event(7, 11)
        };

        starts = instance.calcStartTimes(event, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        ok = starts.toString().equals("[(1:2), (2:3), (3:5), (6:7), (8:10)]");
        assertTrue("B_Sheduler failed with test 2", ok);

        instance = new B_Sheduler();
        B_Sheduler.Event[] even = {new B_Sheduler.Event(1, 10), new B_Sheduler.Event(7, 11),
                new B_Sheduler.Event(8, 10), new B_Sheduler.Event(4, 6), new B_Sheduler.Event(8, 10), new B_Sheduler.Event(7, 11)
        };

        starts = instance.calcStartTimes(even, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        ok = starts.toString().equals("[(4:6), (8:10)]");
        assertTrue("B_Sheduler failed with test 3", ok);

        instance = new B_Sheduler();
        B_Sheduler.Event[] eve = {new B_Sheduler.Event(1, 10), new B_Sheduler.Event(7, 10),
                new B_Sheduler.Event(8, 10), new B_Sheduler.Event(4, 10), new B_Sheduler.Event(8, 10), new B_Sheduler.Event(7, 11)
        };

        starts = instance.calcStartTimes(eve, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        ok = starts.toString().equals("[(1:10)]");
        assertTrue("B_Sheduler failed with test 4", ok);

        instance = new B_Sheduler();
        B_Sheduler.Event[] ev = {new B_Sheduler.Event(1, 6), new B_Sheduler.Event(1, 10),
                new B_Sheduler.Event(1, 5), new B_Sheduler.Event(1, 9), new B_Sheduler.Event(1, 5), new B_Sheduler.Event(1, 11)
        };
        starts = instance.calcStartTimes(ev, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        ok = starts.toString().equals("[(1:5)]");
        assertTrue("B_Sheduler failed with test 5", ok);

        instance = new B_Sheduler();
        B_Sheduler.Event[] e = {new B_Sheduler.Event(1, 11)};

        starts = instance.calcStartTimes(e, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        ok = starts.toString().equals("[(1:11)]");
        assertTrue("B_Sheduler failed with test 6", ok);
    }

    @Test
    public void C_GreedyKnapsack() throws Exception {
        String root = System.getProperty("user.dir");
        File f = new File(root + "/by/it/group151002/zavaliuk/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        boolean ok = costFinal == 200;
        assertTrue("C_GreedyKnapsack failed with file greedyKnapsack.txt", ok);

        root = System.getProperty("user.dir");
        f = new File(root + "/by/it/group151002/zavaliuk/lesson02/greedyKnapsack2.txt");
        costFinal = new C_GreedyKnapsack().calc(f);
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String format = decimalFormat.format(costFinal);
        ok = format.equals("151,21");
        assertTrue("C_GreedyKnapsack failed with file greedyKnapsack1.txt", ok);

        root = System.getProperty("user.dir");
        f = new File(root + "/by/it/group151002/zavaliuk/lesson02/greedyKnapsack1.txt");
        costFinal = new C_GreedyKnapsack().calc(f);
        ok = costFinal == 16596;
        assertTrue("C_GreedyKnapsack failed with file greedyKnapsack2.txt", ok);

        root = System.getProperty("user.dir");
        f = new File(root + "/by/it/group151002/zavaliuk/lesson02/greedyKnapsack3.txt");
        costFinal = new C_GreedyKnapsack().calc(f);
        ok = costFinal == 1;
        assertTrue("C_GreedyKnapsack failed with file greedyKnapsack3.txt", ok);

        root = System.getProperty("user.dir");
        f = new File(root + "/by/it/group151002/zavaliuk/lesson02/greedyKnapsack4.txt");
        costFinal = new C_GreedyKnapsack().calc(f);
        ok = costFinal == 8;
        assertTrue("C_GreedyKnapsack failed with file greedyKnapsack4.txt", ok);

        root = System.getProperty("user.dir");
        f = new File(root + "/by/it/group151002/zavaliuk/lesson02/greedyKnapsack5.txt");
        costFinal = new C_GreedyKnapsack().calc(f);
        ok = costFinal == 8;
        assertTrue("C_GreedyKnapsack failed with file greedyKnapsack5.txt", ok);
    }

}
