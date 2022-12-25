package by.it.group151002.rusakovich.lesson12;

import by.it.group151002.rusakovich.lesson11.DirectedGraph;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class Test12 {
    @Test
    public void TestA(){
        DirectedGraph<Character> graph = new DirectedGraph<>(8);
        graph.addNode(0, 'A');
        graph.addNode(1, 'B');
        graph.addNode(2, 'C');
        graph.addNode(3, 'D');
        graph.addNode(4, 'E');
        graph.addNode(5, 'F');
        graph.addNode(6, 'G');
        graph.addNode(7, 'H');

        graph.addIncident(0, 1, 1);
        graph.addIncident(0, 4, 4);
        graph.addIncident(0, 5, 8);
        graph.addIncident(1, 2, 2);
        graph.addIncident(1, 5, 6);
        graph.addIncident(1, 6, 6);
        graph.addIncident(2, 3, 1);
        graph.addIncident(2, 6, 2);
        graph.addIncident(3, 6, 1);
        graph.addIncident(3, 7, 4);
        graph.addIncident(4, 5, 5);
        graph.addIncident(6, 5, 1);
        graph.addIncident(6, 7, 1);

        int[][] res = graph.Deikstra(0);
        assertEquals(res[res.length-1][0], 0);
        assertEquals(res[res.length-1][1], 1);
        assertEquals(res[res.length-1][2], 3);
        assertEquals(res[res.length-1][3], 4);
        assertEquals(res[res.length-1][4], 4);
        assertEquals(res[res.length-1][5], 6);
        assertEquals(res[res.length-1][6], 5);
        assertEquals(res[res.length-1][7], 6);
        System.out.println("A->0, B->1, C->3, D->4, E->4, F->6, G->5, H->6");
    }

    @Test
    public void TestB(){
        DirectedGraph<Character> graph = new DirectedGraph<>(10);
        graph.addNode(0, 'A');
        graph.addNode(1, 'B');
        graph.addNode(2, 'C');
        graph.addNode(3, 'D');
        graph.addNode(4, 'E');
        graph.addNode(5, 'F');
        graph.addNode(6, 'G');
        graph.addNode(7, 'H');
        graph.addNode(8, 'I');
        graph.addNode(9, 'S');

        graph.addIncident(0,1, 4);
        graph.addIncident(0,2, -2);
        graph.addIncident(1,6, -2);
        graph.addIncident(1,7, -4);
        graph.addIncident(2,3, 2);
        graph.addIncident(2,5, 1);
        graph.addIncident(4,5, -2);
        graph.addIncident(4,7, 3);
        graph.addIncident(5,3, 3);
        graph.addIncident(6,8, -1);
        graph.addIncident(7,6, 1);
        graph.addIncident(8,7, 1);
        graph.addIncident(9,0, 7);
        graph.addIncident(9,2, 6);
        graph.addIncident(9,4, 6);
        graph.addIncident(9,5, 5);

        final int[] expected = {0, 4, -2, 0, Integer.MAX_VALUE, -1, 1, 0, 0, Integer.MAX_VALUE};
        assertEquals(Arrays.toString(expected), Arrays.toString(graph.BellmanFord(0)));
        for(int i = 0; i < expected.length; i++){
            System.out.println("Way from A to " + graph.indexToValue(i) + ": " +
                    (expected[i] == Integer.MAX_VALUE ? "NO WAY" : expected[i]));
        }
    }

    @Test
    public void TestC() {
        DirectedGraph<Character> graph = new DirectedGraph<>(9);
        graph.addNode(0, 'A');
        graph.addNode(1, 'B');
        graph.addNode(2, 'C');
        graph.addNode(3, 'D');
        graph.addNode(4, 'E');
        graph.addNode(5, 'F');
        graph.addNode(6, 'G');
        graph.addNode(7, 'H');
        graph.addNode(8, 'H');

        graph.addTwoWayIncident(0, 1, 4, 4);
        graph.addTwoWayIncident(0, 7, 8, 8);
        graph.addTwoWayIncident(1, 2, 8, 8);
        graph.addTwoWayIncident(1, 7, 11, 11);
        graph.addTwoWayIncident(2, 3, 7, 7);
        graph.addTwoWayIncident(2, 5, 4, 4);
        graph.addTwoWayIncident(2, 8, 2, 2);
        graph.addTwoWayIncident(3, 4, 9, 9);
        graph.addTwoWayIncident(3, 5, 14, 14);
        graph.addTwoWayIncident(4, 5, 10, 10);
        graph.addTwoWayIncident(5, 6, 2, 2);
        graph.addTwoWayIncident(6, 7, 1, 1);
        graph.addTwoWayIncident(6, 8, 6, 6);
        graph.addTwoWayIncident(7, 8, 7, 7);


        assertEquals(14, graph.findLenOfMinCycle(2, 5));
        System.out.println("Min cycle with 2 -> 5 has length: 14");
    }
}
