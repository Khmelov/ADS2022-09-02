package by.it.group151004.eremeichik.lesson12;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Lesson12Test {
    @Test
    public void testA(){
        TaskA.setVertexNum(8);
        List<List<Node>> graph = TaskA.graph;
        graph.get(0).add(new Node(1, 1));
        graph.get(0).add(new Node(4, 4));
        graph.get(0).add(new Node(5, 8));
        graph.get(1).add(new Node(2, 2));
        graph.get(1).add(new Node(5, 6));
        graph.get(1).add(new Node(6, 6));
        graph.get(2).add(new Node(6, 2));
        graph.get(2).add(new Node(3, 1));
        graph.get(3).add(new Node(6, 1));
        graph.get(3).add(new Node(7, 4));
        graph.get(4).add(new Node(5, 5));
        graph.get(6).add(new Node(7, 1));

        int[] expected = {0,1,3,4,4,7,5,6};
        int[] actual = TaskA.dijkstraAlgorithm(0);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testB(){
        TaskB.fordBellman(0);

        int[] expected = {0,4,-2,0,TaskB.INF,-1,1,0,0,TaskB.INF};
        int[] actual = TaskB.getDistArr();

        assertArrayEquals(expected,actual);
    }

    @Test
    public void testC(){
        TaskC.setVertexNum(10);
        List<List<Node>> graph = TaskC.graph;
        graph.get(0).add(new Node(1, 4));
        graph.get(0).add(new Node(2, 2));
        graph.get(1).add(new Node(6, 2));
        graph.get(1).add(new Node(7, 4));
        graph.get(2).add(new Node(3, 2));
        graph.get(2).add(new Node(5, 1));
        graph.get(4).add(new Node(5, 2));
        graph.get(4).add(new Node(7, 3));
        graph.get(5).add(new Node(3, 3));
        graph.get(6).add(new Node(8, 1));
        graph.get(7).add(new Node(6, 1));
        graph.get(8).add(new Node(7, 1));
        graph.get(9).add(new Node(0, 7));
        graph.get(9).add(new Node(2, 6));
        graph.get(9).add(new Node(4, 6));
        graph.get(9).add(new Node(5, 5));
        int u = 7;
        int v = 6;
        TaskC.dijkstraArr(v);

        int expected = 2;
        int actual = TaskC.distArr[u];

        assertEquals(expected,actual);
    }
}
