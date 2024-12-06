import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day06 {
    public static void main(String[] args) {
        String[][] fileData = getFileData("inputs/Day06Input.txt");
        partOne(fileData);
        partTwo(fileData);
    }

    public static String[][] getFileData(String fileName) {
        String[][] fileData = new String[132][132]; // 132

        for (int i = 0; i < 132; i++) {
            for (int j = 0; j < 132; j++) {
                fileData[i][j] = "_";
            }
        }

        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            int count = 0;
            while (s.hasNext()) {
                String line = s.nextLine();
                if (!line.isEmpty()) {
                    for (int i = 0; i < line.length(); i++) {
                        fileData[count + 1][i + 1] = line.substring(i, i + 1);
                    }
                    count++;
                }
            }
            return fileData;
        } catch (FileNotFoundException e) {
            return new String[10][10];
        }
    }

    public static void partOne(String[][] data) {
        int row = 0;
        int col = 0;
        int count = 0;
        boolean loopEnded = false;
        String direction = "up";
        for (int r = 0; r < data.length; r++) {
            for (int c = 0; c < data[0].length; c++) {
                if (data[r][c].equals("^")){
                    row = r;
                    col = c;
                }
            }
        }
        int originalRow = row;
        int originalCol = col;
        while (!loopEnded){
            if (direction.equals("up")) {
                if (data[row-1][col].equals("_")){
                    data[row][col] = "X";
                    loopEnded = true;
                }
                else if (!data[row-1][col].equals("#")) {
                    data[row][col] = "X";
                    row--;
                }
                else {
                    direction = "right";
                }
            }
            if (direction.equals("right")) {
                if (data[row][col+1].equals("_")){
                    data[row][col] = "X";
                    loopEnded = true;
                }
                else if (!data[row][col+1].equals("#")) {
                    data[row][col] = "X";
                    col++;
                }
                else {
                    direction = "down";
                }
            }
            if (direction.equals("down")) {
                if (data[row+1][col].equals("_")){
                    data[row][col] = "X";
                    loopEnded = true;
                }
                else if (!data[row+1][col].equals("#")) {
                    data[row][col] = "X";
                    row++;
                }
                else {
                    direction = "left";
                }
            }
            if (direction.equals("left")) {
                if (data[row][col-1].equals("_")){
                    data[row][col] = "X";
                    loopEnded = true;
                }
                else if (!data[row][col-1].equals("#")) {
                    data[row][col] = "X";
                    col--;
                }
                else {
                    direction = "up";
                }
            }
        }
        data[originalRow][originalCol] = "^";
        for (String[] arr : data) {
            for (String str : arr) {
                if (str.equals("X") || str.equals("^")) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }


    public static void partTwo(String[][] data) {
        // data will track the looping/nonlooping walls, and the algorithm will be run on changingArr
        String[][] changingArr = new String[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, changingArr[i], 0, data[i].length);
        }

        int row = 0;
        int col = 0;
        boolean loopEnded;
        String direction = "up";
        for (int r = 0; r < data.length; r++) {
            for (int c = 0; c < data[0].length; c++) {
                if (data[r][c].equals("^")){
                    row = r;
                    col = c;
                }
            }
        }
        int originalRow = row;
        int originalCol = col;
        int totalLoops = 0;

        String[][] originalData = new String[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, originalData[i], 0, data[i].length);
        }

        for (int dataRow = 0; dataRow < data.length; dataRow++){
            for (int dataCol = 0; dataCol < data[0].length; dataCol++) {
                if (data[dataRow][dataCol].equals("X")) {
                    for (int i = 0; i < data.length; i++) {
                        System.arraycopy(originalData[i], 0, changingArr[i], 0, data[i].length);
                    }

                    row = originalRow;
                    col = originalCol;
                    direction = "up";
                    loopEnded = false;

                    changingArr[dataRow][dataCol] = "#";

                    for (int r = 0; r < changingArr.length; r++) {
                        for (int c = 0; c < changingArr[0].length; c++) {
                            if (changingArr[r][c].equals("X")) {
                                changingArr[r][c] = ".";
                            }
                        }
                    }

                    int hits = 0;
                    int iterations = 0;
                    while (iterations < 10000) {
                        if (direction.equals("up")) {
                            if (row - 1 < 0 || changingArr[row - 1][col].equals("_")) {
                                loopEnded = true;
                            } else if (!changingArr[row - 1][col].equals("#")) {
                                changingArr[row][col] = "X";
                                row--;
                            } else {
                                direction = "right";
                                hits++;
                            }
                        }
                        else if (direction.equals("right")) {
                            if (col + 1 >= changingArr[0].length || changingArr[row][col + 1].equals("_")) {
                                loopEnded = true;
                            } else if (!changingArr[row][col + 1].equals("#")) {
                                changingArr[row][col] = "X";
                                col++;
                            } else {
                                direction = "down";
                                hits++;
                            }
                        }
                        else if (direction.equals("down")) {
                            if (row + 1 >= changingArr.length || changingArr[row + 1][col].equals("_")) {
                                loopEnded = true;
                            } else if (!changingArr[row + 1][col].equals("#")) {
                                changingArr[row][col] = "X";
                                row++;
                            } else {
                                direction = "left";
                                hits++;
                            }
                        }
                        else if (direction.equals("left")) {
                            if (col - 1 < 0 || changingArr[row][col - 1].equals("_")) {
                                loopEnded = true;
                            } else if (!changingArr[row][col - 1].equals("#")) {
                                changingArr[row][col] = "X";
                                col--;
                            } else {
                                direction = "up";
                                hits++;
                            }
                        }
                        iterations++;
                    }

                    changingArr[originalRow][originalCol] = "^";
                    if (hits > 4) {
                        data[dataRow][dataCol] = "L";
                    }
                    if (loopEnded) {
                        data[dataRow][dataCol] = "N";
                    }
                }
            }
        }
        for (String[] lastCheckArr : data) {
            for (String lastCheckStr : lastCheckArr) {
                if (lastCheckStr.equals("L")) {
                    totalLoops++;
                }
            }
        }
        System.out.println(totalLoops);
    }
}