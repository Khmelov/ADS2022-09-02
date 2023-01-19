package by.it.group151002.saprin.lesson12;

public class TaskB {
    public static class Graph {

        class Edge {
            int src, dest, weight;

            Edge() {
                src = dest = weight = 0;
            }
        }

        int V, E;
        Edge[] edge;

        Graph(int v, int e) {
            V = v;
            E = e;
            edge = new Edge[e];
            for (int i = 0; i < e; ++i)
                edge[i] = new Edge();
        }

        void BellmanFord(Graph graph) {
            int V = graph.V, E = graph.E;
            int[] dist = new int[V];

            for (int i = 0; i < V; ++i)
                dist[i] = Integer.MAX_VALUE;
            dist[0] = 0;

            for (int i = 1; i < V; ++i) {
                for (int j = 0; j < E; ++j) {
                    int u = graph.edge[j].src;
                    int v = graph.edge[j].dest;
                    int weight = graph.edge[j].weight;
                    if (dist[u] != Integer.MAX_VALUE
                            && dist[u] + weight < dist[v])
                        dist[v] = dist[u] + weight;
                }
            }

            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE
                        && dist[u] + weight < dist[v]) {
                    System.out.println(
                            "Graph contains negative weight cycle");
                    return;
                }
            }
            printArr(dist, V);
        }

        void printArr(int[] dist, int V) {
            System.out.println("Vertex Distance from Source");
            for (int i = 0; i < V; ++i)
                System.out.println((char)((int) 'A' + i) + "\t\t" + dist[i]);
        }
    }
    public static void main(String[] args) {
        Graph graph = new Graph(10, 16);
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 4;

        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = -2;

        graph.edge[2].src = 1;
        graph.edge[2].dest = 6;
        graph.edge[2].weight = -2;

        graph.edge[3].src = 1;
        graph.edge[3].dest = 7;
        graph.edge[3].weight = -4;

        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 2;

        graph.edge[5].src = 2;
        graph.edge[5].dest = 5;
        graph.edge[5].weight = 1;

        graph.edge[6].src = 4;
        graph.edge[6].dest = 5;
        graph.edge[6].weight = -2;

        graph.edge[7].src = 4;
        graph.edge[7].dest = 7;
        graph.edge[7].weight = 3;

        graph.edge[8].src = 5;
        graph.edge[8].dest = 3;
        graph.edge[8].weight = 3;

        graph.edge[9].src = 6;
        graph.edge[9].dest = 8;
        graph.edge[9].weight = -1;

        graph.edge[10].src = 8;
        graph.edge[10].dest = 7;
        graph.edge[10].weight = 1;

        graph.edge[11].src = 9;
        graph.edge[11].dest = 0;
        graph.edge[11].weight = 7;

        graph.edge[12].src = 9;
        graph.edge[12].dest = 2;
        graph.edge[12].weight = 6;

        graph.edge[13].src = 9;
        graph.edge[13].dest = 5;
        graph.edge[13].weight = 5;

        graph.edge[14].src = 9;
        graph.edge[14].dest = 4;
        graph.edge[14].weight = 6;

        graph.BellmanFord(graph);
    }

}
