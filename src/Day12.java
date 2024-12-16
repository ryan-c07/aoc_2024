import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Day12 {
    private static String[][] fileData = StarterMethods.getFileDataBorder("inputs/Day12Input.txt", 140);
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
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int total = 0;
        while (!alphabet.isEmpty()) {
            String currentLetter = alphabet.substring(0,1);
            for (int row = 0; row < fileData.length; row++) {
                for (int col = 0; col < fileData[0].length; col++) {
                    if (fileData[row][col].equals(currentLetter)) {
                        findShapes(row, col, currentLetter);
                        int area = 0;
                        int sides = 0;
                        for (int r = 0; r < fileData.length; r++) {
                            for (int c = 0; c < fileData[0].length; c++) {
                                if (tempData[r][c].equals(currentLetter)) {
                                    area++;
                                }
                            }
                        }
                        sides = findSides(currentLetter);
                        total += area*sides;
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


    public static int findSides(String letter) {
        int top = 0;
        int bottom = 0;
        int left = 0;
        int right = 0;
        top = findTop(letter);
        bottom = findBottom(letter);
        left = findLeft(letter);
        right = findRight(letter);
        return top + bottom + left + right;
    }


    public static int findTop(String letter) {
        int top = 0;
        ArrayList<String> arr = new ArrayList<>();
        for (int row = 1; row < tempData.length; row++){
            for (int col = 0; col < tempData[0].length; col++){
                if (tempData[row-1][col].equals(".") && tempData[row][col].equals(letter)){
                    arr.add(row + "," + col);
                }
            }
        }

        Collections.sort(arr, (a, b) -> {
            String[] partsA = a.split(",");
            String[] partsB = b.split(",");
            int firstA = Integer.parseInt(partsA[0]);
            int secondA = Integer.parseInt(partsA[1]);
            int firstB = Integer.parseInt(partsB[0]);
            int secondB = Integer.parseInt(partsB[1]);
            if (firstA != firstB) {
                return Integer.compare(firstA, firstB);
            }
            return Integer.compare(secondA, secondB);
        });
        for (int i = 0; i < arr.size() - 1; i++){
            int val1X = Integer.parseInt(arr.get(i).substring(0, arr.get(i).indexOf(",")));
            int val2X = Integer.parseInt(arr.get(i+1).substring(0, arr.get(i+1).indexOf(",")));
            int val1Y = Integer.parseInt(arr.get(i).substring(arr.get(i).indexOf(",")+1));
            int val2Y = Integer.parseInt(arr.get(i+1).substring(arr.get(i+1).indexOf(",")+1));
            if (val1X == val2X && Math.abs(val1Y - val2Y) == 1){
            }
            else {
                top++;
            }
            if (i == arr.size() - 2){
                top++;
            }
        }
        if (arr.size() == 1){
            top++;
        }
        return top;
    }


    public static int findBottom(String letter) {
        int bottom = 0;
        ArrayList<String> arr = new ArrayList<>();
        for (int row = 0; row < tempData.length - 1; row++){
            for (int col = 0; col < tempData[0].length; col++){
                if (tempData[row+1][col].equals(".") && tempData[row][col].equals(letter)){
                    arr.add(row + "," + col);
                }
            }
        }

        Collections.sort(arr, (a, b) -> {
            String[] partsA = a.split(",");
            String[] partsB = b.split(",");
            int firstA = Integer.parseInt(partsA[0]);
            int secondA = Integer.parseInt(partsA[1]);
            int firstB = Integer.parseInt(partsB[0]);
            int secondB = Integer.parseInt(partsB[1]);
            if (firstA != firstB) {
                return Integer.compare(firstA, firstB);
            }
            return Integer.compare(secondA, secondB);
        });
        for (int i = 0; i < arr.size() - 1; i++){
            int val1X = Integer.parseInt(arr.get(i).substring(0, arr.get(i).indexOf(",")));
            int val2X = Integer.parseInt(arr.get(i+1).substring(0, arr.get(i+1).indexOf(",")));
            int val1Y = Integer.parseInt(arr.get(i).substring(arr.get(i).indexOf(",")+1));
            int val2Y = Integer.parseInt(arr.get(i+1).substring(arr.get(i+1).indexOf(",")+1));
            if (val1X == val2X && Math.abs(val1Y - val2Y) == 1){
            }
            else {
                bottom++;
            }
            if (i == arr.size() - 2){
                bottom++;
            }
        }
        if (arr.size() == 1){
            bottom++;
        }
        return bottom;
    }


    public static int findRight(String letter) {
        String[][] flipped = new String[tempData.length][tempData[0].length];
        for (int r = 0; r < tempData.length; r++) {
            for (int c = tempData[0].length - 1; c >= 0; c--) {
                flipped[r][c] = tempData[c][r];
            }
        }
        int right = 0;
        ArrayList<String> arr = new ArrayList<>();
        for (int row = 0; row < flipped.length - 1; row++){
            for (int col = 0; col < flipped[0].length; col++){
                if (flipped[row+1][col].equals(".") && flipped[row][col].equals(letter)){
                    arr.add(row + "," + col);
                }
            }
        }

        Collections.sort(arr, (a, b) -> {
            String[] partsA = a.split(",");
            String[] partsB = b.split(",");
            int firstA = Integer.parseInt(partsA[0]);
            int secondA = Integer.parseInt(partsA[1]);
            int firstB = Integer.parseInt(partsB[0]);
            int secondB = Integer.parseInt(partsB[1]);
            if (firstA != firstB) {
                return Integer.compare(firstA, firstB);
            }
            return Integer.compare(secondA, secondB);
        });
        for (int i = 0; i < arr.size() - 1; i++){
            int val1X = Integer.parseInt(arr.get(i).substring(0, arr.get(i).indexOf(",")));
            int val2X = Integer.parseInt(arr.get(i+1).substring(0, arr.get(i+1).indexOf(",")));
            int val1Y = Integer.parseInt(arr.get(i).substring(arr.get(i).indexOf(",")+1));
            int val2Y = Integer.parseInt(arr.get(i+1).substring(arr.get(i+1).indexOf(",")+1));
            if (val1X == val2X && Math.abs(val1Y - val2Y) == 1){
            }
            else {
                right++;
            }
            if (i == arr.size() - 2){
                right++;
            }
        }
        if (arr.size() == 1){
            right++;
        }
        return right;
    }

    public static int findLeft(String letter) {
        String[][] flipped = new String[tempData.length][tempData[0].length];
        for (int r = 0; r < tempData.length; r++){
            for (int c = tempData[0].length - 1; c >= 0; c--){
                flipped[r][c] = tempData[c][r];
            }
        }
        int left = 0;
        ArrayList<String> arr = new ArrayList<>();
        for (int row = 1; row < flipped.length; row++){
            for (int col = 0; col < flipped[0].length; col++){
                if (flipped[row-1][col].equals(".") && flipped[row][col].equals(letter)){
                    arr.add(row + "," + col);
                }
            }
        }

        Collections.sort(arr, (a, b) -> {
            String[] partsA = a.split(",");
            String[] partsB = b.split(",");
            int firstA = Integer.parseInt(partsA[0]);
            int secondA = Integer.parseInt(partsA[1]);
            int firstB = Integer.parseInt(partsB[0]);
            int secondB = Integer.parseInt(partsB[1]);
            if (firstA != firstB) {
                return Integer.compare(firstA, firstB);
            }
            return Integer.compare(secondA, secondB);
        });


        for (int i = 0; i < arr.size() - 1; i++){
            int val1X = Integer.parseInt(arr.get(i).substring(0, arr.get(i).indexOf(",")));
            int val2X = Integer.parseInt(arr.get(i+1).substring(0, arr.get(i+1).indexOf(",")));
            int val1Y = Integer.parseInt(arr.get(i).substring(arr.get(i).indexOf(",")+1));
            int val2Y = Integer.parseInt(arr.get(i+1).substring(arr.get(i+1).indexOf(",")+1));
            if (val1X == val2X && Math.abs(val1Y - val2Y) == 1){
            }
            else {
                left++;
            }
            if (i == arr.size() - 2){
                left++;
            }
        }
        if (arr.size() == 1){
            left++;
        }
        return left;
    }
}