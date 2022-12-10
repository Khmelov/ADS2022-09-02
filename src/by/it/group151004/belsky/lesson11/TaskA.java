package by.it.group151004.belsky.lesson11;

import java.util.*;

public class TaskA {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Vertex vA = new Vertex("A");
        graph.addVertex(vA);
        Vertex vB = new Vertex("B");
        graph.addVertex(vB);
        Vertex vC = new Vertex("C");
        graph.addVertex(vC);
        Vertex vD = new Vertex("D");
        graph.addVertex(vD);
        Vertex vE = new Vertex("E");
        graph.addVertex(vE);
        Vertex vF = new Vertex("F");
        graph.addVertex(vF);
        Vertex vG = new Vertex("G");
        graph.addVertex(vG);
        Vertex vH = new Vertex("H");
        graph.addVertex(vH);

        vA.addEdge(new Edge(vA, vB, 1));
        vA.addEdge(new Edge(vA, vF, 8));
        vA.addEdge(new Edge(vA, vE, 4));

        vB.addEdge(new Edge(vB, vC, 2));
        vB.addEdge(new Edge(vB, vG, 6));
        vB.addEdge(new Edge(vB, vF, 6));

        vC.addEdge(new Edge(vC, vD, 1));
        vC.addEdge(new Edge(vC, vG, 2));

        vD.addEdge(new Edge(vD, vG, 1));
        vD.addEdge(new Edge(vD, vH, 4));

        vE.addEdge(new Edge(vE, vF, 5));

        vG.addEdge(new Edge(vG, vF, 1));
        vG.addEdge(new Edge(vG, vH, 1));

        HashMap<Vertex, Float> map = new HashMap<>();
        map.put(vA, Float.POSITIVE_INFINITY);
        map.put(vB, Float.POSITIVE_INFINITY);
        map.put(vC, Float.POSITIVE_INFINITY);
        map.put(vD, Float.POSITIVE_INFINITY);
        map.put(vE, Float.POSITIVE_INFINITY);
        map.put(vF, Float.POSITIVE_INFINITY);
        map.put(vG, Float.POSITIVE_INFINITY);
        map.put(vH, Float.POSITIVE_INFINITY);
        map.put(vA, 0.0f);
        PriorityQueue<Vertex> pQueue = new PriorityQueue<>(11, (e1, e2) -> (int) Math.floor(map.get(e1) - map.get(e2)));
        pQueue.add(vA);

        while (!pQueue.isEmpty()) {
            Vertex prev = pQueue.remove();
            for (Edge e : prev.getOutEdges()) {
                if (e.getWeight()+map.get(prev) < map.get(e.getTo())) {
                    map.put(e.getTo(), e.getWeight()+map.get(prev));
                    pQueue.add(e.getTo());
                }
            }
        }

        map.forEach(((vertex, aFloat) -> {
            System.out.println(vertex.getName()+": "+aFloat);
        }));

    }
}
