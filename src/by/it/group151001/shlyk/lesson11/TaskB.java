package by.it.group151001.shlyk.lesson11;

import java.util.Arrays;
import java.util.Stack;

public class TaskB {

    private class Node{
        Node next;
        int key;
        Node(int key){
            this.key = key;
        }
    }

    int[][] matrix;
    int[] arrNest;
    boolean[] arrVisited;


    int nRest;
    void insideTravel(int iX){
        nRest--;
        for(int iY = (iX + 1); iY < matrix.length; iY++){
            if(matrix[iY][iX] != 0){
                arrNest[iY]++;
                if(!arrVisited[iY])
                    insideTravel(iY);
            }
        }
        arrVisited[iX] = true;
    }
    void startBuilding(){
        arrNest = new int[matrix.length];
        arrVisited = new boolean[matrix.length];
        Arrays.fill(arrVisited, false);
        nRest = arrVisited.length;
        while(nRest != 0){
            for(int i = 0; i < matrix.length; i++){
                insideTravel(i);
                if(nRest == 0)
                    break;
            }
        }
    }
    Node topologySort(){
        Node head = null;
        Node item = null;
        Stack<Integer> follow = new Stack<>();
        Arrays.fill(arrVisited, false);
        int nRest = arrNest.length;
        for(int i = 0; i < arrNest.length; i++){
            if(arrNest[i] == 0 && !arrVisited[i]){
                follow.push(i);
            }
        }
        while(!follow.isEmpty()){
            int iX = follow.pop();
            for(int iY = 0; iY < matrix.length; iY++){
                if(!arrVisited[iY] && matrix[iY][iX] != 0){
                    arrNest[iY] -= 1;
                    if(arrNest[iY] == 0){
                        arrVisited[iY] = true;
                        follow.push(iY);
                    }
                }
            }
            if(head == null) {
                head = new Node(iX);
                item = head;
            }
            else {
                item.next = new Node(iX);
                item = item.next;
            }

        }
        return head;
    }
    TaskB() {
        matrix = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
        };
        startBuilding();
        Node node = topologySort();
        StringBuilder strOut = new StringBuilder();
        while(node != null){
            strOut.append((char) (node. key + 'A') ).append(" -> ");
            node = node.next;
        }
        strOut.setLength(strOut.length() - 4);
        System.out.println(strOut.toString());
    }

}
