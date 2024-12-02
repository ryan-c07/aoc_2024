import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;

public class Day02 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("inputs/Day02Input.txt");
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
        int safeNumbers = 0;
        for (String sample : data) {
            String[] line = sample.split(" ");
            boolean safe = true;
            boolean isIncreasing = Integer.parseInt(line[0]) - Integer.parseInt(line[1]) > 0;

            for (int i = 0; i < line.length - 1; i++){
                if (Integer.parseInt(line[i]) - Integer.parseInt(line[i+1]) < 0){
                    if (isIncreasing){
                        safe = false;
                    }
                }
                if (Integer.parseInt(line[i]) - Integer.parseInt(line[i+1]) > 0){
                    if (!isIncreasing){
                        safe = false;
                    }
                }
                if (Integer.parseInt(line[i]) - Integer.parseInt(line[i+1]) == 0){
                    safe = false;
                }
                if (Math.abs(Integer.parseInt(line[i]) - Integer.parseInt(line[i+1])) > 3 ){
                    safe = false;
                }
            }
            if (safe){
                safeNumbers++;
            }
        }
        System.out.println(safeNumbers);
    }


    public static void partTwo(ArrayList<String> data) {
        int safeNumbers = 0;
        for (String sample : data) {
            String[] line = sample.split(" ");
            ArrayList<Integer> numbers = new ArrayList<>();
            for (String num : line) {
                numbers.add(Integer.parseInt(num));
            }

            boolean safe = true;
            boolean isIncreasing = Integer.parseInt(line[0]) - Integer.parseInt(line[1]) > 0;

            for (int i = 0; i < line.length - 1; i++){
                if (Integer.parseInt(line[i]) - Integer.parseInt(line[i+1]) < 0){
                    if (isIncreasing){
                        safe = false;
                    }
                }
                if (Integer.parseInt(line[i]) - Integer.parseInt(line[i+1]) > 0){
                    if (!isIncreasing){
                        safe = false;
                    }
                }
                if (Integer.parseInt(line[i]) - Integer.parseInt(line[i+1]) == 0){
                    safe = false;
                }
                if (Math.abs(Integer.parseInt(line[i]) - Integer.parseInt(line[i+1])) > 3 ){
                    safe = false;
                }
            }


            if (!safe) {
                for (int i = 0; i < numbers.size(); i++) {
                    ArrayList<Integer> modified = new ArrayList<>(numbers);
                    modified.remove(i);

                    safe = true;
                    isIncreasing = modified.get(1) > modified.get(0);

                    for (int j = 0; j < modified.size() - 1; j++) {
                        if (modified.get(j + 1) - modified.get(j) > 0) {
                            if (!isIncreasing) {
                                safe = false;
                                break;
                            }
                        } else if (modified.get(j + 1) - modified.get(j) < 0) {
                            if (isIncreasing) {
                                safe = false;
                                break;
                            }
                        }
                        if (modified.get(j + 1) - modified.get(j) == 0) {
                            safe = false;
                            break;
                        }
                        if (Math.abs(modified.get(j + 1) - modified.get(j)) > 3){
                            safe = false;
                            break;
                        }
                    }

                    if (safe) {
                        break;
                    }
                }
            }

            if (safe) {
                safeNumbers++;
            }
        }

        System.out.println(safeNumbers);
    }
}