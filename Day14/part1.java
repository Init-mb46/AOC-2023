package Day14;

import java.util.*;
import java.io.*;

public class part1 {
    public static void main(String[] args) throws IOException {
        File f = new File("Day14/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        ArrayList<ArrayList<Character>> map = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            ArrayList<Character> x = new ArrayList<>();
            char[] c = line.toCharArray();
            for (int i = 0; i < c.length; i++) x.add((c[i]));
            map.add(x);
        }

        int total = 0;

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) == 'O') {
                    int iy = i - 1;
                    while (iy >= 0 && map.get(iy).get(j) == '.') {
                        map.get(iy).set(j, 'O');
                        map.get(iy + 1).set(j, '.');
                        iy--;
                    }
                    total += map.size() - (iy + 1);
                }
            }
        }

        System.out.println(total);
    }
}
