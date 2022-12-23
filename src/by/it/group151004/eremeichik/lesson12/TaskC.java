package by.it.group151004.eremeichik.lesson12;

import java.util.*;

public class TaskC {
    public static List<List<Node>> graph;
    public static PriorityQueue<Node> nodePriorityQueue;
    public static Set<Integer> visitedArr;
    public static int[] distArr;
    public static int vertexNum;

    public static void dijkstraArr(int source){
        for (int i = 0; i < vertexNum; i++) {
            distArr[i] = Integer.MAX_VALUE;
        }
        nodePriorityQueue.add(new Node(source, 0));
        distArr[source] = 0;
        while (visitedArr.size() != vertexNum && !nodePriorityQueue.isEmpty()) {
            int vertex = nodePriorityQueue.remove().value;
            visitedArr.add(vertex);
            handleNeighbours(vertex);
        }
    }
    private static void handleNeighbours(int vertex){
        int edgeDist;
        int newDist ;
        for (int i = 0; i < graph.get(vertex).size(); i++) {
            Node v = graph.get(vertex).get(i);
            if (!visitedArr.contains(v.value)) {
                edgeDist = v.cost;
                newDist = distArr[vertex] + edgeDist;
                if (newDist < distArr[v.value])
                    distArr[v.value] = newDist;
                nodePriorityQueue.add(new Node(v.value, distArr[v.value]));
            }
        }
    }

    public static void setVertexNum(int num){
        vertexNum = num;
        distArr = new int[vertexNum];
        nodePriorityQueue = new PriorityQueue<>(vertexNum, new Node());
        visitedArr = new HashSet<>();
        graph = new ArrayList<>();
        for (int i = 0; i < vertexNum; i++){
            graph.add(new ArrayList<>());
        }
    }
}
