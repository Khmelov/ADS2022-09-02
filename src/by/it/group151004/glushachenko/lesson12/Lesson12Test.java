package by.it.group151004.glushachenko.lesson12;

import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

@SuppressWarnings("all")
public class Lesson12Test {

    @Test(timeout = 2000)
    public void taskA() throws Exception {
        TaskA taskA = new TaskA();
        for (int i = 0; i < 8; i++) {
            taskA.adj.add(new ArrayList<TaskA.Pair>());
        }

        taskA.adj.get(0).add(new TaskA.Pair(4, 4));
        taskA.adj.get(0).add(new TaskA.Pair(5, 8));
        taskA.adj.get(0).add(new TaskA.Pair(1, 1));
        taskA.adj.get(1).add(new TaskA.Pair(5, 6));
        taskA.adj.get(1).add(new TaskA.Pair(6, 6));
        taskA.adj.get(1).add(new TaskA.Pair(2, 2));
        taskA.adj.get(2).add(new TaskA.Pair(6, 2));
        taskA.adj.get(2).add(new TaskA.Pair(3, 1));
        taskA.adj.get(3).add(new TaskA.Pair(6, 1));
        taskA.adj.get(3).add(new TaskA.Pair(7, 4));
        taskA.adj.get(6).add(new TaskA.Pair(5, 1));
        taskA.adj.get(6).add(new TaskA.Pair(7, 1));
        taskA.adj.get(4).add(new TaskA.Pair(5, 5));

        taskA.dijkstra(0);

        boolean ok;
        ok = 0 == taskA.dist[0];
        assertTrue("Failed", ok);

        ok = 1 == taskA.dist[1];
        assertTrue("Failed", ok);

        ok = 3 == taskA.dist[2];
        assertTrue("Failed", ok);

        ok = 4 == taskA.dist[3];
        assertTrue("Failed", ok);

        ok = 4 == taskA.dist[4];
        assertTrue("Failed", ok);

        ok = 6 == taskA.dist[5];
        assertTrue("Failed", ok);

        ok = 5 == taskA.dist[6];
        assertTrue("Failed", ok);

        ok = 6 == taskA.dist[7];
        assertTrue("Failed", ok);
    }

    @Test(timeout = 2000)
    public void TaskB() throws Exception {
        TaskB taskB = new TaskB();
        taskB.fordBellman(0);
        boolean ok;
        ok = (taskB.dist[0] == 0);
        assertTrue("Failed", ok);
        ok = (taskB.dist[1] == 4);
        assertTrue("Failed", ok);
        ok = (taskB.dist[2] == -2);
        assertTrue("Failed", ok);
        ok = (taskB.dist[3] == 0);
        assertTrue("Failed", ok);
        ok = (taskB.dist[5] == -1);
        assertTrue("Failed", ok);
        ok = (taskB.dist[6] == 1);
        assertTrue("Failed", ok);
        ok = (taskB.dist[7] == 0);
        assertTrue("Failed", ok);
        ok = (taskB.dist[8] == 0);
        assertTrue("Failed", ok);
    }

    @Test(timeout = 2000)
    public void TaskC() throws Exception {
        TaskC gr = new TaskC();
        gr.vertexNum = 10;
        gr.dist = new int[gr.vertexNum];
        gr.pq = new PriorityQueue<Node>(gr.vertexNum, new Node());
        for (int i = 0; i < gr.vertexNum; i++) {
            List<Node> item = new ArrayList<Node>();
            gr.graph.add(item);
        }
        gr.graph.get(0).add(new Node(1, 4));
        gr.graph.get(0).add(new Node(2, 2));
        gr.graph.get(1).add(new Node(6, 2));
        gr.graph.get(1).add(new Node(7, 4));
        gr.graph.get(2).add(new Node(3, 2));
        gr.graph.get(2).add(new Node(5, 1));
        gr.graph.get(4).add(new Node(5, 2));
        gr.graph.get(4).add(new Node(7, 3));
        gr.graph.get(5).add(new Node(3, 3));
        gr.graph.get(6).add(new Node(8, 1));
        gr.graph.get(7).add(new Node(6, 1));
        gr.graph.get(8).add(new Node(7, 1));
        gr.graph.get(9).add(new Node(0, 7));
        gr.graph.get(9).add(new Node(2, 6));
        gr.graph.get(9).add(new Node(4, 6));
        gr.graph.get(9).add(new Node(5, 5));
        gr.dijkstra(6);
        gr.res = gr.dist[7];
        for (int i = 0; i < gr.graph.get(7).size(); i++)
            if (gr.graph.get(7).get(i).node == 6)
                gr.res += gr.graph.get(7).get(i).cost;
        boolean ok;
        ok = (gr.res == 3);
        assertTrue("Failed", ok);
    }
}