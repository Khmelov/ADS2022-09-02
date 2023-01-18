package by.it.group151002.ravodin.lesson12;

import java.util.*;
public class GraphA {
    public static int[] distances;
    public static int vertexNum;
    public static List<List<Node>> graph = new ArrayList<List<Node>>();
    public static PriorityQueue<Node> priorityQueue;
    public static Set<Integer> visitedVerts = new HashSet<Integer>();;
    public static void dijkstra(int src){
        for (int i = 0; i < vertexNum; i++)
            distances[i] = Integer.MAX_VALUE;
        priorityQueue.add(new Node(src, 0));
        distances[src] = 0;
        int num = 2;
        while (visitedVerts.size() != vertexNum) {
            if(priorityQueue.isEmpty())
                return ;
            int vertex = priorityQueue.remove().node;
            num += 2;
            visitedVerts.add(vertex);
            processNeighbours(vertex);
        }
    }
    private static void processNeighbours(int vertex){
        int eDist = -1;
        int nextDist = -1;
        for (int i = 0; i < graph.get(vertex).size(); i++) {
            Node v = graph.get(vertex).get(i);
            if (!visitedVerts.contains(v.node)) {
                eDist = v.value;
                nextDist = distances[vertex] + eDist;
                if (nextDist < distances[v.node])
                    distances[v.node] = nextDist;
                priorityQueue.add(new Node(v.node, distances[v.node]));
            }
        }
    }
    public static void main(String[] args) {
        vertexNum = 8;
        distances = new int[vertexNum];
        priorityQueue = new PriorityQueue<Node>(vertexNum, new Node());
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
        for (int i = 0; i < distances.length; i++)
            System.out.println("from " + (char)(0 + 'A') + " to " + (char)(i + 'A') + " is " + distances[i]);
    }

}