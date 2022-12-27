package by.it.group151003.patiyuk.lesson12;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Lesson12Test {

    @Test(timeout = 2000)
    public void testTaskA() throws Exception {
        Graph graph = new Graph();
        graph.loadGraph("GraphA.txt");
        TaskA.Dijkstra dijkstra = new TaskA.Dijkstra(graph);

        String[] vertices = {"A","B","C","D","E","F","G","H"};
        int[] expected = {0, 1, 3, 4, 4, 6, 5, 6};
        for (int i = 0; i < vertices.length; i++) {
            Graph.Path path = dijkstra.findShortestPath("A", vertices[i]);
            assertEquals(expected[i], path.getWeight());
        }
    }

    @Test(timeout = 2000)
    public void testTaskB() throws Exception {
        Graph graph = new Graph();
        graph.loadGraph("GraphB.txt");
        TaskB.BellmanFord bellmanFord = new TaskB.BellmanFord(graph);

        String[] vertices = {"A","B","C","D","E","F","G","H","I","S"};
        Object[] expected = {0, 4, -2, 0, null, -1, 1, 0, 0, null};

        for (int i = 0; i < vertices.length; i++) {
            Graph.Path path = bellmanFord.findShortestPath("A", vertices[i]);
            if (expected[i] == null) {
                assertEquals(null, path);
            } else {
                assertEquals(expected[i], path.getWeight());
            }
        }
    }

    @Test(timeout = 2000)
    public void testTaskC() throws Exception {
        Graph graph = new Graph();
        graph.loadGraph("GraphC.txt");
        int expected = 10;

        assertEquals(expected, TaskC.MinCycleAlgorithm(graph, "B", "C"));
    }
}
