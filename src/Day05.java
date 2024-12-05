import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day05 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("inputs/Day05Input.txt");
        ArrayList<String> partOneData = new ArrayList<>();
        ArrayList<String> partTwoData = new ArrayList<>();
        for (String line : fileData) {
            if (line.contains("|")) {
                partOneData.add(line);
            } else {
                partTwoData.add(line);
            }
        }
        partOne(partOneData, partTwoData);
        partTwo(partOneData, partTwoData);
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
        } catch (FileNotFoundException e) {
            return fileData;
        }
    }

    public static void partOne(ArrayList<String> partOne, ArrayList<String> partTwo) {
        ArrayList<String> correctUpdates = new ArrayList<>();
        ArrayList<String[]> correctUpdatesArray = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < partTwo.size(); i++) {
            boolean valid = true;
            for (int j = 0; j < partOne.size(); j++) {
                String line = partOne.get(j);
                String before = line.substring(0, line.indexOf("|"));
                String after = line.substring(line.indexOf("|") + 1);
                if (partTwo.get(i).contains(before)
                        && partTwo.get(i).contains(after)
                        && partTwo.get(i).indexOf(before) > partTwo.get(i).indexOf(after)) {
                    valid = false;
                }
            }
            if (valid) {
                correctUpdates.add(partTwo.get(i));
            }
        }
        for (String line : correctUpdates) {
            String[] arr = line.split(",");
            correctUpdatesArray.add(arr);
        }
        for (String[] str : correctUpdatesArray) {
            sum += Integer.parseInt(str[str.length / 2]);
        }
        System.out.println(sum);
    }


    public static void partTwo(ArrayList<String> partOne, ArrayList<String> partTwo) {
        ArrayList<ArrayList<String>> incorrectUpdatesArray = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < partTwo.size(); i++) {
            boolean valid = true;
            for (int j = 0; j < partOne.size(); j++) {
                String line = partOne.get(j);
                String before = line.substring(0, line.indexOf("|"));
                String after = line.substring(line.indexOf("|") + 1);
                if (partTwo.get(i).contains(before)
                        && partTwo.get(i).contains(after)
                        && partTwo.get(i).indexOf(before) > partTwo.get(i).indexOf(after)
                ) {
                    valid = false;
                }
            }
            if (!valid) {
                String[] arr = partTwo.get(i).split(",");
                incorrectUpdatesArray.add(new ArrayList<String>(Arrays.asList(arr)));
            }
        }


        for (int i = 0; i < incorrectUpdatesArray.size(); i++){
            for (int j = 0; j < partOne.size(); j++){
                String line = partOne.get(j);
                String before = line.substring(0, line.indexOf("|"));
                String after = line.substring(line.indexOf("|") + 1);
                if (incorrectUpdatesArray.get(i).contains(before)
                        && incorrectUpdatesArray.get(i).contains(after)
                        && incorrectUpdatesArray.get(i).indexOf(before) > incorrectUpdatesArray.get(i).indexOf(after)
                ) {
                    ArrayList<String> tempArray = incorrectUpdatesArray.get(i);
                    int afterIndex = tempArray.indexOf(after);
                    int beforeIndex = tempArray.indexOf(before);
                    tempArray.remove(beforeIndex);
                    if (afterIndex != 0) {
                        tempArray.add(afterIndex, before);
                    } else {
                        tempArray.addFirst(before);
                    }
                    incorrectUpdatesArray.set(i, tempArray);
                }
            }
        }

        for (int i = 0; i < incorrectUpdatesArray.size(); i++){
            for (int j = 0; j < partOne.size(); j++){
                String line = partOne.get(j);
                String before = line.substring(0, line.indexOf("|"));
                String after = line.substring(line.indexOf("|") + 1);
                if (incorrectUpdatesArray.get(i).contains(before)
                        && incorrectUpdatesArray.get(i).contains(after)
                        && incorrectUpdatesArray.get(i).indexOf(before) > incorrectUpdatesArray.get(i).indexOf(after)
                ) {
                    ArrayList<String> tempArray = incorrectUpdatesArray.get(i);
                    int afterIndex = tempArray.indexOf(after);
                    int beforeIndex = tempArray.indexOf(before);
                    tempArray.remove(beforeIndex);
                    if (afterIndex != 0) {
                        tempArray.add(afterIndex, before);
                    } else {
                        tempArray.addFirst(before);
                    }
                    incorrectUpdatesArray.set(i, tempArray);
                }
            }
        }

        for (ArrayList<String> str : incorrectUpdatesArray) {
            sum += Integer.parseInt(str.get(str.size() / 2));
        }
            System.out.println(sum);
    }
}


