package Day5;

import java.io.*;
import java.util.*;

public class part1 {
    public static void main (String[] args) throws IOException {
        File f = new File("Day5/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        String[] s = br.readLine().replaceAll("\s+", " ").split(" ");
        long[] data = new long[s.length-1];
        for (int i = 1; i < s.length; i++) {
            data[i-1] = Long.parseLong(s[i]);
        }

        long[] map = flushMap(data.length);
        boolean mapping = false;

        while ((line = br.readLine()) != null) {
            if (line.contains(":")) {
                mapping = true;
                continue;
            }

            if (line.isEmpty() && mapping) {
                mapping = false;
                for (int i = 0; i < data.length; i++) {
                    if (map[i] == -1) {
                        map[i] = data[i];
                    }
                }
                data = map.clone();
                map = flushMap(data.length);
                continue;
            } else if (line.isEmpty()) continue;

            String[] l = line.split(" ");
            long startD = Long.parseLong(l[0]);
            long startS = Long.parseLong(l[1]);
            long n = Long.parseLong(l[2]);

            for (int i = 0; i < data.length; i++) {
                if (data[i] >= startS && data[i] < startS + n) { //can be mapped
                    map[i] = startD + data[i] - startS;
                }
            }
        }

        for (int i = 0; i < data.length; i++) {
            if (map[i] == -1) {
                map[i] = data[i];
            }
        }
        data = map.clone();

        long min = data[0];
        for (long datum : data) {
            if (datum < min) min = datum;
        }

        System.out.println(min);
    }

    public static long[] flushMap(int length) {
        long[] map = new long[length];
        Arrays.fill(map, -1);
        return map;
    }

}
