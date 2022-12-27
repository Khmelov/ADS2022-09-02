package by.it.group151002.kragel.lesson11;

import java.util.*;

class VertexB implements Comparable<VertexB> {
    String name;
    VertexB(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(VertexB o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof VertexB && Objects.equals(this.name, ((VertexB) obj).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

public class GraphBC {
    private Map<VertexB, List<VertexB>> vertexes;
    private List<VertexB> visited;
    private Map<VertexB, Integer> pres, posts;
    private int clock;

    GraphBC() {
        vertexes = new TreeMap<>();
    }

    void addVertex(VertexB vertex) {
        vertexes.putIfAbsent(vertex, new ArrayList<>());
    }

    void addVertex(String name) {
        VertexB v = new VertexB(name);
        addVertex(v);
    }

    void addOrientEdge(VertexB v1, VertexB v2) {
        if (!vertexes.containsKey(v1))
            addVertex(v1);
        vertexes.get(v1).add(v2);
    }

    void addOrientEdge(String name1, String name2) {
        VertexB v1 = new VertexB(name1);
        VertexB v2 = new VertexB(name2);
        addOrientEdge(v1, v2);
    }

    void removeVertex(VertexB vertex) {
        vertexes.remove(vertex);
        for (List<VertexB> l : vertexes.values()) {
            l.remove(vertex);
        }
    }

    void removeVertex(String name) {
        VertexB v = new VertexB(name);
        removeVertex(v);
    }

    void removeEdge(VertexB v1, VertexB v2) {
        vertexes.get(v1).remove(v2);
        vertexes.get(v2).remove(v1);
    }

    void removeEdge(String name1, String name2) {
        VertexB v1 = new VertexB(name1);
        VertexB v2 = new VertexB(name2);
        removeEdge(v1, v2);
    }
    List<VertexB> findStarts(){
        List<VertexB> starts = new ArrayList<>();
        List<VertexB> values = new ArrayList<>();
        for (List<VertexB> l : vertexes.values()){
            values.addAll(l);
        }
        for (VertexB k : vertexes.keySet()) {
            if(!values.contains(k)){
                starts.add(k);
            }
        }
        return starts;
    }
    List<VertexB> findEnds(){
        List<VertexB> ends = new ArrayList<>();
        List<VertexB> values = new ArrayList<>();
        for (List<VertexB> l : vertexes.values()){
            values.addAll(l);
        }
        for (VertexB v : values) {
            if(!vertexes.containsKey(v)){
                ends.add(v);
            }
        }
        return ends;
    }

    private int dfsAllWays(VertexB v, VertexB end, List<VertexB> visited){
        if (v.equals(end))
            return 1;
        int count = 0;
        visited.add(v);
        if(vertexes.get(v) != null) {
            for (VertexB adjv : vertexes.get(v)) {
                if (!visited.contains(adjv)) {
                    count += dfsAllWays(adjv, end, visited);
                }
            }
        }
        visited.remove(v);
        return count;
    }
    int getLinear(){
        int linear = 0;
        for(VertexB s : findStarts()){
            for(VertexB e : findEnds()){
                linear += dfsAllWays(s, e, new ArrayList<>());
            }
        }
        return linear;
    }
    void dfs() {
        clock = 0;
        pres = new TreeMap<>();
        posts = new TreeMap<>();
        visited = new ArrayList<>();
        for (VertexB k : vertexes.keySet()) {
            if (!visited.contains(k)) {
                recursdfs(k);
            }
        }
    }

    String dfsToString() {
        dfs();
        return visited.toString();
    }

    private void recursdfs(VertexB v) {
        visited.add(v);
        pres.put(v, clock++);
        if(vertexes.get(v) != null) {
            for (VertexB adjv : vertexes.get(v)) {
                if (!visited.contains(adjv)) {
                    recursdfs(adjv);
                }
            }
        }
        posts.put(v, clock++);
    }
    List<VertexB> getTopological(){
        List<VertexB> topological = new ArrayList<>();
        dfs();
        TreeSet<Map.Entry<VertexB, Integer>> sort = new TreeSet<>((a, b) -> -Integer.compare(a.getValue(), b.getValue()));
        sort.addAll(posts.entrySet());
        for(Map.Entry<VertexB, Integer> entry : sort){
            topological.add(entry.getKey());
        }
        return topological;
    }
    Map<VertexB, Integer> getPres() {
        dfs();
        return pres;
    }

    Map<VertexB, Integer> getPosts() {
        dfs();
        return posts;
    }
}
