package by.it.group151001.shlyk.lesson12;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Graph {
    public final static int UNACHIEVABLE = 1000;
    public static String encodeLabel(int lblId){
        //StringBuilder result = new StringBuilder();
        //int rest;
        //lblId++; //start with zero
        return String.valueOf((char) (lblId + 'A'));
    }
    public static int decodeLabel(char strInput){
        return strInput - 'A';
    }

    public char getMinLabel(){
        return ('A');
    }
    public char getMaxLabel(){
        return (char) (graphMatrix.length + 'A' - 1);
    }
    public static class GraphItemData{
        private final int nodeLabel;
        private int pathValue;
        GraphItemData(int lblNode){
            nodeLabel = lblNode;
            pathValue = UNACHIEVABLE;
        }
    }
    public class GraphItem{

        private final GraphItemData self;
        private int edgeWeight; //to the next arc
        public GraphItem next;
        public GraphItem prev;
        boolean isDummy;

        public int getPathValue(){
            return self.pathValue;
        }
        public void setPathValue(int pathValue){
            self.pathValue = pathValue;
        }
        public int getLabel(){
            return self.nodeLabel;
        }
        GraphItem(int label){
            self = new GraphItemData(label);
            isDummy = false;
        }
        GraphItem(GraphItem origin){
            self = origin.self;
            isDummy = true;
        }
        public void add(int edgeWeight, @NotNull GraphItem next){
            next.edgeWeight = edgeWeight;
            if(this.next == null){
                this.next = next;
            }
            else{
             GraphItem saved = this.next;
             this.next = next;
             next.next = saved;
            }
        }
        public void addBehind(@NotNull GraphItem last){
            GraphItem emptyItem = new GraphItem(last);
            if(this.prev == null) {
                this.prev = emptyItem;
            }
            else{
                GraphItem saved = this.prev;
                this.prev = emptyItem;
                emptyItem.prev = saved;
            }

        }
        public void link(int pathValue, int edgeWeight){
            int probeValue = pathValue + edgeWeight;
            if(probeValue < this.getPathValue())
                this.setPathValue(probeValue);
        }

        public boolean isSuitable(int nextWeight){
            GraphItem item = this.next;
            boolean isFound = false;
            while(item != null && !isFound) {
                isFound = (nextWeight - item.edgeWeight) == self.pathValue;
                item = item.next;
            }
            return isFound;
        }
    }
    public class GraphPath implements Comparable<GraphPath> {
        ArrayList<Integer> path;
        int weight;

        GraphPath(int lblStart){
            path = new ArrayList<>();
            path.add(lblStart);
            weight = 0;
        }
        GraphPath(GraphPath originPath){
            path = new ArrayList<>();
            path.addAll(originPath.path);
            weight = originPath.weight;
        }
        //in dijkstra algo

        int getLastLbl(){
            return path.get(path.size() - 1);// the last added element
        }
        void add(int lblCurr, int edgeWeight){
            path.add(lblCurr);
            weight += edgeWeight;
        }
        void add(int index, int lblCurr, int edgeWeight){
            weight += edgeWeight;
            path.add(index, lblCurr);
        }
        void add(int lblCurr){
            path.add(lblCurr);
        }
        void setWeight(int currWeight){
            this.weight = currWeight;
        }
        GraphPath getCopy(){
            return new GraphPath(this);
        }
        void reverseOrder(){
            Integer lblSaved;
            for(int i = 0; i < path.size() / 2; i++){
                lblSaved = path.get(i);
                path.set(i, path.get(path.size() - i - 1));
                path.set(path.size() - i -1, lblSaved);
            }
        }
        void printPath(){
            StringBuilder strOutput = new StringBuilder();
            if(path.size() == 0) {
                strOutput.append("No such path");
            }
            else {

                for (Integer pathNode : path) {
                    strOutput.append(encodeLabel(pathNode)).append("->");
                }
                strOutput.delete(strOutput.length() - 2, strOutput.length());
                strOutput.append(" (").append(weight).append(")");
                System.out.println(strOutput);
            }
        }

        @Override
        public int compareTo(GraphPath o) {
            return this.weight - o.weight;
        }
    }
    //use with
    public class GraphHeader{
        GraphItem[] items;
        GraphHeader(int[][] mAdj){
            items = new GraphItem[mAdj.length];
            for(int i = 0; i < mAdj.length; i++){
                items[i] = new GraphItem(i);
            }
            for(int iX = 0; iX < mAdj.length; iX++){
                for(int iY = 0; iY < mAdj.length; iY++){
                    if(mAdj[iY][iX] == UNACHIEVABLE || mAdj[iY][iX] == 0)
                        continue;
                    items[iX].add(mAdj[iY][iX], new GraphItem(items[iY]));
                    items[iY].addBehind(items[iX]);
                }
            }
        }
        boolean isEmpty(){
            return items.length == 0;
        }
    }
    private int[][] graphMatrix;
    private int[][] floydMatrix;
    private GraphHeader linkedHeader;
    private GraphPath longestPath;
    private GraphPath shortestPath;
    private GraphPath[] allPaths;

    private int lblStart;
    private int lblFinish;

    private boolean isLinked;
    Graph(int[][] mOrigin){
        this.graphMatrix = mOrigin;
        this.linkedHeader = new GraphHeader(mOrigin);
        index = new boolean[graphMatrix.length];
        Arrays.fill(index, false);
        isLinked = false;
    }
    void setStartPoint(char strStart){
        lblStart = decodeLabel(strStart);
        lblFinish = lblStart;
    }
    void setFinishPoint(char strFinish){
        lblFinish = decodeLabel(strFinish);
        if(isLinked) {
            isLinked = false;
            linkedHeader = new GraphHeader(graphMatrix);
            updateIndex();
        }
        shortestPath = null;
        longestPath = null;
        allPaths = null;
    }
    void markIndex(int lblNum){
        index[lblNum] = true;
    }
    boolean isFreeIndex(int lblNum){
        return !index[lblNum];
    }
    void updateIndex(){
        Arrays.fill(index, false);
    }
    private void linkPath(@NotNull GraphItem itemStart){
        ArrayList<GraphItem> nextGeneration = new ArrayList<>();
        ArrayList<GraphItem> currGeneration = new ArrayList<>();
        itemStart.setPathValue(0);//we at the start
        nextGeneration.add(itemStart);
        updateIndex();
        do {
            currGeneration.addAll(nextGeneration);
            nextGeneration.clear();
            for (GraphItem item : currGeneration) {
                if(!isFreeIndex(item.getLabel()))
                    continue;
                GraphItem nextItem = item;
                GraphItem tableItem;
                while (nextItem.next != null) {
                    nextItem = nextItem.next;
                    nextItem.link(item.getPathValue(), nextItem.edgeWeight);
                    tableItem = linkedHeader.items[nextItem.getLabel()];
                    if(!nextGeneration.contains(tableItem)) {
                       if(isFreeIndex(tableItem.getLabel())){
                           //       tableItem.addBehind(item);
                           nextGeneration.add(tableItem);
                       }
                    }
                }
                markIndex(item.getLabel());
            }
            nextGeneration.sort((o1, o2) -> o1.getPathValue() - o2.getPathValue());
            currGeneration.clear();
        }while(nextGeneration.size() != 0);
        isLinked = true;
        //finally path is linked and should be found the less path
    }
    GraphPath restorePath(int lblFinish){
        GraphItem tableItem = linkedHeader.items[lblFinish], item;
        int nCurrWeight = tableItem.getPathValue();
        if(nCurrWeight == 0 || nCurrWeight == UNACHIEVABLE)
                return null;
        boolean isFound = false;
        GraphPath arrResult = new GraphPath(tableItem.getLabel());
        arrResult.setWeight(nCurrWeight);
        while(nCurrWeight != 0 && !isFound){
            item = tableItem;
            while(item.prev != null && !isFound){
                item = item.prev;
                isFound = linkedHeader.items[item.getLabel()].isSuitable(tableItem.getPathValue());
            }
            if(!isFound) {
                isFound = true; //no such path
            }
            else {
                tableItem = linkedHeader.items[item.getLabel()];
                nCurrWeight = tableItem.getPathValue();
                arrResult.add(tableItem.getLabel());
                isFound = false;
            }
        }
        arrResult.reverseOrder();
        return arrResult;
    }
    GraphPath getShortestPath(int lblStart, int lblFinish){
        if(linkedHeader.isEmpty())
            return null;
        GraphPath resultPath;
        GraphItem itemStart = linkedHeader.items[lblStart];
        if(!isLinked)
            linkPath(itemStart);
        resultPath = restorePath(lblFinish);
        return resultPath;
    }

    private void buildFloydMatrix(){
        if(floydMatrix != null)
            return;
        int[][] floydMatrix = new int[graphMatrix.length][graphMatrix.length];
        for(int iY = 0; iY < graphMatrix.length; iY++)
            System.arraycopy(graphMatrix[iY], 0, floydMatrix[iY], 0, graphMatrix.length);

        for(int k = 0; k < floydMatrix.length; k++){
            for(int iX = 0; iX < floydMatrix.length; iX++){
                for(int iY = 0; iY < floydMatrix.length; iY++){
                    long probeValue = (long) floydMatrix[k][iX] + floydMatrix[iY][k];
                    if( (long) floydMatrix[iY][iX] > probeValue)
                        floydMatrix[iY][iX] = (int) probeValue;
                }
            }
        }
        this.floydMatrix = floydMatrix;
    }
    //print graph
    //using of Linked array
    public void print(){
        if(linkedHeader == null){
            System.out.println("No any element");
            return;
        }
        StringBuilder strOutput = new StringBuilder();
        System.out.println("Info: \n Format of \"Start--Weight-->Finish\"");
        for(GraphItem item : linkedHeader.items){
            GraphItem nextItem = item;
            strOutput.setLength(0);
            System.out.println(encodeLabel(item.getLabel()) + ": ");
            while(nextItem.next != null){
                nextItem = nextItem.next;
                strOutput.append("\t" + "---(").append(nextItem.edgeWeight).append(")-->").append(encodeLabel((nextItem.getLabel()))).append("\n");
            }
            if(strOutput.length() == 0)
                strOutput.append("\tNo elements\n");
            System.out.print(strOutput);
        }
    }
    int getGraphCenter(){
        buildFloydMatrix();
        int[] arrCenter = new int[floydMatrix.length];
        for(int iX = 0; iX < floydMatrix.length; iX++){
            int maxWeight = 0;
            for(int iY = 0; iY < floydMatrix.length; iY++){
                if(floydMatrix[iY][iX] > maxWeight && floydMatrix[iY][iX] < UNACHIEVABLE)
                    maxWeight = floydMatrix[iY][iX];
            }
            arrCenter[iX] = maxWeight;
        }
        int minWeight = arrCenter[0];
        for(int min : arrCenter){
            if(min < minWeight && min != 0)
                minWeight = min;
        }
        return minWeight;
    }
    boolean index[];

    GraphPath[] getAllPaths(){
        if(allPaths == null)
            allPaths = getAllPaths(lblStart, lblFinish);
        return allPaths;
    }
    GraphPath getShortestPath(){
        if(shortestPath == null)
            shortestPath = getShortestPath(lblStart, lblFinish);
        return shortestPath;
    }
    GraphPath getLongestPath(){
        if(longestPath == null)
        {
         GraphPath[] allPaths = getAllPaths();
         GraphPath maxPath = null;
         if(allPaths.length != 0)
            maxPath = allPaths[0];
         for (int i = 1; i < allPaths.length; i++) {
             if(maxPath.weight < allPaths[i].weight)
                 maxPath = allPaths[i];
         }
         longestPath = maxPath;
        }
        return longestPath;
    }
    GraphPath[] getAllPaths(int lblStart, int lblFinish){
        if(graphMatrix.length == 0)
            return null;
        //buildMatrix();
        ArrayList<GraphPath> arrResult = new ArrayList<>();
        ArrayList<GraphPath> currGeneration = new ArrayList<>();
        ArrayList<GraphPath> nextGeneration = new ArrayList<>();
        nextGeneration.add(new GraphPath(lblStart));
        updateIndex();
        do{
            currGeneration.addAll(nextGeneration);
            nextGeneration.clear();
            for(GraphPath path : currGeneration){
                for(int iY = 0; iY < graphMatrix.length; iY++){
                    int edgeWeight = graphMatrix[iY][path.getLastLbl()];
                    if(edgeWeight != 0 && edgeWeight != UNACHIEVABLE && isFreeIndex(iY)){
                        GraphPath nextPath = path.getCopy();
                        nextPath.add(iY, edgeWeight);
                        if(iY == lblFinish)
                            arrResult.add(nextPath);
                        else
                            nextGeneration.add(nextPath);
                    }
                }
                markIndex(path.getLastLbl());
            }
            nextGeneration.sort((o1, o2) -> o1.weight - o2.weight);
            currGeneration.clear();
        }while(nextGeneration.size() != 0);
        return arrResult.toArray(new GraphPath[0]);
    }
}
