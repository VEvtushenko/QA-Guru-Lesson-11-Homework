package demo.qa.data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;


public class TestData {


    public static Faker faker = new Faker(new Locale("EN", "IND"));

    public static SimpleDateFormat dateFormater = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH);
    public static String stringBirthDate = dateFormater.format(faker.date().birthday(18, 70));
    public static String[] birthDate = stringBirthDate.split(" ");
    public static String firstName = faker.name().firstName();
    public static String lastName = faker.name().lastName();
    public static String email = faker.internet().emailAddress();
    public static String mobileNumber =  faker.number().digits(10);
    public static String address = faker.address().fullAddress();

    public static String[] hobbies = new String[] {"Sports", "Reading", "Music"};
    public static String[] genders = new String[] {"Male", "Female", "Other"};
    public static String[] subjects = new String[] {"Economics", "Arts", "Computer Science", "English", "Maths", "Hindi"};

    public static String[] states = new String[] {"NCR", "Uttar Pradesh", "Haryana", "Rajastan"};
    public static String[] cities = new String[] {"Delhi", "Gurgaon", "Nodia",
                                                  "Agra", "Lucknow", "Merrut",
                                                  "Karnal", "Panipat",
                                                  "Jaipur", "Jaiselmer"};
    public static Random random = new Random();

     public static String randomHobbie() {
        return TestData.hobbies[random.nextInt(3)];
    }

    public static String randomGender() {
        return TestData.genders[random.nextInt(3)];
    }

    public static String randomSubject() {
        return TestData.subjects[random.nextInt(6)];
    }

}
