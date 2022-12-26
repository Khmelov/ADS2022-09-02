package by.it.group151002.krumkachev.lesson12;


import java.util.*;

public class GraphC {
    public static int res;
    public static List<List<Node>> graph = new ArrayList<List<Node>>();
    public static PriorityQueue<Node> pq;
    public static Set<Integer> visited = new HashSet<Integer>();
    public static int[] dist;
    public static int vertexNum;
    public static void dijkstra(int src){
        for (int i = 0; i < vertexNum; i++)
            dist[i] = Integer.MAX_VALUE;
        pq.add(new Node(src, 0));
        dist[src] = 0;
        while (visited.size() != vertexNum) {
            if(pq.isEmpty())
                return ;
            int vertex = pq.remove().node;
            visited.add(vertex);
            processNeighbours(vertex);
        }
    }
    private static void processNeighbours(int vertex){
        int edgeDist = -1;
        int newDist = -1;
        for (int i = 0; i < graph.get(vertex).size(); i++) {
            Node v = graph.get(vertex).get(i);
            if (!visited.contains(v.node)) {
                edgeDist = v.cost;
                newDist = dist[vertex] + edgeDist;
                if (newDist < dist[v.node])
                    dist[v.node] = newDist;
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }
    public static void main(String[] args) {
        vertexNum = 10;
        dist = new int[vertexNum];
        pq = new PriorityQueue<Node>(vertexNum, new Node());
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
        res = dist[u];
        for (int i = 0; i < graph.get(u).size(); i++)
            if (graph.get(u).get(i).node == v)
                res += graph.get(u).get(i).cost;
        if (dist[u] != Integer.MAX_VALUE)
            System.out.print("Smallest length of the cycle that includes an edge " + (char)(u + 'A') + " -> " + (char)(v + 'A') + ": " + res);
    }
}