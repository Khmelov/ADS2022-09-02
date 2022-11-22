package by.it.group151001.shlyk.lesson06;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class Lesson6Test {
    @Test
    public void A() throws Exception {
        final int[][] testSegs = new int[][]{
                {3, 4, 0, 1, 5, 3, 5},
                {8, 6, 4, 2},
                {11, 2, 10, 13, 4, 6, 12, 56}
        };
        final int[] responses = new int[] {4, 1, 5};

        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/shlyk/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        boolean result;
        for(int i = 0; i < testSegs.length; i++) {
            result = (instance.getResult(testSegs[i]).length == responses[i]);
            assertTrue("Test A failed", result);
        }
        result =(instance.getSeqSize(stream) == 3);
        assertTrue("Test A failed", result);
    }


    @Test
    public void B() throws Exception {
        final int[][] testSeqs = new int[][]{
                {2, 3, 5, 7},
                {1, 2, 3, 4, 5},
                {2, 3, 5, 4, 9},
                {2, 3, 5, 9, 9, 15, 30, 4, 8, 60}
        };
        final int[] responses = new int[] {
                1, 3, 2, 4
        };
        boolean result;
        B_LongDivComSubSeq instance=new B_LongDivComSubSeq();
        for(int i = 0; i < testSeqs.length; i++){
            result = instance.getResultSeq(testSeqs[i]).length == responses[i];
            assertTrue("Test B failed " + i, result);
        }
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/shlyk/lesson06/dataB.txt");
        result = instance.getDivSeqSize(stream) == 3;
        assertTrue("B failed", result);
    }

    private interface Generator{
        static final int NO_ERRORS = 0;
        static final int IO_ERROR = 1;
        static int RANDOM_SEED = 200;

        final int DEFAULT_DISPERSION = 100; //distance between sequence's elements
        final int DEFAULT_SEQUENCE_SIZE = 10_000;

        enum CreateType {Random, Reverse, Dispersion, Solid, Default}
        static int createFile(String fName, CreateType type){
            try {
                int MAX_LITTLE_LENGTH;
                int[] arrOutput = null;
                FileWriter fOutput = new FileWriter(fName);
                StringBuilder sbOutput = new StringBuilder();
                final int DECREASING_SCALE = 10;
                final float scale = 2.5f;
                switch(type){
                    case Random -> {
                        final int [] randomTest = {1, 2, 5, 3, 3, 5, 1, 6};
                        arrOutput = randomTest;
                        MAX_LITTLE_LENGTH = 4;

                    }
                    case Dispersion -> {
                        final int [] dispersionTest = {1, 3, -10, 5, 6, -11, 7, 8, -12};
                        arrOutput = dispersionTest;
                        MAX_LITTLE_LENGTH = 4;
                    }
                    case Reverse -> {
                        final int[] reverseTest = {10, 9, 5, 2, 1, -10};
                        arrOutput = reverseTest;
                        MAX_LITTLE_LENGTH = reverseTest.length;
                    }
                    case Solid -> {
                        final int[] solidTest = {1, 1, 1, 1, 1};
                        arrOutput = solidTest;
                        MAX_LITTLE_LENGTH = solidTest.length;
                    }
                    default -> {
                        MAX_LITTLE_LENGTH = 1;
                        arrOutput = new int[] {1, 2, 3};
                    }

                }
                sbOutput.append(arrOutput.length);
                for (int item : arrOutput) {
                    sbOutput.append(" ");
                    sbOutput.append(item);
                }
                sbOutput.append(" ");
                sbOutput.append(MAX_LITTLE_LENGTH);
                fOutput.write(sbOutput.toString());
                fOutput.close();
            }
            catch (IOException error){
                return IO_ERROR;
            }
            return NO_ERRORS;
        }

        void generate(String[] arrNames, CreateType[] arrTypes);
    }

    private int getLastInt(Scanner input){
        int lastValue = 0;
        while(input.hasNextInt()){
            lastValue = input.nextInt();
        }
        return lastValue;
    }
    @Test(timeout = 1000000000)
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        String pathVerbose = root + "by/it/group151001/shlyk/lesson06/";
        String[] arrTestFiles = new String[]{
            pathVerbose + "Random.tst",
            pathVerbose + "Distribute values.tst",
            pathVerbose + "Reversed values.tst",
            pathVerbose + "Solid values.tst"
        };
        Generator.CreateType[] arrTestTypes = new Generator.CreateType[]{
            Generator.CreateType.Random,
            Generator.CreateType.Dispersion,
            Generator.CreateType.Reverse,
            Generator.CreateType.Solid};

        Generator fGenerator = (arrNames, arrTypes) -> {
            int minLength = Math.min(arrNames.length, arrTypes.length);
            for(int i = 0; i < minLength; i++){
                Generator.createFile(arrNames[i], arrTypes[i]);
            }
        };
        fGenerator.generate(arrTestFiles, arrTestTypes);
        boolean result;
        int seqLength;
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        for(int i = 0; i < arrTestFiles.length; i++){
            instance.fileSearch(arrTestFiles[i]);
            seqLength = getLastInt(new Scanner (new FileInputStream(arrTestFiles[i]) ));
            result = seqLength == instance.getSeqLen();
            assertTrue("Test C failed by id: " + i, result);
        }
        instance.fileSearch(pathVerbose + "dataC.txt");
        result = instance.getSeqLen() == 4;
        assertTrue("Default test C failed", result);
    }

}
