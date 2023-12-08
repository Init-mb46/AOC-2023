package Day8;

import java.io.*;
import java.util.*;

public class part1 {
    public static void main(String[] args) throws IOException {
        File f = new File("Day8/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        char[] instructions = br.readLine().toCharArray();
        br.readLine();

        HashMap<String, String[]> mapping = new HashMap<>();
        String line;

        while ((line = br.readLine()) != null) {
            String key = line.substring(0,3);
            String[] values = line.substring(7,line.length()-1).split(", ");
            mapping.put(key, values);
        }

        boolean found = false;
        String position = "AAA";
        int moves = 0;

        while (!found) {
            for (int i = 0; i < instructions.length; i++) {
                moves++;
                if (instructions[i] == 'L') {
                    position = mapping.get(position)[0];
                } else {
                    position = mapping.get(position)[1];
                }
                if (position.equals("ZZZ")) {
                    found = true;
                    break;
                }
            }
        }

        System.out.println(moves);
    }
}
