package by.it.group151004.belsky.lesson11;

import java.util.ArrayList;

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
    ArrayList<Vertex> vertices;

    public Graph() {
        this.vertices = new ArrayList<>();
    }

    public Graph(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public void removeVertex(Vertex v) {
        vertices.remove(v);
    }

    public int size() {
        return vertices.size();
    }

    public int indexOf(Vertex v) {
        return vertices.indexOf(v);
    }
}
