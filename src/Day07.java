import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day07 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("inputs/Day07Input.txt");
//        System.out.println(fileData);
//        partOne(fileData);
        partTwo(fileData);
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.isEmpty())
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
    public static void partOne(ArrayList<String> data) {
        long grandTotal = 0;
        ArrayList<Long> integersArr = new ArrayList<>();
        for (String line : data){
            String[] parts = line.split(":");
            String[] numbers = parts[1].trim().split(" ");
            long answer = Long.parseLong(parts[0]);
            int numOfOperators = numbers.length - 1;
            int numOfCombinations = (int) Math.pow(2, numbers.length - 1);
            for (String num : numbers) {
                integersArr.add(Long.parseLong(num));
            }

            ArrayList<String> allCombinations = new ArrayList<>();
            StringBuilder str = new StringBuilder();
            while (allCombinations.size() != numOfCombinations){ // random search cuz fun
                int randomNumber = (int)(Math.random() * 2) + 1;
                if (randomNumber == 1){
                    str.append("A");
                }
                else {
                    str.append("M");
                }
                if (str.length() == numOfOperators){
                    if (!allCombinations.contains(str.toString())){
                        allCombinations.add(str.toString());
                    }
                    str = new StringBuilder();
                }
            }
            ArrayList<Long> allResults = new ArrayList<>();
            while (!allCombinations.isEmpty()){
                long num = integersArr.getFirst();
                for (int i = 0; i < numOfOperators; i++){
                    if (allCombinations.getFirst().charAt(i) == 'A'){
                        num += integersArr.get(i+1);
                    }
                    if (allCombinations.getFirst().charAt(i) == 'M'){
                        num *= integersArr.get(i+1);
                    }
                }
                allResults.add(num);
                allCombinations.removeFirst();
            }
            if (allResults.contains(answer)){
                grandTotal += answer;
            }
            integersArr = new ArrayList<>();
        }
        System.out.println(grandTotal);
    }


    public static void partTwo(ArrayList<String> data) {
        long grandTotal = 0;
        ArrayList<Long> integersArr = new ArrayList<>();
        for (String line : data){
            String[] parts = line.split(":");
            String[] numbers = parts[1].trim().split(" ");
            long answer = Long.parseLong(parts[0]);
            int numOfOperators = numbers.length - 1;
            int numOfCombinations = (int) Math.pow(3, numbers.length - 1);
            for (String num : numbers) {
                integersArr.add(Long.parseLong(num));
            }
            char[] symbols = {'A', '|', 'M'};
            int[] currentCombination = new int[numOfOperators]; // array with the length of # of operators -> integers correspond to the symbols
            ArrayList<String> allCombinations = new ArrayList<>();

            while (allCombinations.size() != numOfCombinations){ // linear search cuz random is turtle
                StringBuilder str = new StringBuilder();
                for (int digit : currentCombination) { // translation
                    str.append(symbols[digit]);
                }
                allCombinations.add(str.toString());

                int index = numOfOperators - 1;
                while (index >= 0) {
                    currentCombination[index]++;
                    if (currentCombination[index] < 3) { // new combo
                        break;
                    }
                    currentCombination[index] = 0; // sets back to default
                    index--; // next index
                }
                if (index < 0) {
                    break;
                }
            }


            ArrayList<Long> allResults = new ArrayList<>();
            while (!allCombinations.isEmpty()){
                long num = integersArr.getFirst();
                for (int i = 0; i < numOfOperators; i++){
                    if (allCombinations.getFirst().charAt(i) == 'A'){
                        num += integersArr.get(i+1);
                    }
                    if (allCombinations.getFirst().charAt(i) == '|'){
                        num = Long.parseLong("" + num + integersArr.get(i+1));
                    }
                    if (allCombinations.getFirst().charAt(i) == 'M'){
                        num *= integersArr.get(i+1);
                    }
                }
                allResults.add(num);
                allCombinations.removeFirst();
            }
            if (allResults.contains(answer)){
                grandTotal += answer;
            }
            integersArr = new ArrayList<>();
        }
        System.out.println(grandTotal);
    }
}