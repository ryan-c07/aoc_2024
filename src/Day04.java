import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day04 {
    public static void main(String[] args) {
        String[][] fileData = getFileData("inputs/Day04Input.txt");
        int size = 140; // 140 for real, 10 for test
        for (String[] row : fileData) {
            System.out.println(Arrays.toString(row));
        }
        partOne(fileData);
        partTwo(fileData);
    }

    public static String[][] getFileData(String fileName) {
        String[][] fileData = new String[140][140];
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
        int count = 0;
        for (int row = 0; row < data.length; row++){
            for (int col = 0; col < data[0].length; col++){
                // bottom right, row + 1, col + 1
                if (row + 3 < data.length && col + 3 < data[0].length && data[row][col].equals("X") && data[row+1][col+1].equals("M") && data[row+2][col+2].equals("A") && data[row+3][col+3].equals("S")){
                    count++;
                }
                // down, row + 1
                if (row + 3 < data.length && data[row][col].equals("X") && data[row+1][col].equals("M") && data[row+2][col].equals("A") && data[row+3][col].equals("S")){
                    count++;
                }
                // bottom left, row + 1, col - 1
                if (row + 3 < data.length && col - 3 >= 0 && data[row][col].equals("X") && data[row+1][col-1].equals("M") && data[row+2][col-2].equals("A") && data[row+3][col-3].equals("S")){
                    count++;
                }
                // right, col + 1
                if (col + 3 < data[0].length && data[row][col].equals("X") && data[row][col+1].equals("M") && data[row][col+2].equals("A") && data[row][col+3].equals("S")){
                    count++;
                }
                // left, col - 1
                if (col - 3 >= 0 && data[row][col].equals("X") && data[row][col-1].equals("M") && data[row][col-2].equals("A") && data[row][col-3].equals("S")){
                    count++;
                }
                // top left, row - 1, col - 1
                if (row - 3 >= 0 && col - 3 >= 0 && data[row][col].equals("X") && data[row-1][col-1].equals("M") && data[row-2][col-2].equals("A") && data[row-3][col-3].equals("S")){
                    count++;
                }
                // top, row - 1
                if (row - 3 >= 0 && data[row][col].equals("X") && data[row-1][col].equals("M") && data[row-2][col].equals("A") && data[row-3][col].equals("S")){
                    count++;
                }
                // top right, row - 1, col + 1
                if (row - 3 >= 0 && col + 3 < data[0].length && data[row][col].equals("X") && data[row-1][col+1].equals("M") && data[row-2][col+2].equals("A") && data[row-3][col+3].equals("S")){
                    count++;
                }
            }
        }
        System.out.println(count);
    }


    public static void partTwo(String[][] data) {
        int totalCount = 0;
        for (int row = 0; row < data.length; row++){
            for (int col = 0; col < data[0].length; col++){
                int count = 0;
                // bottom right, row + 1, col + 1
                /*
                * M__
                * _A_
                * __S
                * */

                if (row + 1 < data.length
                        && row - 1 >= 0
                        && col + 1 < data[0].length
                        && col - 1 >= 0
                        && data[row-1][col-1].equals("M")
                        && data[row][col].equals("A")
                        && data[row+1][col+1].equals("S")){
                    count++;
                }
                // bottom left, row + 1, col - 1
                /*
                 * __M
                 * _A_
                 * S__
                 * */

                if (row + 1 < data.length
                        && row - 1 >= 0
                        && col + 1 < data[0].length
                        && col - 1 >= 0
                        && data[row-1][col+1].equals("M")
                        && data[row][col].equals("A")
                        && data[row+1][col-1].equals("S")){
                    count++;
                }

                // top left, row - 1, col - 1
                /*
                 * S__
                 * _A_
                 * __M
                 * */

                if (row + 1 < data.length
                        && row - 1 >= 0
                        && col + 1 < data[0].length
                        && col - 1 >= 0
                        && data[row+1][col+1].equals("M")
                        && data[row][col].equals("A")
                        && data[row-1][col-1].equals("S")){
                    count++;
                }

                // top right, row - 1, col + 1
                /*
                 * __S
                 * _A_
                 * M__
                 * */

                if (row + 1 < data.length
                        && row - 1 >= 0
                        && col + 1 < data[0].length
                        && col - 1 >= 0
                        && data[row+1][col-1].equals("M")
                        && data[row][col].equals("A")
                        && data[row-1][col+1].equals("S")){
                    count++;
                }
                if (count == 2){
                    totalCount++;
                }
            }
        }
        System.out.println(totalCount);
    }

}