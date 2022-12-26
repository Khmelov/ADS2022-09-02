package by.it.group151002.ryabov.lesson12;

import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

@SuppressWarnings("all")
public class Lesson12Test {

    @Test(timeout = 2000)
    public void TaskA() throws Exception {
        GraphA gr = new GraphA();
        gr.vertexNum = 8;
        gr.dist = new int[gr.vertexNum];
        gr.pq = new PriorityQueue<Node>(gr.vertexNum, new Node());
        for (int i = 0; i < gr.vertexNum; i++) {
            List<Node> item = new ArrayList<Node>();
            gr.graph.add(item);
        }
        gr.graph.get(0).add(new Node(1, 1));
        gr.graph.get(0).add(new Node(4, 4));
        gr.graph.get(0).add(new Node(5, 8));
        gr.graph.get(1).add(new Node(2, 2));
        gr.graph.get(1).add(new Node(5, 6));
        gr.graph.get(1).add(new Node(6, 6));
        gr.graph.get(2).add(new Node(6, 2));
        gr.graph.get(2).add(new Node(3, 1));
        gr.graph.get(3).add(new Node(6, 1));
        gr.graph.get(3).add(new Node(7, 4));
        gr.graph.get(4).add(new Node(5, 5));
        gr.graph.get(6).add(new Node(7, 1));
        gr.dijkstra(0);
        boolean ok;
        ok = (gr.dist[0] == 0);
        assertTrue("Failed", ok);
        ok = (gr.dist[1] == 1);
        assertTrue("Failed", ok);
        ok = (gr.dist[2] == 3);
        assertTrue("Failed", ok);
        ok = (gr.dist[3] == 4);
        assertTrue("Failed", ok);
        ok = (gr.dist[4] == 4);
        assertTrue("Failed", ok);
        ok = (gr.dist[5] == 7);
        assertTrue("Failed", ok);
        ok = (gr.dist[6] == 5);
        assertTrue("Failed", ok);
        ok = (gr.dist[7] == 6);
        assertTrue("Failed", ok);
    }
    @Test(timeout = 2000)
    public void TaskB() throws Exception {
        GraphB gr = new GraphB();
        gr.fordBellman(0);
        boolean ok;
        ok = (gr.dist[0] == 0);
        assertTrue("Failed", ok);
        ok = (gr.dist[1] == 4);
        assertTrue("Failed", ok);
        ok = (gr.dist[2] == -2);
        assertTrue("Failed", ok);
        ok = (gr.dist[3] == 0);
        assertTrue("Failed", ok);
        ok = (gr.dist[5] == -1);
        assertTrue("Failed", ok);
        ok = (gr.dist[6] == 1);
        assertTrue("Failed", ok);
        ok = (gr.dist[7] == 0);
        assertTrue("Failed", ok);
        ok = (gr.dist[8] == 0);
        assertTrue("Failed", ok);
    }

    @Test(timeout = 2000)
    public void TaskC() throws Exception {
        GraphC gr = new GraphC();
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
