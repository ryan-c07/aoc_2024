import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("inputs/Day03Input.txt");
        partOne(fileData);
        partTwo(fileData);
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
    public static void partOne(ArrayList<String> data) {
        int total = 0;
        for (String sample : data) {
            Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
            Matcher matcher = pattern.matcher(sample);
            boolean matchFound = matcher.find();
            while (matchFound){
                System.out.println(matcher.group());
                total += Integer.parseInt(matcher.group().substring(matcher.group().indexOf("(") + 1, matcher.group().indexOf(","))) * Integer.parseInt(matcher.group().substring(matcher.group().indexOf(",") + 1, matcher.group().indexOf(")")));
                matchFound = matcher.find();
            }
            System.out.println(total);
        }
    }


    public static void partTwo(ArrayList<String> data) {
        int total = 0;
        boolean enabled = true;
        for (String sample : data) {
            Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
            String dupe = sample;
            Matcher matcher = pattern.matcher(sample);
            boolean matchFound = matcher.find();
            while (matchFound){
                if (sample.substring(0, matcher.start()).contains("do()")){
                    enabled = true;
                    sample = "*".repeat(sample.substring(0, matcher.start()).length()) + sample.substring(matcher.start());
                }
                if (sample.substring(0, matcher.start()).contains("don't()")){
                    enabled = false;
                    sample = "*".repeat(sample.substring(0, matcher.start()).length()) + sample.substring(matcher.start());
                }
                if (enabled) {
                    total += Integer.parseInt(matcher.group().substring(matcher.group().indexOf("(") + 1, matcher.group().indexOf(","))) * Integer.parseInt(matcher.group().substring(matcher.group().indexOf(",") + 1, matcher.group().indexOf(")")));
                    System.out.println(matcher.group());
                }
                matchFound = matcher.find();
            }
            System.out.println(total);
        }
    }

}