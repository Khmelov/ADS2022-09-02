package by.it.group151002.talalaev.lesson11;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@SuppressWarnings("all")
public class Tests {

    @Test (timeout = 3000)
    public void testTaskA() throws Exception {
        TaskA grap = new TaskA();
        int v = 9;
        grap.v = v;
        grap.arr = new int[v][v];
        grap.addEdge(0, 1);
        grap.addEdge(0, 4);
        grap.addEdge(1,2);
        grap.addEdge(1,4);;
        grap.addEdge(2,5);
        grap.addEdge(4, 5);
        grap.addEdge(5, 8);
        grap.addEdge(3,6);
        grap.addEdge(3,7);
        grap.addEdge(6,7);
        grap.initDFS();
        String actual = grap.restStr;
        String expected = "0 1 2 5 4 8 3 6 7 ";
        assertTrue("DFS incorrect", expected.equals(actual));
        String actualPre = Arrays.toString(grap.pre);
        int[] expectedPreArr = {0, 1, 2, 12, 4, 3, 13, 14, 6};
        String expectedPre = Arrays.toString(expectedPreArr);
        assertTrue("PRE Values are incorrect", expectedPre.equals(actualPre));
        String actualPost = Arrays.toString(grap.post);
        int[] expectedPostArr = {11, 10, 9, 17, 5, 8, 16, 15, 7};
        String expectedPost = Arrays.toString(expectedPostArr);
        assertTrue("POST Values are Incorrect", expectedPost.equals(actualPost));
    }

    @Test (timeout = 3000)
    public void testTaskB() throws Exception {
        TaskB graph = new TaskB();
        int v = 8;
        graph.v = v;
        graph.arr = new int[v][v];
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.sort();
        int[] expectedArr = {1, 0, 2, 4, 3, 5 ,7, 6};
        String expected = Arrays.toString(expectedArr);
        String actual = Arrays.toString(graph.tsort);
        assertTrue("SORT is not VALID", expected.equals(actual));
    }

    @Test(timeout = 2000)
    public void taskC() {
        Graph graph = new Graph(8);
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
        assertEquals("Failed Sources", expectedSources, TaskC.sinkAndSources(graph));
    }
}