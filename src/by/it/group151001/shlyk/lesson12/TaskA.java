package by.it.group151001.shlyk.lesson12;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TaskA {
    Graph solution;
    Graph.GraphPath path;
    int[][] matrix;

    char[] getLabels(char lblLimit){
        Scanner input = new Scanner(System.in);
        boolean bothCorrect = false;
        char[] arrLabels = {0, 0};//on stack)))
        System.out.println("Info: \n\t Format of \"<lbl>-><lbl> \"");
        while(!bothCorrect){
            System.out.println("Input labels");
            String strInput = input.nextLine();
            String[] params = strInput.split("->");
            if(params.length == 2){
                arrLabels[0] = params[0].charAt(0);
                arrLabels[1] = params[1].charAt(0);
                bothCorrect = (arrLabels[0] <= lblLimit && arrLabels[1] <= lblLimit)
                               && (arrLabels[0] != arrLabels[1]);
            }
        }
        return arrLabels;
    }
    TaskA(){
        matrix = new int[][]{
                {0, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE},
                {1, 0, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE},
                {Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 0, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE},
                {Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 1, 0, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE},
                {4, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 0, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE},
                {8, 6, 0, 0, 5, 0, 1, 0},
                {Graph.UNACHIEVABLE, 6, 2, 1, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 0, Graph.UNACHIEVABLE},
                {Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 4, Graph.UNACHIEVABLE, Graph.UNACHIEVABLE, 1, 0},

        };
        solution = new Graph(matrix);
        solution.print();

        char[] arrLabels = getLabels('H');
        solution.setStartPoint(arrLabels[0]);
        solution.setFinishPoint(arrLabels[1]);
        path = solution.getShortestPath();
        if(path == null)
            System.out.println("No such path");
        else
            path.printPath();
    }

}
