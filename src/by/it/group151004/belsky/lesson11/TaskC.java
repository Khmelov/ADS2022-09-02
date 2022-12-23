package by.it.group151004.belsky.lesson11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class TaskC {
    public static void calculate () throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/belsky/lesson11/graphC.txt");

        Graph graph = Graph.readGraphFromFile(stream);

        visited = new HashSet<>();
        pre = new TreeMap<>();
        post = new TreeMap<>();
        clock = 1;
        topologicalSort(graph);

        getSourcesAndSinks(graph);
        linCount = getLinearisationCount(graph);
    }

    public static TreeMap<String, Integer> getPreResult() {
        return pre;
    }

    public static TreeMap<String, Integer> getPostResult() {
        return post;
    }

    private static HashSet<Vertex> visited;
    private static TreeMap<String, Integer> pre, post;
    private static int clock;

    public static void dfs(Graph graph, Vertex curVertex) {
        visited.add(curVertex);
        pre.put(curVertex.getName(), clock++);
        for (Vertex neighbour : graph.getNeighbours(curVertex.getName())) {
            if (!visited.contains(neighbour)) {
                dfs(graph, neighbour);
            }
        }
        post.put(curVertex.getName(), clock++);
        topol.add(0, curVertex);
    }

    private static ArrayList<Vertex> topol;
    public static void topologicalSort(Graph graph) {
        visited = new HashSet<>();
        topol = new ArrayList<>();

        for (String vName : graph.getVertices().keySet()) {
            Vertex v = graph.getVertex(vName);
            if (!visited.contains(v)) {
                dfs(graph, v);
            }
        }
    }

    public static ArrayList<Vertex> getSourcesResult() {
        return sources;
    }

    public static ArrayList<Vertex> getSinksResult() {
        return sinks;
    }

    private static ArrayList<Vertex> sources, sinks;
    private static void getSourcesAndSinks(Graph graph) {
        sources = new ArrayList<>();
        sinks = new ArrayList<>();

        for (String vKey : graph.getVertices().keySet()) {
            Vertex v = graph.getVertex(vKey);
            if (v.getOutEdges().size() == 0) sinks.add(v);

            boolean isSource = true;
            for (String v2Key : graph.getVertices().keySet()) {
                if (graph.getNeighbours(v2Key).contains(v)) {
                    isSource = false;
                    break;
                }
            }
            if (isSource) sources.add(v);
        }
    }

    public static int getLinearisationCount(Graph graph) {
        map = new HashMap<>();
        for (String vKey : graph.getVertices().keySet()) {
            Vertex v = graph.getVertex(vKey);
            map.put(v, 0);
        }
        linearList = new ArrayList<>();
        for (String vKey : graph.getVertices().keySet()) {
            Vertex v = graph.getVertex(vKey);
            step(v);
        }

        visited = new HashSet<>();
        linCount = 0;
        indegree = new HashMap<>();
        for (String vKey : graph.getVertices().keySet()) {
            Vertex v = graph.getVertex(vKey);
            indegree.put(v, 0);
        }

        for (String vKey : graph.getVertices().keySet()) {
            Vertex v = graph.getVertex(vKey);
            for (Vertex n : graph.getNeighbours(vKey)) {
                indegree.put(n, indegree.get(n)+1);
            }
        }
        getCount(graph);

        return linCount;
    }

    public static ArrayList<Vertex> getLinearList() {
        return linearList;
    }

    public static int getLinCount() {
        return linCount;
    }

    public static HashMap<Vertex, Integer> map;
    public static ArrayList<Vertex> linearList;
    private static void step(Vertex v) {
        if (map.get(v) == 2) return;

        if (map.get(v) == 0) {
            map.put(v, 1);
            for (Edge e : v.getOutEdges()) {
                step(e.getTo());
            }
            map.put(v, 2);
            linearList.add(0, v);
        }
    }

    //visited should be false
    public static HashMap<Vertex, Integer> indegree;
    public static int linCount;
    public static void getCount(Graph graph){
        boolean flag = false;

        for (String vName : graph.getVertices().keySet()) {
            Vertex v = graph.getVertex(vName);

            if (!visited.contains(v) && indegree.get(v) == 0) {
                visited.add(v);
                for (Vertex neighV : graph.getNeighbours(v.getName())) {
                    indegree.put(neighV, indegree.get(neighV)-1);
                }
                getCount(graph);
                visited.remove(v);
                for (Vertex neighV : graph.getNeighbours(v.getName())) {
                    indegree.put(neighV, indegree.get(neighV)+1);
                }
                flag = true;
            }
        }

        if (!flag)
            linCount++;
    }

}
