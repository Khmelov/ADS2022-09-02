package by.it.group151002.ravodin.lesson12;

import java.util.*;

public class GraphC {
    public static int res;

    public static Set<Integer> visitedVerts = new HashSet<Integer>();
    public static int[] distances;
    public static int vertexNum;
    public static List<List<Node>> graph = new ArrayList<List<Node>>();
    public static PriorityQueue<Node> priorityQueue;
    public static void dijkstra(int source){
        for (int i = 0; i < vertexNum; i++)
            distances[i] = Integer.MAX_VALUE;
        priorityQueue.add(new Node(source, 0));
        distances[source] = 0;
        while (visitedVerts.size() != vertexNum) {
            if(priorityQueue.isEmpty())
                return ;
            int vertex = priorityQueue.remove().node;
            visitedVerts.add(vertex);
            processNeighbours(vertex);
        }
    }
    private static void processNeighbours(int vert){
        int currentDistance = -1;
        int newDistance = -1;
        for (int i = 0; i < graph.get(vert).size(); i++) {
            Node vertex = graph.get(vert).get(i);
            if (!visitedVerts.contains(vertex.node)) {
                currentDistance = vertex.value;
                newDistance = distances[vert] + currentDistance;
                if (newDistance < distances[vertex.node])
                    distances[vertex.node] = newDistance;
                priorityQueue.add(new Node(vertex.node, distances[vertex.node]));
            }
        }
    }
    public static void main(String[] args) {
        vertexNum = 10;
        distances = new int[vertexNum];
        priorityQueue = new PriorityQueue<Node>(vertexNum, new Node());
        for (int i = 0; i < vertexNum; i++){
            List<Node> item = new ArrayList<Node>();
            graph.add(item);
        }
        graph.get(0).add(new Node(1, 4));
        graph.get(0).add(new Node(2, 2));
        graph.get(1).add(new Node(6, 2));
        graph.get(1).add(new Node(7, 4));
        graph.get(2).add(new Node(3, 2));
        graph.get(2).add(new Node(5, 1));
        graph.get(4).add(new Node(5, 2));
        graph.get(4).add(new Node(7, 3));
        graph.get(5).add(new Node(3, 3));
        graph.get(6).add(new Node(8, 1));
        graph.get(7).add(new Node(6, 1));
        graph.get(8).add(new Node(7, 1));
        graph.get(9).add(new Node(0, 7));
        graph.get(9).add(new Node(2, 6));
        graph.get(9).add(new Node(4, 6));
        graph.get(9).add(new Node(5, 5));
        int u = 7;
        int v = 6;
        dijkstra(v);
        res = distances[u];
        for (int i = 0; i < graph.get(u).size(); i++)
            if (graph.get(u).get(i).node == v)
                res += graph.get(u).get(i).value;
        if (distances[u] != Integer.MAX_VALUE)
            System.out.print("Smallest length of the cycle, that includes an edge " + (char)(u + 'A') + " -> " + (char)(v + 'A') + ": " + res);
    }
}