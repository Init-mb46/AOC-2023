package Day12;

import java.util.*;
import java.io.*;

public class part1 {
    public static void main(String[] args) throws IOException {
        File f = new File("Day12/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        ArrayList<String> seq = new ArrayList<>();
        ArrayList<ArrayList<Integer>> orders = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            seq.add(line.split(" ")[0]);
            String[] c = line.split(" ")[1].split(",");
            ArrayList<Integer> n = new ArrayList<>();
            for (int i = 0; i < c.length; i++) {
                n.add(Integer.parseInt(c[i]));
            }
            orders.add(n);
        }

        int t = 0;

        for (int i = 0; i < seq.size(); i++) {
           t += find(seq.get(i), orders.get(i));
        }

        System.out.println(t);
    }
    public static int find(String s, ArrayList<Integer> ord) {
        if (ord.isEmpty()) {
            return !s.contains("#") ? 1 : -1;
        }
        int p = ord.remove(0);
        if (s.length() < p) return -1;

        int total = 0;
        boolean hash = false;

        for (int i = 0; i < s.length(); i++) {
            boolean f = false;
            if (hash) break;
            if (s.charAt(i) == '?' || s.charAt(i) == '#') {
                f = true;
                boolean allHash = true;
                for (int j = i; j < i + p; j++) {
                    if (j >= s.length() || s.charAt(j) == '.') {
                        f = false;
                        allHash = false;
                        break;
                    }
                    if (s.charAt(j) != '#') {
                        allHash = false;
                    }
                }
                if (f && s.substring(0,i).contains("#")) break;

                if (f && i + p < s.length() && s.charAt(i + p) == '#') f = false;

                if (f && allHash) hash = true;
            }
            if (f) {
                int x = find((1 + p + i >= s.length()) ? "" : s.substring(i + p + 1), (ArrayList<Integer>) ord.clone());
                if (x != -1) total += x > 0 ? x : 1;
            }
        }

        if ( total == 0) {
            return -1;
        } else {
            return total;
        }
    }
}
