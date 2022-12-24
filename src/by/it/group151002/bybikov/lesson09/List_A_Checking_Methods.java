package by.it.group151002.bybikov.lesson09;

import java.util.ArrayList;
import java.util.List;

public class List_A_Checking_Methods {

    boolean add_Methods_Checking() {
        RandomValuesMethods randomValuesMethods = new RandomValuesMethods();
        int test_Size = randomValuesMethods.getIntRandomValueInRange(10, 100);
        while (test_Size-- >= 0){
            List_A<Integer> custom_List = new List_A<>();
            List<Integer> java_List = new ArrayList<>();
            for (int i = 0; i < test_Size * 10; i++) {
                int value = randomValuesMethods.getIntRandomValueInRange(-1000, 1000);
                int index = randomValuesMethods.getIntRandomValueInRange(-10, custom_List.size() + 100);
                int choice = randomValuesMethods.getIntRandomValueInRange(1, 6);
                switch (choice){
                    case 1:
                        try {
                            custom_List.add(value);
                        }catch (Exception e){

                        }
                        try {
                            java_List.add(value);
                        }catch (Exception e){

                        }
                        break;
                    case 2:
                        try {
                            custom_List.add(index, value);
                        }catch (Exception e){

                        }
                        try {
                            java_List.add(index, value);
                        }catch (Exception e){

                        }
                        break;
                    case 3:
                        try {
                            custom_List.remove(index);
                        }catch (Exception e){

                        }
                        try {
                            java_List.remove(index);
                        }catch (Exception e){

                        }
                        break;
                    case 4:
                        Object custom = null;
                        Object java = null;
                        try {
                            custom = custom_List.get(index);
                        }catch (Exception e){

                        }
                        try {
                            java = java_List.get(index);
                        }catch (Exception e){

                        }

                        try {
                            if (!custom.equals(java)) {
                                System.err.println("Get mismatch");
                                return false;
                            }
                        }catch (Exception e){

                        }
                        break;
                    default:
                        if (custom_List.size() != java_List.size()){
                            System.err.println("Size mismatch");
                            return false;
                        }
                        if (!custom_List.toString().equals(java_List.toString())){
                            System.err.println("To string compare mismatch");
                            return false;
                        }
                        break;
                }
            }
        }
        return true;
    }

}
