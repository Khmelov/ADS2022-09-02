package by.it.group151002.bybikov.lesson07;

public class RandomValuesMethods {

    private int getRandomIntValueInRange (int lowerBound, int upperBound) {
        if(lowerBound > upperBound) {
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        return  (int) (Math.random()*(upperBound - lowerBound + 1)) + lowerBound;
    }

    String getRandomString () {
        int resultStringLength = getRandomIntValueInRange(0, 10);
        StringBuilder resultStringBuilder = new StringBuilder();
        for (int i = 0; i < resultStringLength; i++) {
            char currentChar = (char) getRandomIntValueInRange('a', 'z');
            resultStringBuilder.append(currentChar);
        }
        return resultStringBuilder.toString();
    }

    boolean isCorrectStringFormat (String str) {
        if (str == null)
            return true;
        boolean isCorrect = true;
        boolean isContains_a = false;
        boolean isContains_z = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < 'a' || str.charAt(i) > 'z') {
                isCorrect = false;
            }
            else if (str.charAt(i) == 'a') {
                isContains_a = true;
            }
            else if (str.charAt(i) == 'z') {
                isContains_z = true;
            }
        }
        isCorrect = isCorrect && isContains_a && isContains_z;
        return isCorrect;
    }

}

