package Day2;

import java.io.*;
import java.util.*;

public class part1 {
    public static void main (String[] args) throws IOException {
        File f = new File("Day2/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        int total = 0;

        String line;

        while ((line = br.readLine()) != null) {
            String[] comps = line.split(" ");
            boolean works = true;
            int id = Integer.parseInt(comps[1].substring(0,comps[1].length()-1));
            for (int i = 2; i < comps.length; i++) {
                if (comps[i].contains("red")){
                    if (Integer.parseInt(comps[i-1]) > 12) works = false;
                } else if (comps[i].contains("blue")) {
                    if (Integer.parseInt(comps[i-1]) > 14) works = false;
                } else if (comps[i].contains("green")) {
                    if (Integer.parseInt(comps[i-1]) > 13) works = false;
                }
            }

            if (works) total += id;
        }

        System.out.println(total);
    }
}
