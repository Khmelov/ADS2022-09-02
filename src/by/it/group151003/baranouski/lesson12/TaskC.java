package by.it.group151003.baranouski.lesson12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TaskC {
    public static List<List<Edge>> m_graph = new ArrayList<>();
    public static int[] m_destination;
    public static boolean[] m_visited;
    public static final int m_node = 2;
    public static final int m_u = 1;

    public static class Edge {
        public int m_here;
        public int m_cost;

        Edge (int dest, int cost) {
            this.m_here = dest;
            this.m_cost = cost;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++)
            m_graph.add(new ArrayList<>());

        m_graph.get(0).add(new Edge(1, 1));//A -> B
        m_graph.get(0).add(new Edge(4, 4));//A -> E
        m_graph.get(0).add(new Edge(5, 8));//A -> F

        m_graph.get(1).add(new Edge(5, 6));//B -> F
        m_graph.get(1).add(new Edge(2, 2));//B -> C

        m_graph.get(2).add(new Edge(6, 2));//C -> G
        m_graph.get(2).add(new Edge(3, 1));//C -> D

        m_graph.get(3).add(new Edge(6, 1));//D -> G
        m_graph.get(3).add(new Edge(7, 4));//D -> H

        m_graph.get(4).add(new Edge(5, 5));//E -> F

        m_graph.get(6).add(new Edge(1, 6));//G -> B
        m_graph.get(6).add(new Edge(5, 1));//G -> F
        m_graph.get(6).add(new Edge(7, 1));//G -> H

        getPath(m_node);
        System.out.print("Length of the smallest cycle between "
                + (char)(m_u + 'A') + " and " + (char)(m_node + 'A') + " = " + findSmallestCycle());
    }

    public static void getPath(int start) {
        m_destination = new int[m_graph.size()];
        for (int i = 0; i < m_graph.size(); i++)
            m_destination[i] = 100;
        m_visited = new boolean[m_graph.size()];
        m_destination[start] = 0;

        PriorityQueue<Edge> H = new PriorityQueue<>(Comparator.comparingInt(p -> p.m_cost));
        H.offer(new Edge(start, 0));

        while (!H.isEmpty()) {
            Edge edge = H.poll();
            int curr = edge.m_here;
            if (m_visited[curr])
                continue;

            m_visited[curr] = true;
            for (int i = 0; i < m_graph.get(curr).size(); i++) {
                int next = m_graph.get(curr).get(i).m_here;
                int cost = m_graph.get(curr).get(i).m_cost;

                if (m_destination[next] > m_destination[curr] + cost) {
                    m_destination[next] = m_destination[curr] + cost;
                    H.offer(new Edge(next, m_destination[next]));
                }
            }
        }
    }
    public static int findSmallestCycle() {
        int cycle = 0;
        for (int i = 0; i < m_graph.get(m_u).size(); i++)
            if (m_node == m_graph.get(m_u).get(i).m_here)
                cycle = m_graph.get(m_u).get(i).m_cost;
        return cycle + m_destination[m_u];
    }
}
