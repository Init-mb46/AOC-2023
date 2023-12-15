package Day15;

import java.io.*;
import java.util.*;

public class part1 {
    public static void main(String[] args) throws IOException {
        File f = new File("Day15/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String[] codes = br.readLine().split(",");

        int t = 0;
        for (int i = 0; i < codes.length; i++) {
            t += hash(0, codes[i]);
        }

        System.out.println(t);
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
