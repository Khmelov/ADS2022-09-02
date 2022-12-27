package lesson11;

public class TaskBDFS_Stack {
    static final int n = 8;
    static final int check_value = 28;
    static final int[][]graph={
            {0,0,1,0,0,0,0,0},
            {0,0,1,0,0,0,0,0},
            {0,0,0,1,1,0,0,0},
            {0,0,0,0,0,1,0,0},
            {0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,1,1},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}
    };
    static int[] stack_arr = {-1,-1,-1,-1,-1,-1,-1,-1};
    static int[] visited = {0,0,0,0,0,0,0,0};
    static int st_p = 7;

    static void DFS(int start){
        visited[start]=1;
        for (int r=0; r < n; r++)
            if ((graph[start][r]!=0) && (visited[r]==0)){
                DFS(r);
            }
        stack_arr[st_p] = start;
        st_p--;
    }

    static void print_sorted_graph(){
        System.out.print("New graph matrix:\n   A B C D E F G H");
        for(int i =0; i <n;i++){
            System.out.print("\n"+(char)('A'+stack_arr[i])+": ");
            for(int j =0; j<n;j++)
                System.out.print(graph[stack_arr[i]][j]+" ");
        }

    }

    static void find_last(){
        int res = 0;
        for(int i = 7; i > 1; i--)
            res = res + stack_arr[i];
        stack_arr[0] = 28 - res;
    }

    public static int[] retStack(){
        for(int i =0; i<n;i++)
            visited[i] = 0;
        DFS(0);
        find_last();
        return stack_arr;
    }

    public static void main(String[] args){
        DFS(0);
        find_last();
        print_sorted_graph();
    }
}
