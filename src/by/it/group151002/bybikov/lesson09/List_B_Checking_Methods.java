package by.it.group151002.bybikov.lesson09;

import java.util.ArrayList;
import java.util.List;

public class List_B_Checking_Methods {

    boolean add_Methods_Checking() {
        Object custom = null;
        Object java = null;
        RandomValuesMethods randomValuesMethods = new RandomValuesMethods();
        int test_Size = randomValuesMethods.getIntRandomValueInRange(10, 20);
        while (test_Size-- >= 0){
            List_B<Double> custom_List = new List_B<>();
            List<Double> java_List = new ArrayList<>();
            for (int i = 0; i < test_Size * 10; i++) {
                Double value = null;
                int is_Null = randomValuesMethods.getIntRandomValueInRange(0, 1);
                if (is_Null == 0) {
                    value = randomValuesMethods.getIntRandomValueInRange(-1000, 1000) + 0.1;
                }
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
                        custom = null;
                        java = null;
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
                    case 5:
                        if (custom_List.contains(value) != java_List.contains(value)) {
                            System.err.println("Contains mismatch");
                            return false;
                        }
                        break;
                    case 6:
//                        if (!custom_List.toArray().toString().equals(java_List.toArray().toString())) {
//                            System.err.println("To array mismatch");
//                            return false;
//                        }
                        break;
                    case 7:
                        if(custom_List.remove(value) != (java_List.remove(value))) {
                            System.err.println("Remove object mismatch");
                            return false;
                        }
                        break;
                    case 8:
                        custom = null;
                        java = null;
                        try {
                            custom = custom_List.set(index, value);
                        }catch (Exception e){

                        }
                        try {
                            java = java_List.set(index, value);
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
                    case 9:
                        if (custom_List.indexOf(value) != java_List.indexOf(value)){
                            System.err.println("IndexOf mismatch");
                            return false;
                        }
                        break;
                    case 10:
                        if (custom_List.lastIndexOf(value) != java_List.lastIndexOf(value)){
                            System.err.println("LastIndexOf mismatch");
                            return false;
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
