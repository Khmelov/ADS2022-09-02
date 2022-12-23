package by.it.group151004.belsky.lesson12;

import java.io.*;
import java.util.*;

public class TaskC {

    public static Edge getContainedEdge(Graph graph, InputStream stream) {
        Scanner in = new Scanner(stream);
        Edge returnEdge = null;
        while (in.hasNextLine()) {
            String arr[] = in.nextLine().split(" ");
            if (arr.length == 5) {
                Vertex startVertex = graph.getVertex(arr[1]);
                for (Edge e : startVertex.getOutEdges()) {
                    if (e.getTo().getName().equals(arr[2])) {
                        returnEdge = e;
                    }
                }
                break;
            }
        }
        return returnEdge;
    }

    public static float getShortestLoop(Graph graph, Edge containedEdge) {
        float shortest = Float.POSITIVE_INFINITY;
        Queue<Vertex> vertexQueue = new LinkedList<>();
        Queue<Float> lenQueue = new LinkedList<>();
        HashSet<Vertex> visited = new HashSet<>();

        Vertex startVertex = containedEdge.getFrom();

        lenQueue.add(0.0f);
        vertexQueue.add(startVertex);

        while (!vertexQueue.isEmpty()) {
            float currentLen = lenQueue.poll();
            Vertex currentVertex = vertexQueue.poll();

            if (visited.contains(currentVertex)) {
                shortest = Math.min(currentLen, shortest);
                continue;
            }

            visited.add(currentVertex);
            for (Edge e : currentVertex.getOutEdges()) {
                vertexQueue.add(e.getTo());
                lenQueue.add(currentLen+e.getWeight());
            }
        }
        return shortest;
    }

    public static float getLen (String relFilePath) throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + relFilePath); //"by/it/group151004/belsky/lesson11/graphC.txt"
        Graph graph = Graph.readGraphFromFile(stream);

        stream = new FileInputStream(root + relFilePath);
        Edge containedEdge = getContainedEdge(graph, stream);
        if (containedEdge == null) {
            System.out.println("No contained edge was specified.");
            throw new Exception("Incorrect input file format.");
        }

        return getShortestLoop(graph, containedEdge);
    }

}
