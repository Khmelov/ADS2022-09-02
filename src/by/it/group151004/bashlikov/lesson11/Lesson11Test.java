package by.it.group151004.bashlikov.lesson11;

import by.it.HomeWork;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Lesson11Test extends HomeWork {
    @Test(timeout = 3000)
    public void testTaskA() {
        TaskA g = new TaskA();
        int depth = 9;
        g.depth = depth;
        g.matrix = new int[depth][depth];
        g.addNode(0, 1);
        g.addNode(0, 4);
        g.addNode(1,2);
        g.addNode(1,4);
        g.addNode(2,5);
        g.addNode(4, 5);
        g.addNode(5, 8);
        g.addNode(3,6);
        g.addNode(3,7);
        g.addNode(6,7);
        g.setNodes();
        String actual = g.graphInString;
        String expected = "0 1 2 5 4 8 3 6 7 ";
        assertEquals("DFS incorrect", expected, actual);
        String actualPre = Arrays.toString(g.prev);
        int[] expectedPreArr = {0, 1, 2, 12, 4, 3, 13, 14, 6};
        String expectedPre = Arrays.toString(expectedPreArr);
        assertEquals("PRE Values are incorrect", expectedPre, actualPre);
        String actualPost = Arrays.toString(g.next);
        int[] expectedPostArr = {11, 10, 9, 17, 5, 8, 16, 15, 7};
        String expectedPost = Arrays.toString(expectedPostArr);
        assertEquals("POST Values are Incorrect", expectedPost, actualPost);
    }

    @Test (timeout = 3000)
    public void testTaskB() {
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
        assertEquals("SORT is not VALID", expected, actual);
    }
}
