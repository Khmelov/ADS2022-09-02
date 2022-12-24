package by.it.group151004.belsky.lesson12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class TaskA {
    public static HashMap<Vertex, Float> getRoutes(String relFilePath, String startVertexName) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + relFilePath);
        Graph graph = Graph.readGraphFromFile(stream);

        HashMap<Vertex, Float> map = new HashMap<>();
        for (Vertex v : graph.getVertices().values()) {
            map.put(v, Float.POSITIVE_INFINITY);
        }
        map.put(graph.getVertex("A"), 0.0f);
        PriorityQueue<Vertex> pQueue = new PriorityQueue<>(11, (e1, e2) -> (int) Math.floor(map.get(e1) - map.get(e2)));
        pQueue.add(graph.getVertex("A"));

        while (!pQueue.isEmpty()) {
            Vertex prev = pQueue.remove();
            for (Edge e : prev.getOutEdges()) {
                if (e.getWeight()+map.get(prev) < map.get(e.getTo())) {
                    map.put(e.getTo(), e.getWeight()+map.get(prev));
                    pQueue.add(e.getTo());
                }
            }
        }

        return map;
    }
}
