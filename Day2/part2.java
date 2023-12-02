package Day2;

import java.io.*;
import java.util.*;

public class part2 {
    public static void main (String[] args) throws IOException {
        File f = new File("Day2/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        int total = 0;
        int r = 0; int g = 0; int b = 0;

        String line;

        while ((line = br.readLine()) != null) {
            r = 0; g = 0; b = 0;
            String[] comps = line.split(" ");

            for (int i = 2; i < comps.length; i++) {
                if (comps[i].contains("red")){
                    if (Integer.parseInt(comps[i-1]) > r) r = Integer.parseInt(comps[i-1]);
                } else if (comps[i].contains("blue")) {
                    if (Integer.parseInt(comps[i-1]) > b) b = Integer.parseInt(comps[i-1]);
                } else if (comps[i].contains("green")) {
                    if (Integer.parseInt(comps[i-1]) > g) g = Integer.parseInt(comps[i-1]);
                }
            }

            total += r*b*g;
        }

        System.out.println(total);
    }
}
