package by.it.group151003.patiyuk.lesson12;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {
    public static class Path {
        public int weight;
        public String[] vertices;

        public Path(int weight, String[] vertices) {
            this.weight = weight;
            this.vertices = vertices;
        }

        int getWeight() {
            return weight;
        }

        String[] getVertices() {
            return vertices;
        }

        int getLength() {
            return vertices.length;
        }

        @Override
        public String toString() {
            return "Weight: " + weight + " Path: " + String.join(" -> ", vertices);
        }
    }

    public class Vertex {
        public String name;
        public LinkedList<Edge> edges;

        public Vertex(String name) {
            this.name = name;
            edges = new LinkedList<>();
        }
    }

    public class Edge {
        public Vertex from;
        public Vertex to;
        public int weight;

        public Edge(Vertex from, Vertex to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public LinkedList<Vertex> vertices;

    public Graph() {
        vertices = new LinkedList<>();
    }

    public void addVertex(String name) {
        vertices.add(new Vertex(name));
    }

    public void addEdge(String from, String to, int weight) {
        Vertex fromVertex = null;
        Vertex toVertex = null;
        for (Vertex vertex : vertices) {
            if (vertex.name.equals(from)) {
                fromVertex = vertex;
            }
            if (vertex.name.equals(to)) {
                toVertex = vertex;
            }
        }
        if (fromVertex == null) {
            fromVertex = new Vertex(from);
            vertices.add(fromVertex);
        }
        if (toVertex == null) {
            toVertex = new Vertex(to);
            vertices.add(toVertex);
        }
        fromVertex.edges.add(new Edge(fromVertex, toVertex, weight));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex vertex : vertices) {
            sb.append(vertex.name).append(": ");
            for (Edge edge : vertex.edges) {
                sb.append(edge.to.name).append("(").append(edge.weight).append(") ");
            }
            sb.append("\r");
        }
        return sb.toString();
    }

    public void loadGraph(String filename) {
        InputStream inputStream = Graph.class.getResourceAsStream(filename);
        Scanner scanner = new Scanner(inputStream);
        int numVertices = scanner.nextInt();
        for (int i = 0; i < numVertices; i++) {
            addVertex(scanner.next());
        }
        while (scanner.hasNext()) {
            String from = scanner.next();
            String to = scanner.next();
            int weight = scanner.nextInt();
            addEdge(from, to, weight);
        }
    }
}
