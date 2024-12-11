import java.util.ArrayList;
import java.util.List;

public class Day11 {
    public static void main(String[] args) {
        ArrayList<String> fileData = StarterMethods.getFileData("inputs/Day11Input.txt");
        System.out.println(fileData);
//        partOne(fileData);
        partTwo(fileData);
    }

    public static void partOne(ArrayList<String> data) {
        int count = 0;
        while (count < 25){
            for (int i = 0; i < data.size(); i++){
                String sample = data.get(i);
                sample = String.valueOf(Long.parseLong(sample));
                if (sample.length() % 2 == 0){
                    String left = sample.substring(0, sample.length()/2);
                    String right = sample.substring(sample.length()/2); // change leading zeros
                    data.set(i, left);
                    data.add(i+1, right);
                    i++;
                }
                else {
                    for (int j = 0; j < sample.length(); j++){
                        if (sample.equals("0")){
                            data.set(i, "1");
                        }
                        else {
                            data.set(i, String.valueOf(Long.parseLong(sample)*2024));
                        }
                    }
                }
            }
            count++;
        }
        System.out.println(data.size());
    }


    public static void partTwo(ArrayList<String> data) {
        /*
        * idk i probably have to rewrite part1
        * */
    }
}