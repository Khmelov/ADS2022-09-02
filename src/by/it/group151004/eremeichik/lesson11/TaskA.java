package by.it.group151004.eremeichik.lesson11;

public class TaskA {
    public static int[] postArr;
    public static int clock;
    public static boolean[] visitedVertices;
    public static int[] preArr;

    public static StringBuilder deepFirstSearch(Graph graph) {
        StringBuilder builder = new StringBuilder();
        visitedVertices = new boolean[graph.getVertexCount()];
        preArr = new int[graph.getVertexCount()];
        postArr = new int[graph.getVertexCount()];
        clock = 0;
        for (int i = 0; i < graph.getVertexCount(); i++)
            if (!visitedVertices[i]) {
                search(graph, i, builder);
                builder.append("\n");
            }
        return builder;
    }

    public static void search(Graph graph, int vertex,StringBuilder builder) {
        visitedVertices[vertex] = true;
        preArr[vertex] = clock++;
        for (int i : graph.getNeighbors(vertex)) {
            if (!visitedVertices[i]) {
                builder.append(String.format("%s - %s (tree) ",graph.getVertexNameByIndex(vertex),graph.getVertexNameByIndex(i)));
                search(graph, i,builder);
            } else if (preArr[vertex] > preArr[i] && postArr[vertex] <= postArr[i])
                builder.append(String.format("%s - %s (back) ",graph.getVertexNameByIndex(vertex),graph.getVertexNameByIndex(i)));
        }
        postArr[vertex] = clock++;
    }
}
