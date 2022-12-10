package by.it.group151004.belsky.lesson11;

import java.util.ArrayList;
import java.util.HashMap;

class Edge {
    private Vertex from;
    private Vertex to;
    private float weight;

    public Edge(Vertex from, Vertex to, float weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Vertex getFrom() {
        return from;
    }

    public void setFrom(Vertex from) {
        this.from = from;
    }

    public Vertex getTo() {
        return to;
    }

    public void setTo(Vertex to) {
        this.to = to;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}

class Vertex {
    private String name;
    private ArrayList<Edge> outEdges;

    public Vertex(String name) {
        this.name = name;
        outEdges = new ArrayList<>();
    }

    public Vertex(String name, ArrayList<Edge> outEdges) {
        this.name = name;
        this.outEdges = outEdges;
    }

    public void addEdge(Edge e) {
        outEdges.add(e);
    }

    public void addEdge(Vertex v, float weight) {
        outEdges.add(new Edge(this, v, weight));
    }

    public void removeEdge(Edge e) {
        outEdges.remove(e);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Edge> getOutEdges() {
        return outEdges;
    }
}

public class Graph {
    private HashMap<String, Vertex> vertices = new HashMap<>();;
    private ArrayList<Edge> edges = new ArrayList<>();

    public Graph() { }

    public void addVertex(Vertex v) {
        vertices.put(v.getName(), v);
    }

    public Vertex getVertex(String name) {
        return vertices.get(name);
    }

    public HashMap<String, Vertex> getVertices() {
        return vertices;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void addEdge(String fromVert, String toVert, float weight) {
        Edge newEdge = new Edge(vertices.get(fromVert), vertices.get(toVert), weight);
        edges.add(newEdge);
        vertices.get(fromVert).addEdge(newEdge);
    }

    public void removeVertex(Vertex v) {
        vertices.remove(v);
    }

    public int verticesCount() {
        return vertices.size();
    }

//    public int indexOf(Vertex v) {
//        return vertices.indexOf(v);
//    }
}
