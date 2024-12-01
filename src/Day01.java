import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Day01 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("inputs/Day01Input.txt");
        partOne(fileData);
        partTwo(fileData);
        partTwoAlternate(fileData);
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
        int difference = 0;
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        for (String sample : data) {
            String[] split = sample.split(" {3}");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1]));
        }
        Collections.sort(left);
        Collections.sort(right);
        for (int i = 0; i < left.size(); i++){
            difference += Math.abs(left.get(i) - right.get(i));
        }
        System.out.println(difference);
    }


    public static void partTwo(ArrayList<String> data) {
        int similarity = 0;
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        for (String sample : data) {
            String[] split = sample.split(" {3}");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1]));
        }
        Collections.sort(left);
        Collections.sort(right);
        for (int num : left) {
            if (left.contains(num)) {
                similarity += num * Collections.frequency(right, num);
            }
        }
        System.out.println(similarity);
    }

    public static void partTwoAlternate(ArrayList<String> data) {
        int similarity = 0;
        int multiplier = 0;
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        for (String sample : data) {
            String[] split = sample.split(" {3}");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1]));
        }
        Collections.sort(left);
        Collections.sort(right);
        for (int num1 : left) {
            for (int num2 : right) {
                if (num1 == num2){
                    multiplier++;
                }
            }
            similarity += num1 * multiplier;
            multiplier = 0;
        }
        System.out.println(similarity);
    }
}