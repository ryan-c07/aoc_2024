import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Day10 {
    public static void main(String[] args) {
        String[][] fileData = getFileData("inputs/Day10Input.txt");
        for (String[] row : fileData) {
            System.out.println(Arrays.toString(row));
        }
        partOne(fileData);
        partTwo(fileData);
    }


    public static String[][] getFileData(String fileName) {
        String[][] fileData = new String[40][40];
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            int count = 0;
            while (s.hasNext()) {
                String line = s.nextLine();
                if (!line.equals("")) {
                    for (int i = 0; i < line.length(); i++) {
                        fileData[count][i] = line.substring(i, i + 1);
                    }
                    count++;
                }
            }
            return fileData;
        } catch (FileNotFoundException e) {
            return new String[10][10];
        }
    }


    public static void partOne(String[][] data){
        int currentNumber = 0;
        int score = 0;
        for (int row = 0; row < data.length; row++){
            for (int col = 0; col < data[0].length; col++) {
                if (data[row][col].equals("0")) {
                    ArrayList<String> visited = new ArrayList<>();
                    score += findTrail(currentNumber, row, col, data, visited);
                }
            }
        }
        System.out.println(score);
    }


    public static void partTwo(String[][] data) {
        int currentNumber = 0;
        int score = 0;
        for (int row = 0; row < data.length; row++){
            for (int col = 0; col < data[0].length; col++) {
                if (data[row][col].equals("0")) {
                    score += findTrail(currentNumber, row, col, data);
                }
            }
        }
        System.out.println(score);
    }

    public static int findTrail(int currNum, int row, int col, String[][] data, ArrayList<String> visited) {
        String str = row + "|" + col;
        if (currNum == 9 && data[row][col].equals("9") && !visited.contains(str)){ // reached the end 🧟‍♀️
            visited.add(str);
            return 1;
        }
        if (!String.valueOf(currNum).equals(data[row][col])){ // not a trail 🧟‍♀️
            return 0;
        }
        int trails = 0;
        if (row-1 >= 0){ // up
            trails += findTrail(currNum+1,row-1,col,data, visited);
        }
        if (col-1 >= 0){
            trails += findTrail(currNum+1,row,col-1,data, visited);
        }
        if (row+1 < data.length){
            trails += findTrail(currNum+1,row+1,col,data, visited);
        }
        if (col+1 < data[0].length){
            trails += findTrail(currNum+1,row,col+1,data, visited);
        }
        return trails;
    }

    public static int findTrail(int currNum, int row, int col, String[][] data) {
        if (currNum == 9 && data[row][col].equals("9")){ // reached the end 🧟‍♀️
            return 1;
        }
        if (!String.valueOf(currNum).equals(data[row][col])){ // not a trail 🧟‍♀️
            return 0;
        }
        int trails = 0;
        if (row-1 >= 0){ // up
            trails += findTrail(currNum+1,row-1,col,data);
        }
        if (col-1 >= 0){
            trails += findTrail(currNum+1,row,col-1,data);
        }
        if (row+1 < data.length){
            trails += findTrail(currNum+1,row+1,col,data);
        }
        if (col+1 < data[0].length){
            trails += findTrail(currNum+1,row,col+1,data);
        }
        return trails;
    }
}

