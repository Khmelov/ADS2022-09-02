package by.it.group151002.ryabov.lesson12;
import java.util.*;
class Node implements Comparator<Node> {
    public int node;
    public int cost;

    public Node(){}

    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}
public class GraphA {
    public static List<List<Node>> graph = new ArrayList<List<Node>>();
    public static PriorityQueue<Node> pq;
    public static Set<Integer> visited  = new HashSet<Integer>();;
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
        vertexNum = 8;
        dist = new int[vertexNum];
        pq = new PriorityQueue<Node>(vertexNum, new Node());
        for (int i = 0; i < vertexNum; i++){
            List<Node> item = new ArrayList<Node>();
            graph.add(item);
        }
        graph.get(0).add(new Node(1, 1));
        graph.get(0).add(new Node(4, 4));
        graph.get(0).add(new Node(5, 8));
        graph.get(1).add(new Node(2, 2));
        graph.get(1).add(new Node(5, 6));
        graph.get(1).add(new Node(6, 6));
        graph.get(2).add(new Node(6, 2));
        graph.get(2).add(new Node(3, 1));
        graph.get(3).add(new Node(6, 1));
        graph.get(3).add(new Node(7, 4));
        graph.get(4).add(new Node(5, 5));
        graph.get(6).add(new Node(7, 1));
        dijkstra(0);
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dist.length; i++)
            System.out.println("from " + (char)(0 + 'A') + " to " + (char)(i + 'A') + " is " + dist[i]);
    }

}
