package lesson11;

public class TaskAdepth {
    static final int n = 9;
    static int[][] graph = {
            {0,1,0,0,1,0,0,0,0},
            {1,0,1,0,1,0,0,0,0},
            {0,1,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,1,1,0},
            {1,1,0,0,0,1,0,0,0},
            {0,0,1,0,1,0,0,0,1},
            {0,0,0,1,0,0,0,1,0},
            {0,0,0,1,0,0,1,0,0},
            {0,0,0,0,0,1,0,0,0}};

    static int[]visited = {0,0,0,0,0,0,0,0,0};
    static int[][] pre_post = {{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
    static int[][] tree_and_reverse = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}};

    static void DFS(int start){
        System.out.print((char)((int)'A' + start)+" ");
        visited[start]=1;
        for (int r=0; r < n; r++)
            if ((graph[start][r]!=0) && (visited[r]==0)){
                tree_and_reverse[start][r] = 1;
                DFS(r);
                pre_post[start][1] = r;
                pre_post[r][0] = start;
            }else {
                if(graph[start][r]!=0)
                    tree_and_reverse[start][r] = -1;
            }
    }

    public static void printOutput(){
        for(int i = 0; i< n; i++){
            System.out.print("\n"+(char)((int)'A'+i));
            System.out.print(": [");
            for(int j=0; j< n; j++){
                visited[j] = 0;
                for(int z = 0; z<n; z++)
                    tree_and_reverse[j][z] = 0;
            }

            DFS(i);
            System.out.println("]");
            for(int j =0; j<n;j++){
                System.out.print(((char)((int)'A'+j))+" pre:");
                if(pre_post[j][0] == -1)
                    System.out.print("None");
                else
                    System.out.print((char)((int)'A'+pre_post[j][0]));
                System.out.print(" post:");
                if(pre_post[j][1] == -1)
                    System.out.println("None");
                else
                    System.out.println((char)((int)'A'+pre_post[j][1]));
                pre_post[j][0] = -1;
                pre_post[j][1] = -1;
            }
            System.out.print("\nTree: ");
            for(int j =0; j< n; j++){
                for(int z =0;z<n;z++){
                    if(tree_and_reverse[j][z] == 1) {
                        char f = (char) ('A' + j);
                        System.out.print(f);
                        f = (char) ('A' + z);
                        System.out.print(f+" ");
                    }
                }
            }
            System.out.print("\nReverse: ");
            for(int j =0; j< n; j++){
                for(int z =j;z<n;z++) {
                    if (tree_and_reverse[j][z] == -1){
                        char f = (char) ('A' + j);
                        System.out.print(f);
                        f = (char) ('A' + z);
                        System.out.print(f+" ");
                    }
                }
            }
        }
    }

    public static int[][] return_treerev(){
        for(int i = 0; i <n; i++)
            visited[i] = 0;
        DFS(0);
        return tree_and_reverse;
    }

    public static int[][] retPrePost(){
        for(int i = 0; i <n; i++)
            visited[i] = 0;
        DFS(0);
        return pre_post;
    }

    public static void main(String[] args){
        printOutput();
        retPrePost();
    }
}

