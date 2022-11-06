package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFinder {

    public String text;

    public RegexFinder(String text) {
        this.text = text;
    }
// It can be while loop instead of if
    public Optional<String> findEmail() {
        String regex = "[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+";
        Pattern pattern =Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find()?Optional.of(matcher.group()):Optional.empty();
    }

    public Optional<String> findName() {
        String keyword1 = " i am";
        String keyword2 = "my name is";
        String keyword3 = "my name’s";
        List<String> keywords = new ArrayList<>(Arrays.asList(keyword1,keyword2,keyword3));
        String name=null;
        String text1 = text.toLowerCase();

        for(String keyword :keywords ) {
            int i = text1.indexOf(keyword);

            if (i > -1) {
                int startName = i + keyword.length() + 1;
                int endName = text1.indexOf(" ", i + keyword.length() + 2);
                int startSurname = endName + 1;
                int endSurname = text1.indexOf(" ", startSurname);
                name = text.substring(startName, endName);
                //return Optional.of(name);
            }
        }
            return Optional.of(name);
    }

    public Optional<String> findSurname(){
        String keyword1 = " i am ";
        String keyword2 = "my name is";
        String keyword3 = "my name’s";
        List<String> keywords = new ArrayList<>(Arrays.asList(keyword1,keyword2,keyword3));

        String text1 = text.toLowerCase();

        for(String keyword :keywords ) {
            int i = text1.indexOf(keyword);
            if (i > -1) {
                int startName = i + keyword.length() + 1;
                int endName = text1.indexOf(" ", i + keyword.length() + 2);
                int startSurname = endName + 1;
                int endSurname = text1.indexOf(" ", startSurname);
               return Optional.of(text.substring(startSurname, endSurname ));
            }
        }
        return Optional.empty();
    }

    public Optional<String> findIdentityNo() {
        String regex = "[1-9]\\d{9}[02468]";
        Pattern pattern =Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find()?Optional.of(matcher.group()):Optional.empty();

    }

    public Optional<LocalDate> findBirthDate() {
        String regex = "\\d{1,2}/\\d{1,2}/\\d{4}";
        Pattern pattern =Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find()?Optional.of(LocalDate.parse(matcher.group(), DateTimeFormatter.ofPattern("d/M/yyyy"))):Optional.empty();
    }

    public Optional<String> findPhoneNumber() {
        String regex = "(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})+";
        Pattern pattern =Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find()?Optional.of(matcher.group()):findHouseNumber();
    }

    private Optional<String> findHouseNumber() {
        String regex = "/^(0)([2348]{1})([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$/";
        Pattern pattern =Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find()?Optional.of(matcher.group()):Optional.empty();
    }
}
