package by.it.group151004.belsky.lesson11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class TaskB {
    private static HashMap<Vertex, Integer> map;
    private static LinkedList<Vertex> list;

    public static LinkedList<Vertex> calculate () throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/belsky/lesson11/graphB.txt");

        Graph graph = Graph.readGraphFromFile(stream);

        map = new HashMap<>();
        list = new LinkedList<>();

        for (String vKey : graph.getVertices().keySet()) {
            Vertex v = graph.getVertex(vKey);
            map.put(v, 0);
        }

        ArrayList<Vertex> vertices = new ArrayList<>();
        for (String vKey : graph.getVertices().keySet()) {
            vertices.add(graph.getVertex(vKey));
        }
        vertices.sort(Comparator.comparing(Vertex::getName));

        for (Vertex v : vertices) {
            step(v);
        }

        return list;
    }

    private static void step(Vertex v) {
        if (map.get(v) == 2) return;

        if (map.get(v) == 0) {
            map.put(v, 1);
            for (Edge e : v.getOutEdges()) {
                step(e.getTo());
            }
            map.put(v, 2);
            list.add(0, v);
        }
    }
}
