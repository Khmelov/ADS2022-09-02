package lesson11;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TaskABtest11lesson {
    int[][] tree_exp = {
            {0,1,0,0,-1,0,0,0,0},
            {-1,0,1,0,-1,0,0,0,0},
            {0,-1,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {-1,-1,0,0,0,-1,0,0,0},
            {0,0,-1,0,1,0,0,0,1},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,-1,0,0,0}
    };
    int[][] pp_exp = {{-1,1},{0,2},{1,5},{-1,-1},{5,-1},{2,8},{-1,-1},{-1,-1},{5,-1}};
    int[] st_exp = {1,0,2,4,3,5,7,6};

    @Test
    public void testAlevel() throws Exception{
        TaskAdepth inst = new TaskAdepth();
        int[][] tree_act = inst.return_treerev();
        int[][] pp_act = inst.retPrePost();
        assertEquals("\ntree and reverse test failed",
                tree_exp,
                tree_act);
        assertEquals("\npre&post test failed",
                pp_exp,
                pp_act);
        System.out.println("\ntest succeed");
    }

    @Test
    public void testBlevel() throws Exception{
        TaskBDFS_Stack inst = new TaskBDFS_Stack();
        int[] st_act = inst.retStack();
        for(int i = 0; i<8;i++)
            assertEquals("\ntest failed: element"+i,
                    st_exp[i],
                    st_act[i]);
        System.out.println("test succeed");
    }
}
