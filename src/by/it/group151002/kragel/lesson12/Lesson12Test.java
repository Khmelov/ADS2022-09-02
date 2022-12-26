package by.it.group151002.kragel.lesson12;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Lesson12Test {
    private void assertEqualsAndPrint(String message, Object expected, Object actual){
        System.out.println(actual);
        assertEquals(message, expected, actual);
    }
    @Test
    public void A() throws Exception {
        GraphA gA = new GraphA(8);
        gA.addOrientEdge(0, 1, 1);
        gA.addOrientEdge(0, 4, 4);
        gA.addOrientEdge(0, 5, 8);
        gA.addOrientEdge(1, 2, 2);
        gA.addOrientEdge(1, 5, 6);
        gA.addOrientEdge(1, 6, 6);
        gA.addOrientEdge(2, 3, 1);
        gA.addOrientEdge(2, 6, 2);
        gA.addOrientEdge(3, 6, 1);
        gA.addOrientEdge(3, 7, 4);
        gA.addOrientEdge(4, 5, 5);
        gA.addOrientEdge(6, 5, 1);
        gA.addOrientEdge(6, 7, 1);
        int[] actual = gA.dijkstra(0);
        boolean ok;
        int[] expected = {0, 1, 3, 4, 4, 6, 5, 6};
        for(int i = 0; i < 8; i++){
            ok = actual[i] == expected[i];
            System.out.printf("From 0 to %d = %d\n", i, actual[i]);
            assertTrue("From 0 to %d failed".formatted(i), ok);
        }
    }
    @Test
    public void B() throws Exception {
        GraphB gB = new GraphB(10);
        gB.addOrientEdge(0, 1, 4);
        gB.addOrientEdge(0, 2, -2);
        gB.addOrientEdge(1, 6, -2);
        gB.addOrientEdge(1, 7, -4);
        gB.addOrientEdge(7, 6, 1);
        gB.addOrientEdge(6, 8, -1);
        gB.addOrientEdge(8, 7, 1);
        gB.addOrientEdge(2, 3, 2);
        gB.addOrientEdge(2, 5, 1);
        gB.addOrientEdge(4, 5, -2);
        gB.addOrientEdge(4, 7, 3);
        gB.addOrientEdge(5, 3, 3);
        gB.addOrientEdge(9, 0, 7);
        gB.addOrientEdge(9, 2, 6);
        gB.addOrientEdge(9, 5, 5);
        gB.addOrientEdge(9, 4, 6);
        int[] actual = gB.bellmanFord(0);
        boolean ok;
        int[] expected = {0, 4, -2, 0, GraphB.INF, -1, 1, 0, 0, GraphB.INF};
        for(int i = 0; i < 10; i++){
            ok = actual[i] == expected[i];
            System.out.printf("From 0 to %d = %d\n", i, actual[i]);
            assertTrue("From 0 to %d failed".formatted(i), ok);
        }
    }
    @Test
    public void C() throws Exception {
        GraphC gC = new GraphC(8);
        gC.addOrientEdge(0, 1, 1);
        gC.addOrientEdge(0, 4, 4);
        gC.addOrientEdge(5, 0, 8);
        gC.addOrientEdge(1, 2, 2);
        gC.addOrientEdge(5, 1, 6);
        gC.addOrientEdge(1, 6, 6);
        gC.addOrientEdge(2, 3, 1);
        gC.addOrientEdge(2, 6, 2);
        gC.addOrientEdge(3, 6, 1);
        gC.addOrientEdge(3, 7, 4);
        gC.addOrientEdge(4, 5, 5);
        gC.addOrientEdge(6, 5, 1);
        gC.addOrientEdge(7, 6, 1);
        assertEqualsAndPrint("B-C failed", 11, gC.findMinCycle(1, 2));
        assertEqualsAndPrint("A-B failed", 14, gC.findMinCycle(0, 1));
        assertEqualsAndPrint("B-G failed", 13, gC.findMinCycle(1, 6));
        assertEqualsAndPrint("C-D failed", 11, gC.findMinCycle(2, 3));
    }
}
