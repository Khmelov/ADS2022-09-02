package by.it.group151004.belsky.lesson11;

import com.sun.source.tree.Tree;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

class UndirVertex implements Comparable<UndirVertex> {
    private String name;
    private ArrayList<UndirEdge> outEdges;

    public UndirVertex(String name) {
        this.name = name;
        outEdges = new ArrayList<>();
    }

    public UndirVertex(String name, ArrayList<UndirEdge> outEdges) {
        this.name = name;
        this.outEdges = outEdges;
    }

    public void addEdge(UndirEdge e) {
        outEdges.add(e);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<UndirEdge> getOutEdges() {
        return outEdges;
    }

    @Override
    public int compareTo(UndirVertex o) {
        return this.getName().compareTo(o.getName());
    }
}

class UndirEdge {
    private ArrayList<UndirVertex> vertices = new ArrayList<>();
    private float weight;

    public UndirEdge(UndirVertex v1, UndirVertex v2, float weight) {
        vertices.add(v1);
        vertices.add(v2);
        this.weight = weight;
    }

    public ArrayList<UndirVertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<UndirVertex> vertices) {
        this.vertices = vertices;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}

public class UndirGraph {
    private TreeMap<String, UndirVertex> vertices = new TreeMap<>();;
    private ArrayList<UndirEdge> edges = new ArrayList<>();

    public UndirGraph() { }

    public static UndirGraph readGraphFromFile(InputStream stream) {
        Scanner in = new Scanner(stream);
        UndirGraph graph = new UndirGraph();

        while (in.hasNextLine()) {
            String nextLine = in.nextLine();
            if (nextLine.equals("")) continue;

            String[] arr = nextLine.split(" ");
            if (arr.length == 2) {
                //isVertex
                graph.addVertex(new UndirVertex(arr[1]));

            } else if (arr.length >= 4) {
                //isEdge
                graph.addEdge(arr[1], arr[2], Float.parseFloat(arr[3]));
            }

        }
        return graph;
    }

    public void addVertex(UndirVertex v) {
        vertices.put(v.getName(), v);
    }

    public UndirVertex getVertex(String name) {
        return vertices.get(name);
    }

    public TreeMap<String, UndirVertex> getVertices() {
        return vertices;
    }

    public ArrayList<UndirEdge> getEdges() {
        return edges;
    }

    public void addEdge(String vert1, String vert2, float weight) {
        UndirEdge newEdge = new UndirEdge(vertices.get(vert1), vertices.get(vert2), weight);
        edges.add(newEdge);
        getVertex(vert1).addEdge(newEdge);
        getVertex(vert2).addEdge(newEdge);
    }

}
