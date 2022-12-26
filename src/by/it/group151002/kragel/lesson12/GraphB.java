package by.it.group151002.kragel.lesson12;

import java.util.*;

public class GraphB {
    private List<List<Integer>> adj;
    public final static int INF = 65536;
    GraphB() {
        adj = new ArrayList<>();
    }
    GraphB(Integer count) {
        adj = new ArrayList<>();
        for(int i = 0; i < count; i++)
            AddVertex();
    }
    void AddVertex(){
        adj.add(new ArrayList<>());
        for (int i = 0; i < adj.size(); i++)
            for(int j = adj.get(i).size(); j <= adj.size(); j++)
                adj.get(i).add(INF);
    }
    void addOrientEdge(Integer v1, Integer v2, Integer weight) {
        if (v1 < adj.size() && v2 < adj.size())
            adj.get(v1).set(v2, weight);
    }

    int[] bellmanFord(Integer start){
        int[] dist = new int[adj.size()];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        for (int i = 0; i < dist.length - 1; i++) {
            for (int j = 0; j < dist.length; j++) {
                for (int k = 0; k < dist.length; k++) {
                    if (adj.get(j).get(k) != INF && dist[k] > dist[j] + adj.get(j).get(k)) {
                        dist[k] = dist[j] + adj.get(j).get(k);
                    }
                }
            }
        }
        return dist;
    }
}
