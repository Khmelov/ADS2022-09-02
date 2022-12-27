package by.it.group151001.shlyk.lesson12;

import java.util.Scanner;

public class TaskC {

    Graph graph;
    int[][] matrix;

    char lblFinish;
    char lblStart;
    int savedWeight;
    void printGraph(){
        if(matrix == null) {
            System.out.println("Graph is empty");
            return;
        }
        for(int iX = 0; iX < matrix.length; iX++){
            System.out.println("Node " + (char) (iX + 'A') + " :");
            for(int iY = 0; iY < matrix.length; iY++){
                if(iX != iY && matrix[iY][iX] != Graph.UNACHIEVABLE){
                    System.out.println("\t -> " + (char) (iY + 'A') + " (" + matrix[iY][iX] +')');
                }
            }
        }
    }


    boolean checkEdge(char lblStart, char lblFinish){
        if(lblStart == lblFinish)
            return false;
        return matrix[lblFinish - 'A'][lblStart - 'A'] != Graph.UNACHIEVABLE;
    }
    int swapCeil(int iX, int iY, int value){
        int old = matrix[iY][iX];
        matrix[iY][iX] = value;
        return old;
    }
    boolean setEdge(){
        printGraph();
        System.out.println("Input edge for cycle searching (format: \"<lbl>-><lbl>)\"");
        Scanner input = new Scanner(System.in);
        String strInput = input.next();
        String[] expr = strInput.split("->");
        char[] arrLabels = {0, 0};
        if(expr.length > 2){
            System.out.println("Incorrect params");
            return false;
        }
        for(int i = 0; i < expr.length; i++){
            arrLabels[i] = (expr[i].trim()).charAt(0);
        }
        if(!checkEdge(arrLabels[0], arrLabels[1])){
            System.out.println("Edge not exists");
            return false;
        }
        lblStart = arrLabels[0];
        lblFinish = arrLabels[1];
        savedWeight = swapCeil(lblStart - 'A', lblFinish - 'A', Graph.UNACHIEVABLE);
        return true;
    }
    TaskC(){
        matrix = new int[][]{
                {0, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 3, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 1},
                {2, 0, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 1, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE},
                {Graph.UNACHIEVABLE, 4, 0, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE},
                {Graph.UNACHIEVABLE, 1, Graph.UNACHIEVABLE, 0, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE},
                {Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 2, Graph.UNACHIEVABLE, 0, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE},
                {Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 3, 0, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE},
                {Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 1, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 0, Graph.UNACHIEVABLE},
                {Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 1, 0}
        };
        boolean hasParams = false;
        while(!hasParams)
            hasParams = setEdge();
        graph = new Graph(matrix);
        graph.setStartPoint(lblFinish);
        graph.setFinishPoint(lblStart);
        Graph.GraphPath path = graph.getShortestPath();
        path.add(0, lblStart-'A', savedWeight);
        path.printPath();
    }
}
