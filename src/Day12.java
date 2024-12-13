import java.util.Arrays;

public class Day12 {
    private static String[][] fileData = StarterMethods.getFileDataBorder("inputs/Day12Input.txt", 5);
    private static String[][] tempData = new String[fileData.length][fileData[0].length];

    public static void main(String[] args) {
        for (String[] arr : tempData) {
            Arrays.fill(arr, ".");
        }
        for (String[] row : fileData){
            System.out.println(Arrays.toString(row));
        }
        String[][] copy = new String[fileData.length][fileData[0].length];
        for (int i = 0; i < fileData.length; i++) {
            for (int j = 0; j < fileData[0].length; j++) {
                copy[i][j] = fileData[i][j];
            }
        }
        partOne();
        fileData = copy;
        partTwo();
    }

    public static void partOne() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int total = 0;
        while (!alphabet.isEmpty()) {
            String currentLetter = alphabet.substring(0,1);
            for (int row = 0; row < fileData.length; row++) {
                for (int col = 0; col < fileData[0].length; col++) {
                    if (fileData[row][col].equals(currentLetter)) {
                        findShapes(row, col, currentLetter);
                        int perimeter = 0;
                        int area = 0;
                        for (int r = 0; r < fileData.length; r++) {
                            for (int c = 0; c < fileData[0].length; c++) {
                                if (tempData[r][c].equals(currentLetter)) {
                                    if (!tempData[r][c-1].equals(currentLetter)) {
                                        perimeter++;
                                    }
                                    if (!tempData[r][c+1].equals(currentLetter)) {
                                        perimeter++;
                                    }
                                    if (!tempData[r-1][c].equals(currentLetter)) {
                                        perimeter++;
                                    }
                                    if (!tempData[r+1][c].equals(currentLetter)) {
                                        perimeter++;
                                    }
                                    area++;
                                }
                            }
                        }
                        total += area*perimeter;
                        for (String[] arr : tempData) {
                            Arrays.fill(arr, ".");
                        }
                    }
                }
            }
            alphabet = alphabet.substring(1);
        }
        System.out.println(total);
    }

    public static void partTwo() {
        /*
        * TO BE IMPLEMENTED
        * */
    }


    public static void findShapes(int row, int col, String letter) {
        if (!fileData[row][col].equals(letter) ||
                tempData[row][col].equals(letter)) {
            return;
        }

        tempData[row][col] = letter;
        fileData[row][col] = ".";
        findShapes(row - 1, col, letter);
        findShapes(row, col - 1, letter);
        findShapes(row + 1, col, letter);
        findShapes(row, col + 1, letter);
    }
}
