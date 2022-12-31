package by.it.group151004.belsky.lesson11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class TaskA {

    private static HashSet<UndirVertex> visitedSet = new HashSet<>();
    private static HashSet<UndirEdge> reverseEdges = new HashSet<>();

    public static void calculate () throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/belsky/lesson11/graphA.txt");
        UndirGraph graph = UndirGraph.readGraphFromFile(stream);

        TreeMap<String, UndirVertex> vertices = graph.getVertices();

        ArrayList<String> vertexKeys = new ArrayList<>();
        for (UndirVertex vertex : vertices.values()) {
            vertexKeys.add(vertex.getName());
        }


        visited = new HashSet<>();
        pre = new TreeMap<>();
        post = new TreeMap<>();

        for (String vKey : graph.getVertices().navigableKeySet()) {
            dfs(graph, graph.getVertex(vKey));
        }

        visited = new HashSet<>();
        reverse = new ArrayList<>();
        for (String vKey : graph.getVertices().navigableKeySet()) {
            dfsGetReverse(graph.getVertex(vKey), null);
        }

        for (String vKey : pre.navigableKeySet()) {
            System.out.println(vKey + "\t" + pre.get(vKey) + "\t" + post.get(vKey));
        }

    }

    public static TreeMap<String, Integer> getPreResult() {
        return pre;
    }

    public static TreeMap<String, Integer> getPostResult() {
        return post;
    }

    private static HashSet<UndirVertex> visited;
    private static TreeMap<String, Integer> pre, post;
    private static int clock = 1;
    public static void dfs(UndirGraph graph, UndirVertex curVertex) {
        visited.add(curVertex);
        if (!pre.containsKey(curVertex.getName())) {
            pre.put(curVertex.getName(), clock++);
        }

        TreeSet<UndirVertex> neighbours = new TreeSet<>();
        for (UndirEdge e : curVertex.getOutEdges()) {
            UndirVertex neighbour = getNeighbourVertex(curVertex, e);
            neighbours.add(neighbour);
        }

        for (UndirVertex neighbour : neighbours) {
            if (!visited.contains(neighbour)) {
                dfs(graph, neighbour);
            }
        }

        if (!post.containsKey(curVertex.getName())) {
            post.put(curVertex.getName(), clock++);
        }
    }

    private static ArrayList<String[]> reverse;
    public static ArrayList<String[]> getReverseResult() {
        return reverse;
    }
    private static void dfsGetReverse(UndirVertex curVertex, UndirVertex parent) {
        if (visited.contains(curVertex)) return;
        visited.add(curVertex);
        TreeSet<UndirVertex> neighbours = new TreeSet<>();
        for (UndirEdge e : curVertex.getOutEdges()) {
            UndirVertex neighbour = getNeighbourVertex(curVertex, e);
            neighbours.add(neighbour);
        }
        for (UndirVertex neigbour : neighbours) {
            if (!visited.contains(neigbour)) {
                dfsGetReverse(neigbour, curVertex);
            } else {
                if (pre.get(neigbour.getName()) < pre.get(curVertex.getName()) && post.get(neigbour.getName()) > post.get(curVertex.getName())) {
                    if (neigbour != parent) {
                        reverse.add(new String[] { curVertex.getName(), neigbour.getName() });
                    }
                }
            }
        }
    }

    private static UndirVertex getNeighbourVertex (UndirVertex curVertex, UndirEdge curEdge) {
        for (UndirVertex someVertex : curEdge.getVertices()) {
            if (someVertex != curVertex) return someVertex;
        }
        return null;
    }
}
