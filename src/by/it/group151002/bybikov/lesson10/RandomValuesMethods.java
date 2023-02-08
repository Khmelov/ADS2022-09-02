package by.it.group151002.bybikov.lesson10;

public class RandomValuesMethods {

    int getIntRandomValueInRange (int lowerBound, int upperBound) {
        if(lowerBound > upperBound) {
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        return  (int) (Math.random()*(upperBound - lowerBound + 1)) + lowerBound;
    }
}
