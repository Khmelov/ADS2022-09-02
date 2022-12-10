package by.it.group151004.belsky.lesson11;

import java.io.*;
import java.util.*;

public class TaskC {
    public static Graph readGraphFromFile(InputStream stream) {
        Scanner in = new Scanner(stream);
        Graph graph = new Graph();

        while (in.hasNextLine()) {
            String nextLine = in.nextLine();
            if (nextLine.equals("")) continue;

            String[] arr = nextLine.split(" ");
            if (arr.length == 2) {
                //isVertex
                graph.addVertex(new Vertex(arr[1]));

            } else if (arr.length >= 4) {
                //isEdge
                graph.addEdge(arr[1], arr[2], Float.parseFloat(arr[3]));
            }

        }
        return graph;
    }

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

    public static void main (String[] args) throws IOException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/belsky/lesson11/graph.txt");
        Graph graph = readGraphFromFile(stream);

        stream = new FileInputStream(root + "by/it/group151004/belsky/lesson11/graph.txt");
        Edge containedEdge = getContainedEdge(graph, stream);
        if (containedEdge == null) {
            System.out.println("No contained edge was specified.");
            return;
        }
        
        float result = getShortestLoop(graph, containedEdge);
        System.out.println(result);
    }

}
