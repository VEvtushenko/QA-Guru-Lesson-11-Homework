package one.nlmk.utils;

import java.util.Random;

public class RandomUtils {
    public static String getRandomPhone () {
        Random r = new Random();
        String strResult = "9";
        for (int i = 0; i < 9; i++ ) {
            int result = r.nextInt(9);
            strResult = strResult + Integer.toString(result);
        }
        return strResult;
    }

    public static String getRandomMonth (int numberOfMonth) {
        String[] listOfMonth = new String[] {
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"};
        return listOfMonth[numberOfMonth];
    }
}
