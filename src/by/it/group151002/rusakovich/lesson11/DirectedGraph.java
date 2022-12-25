package by.it.group151002.rusakovich.lesson11;

import by.it.group151002.rusakovich.lesson09.ListB;

import java.util.Arrays;
import java.util.Collections;

public class DirectedGraph<T> {
    private int nodeAmount;
    private class Node{
        int key;
        T value;
        public Node(int key, T val){
            this.key = key;
            this.value = val;
        }
    }
    private class IncNode{
        int key;
        int wayLen;
        public IncNode(int key, int wayLen){
            this.key = key;
            this.wayLen = wayLen;
        }
    }
    ListB<ListB<IncNode>> listInc;
    ListB<Node> nodeList;
    public DirectedGraph(int amount){
        nodeAmount = amount;
        listInc = new ListB<>(nodeAmount);
        nodeList = new ListB<>(nodeAmount);
        for(int i = 0; i < nodeAmount; i++){
            listInc.add(new ListB<>());
        }
    }

    public T indexToValue(int i){
        return nodeList.get(i).value;
    }
    public void addNode(int key, T val){
        Node newNode = new Node(key, val);
        nodeList.add(newNode);
    }
    public void addIncident(int key, int incKey, int wayLen){
        IncNode newInc = new IncNode(incKey, wayLen);
        listInc.get(key).add(newInc);
    }

    public void addTwoWayIncident(int key, int incKey, int firstWayLen, int secondWayLen){
        IncNode newIncF = new IncNode(incKey, firstWayLen);
        listInc.get(key).add(newIncF);
        IncNode newIncS = new IncNode(key, secondWayLen);
        listInc.get(incKey).add(newIncS);
    }

    public void clear(){
        listInc = null;
        nodeList = null;
        nodeAmount = 0;
    }

    private boolean[] visited = null;
    private int[] pre = null;
    private int[] post = null;
    private ListB<Integer> topologicalOrder = null;


    public int[] getPre(){return pre;}
    public int[] getPost(){return post;}

    private T[] indexToValue(ListB<Integer> indexes){
        Object[] res;
        res = new Object[indexes.size()];
        for(int i = 0; i < indexes.size(); i++){
            res[i] = nodeList.get(indexes.get(i)).value;
        }
        return (T[])res;
    }
    public T[] getTopologicalOrder(int startIndex){
        this.DFS(startIndex);
        Collections.reverse(topologicalOrder);
        return indexToValue(topologicalOrder);
    }

    int clock = 1;
    private void explore(int key, StringBuilder str){
        visited[key] = true;
        pre[key] = clock;
        clock++;
        str.append(nodeList.get(key).value).append(" ");
        if(!listInc.get(key).isEmpty()) {
            for(int index = 0; index < listInc.get(key).size(); ++index){
                if(!visited[listInc.get(key).get(index).key])
                    explore(listInc.get(key).get(index).key, str);
            }
        }
        post[key] = clock;
        clock++;
        topologicalOrder.add(key);
    }

    public T[] findSources(){
        boolean[] inc = new boolean[nodeAmount];
        ListB<Integer> temp = new ListB<>();
        for(int i = 0; i < listInc.size(); ++i){
            for(int j = 0; j < listInc.get(i).size(); ++j)
                if(!inc[listInc.get(i).get(j).key])
                    inc[listInc.get(i).get(j).key] = true;
        }

        for(int i = 0; i < inc.length; i++)
            if(!inc[i])
                temp.add(i);
        return indexToValue(temp);
    }

    public T[] findSinks(){
        ListB<Integer> temp = new ListB<>();
        for(int i = 0;i< listInc.size(); ++i)
            if(listInc.get(i).isEmpty())
                temp.add(i);
        return indexToValue(temp);
    }

    private int hasFree(boolean[] visited){
        for(int i = 0; i < visited.length; i++)
            if(!visited[i])
                return i;
        return -1;
    }
    public String DFS(int startIndex){
        visited = new boolean[nodeAmount];
        pre = new int[nodeAmount];
        post = new int[nodeAmount];
        topologicalOrder = new ListB<>(nodeAmount);
        StringBuilder str = new StringBuilder();
        int i = startIndex;
        do{
            if(!visited[i])
                explore(i, str);
        }while((i = hasFree(visited)) != -1);
        return str.toString();
    }

