package by.it.group151002.bybikov.lesson05;

public class RandomValuesMethods {

    public static class Section  {
        int begin;
        int end;

        Section(int begin, int end){
            if (begin > end) {
                begin = begin + end;
                end = begin - end;
                begin = begin - end;
            }
            this.begin = begin;
            this.end = end;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }
    }

    int getIntRandomValueInRange (int lowerBound, int upperBound) {
        if(lowerBound > upperBound) {
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        return  (int) (Math.random()*(upperBound - lowerBound + 1)) + lowerBound;
    }

    int[] getRandomEventArray(int lowerBound, int upperBound) {
        RandomValuesMethods randomValues = new RandomValuesMethods();
        int arrayLength = randomValues.getIntRandomValueInRange(500,10000);
        int[] eventsArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            eventsArray[i] = randomValues.getIntRandomValueInRange(lowerBound, upperBound);
        }
        return eventsArray;
    }

    Section[] getRandomSectionArray(int lowerBound, int upperBound) {
        RandomValuesMethods randomValues = new RandomValuesMethods();
        int arrayLength = randomValues.getIntRandomValueInRange(500,10000);
        Section[] sectionArray = new Section[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            int begin = randomValues.getIntRandomValueInRange(lowerBound, upperBound);
            int end = randomValues.getIntRandomValueInRange(lowerBound, upperBound);
            sectionArray[i] = new Section(begin, end);
        }
        return sectionArray;
    }
}