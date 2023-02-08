package by.it.group151002.kuvshinov.lesson12;

import java.util.*;

public class GraphC {
    public static int res;
    public static PriorityQueue<Node> pq;
    public static int[] dist;
    public static List<List<Node>> graph = new ArrayList<>();
    public static Set<Integer> visited = new HashSet<>();
    public static int vertNum;

    private static void processNeighbours(int vert){
        int edgeDist;
        int newDist;
        int i = 0;
        while (i < graph.get(vert).size()){
            Node v = graph.get(vert).get(i);
            if (!visited.contains(v.node)) {
                edgeDist = v.cost;
                newDist = dist[vert] + edgeDist;
                if (newDist < dist[v.node])
                    dist[v.node] = newDist;
                pq.add(new Node(v.node, dist[v.node]));
            }
            i++;
        }
    }

    public static void task(int src){
        int i = 0;
        while (i < vertNum) {
            dist[i] = Integer.MAX_VALUE;
            i++;
        }
        pq.add(new Node(src, 0));
        dist[src] = 0;
        while (visited.size() != vertNum) {
            if(pq.isEmpty())
                return ;
            int vertex = pq.remove().node;
            visited.add(vertex);
            processNeighbours(vertex);
        }
    }

    public static void main(String[] args) {
        vertNum = 10;
        dist = new int[vertNum];
        pq = new PriorityQueue<>(vertNum, new Node());
        int i = 0;
        while (i < vertNum){
            List<Node> item = new ArrayList<Node>();
            graph.add(item);
            i++;
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
        task(v);
        res = dist[u];
        i = 0;
        while (i < graph.get(u).size()) {
            if (graph.get(u).get(i).node == v)
                res += graph.get(u).get(i).cost;
            i++;
        }
        if (dist[u] != Integer.MAX_VALUE)
            System.out.print("Smallest length of the cycle, that includes an edge " + (char)(u + 'A') + " -> " + (char)(v + 'A') + ": " + res);
    }
}
