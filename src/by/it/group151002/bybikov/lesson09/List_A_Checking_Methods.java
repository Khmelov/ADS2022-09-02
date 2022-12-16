package by.it.group151002.bybikov.lesson09;

import java.util.ArrayList;
import java.util.List;

public class List_A_Checking_Methods {

    boolean add_Methods_Checking() {
        RandomValuesMethods randomValuesMethods = new RandomValuesMethods();
        int test_Size = randomValuesMethods.getIntRandomValueInRange(0, 100);
        while (test_Size-- >= 0){
            List_A<Integer> custom_List = new List_A<>();
            List<Integer> java_List = new ArrayList<>();
            for (int i = 0; i < test_Size; i++) {
                int add_Value = randomValuesMethods.getIntRandomValueInRange(-100, 100);
                custom_List.add(add_Value);
                java_List.add(add_Value);
            }

            if (!custom_List.toString().equals(java_List.toString())){
                System.err.println("Simple add mismatch");
                return false;
            }
                for (int i = 0; i < test_Size; i++) {
                    int add_Value = randomValuesMethods.getIntRandomValueInRange(-100, 100);
                    int add_index = randomValuesMethods.getIntRandomValueInRange(-100, 200);
                    try {
                        custom_List.add(add_index, add_Value);
                        java_List.add(add_index, add_Value);
                    }catch (Exception e){

                    }
                }

            if (!custom_List.toString().equals(java_List.toString())){
                System.err.println("Index && value add mismatch");
                return false;
            }
        }
        return true;
    }

}
