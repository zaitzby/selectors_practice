package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void fullFillForm() {
        String name = "fox";
        String lastName = "mulder";
        String email = "fmulder@test.dot";
        String gender = "Male";
        String mobile = "4455566660";
        String yearOfBirth = "1961";
        String monthOfBirth = "October";
        String dateOfBirth = "13";
        String datePickerLocator = String.format("%s %sth, %s", monthOfBirth, dateOfBirth, yearOfBirth);
        String subject = "Arts";
        String subject2 = "Biology";
        String hobby = "Reading";
        String fileName = "fox.PNG";
        String address = "Arlington, 14-42";
        String state = "Rajasthan";
        String city = "Jaipur";

        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $("[aria-label$='" + datePickerLocator + "']").click();
        $("#subjectsInput").setValue("ar").pressEnter();
        $("#subjectsInput").setValue("bi").pressEnter();
        $("[for='hobbies-checkbox-2']").click();
        $("#uploadPicture").uploadFromClasspath(fileName);
        $("#currentAddress").setValue(address);
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

        $("#submit").click();

        $(".table-responsive").shouldHave(
                text("Student Name " + name + " " + lastName),
                text("Student Email " + email),
                text("Gender " + gender),
                text("Mobile " + mobile),
                text("Date of Birth " + dateOfBirth + " " + monthOfBirth + "," + yearOfBirth),
                text("Subjects " + subject + ", " + subject2),
                text("Hobbies " + hobby),
                text("Picture " + fileName),
                text("Address " + address),
                text("State and City " + state + " " + city)
        );
    }
}
