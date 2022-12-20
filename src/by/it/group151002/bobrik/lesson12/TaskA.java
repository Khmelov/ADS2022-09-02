package by.it.group151002.bobrik.lesson12;

import java.util.Comparator;
import java.util.*;

//class Node implements Comparator<Node>
//{
//    public int node;
//    public int cost;
//
//    public Node(){}
//
//    public Node(int node, int cost)
//    {
//        this.node = node;
//        this.cost = cost;
//    }
//
//    @Override
//    public int compare(Node node1, Node node2)
//    {
//        return Integer.compare(node1.cost, node2.cost);
//    }
//}
public class TaskA {
//    public Node[][] adj;
//    public PriorityQueue<Node> queue;
//    public Set<Integer> visited;
//    public int[] dist;
//    public int v;
//
//    int INF = Integer.MAX_VALUE;
//
//    void addEdge(int x, int y, int z)
//    {
//        adj[x][y].cost = z;
//    }
//
//    public void Deikstra(int src){
//
//        for (int i = 0; i < v; i++)
//            dist[i] = INF;
//        queue.add(new Node(src, 0));
//        dist[src] = 0;
//        while (visited.size() != v) {
//            if (queue.isEmpty())
//            {
//                return ;
//            }
//            else
//            {
//                int vertex = queue.remove().node;
//                visited.add(vertex);
//                watchAround(vertex);
//            }
//
//        }
//    }
//
//    private void watchAround(int vertex){
//        int edgeDist = -1;
//        int newDist = -1;
//        for (int i = 0; i < v; i++) {
//            Node v = adj[vertex][i];
//            if (!visited.contains(v.node)) {
//                edgeDist = v.cost;
//                newDist = dist[vertex] + edgeDist;
//                if (newDist < dist[v.node])
//                    dist[v.node] = newDist;
//                queue.add(new Node(v.node, dist[v.node]));
//            }
//        }
//    }
//     public static void main(String[] args)
//     {
//         int v = 8;
//         TaskA graph = new TaskA();
//         graph.v = v;
//         graph.dist = new int[v];
//         graph.queue = new PriorityQueue<Node>(v, new Node());
//         graph.visited = new HashSet<Integer>();
//         graph.adj = new Node[v][v];
//         for (int i = 0; i < v; i++)
//         {
//             for (int j = 0; j < v; j++)
//             {
//                 graph.adj[i][j] = new Node();
//             }
//         }
//         graph.addEdge(0, 1, 1);
//         graph.addEdge(0, 4, 4);
//         graph.addEdge(0, 5, 8);
//         graph.addEdge(1, 2, 2);
//         graph.addEdge(1, 5, 6);
//         graph.addEdge(1, 6, 6);
//         graph.addEdge(2, 3, 1);
//         graph.addEdge(2, 6, 2);
//         graph.addEdge(3, 6, 1);
//         graph.addEdge(3, 7, 4);
//         graph.addEdge(4, 5, 5);
//         graph.addEdge(6, 5, 1);
//         graph.addEdge(6, 7, 1);
//
//         graph.Deikstra(0);
//         System.out.println(Arrays.toString(graph.dist));
//     }

    public static int INF = Integer.MAX_VALUE;
    public static List<List<Node>> adj = new ArrayList<>();
    public static int[] dist;
    public static boolean[] visited;

    public static void dijkstra(int start) {
        dist = new int[adj.size()];
        Arrays.fill(dist, INF);
        visited = new boolean[adj.size()];

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node p1, Node p2) {
                return p1.cost - p2.cost;
            }
        });

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node p = pq.poll();
            int here = p.here;
            if (visited[here])
                continue;

            visited[here] = true;
            for (int i = 0; i < adj.get(here).size(); i++) {
                int there = adj.get(here).get(i).here;
                int cost = adj.get(here).get(i).cost;

                if (dist[there] > dist[here] + cost) {
                    dist[there] = dist[here] + cost;
                    pq.offer(new Node(there, dist[there]));
                }
            }
        }
    }

    public static class Node {
        int here;
        int cost;

        Node(int here, int cost) {
            this.here = here;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            adj.add(new ArrayList<Node>());
        }

        adj.get(0).add(new Node(4, 4));
        adj.get(0).add(new Node(5, 8));
        adj.get(0).add(new Node(1, 1));
        adj.get(1).add(new Node(5, 6));
        adj.get(1).add(new Node(6, 6));
        adj.get(1).add(new Node(2, 2));
        adj.get(2).add(new Node(6, 2));
        adj.get(2).add(new Node(3, 1));
        adj.get(3).add(new Node(6, 1));
        adj.get(3).add(new Node(7, 4));
        adj.get(6).add(new Node(5, 1));
        adj.get(6).add(new Node(7, 1));
        adj.get(4).add(new Node(5, 5));

        dijkstra(0);

        for (int i = 0; i < dist.length; i++) {
            System.out.println("Shortest path from 0 to " + i + ": " + dist[i]);
        }
    }
}
