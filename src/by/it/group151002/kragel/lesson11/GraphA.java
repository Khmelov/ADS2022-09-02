package by.it.group151002.kragel.lesson11;

import java.util.*;

class VertexA implements Comparable<VertexA> {
    String name;
    VertexA(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(VertexA o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof VertexA && Objects.equals(this.name, ((VertexA) obj).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
class Edge{
    VertexA v1, v2;
    Edge(VertexA v1, VertexA v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return v1 + "-" + v2;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Edge &&
                ((Objects.equals(this.v1, ((Edge) obj).v1) && Objects.equals(this.v2, ((Edge) obj).v2)) ||
                (Objects.equals(this.v2, ((Edge) obj).v1) && Objects.equals(this.v1, ((Edge) obj).v2)));
    }
}
public class GraphA {
    private Map<VertexA, Set<VertexA>> vertexes;
    private List<VertexA> visited;
    private Map<VertexA, Integer> pres, posts;
    private List<Edge> trees, backs;
    private int clock;
    GraphA() {
        vertexes = new TreeMap<>();
    }

    void addVertex(VertexA vertex) {
        vertexes.putIfAbsent(vertex, new TreeSet<>());
    }

    void addVertex(String name) {
        VertexA v = new VertexA(name);
        addVertex(v);
    }

    void addEdge(VertexA v1, VertexA v2) {
        if (!vertexes.containsKey(v1))
            addVertex(v1);
        vertexes.get(v1).add(v2);
        if (!vertexes.containsKey(v2))
            addVertex(v2);
        vertexes.get(v2).add(v1);
    }

    void addEdge(String name1, String name2) {
        VertexA v1 = new VertexA(name1);
        VertexA v2 = new VertexA(name2);
        addEdge(v1, v2);
    }

    void removeVertex(VertexA vertex) {
        vertexes.remove(vertex);
        for (Set<VertexA> s : vertexes.values()) {
            s.remove(vertex);
        }
    }

    void removeVertex(String name) {
        VertexA v = new VertexA(name);
        removeVertex(v);
    }

    void removeEdge(VertexA v1, VertexA v2) {
        vertexes.get(v1).remove(v2);
        vertexes.get(v2).remove(v1);
    }

    void removeEdge(String name1, String name2) {
        VertexA v1 = new VertexA(name1);
        VertexA v2 = new VertexA(name2);
        removeEdge(v1, v2);
    }
    
    void dfs() {
        clock = 0;
        pres = new TreeMap<>();
        posts = new TreeMap<>();
        trees = new ArrayList<>();
        backs = new ArrayList<>();
        visited = new ArrayList<>();
        for (VertexA k : vertexes.keySet()) {
            if (!visited.contains(k)) {
                recursdfs(k);
            }
        }
    }

    String dfsToString() {
        dfs();
        return visited.toString();
    }

    private void recursdfs(VertexA v) {
        visited.add(v);
        pres.put(v, clock++);
        if(vertexes.get(v) != null) {
            for (VertexA adjv : vertexes.get(v)) {
                Edge e = new Edge(v, adjv);
                if (!visited.contains(adjv)) {
                    trees.add(e);
                    recursdfs(adjv);
                } else if (!trees.contains(e) && !backs.contains(e)) {
                    backs.add(e);
                }
            }
        }
        posts.put(v, clock++);
    }

    Map<VertexA, Integer> getPres() {
        dfs();
        return pres;
    }

    Map<VertexA, Integer> getPosts() {
        dfs();
        return posts;
    }
    List<Edge> getTrees(){
        dfs();
        return trees;
    }
    List<Edge> getBacks(){
        dfs();
        return backs;
    }
}

