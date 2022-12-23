package by.it.group151004.eremeichik.lesson12;

import java.util.*;

public class TaskA {
    private static final Set<Integer> visitedSet = new HashSet<>();
    public static List<List<Node>> graph = new ArrayList<>();
    private static PriorityQueue<Node> nodePriorityQueue;
    private static int vertexNum;

    public static int[] dijkstraAlgorithm(int start){
        int[] distArr = new int[vertexNum];
        Arrays.fill(distArr,Integer.MAX_VALUE);
        nodePriorityQueue.add(new Node(start, 0));
        distArr[start] = 0;
        while (visitedSet.size() != vertexNum) {
            if(nodePriorityQueue.isEmpty()) {
                return distArr;
            }
            int vertex = nodePriorityQueue.remove().value;
            visitedSet.add(vertex);
            handleNeighbours(vertex,distArr);
        }
        return distArr;
    }

    private static void handleNeighbours(int vertex, int[] distArr){
        int edgeDist;
        int newDist;
        for (int i = 0; i < graph.get(vertex).size(); i++) {
            Node node = graph.get(vertex).get(i);
            if (!visitedSet.contains(node.value)) {
                edgeDist = node.cost;
                newDist = distArr[vertex] + edgeDist;
                if (newDist < distArr[node.value]) {
                    distArr[node.value] = newDist;
                }
                nodePriorityQueue.add(new Node(node.value, distArr[node.value]));
            }
        }
    }

    public static void setVertexNum(int num){
        vertexNum = num;
        nodePriorityQueue = new PriorityQueue<>(vertexNum, new Node());
        for (int i = 0; i < vertexNum; i++){
            graph.add(new ArrayList<>());
        }
    }
}
