package by.it.group151003.matoshko.lesson11;

import by.it.HomeWork;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("all")
public class Test11 extends HomeWork {

    @Test(timeout = 3000)
    public void testTaskA() throws Exception {
        TaskA g = new TaskA();
        int depth = 9;
        g.depth = depth;
        g.matrix = new int[depth][depth];
        g.addNode(0, 1);
        g.addNode(0, 4);
        g.addNode(1,2);
        g.addNode(1,4);;
        g.addNode(2,5);
        g.addNode(4, 5);
        g.addNode(5, 8);
        g.addNode(3,6);
        g.addNode(3,7);
        g.addNode(6,7);
        g.setNodes();
        String actual = g.graphInString;
        String expected = "0 1 2 5 4 8 3 6 7 ";
        assertTrue("DFS incorrect", expected.equals(actual));
        String actualPre = Arrays.toString(g.prev);
        int[] expectedPreArr = {0, 1, 2, 12, 4, 3, 13, 14, 6};
        String expectedPre = Arrays.toString(expectedPreArr);
        assertTrue("PRE Values are incorrect", expectedPre.equals(actualPre));
        String actualPost = Arrays.toString(g.next);
        int[] expectedPostArr = {11, 10, 9, 17, 5, 8, 16, 15, 7};
        String expectedPost = Arrays.toString(expectedPostArr);
        assertTrue("POST Values are Incorrect", expectedPost.equals(actualPost));
    }

    @Test (timeout = 3000)
    public void testTaskB() throws Exception {
        TaskB graph = new TaskB();
        int depth = 8;
        graph.depth = depth;
        graph.matrix = new int[depth][depth];
        graph.addNode(0, 2);
        graph.addNode(1, 2);
        graph.addNode(2, 3);
        graph.addNode(2, 4);
        graph.addNode(3, 5);
        graph.addNode(4, 5);
        graph.addNode(5, 6);
        graph.addNode(5, 7);
        graph.sort();
        int[] expectedArr = {1, 0, 2, 4, 3, 5 ,7, 6};
        String expected = Arrays.toString(expectedArr);
        String actual = Arrays.toString(graph.topology);
        assertTrue("SORT is not VALID", expected.equals(actual));
    }

    @Test(timeout = 3000)
    public void taskC() {
        Graph1 graph = new Graph1(8);
        graph.setName(0, 'A');
        graph.setName(1, 'B');
        graph.setName(2, 'C');
        graph.setName(3, 'D');
        graph.setName(4, 'E');
        graph.setName(5, 'F');
        graph.setName(6, 'G');
        graph.setName(7, 'H');
        graph.addOrientedEdge(0, 2);
        graph.addOrientedEdge(1, 2);
        graph.addOrientedEdge(2, 3);
        graph.addOrientedEdge(2, 4);
        graph.addOrientedEdge(3, 5);
        graph.addOrientedEdge(4, 5);
        graph.addOrientedEdge(5, 6);
        graph.addOrientedEdge(5, 7);
        TaskC.DFS(graph,0);
        List<Character> expectedSources = new ArrayList<>();
        expectedSources.add('A');
        expectedSources.add('B');
        assertEquals("Failed Sources", expectedSources, TaskC.sourceAndStocks(graph));
    }
}
