package Day15;

import java.io.*;
import java.util.*;

public class part2 {
    public static void main(String[] args) throws IOException {
        File f = new File("Day15/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String[] codes = br.readLine().split(",");
        HashMap<Integer, ArrayList<String>> boxes = new HashMap<>();

        for (int i = 0; i < codes.length; i++) {
            String c = codes[i];
            if (c.contains("-")) { //remove
                String lens = c.substring(0, c.length()-1);
                int b = hash(0,lens);
                if (boxes.get(b) == null) boxes.put(b, new ArrayList<>());
                int x = contains(boxes.get(b), lens);
                if (x != -1) {
                    boxes.get(b).remove(x);
                }
            } else {
                String lens = c.substring(0, c.length()-2);
                int b = hash(0, lens);
                if (boxes.get(b) == null) boxes.put(b, new ArrayList<>());
                int x = contains(boxes.get(b), lens);
                String newLens = lens + " " + c.charAt(c.length()-1);
                if (x != -1) {
                    boxes.get(b).set(x, newLens);
                } else {
                    boxes.get(b).add(newLens);
                }
            }
        }

        int t = 0;

        for (int key : boxes.keySet()) {
            for (int i = 0; i < boxes.get(key).size(); i++) {
                t += (key + 1) * (i + 1) * Integer.parseInt(boxes.get(key).get(i).split(" ")[1]);
            }
        }

        System.out.println(t);
    }

    public static int contains(ArrayList<String> box, String lens) {
        int x = -1;
        for (int i = 0; i < box.size(); i++) {
            if (box.get(i).contains(lens)) {
                x = i;
            }
        }
        return x;
    }

    public static int hash (int t, String s) {
        if (s.isEmpty()) return t;
        int total = t;
        total += s.charAt(0);
        total *= 17;
        total = total % 256;
        return hash(total, s.substring(1));
    }
}
