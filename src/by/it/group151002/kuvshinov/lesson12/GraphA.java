package by.it.group151002.kuvshinov.lesson12;
import java.util.*;
class Node implements Comparator<Node> {
    public int node;
    public int cost;

    public Node(){
    }

    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node n1, Node n2)
    {
        if (n1.cost > n2.cost)
            return 1;
        if (n1.cost < n2.cost)
            return -1;
        return 0;
    }
}
public class GraphA {
    public static List<List<Node>> graph = new ArrayList<>();
    public static Set<Integer> visited  = new HashSet<>();
    public static int[] dist;
    public static int vertNum;
    public static PriorityQueue<Node> pq;
    public static void dijkstra(int src){
        int i = 0;
        while (i < vertNum)
            dist[i++] = Integer.MAX_VALUE;
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
    private static void processNeighbours(int vertex){
        int edgeDist;
        int newDist;
        int i = 0;
        while (i < graph.get(vertex).size()){
            Node v = graph.get(vertex).get(i);
            if (!visited.contains(v.node)) {
                edgeDist = v.cost;
                newDist = dist[vertex] + edgeDist;
                if (newDist < dist[v.node])
                    dist[v.node] = newDist;
                pq.add(new Node(v.node, dist[v.node]));
            }
            i++;
        }
    }
    public static void main(String[] args) {
        vertNum = 8;
        dist = new int[vertNum];
        pq = new PriorityQueue<Node>(vertNum, new Node());
        int i = 0;
        while (i < vertNum){
            List<Node> item = new ArrayList<>();
            graph.add(item);
            i++;
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
        i = 0;
        while (i < dist.length) {
            System.out.println("from " + (char) (0 + 'A') + " to " + (char) (i + 'A') + " is " + dist[i]);
            i++;
        }
    }

}
