package by.it.group151002.bybikov.lesson05;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class FileMethods {

    private void setDateInputFile (String outputPath, RandomValuesMethods.Section[] sectionArray, int[] pointArray) throws IOException {
        FileWriter writer = new FileWriter(outputPath);
        writer.write(sectionArray.length + " ");
        writer.write(pointArray.length + "\n");
        for (int i = 0; i < sectionArray.length; i++) {
            writer.write(sectionArray[i].begin + " ");
            writer.write(sectionArray[i].end + "\n");
        }
        for (int i = 0; i < pointArray.length; i++) {
            writer.write( pointArray[i] + " ");
        }
        writer.close();
    }

    void createInputFile (String outputPath) throws IOException {
        RandomValuesMethods randomValuesMethods = new RandomValuesMethods();
        RandomValuesMethods.Section[] sectionArray = randomValuesMethods.getRandomSectionArray(0, 10000);
        int[] eventArray = randomValuesMethods.getRandomEventArray(0, 100000);
        setDateInputFile(outputPath, sectionArray, eventArray);
    }

}
