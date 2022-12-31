package by.it.group151004.pyshny.lesson12;

import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

import by.it.group151004.pyshny.lesson12.TaskA.Info;

@SuppressWarnings("all")
public class Lesson12Test {

    @Test(timeout = 2000)
    public void taskA() throws Exception {
        TaskA graph = new TaskA();
        for (int i = 0; i < 8; i++) {
            graph.list.add(new ArrayList<Info>());
        }

        graph.list.get(0).add(new Info(4, 4));
        graph.list.get(0).add(new Info(5, 8));
        graph.list.get(0).add(new Info(1, 1));
        graph.list.get(1).add(new Info(5, 6));
        graph.list.get(1).add(new Info(6, 6));
        graph.list.get(1).add(new Info(2, 2));
        graph.list.get(2).add(new Info(6, 2));
        graph.list.get(2).add(new Info(3, 1));
        graph.list.get(3).add(new Info(6, 1));
        graph.list.get(3).add(new Info(7, 4));
        graph.list.get(6).add(new Info(5, 1));
        graph.list.get(6).add(new Info(7, 1));
        graph.list.get(4).add(new Info(5, 5));

        graph.GetWay(0);

        boolean ok;
        ok = 0 == graph.len[0];
        assertTrue("Failed", ok);

        ok = 1 == graph.len[1];
        assertTrue("Failed", ok);

        ok = 3 == graph.len[2];
        assertTrue("Failed", ok);

        ok = 4 == graph.len[3];
        assertTrue("Failed", ok);

        ok = 4 == graph.len[4];
        assertTrue("Failed", ok);

        ok = 6 == graph.len[5];
        assertTrue("Failed", ok);

        ok = 5 == graph.len[6];
        assertTrue("Failed", ok);

        ok = 6 == graph.len[7];
        assertTrue("Failed", ok);
    }

    @Test(timeout = 2000)
    public void taskB() throws Exception {
        TaskB graph = new TaskB();
        graph.GetWay(0);

        boolean ok;
        ok = 0 == graph.len[0];
        assertTrue("Failed", ok);
        ok = 4 == graph.len[1];
        assertTrue("Failed", ok);
        ok = -2 == graph.len[2];
        assertTrue("Failed", ok);
        ok = 0 == graph.len[3];
        assertTrue("Failed", ok);
        ok = 999999998 == graph.len[4];
        assertTrue("Failed", ok);
        ok = -1 == graph.len[5];
        assertTrue("Failed", ok);
        ok = 1 == graph.len[6];
        assertTrue("Failed", ok);
        ok = 0 == graph.len[7];
        assertTrue("Failed", ok);
        ok = 0 == graph.len[8];
        assertTrue("Failed", ok);
        ok = 999999998 == graph.len[9];
        assertTrue("Failed", ok);
    }

    @Test(timeout = 2000)
    public void taskC() throws Exception {
        TaskC graph = new TaskC(2, 3);
        int minLength = graph.GetWay();
        boolean ok = minLength == 2;
        assertTrue("Failed", ok);
    }
}