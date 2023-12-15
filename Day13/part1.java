package Day13;

import java.util.*;
import java.io.*;

public class part1 {
    public static void main(String[] args) throws IOException{
        File f = new File("Day13/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        long total = 0;
        ArrayList<String> pattern = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
                //parse
                int t = process(pattern);
                total += t;
                pattern = new ArrayList<>();
                continue;
            }
            pattern.add(line);
        }
        total += process(pattern);

        System.out.println(total);
    }

    public static int process(ArrayList<String> pattern) {
        int total = 0;
        boolean row = false;
        int maxRef = 0;
        int maxRefPos = 0;

        for (int i = 1; i < pattern.get(0).length(); i++) {
            int max = -1;
            for (int j = 0; j < pattern.size(); j++) {
                int locMax = 0;
                for (int k = 0; k <= i; k++) {
                    if (i - k - 1 < 0 || i + k >= pattern.get(j).length()) break;
                    if (pattern.get(j).charAt(i-k-1) != pattern.get(j).charAt(i+k)) {
                        break;
                    }
                    locMax++;
                }
                if (max == -1 || max > locMax) max = locMax;
            }
            if ((i - max == 0 || i + max == pattern.get(0).length()) && max > maxRef) {
                maxRef = max;
                maxRefPos = i;
            }
        }

        for (int i = 1; i < pattern.size(); i++) {
            int max = 0;

            for (int k = 0; k <= i; k++) {
                if (i - k - 1 < 0 || i + k >= pattern.size()) break;
                if (!pattern.get(i-k-1).equals(pattern.get(i+k))) {
                    break;
                }
                max++;
            }

            if ((i - max == 0 || i + max == pattern.size()) && max > maxRef) {
                row = true;
                maxRef = max;
                maxRefPos = i;
            }
        }

        if (row) {
            total = 100 * maxRefPos;
        } else {
            total = maxRefPos;
        }
        return total;
    }
}
