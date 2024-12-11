
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day11 {
    public static void main(String[] args) {
        ArrayList<String> fileData = StarterMethods.getFileData("inputs/Day11Input.txt");
        ArrayList<Long> fileDataLong = new ArrayList<>();
        for (String str : fileData) {
            fileDataLong.add(Long.parseLong(str));
        }
        System.out.println(fileDataLong);
        partOne(fileData);
        partTwo(fileDataLong);
    }




    public static void partOne(ArrayList<String> data) {
        int count = 0;
        while (count < 25){ // # blinks
            for (int i = 0; i < data.size(); i++){ // each number
                String sample = data.get(i);
                sample = String.valueOf(Long.parseLong(sample));
                if (sample.length() % 2 == 0){ //
                    String left = sample.substring(0, sample.length()/2);
                    String right = sample.substring(sample.length()/2);
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








    public static void partTwo(ArrayList<Long> data) { // 😞😞😞
        HashMap<Long, Long> hash = new HashMap<>();
        for (Long num : data) {
            hash.put(num, hash.getOrDefault(num, 0L) + 1);
        }

        int count = 0;
        while (count < 75) {
            HashMap<Long, Long> tempHash = new HashMap<>(hash);
            hash.clear();

            for (Map.Entry<Long, Long> entry : tempHash.entrySet()) {
                Long key = entry.getKey();
                Long value = entry.getValue();

                if (value > 0) {
                    if (String.valueOf(key).length() % 2 == 0) {
                        String keyString = String.valueOf(key);
                        String left = keyString.substring(0, keyString.length() / 2);
                        String right = keyString.substring(keyString.length() / 2);

                        Long leftKey = Long.parseLong(left);
                        Long rightKey = Long.parseLong(right);

                        hash.put(leftKey, hash.getOrDefault(leftKey, 0L) + value);
                        hash.put(rightKey, hash.getOrDefault(rightKey, 0L) + value);
                    }

                    else {
                        if (key == 0) {
                            Long newKey = 1L;
                            hash.put(newKey, hash.getOrDefault(newKey, 0L) + value);
                        } else {
                            Long newKey = key * 2024;
                            hash.put(newKey, hash.getOrDefault(newKey, 0L) + value);
                        }
                    }
                }
            }
            count++;
        }

        long total = 0;
        for (Map.Entry<Long, Long> entry : hash.entrySet()) {
            if (entry.getValue() > 0){
                total += entry.getValue();
            }
        }
        System.out.println(total);
    }
}
