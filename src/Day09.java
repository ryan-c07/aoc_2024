import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day09 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("inputs/Day09Input.txt");
        System.out.println(fileData);
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
        String line = data.getFirst();
        long checkSum = 0;
        int number = 0;
        ArrayList<String> finalLayout = new ArrayList<>();
        for (int i = 0; i < line.length(); i++){
            if (i % 2 != 1){
                for (int idx = 0; idx < Integer.parseInt(line.substring(i, i+1)); idx++){
                    finalLayout.add(String.valueOf(number));
                }
                number++;
            }
            else {
                for (int idx = 0; idx < Integer.parseInt(line.substring(i, i+1)); idx++){
                    finalLayout.add(".");
                }
            }
        }
        for (int i = 0; i < finalLayout.size(); i++) {
            if (finalLayout.get(i).equals(".")) {
                for (int j = finalLayout.size() - 1; j > i; j--) {
                    if (!finalLayout.get(j).equals(".")) {
                        String temp = finalLayout.get(j);
                        finalLayout.set(i, temp);
                        finalLayout.set(j, ".");
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < finalLayout.size(); i++){
            if (!finalLayout.get(i).equals(".")){
                checkSum += (long) Integer.parseInt(finalLayout.get(i)) * i;
            }
        }
        System.out.println(checkSum);
    }


    public static void partTwo(ArrayList<String> data) {
        String line = data.getFirst();
        long checkSum = 0;
        int number = 0;
        ArrayList<String> finalLayout = new ArrayList<>();

        for (int i = 0; i < line.length(); i++){
            if (i % 2 != 1){
                for (int idx = 0; idx < Integer.parseInt(line.substring(i, i+1)); idx++){
                    finalLayout.add(String.valueOf(number));
                }
                number++;
            }
            else {
                for (int idx = 0; idx < Integer.parseInt(line.substring(i, i+1)); idx++){
                    finalLayout.add(".");
                }
            }
        }

        for (int ID = number - 1; ID >= 0; ID--) { // until no more numbers ( right to left )
            String fileStr = String.valueOf(ID);
            int fileStart = finalLayout.indexOf(fileStr); // when the number first appears

            int fileSize = 0;
            for (int i = fileStart; i < finalLayout.size(); i++) {
                if (finalLayout.get(i).equals(fileStr)){
                    fileSize++;
                }
            }

            int idx = -1;
            for (int i = 0; i < fileStart; i++) { // i represents starting point
                boolean fit = true;
                for (int j = 0; j < fileSize; j++) {
                    if (!finalLayout.get(i + j).equals(".")) { // i + j, represents j away from i.
                        fit = false;
                        break;
                    }
                }
                if (fit) {
                    idx = i;
                    break;
                }
            }
            if (idx != -1) {
                for (int i = 0; i < fileSize; i++) {
                    finalLayout.set(fileStart + i, ".");
                    finalLayout.set(idx + i, fileStr);
                }
            }
        }
        for (int i = 0; i < finalLayout.size(); i++){
            if (!finalLayout.get(i).equals(".")){
                checkSum += (long) Integer.parseInt(finalLayout.get(i)) * i;
            }
        }
        System.out.println(checkSum);
    }
}