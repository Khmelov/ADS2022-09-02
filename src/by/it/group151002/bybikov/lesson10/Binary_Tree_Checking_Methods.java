package by.it.group151002.bybikov.lesson10;

import by.it.group151002.bybikov.lesson09.List_A;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Binary_Tree_Checking_Methods {

    boolean methods_Checking() {
        RandomValuesMethods randomValuesMethods = new RandomValuesMethods();
        int test_Size = randomValuesMethods.getIntRandomValueInRange(100, 2000);
        for (int i = 0; i < test_Size; i++){
            TaskA<Integer> custom_Set = new TaskA<>();
            TreeSet<Integer> java_Set = new TreeSet<>();
            for (int j = 0; j < test_Size; j++) {
                int is_Null = randomValuesMethods.getIntRandomValueInRange(0, 1);
                Integer value;
                if (is_Null == 1){
                    value = null;
                }
                else {
                    value = randomValuesMethods.getIntRandomValueInRange(-1000, 1000);
                }
                int choice = randomValuesMethods.getIntRandomValueInRange(1, 6);
                switch (choice){
                    case 1:
                        boolean custom_Contains = false;
                        boolean java_Contains = false;
                        try {
                            custom_Contains = custom_Set.contains(value);
                        }catch (Exception e){
                        }
                        try {
                            java_Contains = java_Set.contains(value);
                        }catch (Exception e){

                        }
                        if (custom_Contains != java_Contains){
                            System.err.println(value);
                            System.err.println("Contains mismatch");
                            return false;
                        }
                        break;
                    case 2:
                        try {
                            custom_Set.remove(value);
                        }catch (Exception e){

                        }
                        try {
                            java_Set.remove(value);
                        }catch (Exception e){

                        }
                        break;
                    case 3:
                        if (!java_Set.toString().equals(custom_Set.toString())){
                            return false;
                        }
                        break;
                    default:
                        try {
                            custom_Set.add(value);
                        }catch (Exception e){

                        }
                        try {
                            java_Set.add(value);
                        }catch (Exception e){

                        }
                        break;
                }
            }
        }
        return true;
    }

}
