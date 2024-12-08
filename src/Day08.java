import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day08 {
    public static void main(String[] args) {
        String[][] fileData = getFileData("inputs/Day08Input.txt");
        for (String[] row : fileData) {
            System.out.println(Arrays.toString(row));
        }
        partOne(fileData);
        partTwo(fileData);
    }

    public static String[][] getFileData(String fileName) {
        String[][] fileData = new String[50][50];
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

    public static void partOne(String[][] data) {
        String values = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        ArrayList<String> firstCoords = new ArrayList<>();
        int firstX = -1;
        int firstY = -1;
        int secondX;
        int secondY;

        String[][] unique = new String[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                unique[i][j] = ".";
            }
        }

        for (int i = 0; i < values.length(); i++) {
            firstCoords.clear();
            int occurrences = 1;
            for (int times = 0; times < occurrences; times++) {
                for (int row = 0; row < data.length; row++) {
                    for (int col = 0; col < data[0].length; col++) {
                        if (firstX == -1 && firstY == -1 && !firstCoords.contains(row + "|" + col) && data[row][col].equals(values.substring(i, i + 1))) {
                            firstX = col;
                            firstY = row;
                            firstCoords.add(row + "|" + col);
                            occurrences++;
                        } else if (firstX != -1 && firstY != -1 && data[row][col].equals(values.substring(i, i + 1))) {
                            secondX = col;
                            secondY = row;
                            int count = 0;
                            while (count < 1) {

                                try {
                                    if (secondY != firstY && secondX != firstX) {
                                        unique[firstY - (secondY - firstY)][firstX - (secondX - firstX)] = "#";
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {}
                                try {
                                    if (secondY != firstY && secondX != firstX) {
                                        unique[secondY + (secondY - firstY)][secondX + (secondX - firstX)] = "#";
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {}
                                count++;
                            }
                        }
                    }
                }
                firstX = -1;
                firstY = -1;
            }
        }
        int count = 0;
        for (String[] row : unique) {
            for (String element : row) {
                if (element.equals("#")){
                    count++;
                }
            }
        }
        System.out.println(count);
    }


    public static void partTwo(String[][] data) {
        String values = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        ArrayList<String> firstCoords = new ArrayList<>();
        int firstX = -1;
        int firstY = -1;
        int secondX;
        int secondY;

        String[][] unique = new String[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                unique[i][j] = ".";
            }
        }

        for (int i = 0; i < values.length(); i++) {
            firstCoords.clear();
            int occurrences = 1;
            for (int times = 0; times < occurrences; times++) {
                for (int row = 0; row < data.length; row++) {
                    for (int col = 0; col < data[0].length; col++) {
                        if (firstX == -1 && firstY == -1 && !firstCoords.contains(row + "|" + col) && data[row][col].equals(values.substring(i, i + 1))) {
                            firstX = col;
                            firstY = row;
                            firstCoords.add(row + "|" + col);
                            unique[row][col] = "#";
                            occurrences++;
                        } else if (firstX != -1 && firstY != -1 && data[row][col].equals(values.substring(i, i + 1))) {
                            secondX = col;
                            secondY = row;
                            int count = 1;

                            while (count < 50) {
                                try {
                                    if (secondY != firstY && secondX != firstX) {
                                        unique[firstY - (secondY*count - firstY*count)][firstX - (secondX*count - firstX*count)] = "#";
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {}
                                try {
                                    if (secondY != firstY && secondX != firstX) {
                                        unique[secondY + (secondY*count - firstY*count)][secondX + (secondX*count - firstX*count)] = "#";
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {}
                                count++;
                            }
                        }
                    }
                }
                firstX = -1;
                firstY = -1;
            }
        }
        int count = 0;
        for (String[] row : unique) {
            for (String element : row) {
                if (element.equals("#")){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}