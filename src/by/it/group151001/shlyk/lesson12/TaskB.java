package by.it.group151001.shlyk.lesson12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TaskB {
    final static int VERY_LONG_PATH = 1_000_000;
    final static int NODE_CNT   = 10;
    int[][] matrix;
    int[] distance;
    public class GraphNode{
        int iStart;
        int iEnd;
        int weight;
        GraphNode(int iStart, int iEnd, int weight){
            this.iStart = iStart;
            this.iEnd = iEnd;
            this.weight = weight;
        }
    }
    ArrayList<GraphNode> graph;
    int lblToIndex(char label){
        return label - 'A';
    }
    char getStartLabel(char maxLabel){
        boolean isCorrect = false;
        char lblStart = 'A';
        Scanner input = new Scanner(System.in);
        while (!isCorrect) {
            System.out.println("Input start label:");
            String strInput = input.next();
            lblStart = strInput.charAt(0);
            if(lblStart > maxLabel)
                System.out.println("This label not exists");
            isCorrect = lblStart <= maxLabel;
        }
        return lblStart;
    }
    TaskB(){
        distance = new int[NODE_CNT];
        Arrays.fill(distance, VERY_LONG_PATH);
        int iStart = lblToIndex(getStartLabel('J'));
        distance[iStart] = 0;

        graph = new ArrayList<>();
        graph.add(new GraphNode(
            lblToIndex('A'), lblToIndex('C'), -2
        ));
        graph.add(new GraphNode(
                lblToIndex('A'), lblToIndex('B'), 4
        ));
        graph.add(new GraphNode(
                lblToIndex('B'), lblToIndex('G'), -2
        ));
        graph.add(new GraphNode(
                lblToIndex('B'), lblToIndex('H'), -4
        ));
        graph.add(new GraphNode(
                lblToIndex('C'), lblToIndex('D'), 2
        ));
        graph.add(new GraphNode(
                lblToIndex('C'), lblToIndex('F'), 1
        ));//D is skipped
        graph.add(new GraphNode(
                lblToIndex('E'), lblToIndex('F'), -2
        ));
        graph.add(new GraphNode(
                lblToIndex('E'), lblToIndex('H'), 3
        ));
        graph.add(new GraphNode(
                lblToIndex('F'), lblToIndex('D'), 3
        ));
        graph.add(new GraphNode(
                lblToIndex('G'), lblToIndex('I'), -1
        ));
        graph.add(new GraphNode(
                lblToIndex('H'), lblToIndex('G'), 1
        ));
        graph.add(new GraphNode(
                lblToIndex('I'), lblToIndex('H'), 1
        ));

        graph.add(new GraphNode(
                lblToIndex('J'), lblToIndex('A'), 7
        ));
        graph.add(new GraphNode(
                lblToIndex('J'), lblToIndex('C'), 6
        ));

        graph.add(new GraphNode(
                lblToIndex('J'), lblToIndex('F'), 5
        ));
        graph.add(new GraphNode(
                lblToIndex('J'), lblToIndex('E'), 6
        ));

        for(int i = 0; i < NODE_CNT - 1; i++){
            for (GraphNode graphNode : graph) {
                if (distance[graphNode.iStart] < VERY_LONG_PATH)
                    distance[graphNode.iEnd] = Math.min(distance[graphNode.iEnd], distance[graphNode.iStart] + graphNode.weight);
            }
        }

        for(int i = 0; i < NODE_CNT - 1; i++){
            for (GraphNode graphNode : graph) {
                if (distance[graphNode.iStart] < VERY_LONG_PATH && (distance[graphNode.iEnd] > Math.min(distance[graphNode.iEnd], distance[graphNode.iStart] + graphNode.weight)) ){
                    System.out.println("Graph contains negative cycle");
                    return;
                }
            }
        }
        System.out.println("The graphs contains follow minimal distances to:");
        StringBuilder strTemp = new StringBuilder();
        for(int i = 0; i < distance.length; i++){
            strTemp.setLength(0);
            strTemp.append(distance[i]);
            String pathWeight = (distance[i] == VERY_LONG_PATH) ? "Unachievable" : strTemp.toString();
            System.out.println("\t*" + (char) (i + 'A') + " is " + pathWeight);
        }
    }

}
