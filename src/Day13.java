import java.util.ArrayList;

public class Day13 {

    static ArrayList<String> buttonA = new ArrayList<>();
    static ArrayList<String> buttonB = new ArrayList<>();
    static ArrayList<String> prize = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<String> fileData = StarterMethods.getFileData("inputs/Day13Input.txt");
        for (String row : fileData){
            if (row.contains("A")){
                buttonA.add(row.substring(row.indexOf(":") + 2));
            }
            else if (row.contains("B")){
                buttonB.add(row.substring(row.indexOf(":") + 2));
            }
            else {
                prize.add(row.substring(row.indexOf(":") + 2));
            }
        }
        System.out.println(buttonA);
        System.out.println(buttonB);
        System.out.println(prize);
        partOne();
        partTwo();
    }

    public static void partOne() {
        int total = 0;
        for (int num = 0; num < buttonA.size(); num++) {
            int X1 = Integer.parseInt(buttonA.get(num).substring(buttonA.get(num).indexOf("X+") + 2, buttonA.get(num).indexOf(",")));
            int Y1 = Integer.parseInt(buttonA.get(num).substring(buttonA.get(num).indexOf("Y+") + 2));
            int X2 = Integer.parseInt(buttonB.get(num).substring(buttonB.get(num).indexOf("X+") + 2, buttonB.get(num).indexOf(",")));
            int Y2 = Integer.parseInt(buttonB.get(num).substring(buttonB.get(num).indexOf("Y+") + 2));
            int prizeX = Integer.parseInt(prize.get(num).substring(prize.get(num).indexOf("X=") + 2, prize.get(num).indexOf(",")));
            int prizeY = Integer.parseInt(prize.get(num).substring(prize.get(num).indexOf("Y=") + 2));
            int pressA = -1;
            int pressB = -1;
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (((X1 * i) + (X2 * j)) == prizeX && ((Y1 * i) + (Y2 * j)) == prizeY){
                        pressA = i;
                        pressB = j;
                    }
                }
            }
            if (pressA != -1) {
                total += pressA * 3 + pressB;
            }
        }

        System.out.println(total);
    }


    public static void partTwo() {
        long total = 0;
        for (int num = 0; num < buttonA.size(); num++) {
            long X1 = Integer.parseInt(buttonA.get(num).substring(buttonA.get(num).indexOf("X+") + 2, buttonA.get(num).indexOf(",")));
            long Y1 = Integer.parseInt(buttonA.get(num).substring(buttonA.get(num).indexOf("Y+") + 2));
            long X2 = Integer.parseInt(buttonB.get(num).substring(buttonB.get(num).indexOf("X+") + 2, buttonB.get(num).indexOf(",")));
            long Y2 = Integer.parseInt(buttonB.get(num).substring(buttonB.get(num).indexOf("Y+") + 2));
            long prizeX = Integer.parseInt(prize.get(num).substring(prize.get(num).indexOf("X=") + 2, prize.get(num).indexOf(","))) + 10000000000000L;
            long prizeY = Integer.parseInt(prize.get(num).substring(prize.get(num).indexOf("Y=") + 2)) + 10000000000000L;
            long pressB = (Y1 * prizeX - X1 * prizeY) / (Y1 * X2 - X1 * Y2);
            long pressA = (prizeX - (X2 * pressB)) / X1;
            if (((X1 * pressA) + (X2 * pressB)) == prizeX && ((Y1 * pressA) + (Y2 * pressB)) == prizeY){
                total += pressA*3 + pressB;
            }
        }
        System.out.println(total);
    }
}