    public int getNumberOfLinerisation(){
        ListB<T[]> linerisationsList = new ListB<>();
        for(int i = 0; i < nodeAmount;i++)
            linerisationsList.add(getTopologicalOrder(i));
        boolean[] un = new boolean[linerisationsList.size()];
        for(int i = 0; i < linerisationsList.size(); i++)
            for(int j = 0; j < linerisationsList.size();j++)
                if(!un[j] && i != j){
                    un[i] = Arrays.equals(linerisationsList.get(i), linerisationsList.get(j));
                    if(un[i])
                        break;
                }
        int counter = 1;
        for(int i = 0; i < un.length; i++)
            if(!un[i]) {
                counter++;
            }
        return counter > 1 ? counter - 1 : counter;
    }

    private boolean isDDone(int[] p){
        for(int i = 0; i < p.length; i++)
            if(p[i] == -1)
                return false;
        return true;
    }

    private int findNextNode(int[][] res, int[] p, int index){
        ListB<Integer> temp = new ListB<>();
        for(int i = 0; i < p.length; i++)
            if(p[i] == -1)
                temp.add(i);
        int min = temp.get(0);
        for(int i = 1; i < temp.size(); ++i){
            if(res[index][min] > res[index][temp.get(i)] && res[index][temp.get(i)] != -1)
                min = temp.get(i);
        }
        p[min] = res[index][min];
        return min;
    }
    public int[][] Deikstra(int firstNode){
        int[][] res = new int[nodeAmount][nodeAmount];
        int[] p = new int[nodeAmount];
        Arrays.fill(p, -1);
        for(int i = 0; i < nodeAmount; i++)
            Arrays.fill(res[i], -1);
        int i = 0, curr = firstNode;
        res[i][firstNode] = 0;
        p[firstNode] = 0;
        while(!isDDone(p)){
            if(i > 0)
                System.arraycopy(res[i - 1], 0, res[i], 0, nodeAmount);
            for(int j = 0; j < listInc.get(curr).size();j++)
                if(i == 0)
                    res[i][listInc.get(curr).get(j).key] = listInc.get(curr).get(j).wayLen;
                else
                    res[i][listInc.get(curr).get(j).key] = res[i-1][curr] + listInc.get(curr).get(j).wayLen;
            if(i > 0){
                for(int j = 0; j < nodeAmount; j++){
                    if(res[i][j] > res[i-1][j] && res[i-1][j] != -1)
                        res[i][j] = res[i-1][j];
                }
            }
            curr = findNextNode(res, p, i);
            i++;
        }
        System.arraycopy(res[i - 1], 0, res[i], 0, nodeAmount);
        return res;
    }

    public int[] BellmanFord(int firstNode){
        int[] dist = new int[nodeAmount];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[firstNode] = 0;

        for(int i = 1; i < nodeAmount; i++) {
            for(int z = 0; z < nodeAmount; z++)
                for (int j = 0; j < listInc.get(z).size(); j++){
                    int v = listInc.get(z).get(j).key;
                    int weight = listInc.get(z).get(j).wayLen;
                    if(dist[z] != Integer.MAX_VALUE && dist[z] + weight < dist[v])
                        dist[v] = dist[z] + weight;
                }
        }

        for(int i = 0; i < nodeAmount; i++)
            for(int j = 0; j < listInc.get(i).size(); j++){
                int v = listInc.get(i).get(j).key;
                int weight = listInc.get(i).get(j).wayLen;
                if(dist[i] != Integer.MAX_VALUE && dist[i] + weight < dist[v]) {
                    System.out.println("Graph contains negative weight cycle.");
                    return null;
                }
            }

        return dist;
    }

    //here key and incKey -> edge
    //graph must be filled correctly
    public int findLenOfMinCycle(int key, int incKey){
        int weight = 0;
        for(int i = 0;i < listInc.get(key).size();i++)
            if(listInc.get(key).get(i).key == incKey)
                weight = listInc.get(key).get(i).wayLen;
        int[] dist = new int[nodeAmount];
        boolean[] visited = new boolean[nodeAmount];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[incKey] = 0;
        while(true){
            int minDistI = -1, minDist = Integer.MAX_VALUE;
            for(int i = 0; i < nodeAmount; i++){
                if(!visited[i] && dist[i] < minDist){
                    minDistI = i;
                    minDist = dist[i];
                }
            }
            if(minDistI == -1)
                break;
            for(int i = 0; i < listInc.get(minDistI).size(); i++){
                if(listInc.get(minDistI).get(i).wayLen + dist[minDistI] < dist[i])
                    dist[i] = dist[minDistI] + listInc.get(minDistI).get(i).wayLen;
            }
            visited[minDistI] = true;
        }
        return dist[key] + weight;
    }

}
