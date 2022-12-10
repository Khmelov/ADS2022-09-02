package by.it.group151004.belsky.lesson11;

import java.util.*;

public class TaskB {
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
        Vertex vI = new Vertex("I");
        graph.addVertex(vI);
        Vertex vS = new Vertex("S");
        graph.addVertex(vS);

        ArrayList<Edge> edges = new ArrayList<>();
        Edge bufEdge;

        bufEdge = new Edge(vA, vB, 4);
        vA.addEdge(bufEdge);
        edges.add(bufEdge);
        bufEdge = new Edge(vA, vC, -2);
        vA.addEdge(bufEdge);
        edges.add(bufEdge);

        bufEdge = new Edge(vB, vH, -4);
        vB.addEdge(bufEdge);
        edges.add(bufEdge);
        bufEdge = new Edge(vB, vG, -2);
        vB.addEdge(bufEdge);
        edges.add(bufEdge);

        bufEdge = new Edge(vC, vD, 2);
        vC.addEdge(bufEdge);
        edges.add(bufEdge);
        bufEdge = new Edge(vC, vF, 1);
        vC.addEdge(bufEdge);
        edges.add(bufEdge);

        //vD not edges

        bufEdge = new Edge(vE, vF, -2);
        vE.addEdge(bufEdge);
        edges.add(bufEdge);
        bufEdge = new Edge(vE, vH, 3);
        vE.addEdge(bufEdge);
        edges.add(bufEdge);

        bufEdge = new Edge(vF, vD, 3);
        vF.addEdge(bufEdge);
        edges.add(bufEdge);

        bufEdge = new Edge(vG, vI, -1);
        vG.addEdge(bufEdge);
        edges.add(bufEdge);

        bufEdge = new Edge(vI, vH, 1);
        vI.addEdge(bufEdge);
        edges.add(bufEdge);

        bufEdge = new Edge(vS, vA, 7);
        vS.addEdge(bufEdge);
        edges.add(bufEdge);
        bufEdge = new Edge(vS, vC, 6);
        vS.addEdge(bufEdge);
        edges.add(bufEdge);
        bufEdge = new Edge(vS, vE, 6);
        vS.addEdge(bufEdge);
        edges.add(bufEdge);
        bufEdge = new Edge(vS, vF, 5);
        vS.addEdge(bufEdge);
        edges.add(bufEdge);


        HashMap<Vertex, Float> map = new HashMap<>();
        map.put(vA, 0.0f);
        map.put(vB, Float.POSITIVE_INFINITY);
        map.put(vC, Float.POSITIVE_INFINITY);
        map.put(vD, Float.POSITIVE_INFINITY);
        map.put(vE, Float.POSITIVE_INFINITY);
        map.put(vF, Float.POSITIVE_INFINITY);
        map.put(vG, Float.POSITIVE_INFINITY);
        map.put(vH, Float.POSITIVE_INFINITY);
        map.put(vI, Float.POSITIVE_INFINITY);
        map.put(vS, Float.POSITIVE_INFINITY);

        for (int i = 0;i < map.size()-1;i++) {
            for (Edge e : edges) {
                if (map.get(e.getFrom())+e.getWeight() < map.get(e.getTo())) {
                    map.put(e.getTo(), map.get(e.getFrom())+e.getWeight());
                }
            }
        }

        map.forEach(((vertex, aFloat) -> {
            System.out.println(vertex.getName()+": "+aFloat);
        }));

    }
}
