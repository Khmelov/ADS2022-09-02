package by.it.group151001.pastukhou.lesson12;

import by.it.group151001.pastukhou.lesson12.TaskA.Node;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Lesson12Test {
    @Test(timeout = 2000)
    public void taskA() {
        TaskA AClass = new TaskA();
        for (int i = 0; i < AClass.AMOUNT_OF_VERTICES; i++) {
            AClass.graph.add(new ArrayList<>());
        }

        AClass.graph.get(0).add(new Node(1, 1)); //A -> B
        AClass.graph.get(0).add(new Node(4, 4)); //A -> E
        AClass.graph.get(0).add(new Node(5, 8)); //A -> F

        AClass.graph.get(1).add(new Node(5, 6)); //B -> F
        AClass.graph.get(1).add(new Node(6, 6)); //B -> G
        AClass.graph.get(1).add(new Node(2, 2)); //B -> C

        AClass.graph.get(2).add(new Node(6, 2)); //C -> G
        AClass.graph.get(2).add(new Node(3, 1)); //C -> D

        AClass.graph.get(3).add(new Node(6, 1)); //D -> G
        AClass.graph.get(3).add(new Node(7, 4)); //D -> H

        AClass.graph.get(4).add(new Node(5, 5)); //E -> F

        AClass.graph.get(6).add(new Node(5, 1)); //G -> F
        AClass.graph.get(6).add(new Node(7, 1)); //G -> H

        AClass.dijkstra(0);

        int[] correctDist = {0, 1, 3, 4, 4, 6, 5, 6};

        for (int i = 0; i < TaskA.AMOUNT_OF_VERTICES; i++) {
            assertEquals("Failed " + i, correctDist[i], AClass.dist[i]);
        }
    }

    @Test(timeout = 2000)
    public void taskB() {
        TaskB BClass = new TaskB();

        BClass.edges.add(new TaskB.Node(0,2, -2));
        BClass.edges.add(new TaskB.Node(0,1, 4));
        BClass.edges.add(new TaskB.Node(1,6, -2));
        BClass.edges.add(new TaskB.Node(1,7, -4));
        BClass.edges.add(new TaskB.Node(2,3, 2));
        BClass.edges.add(new TaskB.Node(2,5, 1));
        BClass.edges.add(new TaskB.Node(4,5, -2));
        BClass.edges.add(new TaskB.Node(4,7, 3));
        BClass.edges.add(new TaskB.Node(5,3, 3));
        BClass.edges.add(new TaskB.Node(6,8, -1));
        BClass.edges.add(new TaskB.Node(7,6, 1));
        BClass.edges.add(new TaskB.Node(8,7, 1));
        BClass.edges.add(new TaskB.Node(9,0, 7));
        BClass.edges.add(new TaskB.Node(9,2, 6));
        BClass.edges.add(new TaskB.Node(9,5, 5));
        BClass.edges.add(new TaskB.Node(9,4, 6));

        BClass.bellmanFord(0);

        int[] correctDist = {0, 4, -2, 0, 100, -1, 1, 0, 0, 100};

        for (int i = 0; i < BClass.NUMBER_OF_VERTICES; i++) {
            assertEquals("Failed " + i, correctDist[i], BClass.dist[i]);
        }
    }

    @Test(timeout = 2000)
    public void taskC() {
        int expected = 10;
        TaskC CClass = new TaskC();
        for (int i = 0; i < CClass.NUMBER_OF_VERTICES; i++) {
            TaskC.graph.add(new ArrayList<>());
        }
        CClass.graph.get(0).add(new Node(1, 1));
        CClass.graph.get(0).add(new Node(4, 4));
        CClass.graph.get(0).add(new Node(5, 8));
        CClass.graph.get(1).add(new Node(5, 6));
        CClass.graph.get(1).add(new Node(2, 2));
        CClass.graph.get(2).add(new Node(6, 2));
        CClass.graph.get(2).add(new Node(3, 1));
        CClass.graph.get(3).add(new Node(6, 1));
        CClass.graph.get(3).add(new Node(7, 4));
        CClass.graph.get(4).add(new Node(5, 5));
        CClass.graph.get(6).add(new Node(1, 6));
        CClass.graph.get(6).add(new Node(5, 1));
        CClass.graph.get(6).add(new Node(7, 1));

        CClass.dijkstra(CClass.v);

        assertEquals("Failed", expected, TaskC.findTheSmallestCycle());
    }
}
