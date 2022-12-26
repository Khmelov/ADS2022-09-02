package by.it.group151001.shlyk.lesson08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    enum ParserType {Bag, Stair}
    BufferedReader fReader;
    int[] arrInput;
    int bagCapacity;
    final static int BAG_STAGE = 1;
    final static int STAIR_STAGE = 2;
    final static int ARRAY_STAGE = 3;
    Parser(String fName, ParserType type) throws IOException {
        fReader = new BufferedReader(new FileReader(fName));
        int stage;
        if (type == ParserType.Bag)
            stage = BAG_STAGE;
        else
            stage = STAIR_STAGE;
        String strInput;
        while((strInput = fReader.readLine()) != null){
            String[] parsable = strInput.split(" ");
            int iParsable = 0;
            switch(stage){
                case BAG_STAGE :{
                    bagCapacity = Integer.parseInt(parsable[iParsable]);
                    iParsable++;
                }
                case STAIR_STAGE : {
                    arrInput = new int[Integer.parseInt(parsable[iParsable])];
                    stage = ARRAY_STAGE;
                    break;
                }

                case ARRAY_STAGE : {
                    for(int i = iParsable; i < arrInput.length; i++)
                        arrInput[i] = Integer.parseInt(parsable[i]);
                    break;
                }
            }
        }
        fReader.close();
    }
    public int[] getArray(){
        return arrInput.clone();
    }
    public int getBagCapacity(){
        return bagCapacity;
    }
}
