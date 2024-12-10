import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StarterMethods {

    public static ArrayList<String> getFileData(String fileName) { //1D arrays
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

    public static String[][] getFileData(String fileName, int size) { //2D arrays
        String[][] fileData = new String[size][size];
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            int count = 0;
            while (s.hasNext()) {
                String line = s.nextLine();
                if (!line.isEmpty()) {
                    for (int i = 0; i < line.length(); i++) {
                        fileData[count][i] = line.substring(i, i + 1);
                    }
                    count++;
                }
            }
            return fileData;
        } catch (FileNotFoundException e) {
            return new String[size][size];
        }
    }
}
