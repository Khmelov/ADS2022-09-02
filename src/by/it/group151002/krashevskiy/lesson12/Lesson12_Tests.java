package by.it.group151002.krashevskiy.lesson12;

import by.it.group151002.krashevskiy.lesson12.Graph.Node;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Lesson12_Tests {

    @Test(timeout = 2000)
    public void taskA() {
        Graph graph = new Graph();
        for (int i = 0; i < 8; i++) {
            graph.list.add(new ArrayList<>());
        }

        graph.list.get(0).add(new Node(1, 1)); //A -> B
        graph.list.get(0).add(new Node(4, 4)); //A -> E
        graph.list.get(0).add(new Node(5, 8)); //A -> F

        graph.list.get(1).add(new Node(5, 6)); //B -> F
        graph.list.get(1).add(new Node(6, 6)); //B -> G
        graph.list.get(1).add(new Node(2, 2)); //B -> C

        graph.list.get(2).add(new Node(6, 2)); //C -> G
        graph.list.get(2).add(new Node(3, 1)); //C -> D

        graph.list.get(3).add(new Node(6, 1)); //D -> G
        graph.list.get(3).add(new Node(7, 4)); //D -> H

        graph.list.get(4).add(new Node(5, 5)); //E -> F

        graph.list.get(6).add(new Node(5, 1)); //G -> F
        graph.list.get(6).add(new Node(7, 1)); //G -> H

        graph.dijkstra(0);

        int[] correctDist = {0, 1, 3, 4, 4, 6, 5, 6};

        for (int i = 0; i < 7; i++) {
            assertEquals("Failed " + i, correctDist[i], graph.dist[i]);
        }
    }

    @Test(timeout = 2000)
    public void taskB() {
        Graph graph = new Graph();
        int[] dist = graph.shortestPaths(0);

        int[] correctDist = {0, 4, -2, 0, 100, -1, 1, 0, 0, 100};

        for (int i = 0; i < 10; i++) {
            assertEquals("Failed " + i, correctDist[i], dist[i]);
        }
    }

    @Test(timeout = 2000)
    public void taskC() {
        int expected = 10;
        Graph graph = new Graph();

        for (int i = 0; i < 8; i++) {
            graph.list.add(new ArrayList<>());
        }

        graph.list.get(0).add(new Node(1, 1));
        graph.list.get(0).add(new Node(4, 4));
        graph.list.get(0).add(new Node(5, 8));
        graph.list.get(1).add(new Node(5, 6));
        graph.list.get(1).add(new Node(2, 2));
        graph.list.get(2).add(new Node(6, 2));
        graph.list.get(2).add(new Node(3, 1));
        graph.list.get(3).add(new Node(6, 1));
        graph.list.get(3).add(new Node(7, 4));
        graph.list.get(4).add(new Node(5, 5));
        graph.list.get(6).add(new Node(1, 6));
        graph.list.get(6).add(new Node(5, 1));
        graph.list.get(6).add(new Node(7, 1));

        graph.dijkstra(2);

        assertEquals("Failed", expected, graph.findTheSmallestCycle());
    }
}
