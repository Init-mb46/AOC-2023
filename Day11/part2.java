package Day11;

import java.util.*;
import java.io.*;

public class part2 {
    public static void main(String[] args) throws IOException {
        File f = new File("Day11/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String l;
        ArrayList<ArrayList<Character>> universe = new ArrayList<>();


        ArrayList<Integer> rowsExpansion = new ArrayList<>();
        int counter = 0;

        while ((l = br.readLine()) != null) {
            boolean rGalaxy = false;
            ArrayList<Character> chars = new ArrayList<>();
            char[] c = l.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == '#') rGalaxy = true;
                chars.add(c[i]);
            }
            universe.add(chars);
            if (!rGalaxy) rowsExpansion.add(counter);
            counter++;
        }

        ArrayList<Integer> columnsExpansion = new ArrayList<>();
        ArrayList<String> galaxies = new ArrayList<>();

        for (int j = 0; j < universe.get(0).size(); j++) {
            boolean cGalaxy = false;
            for (int i = 0; i < universe.size(); i++) {
                if (universe.get(i).get(j) == '#') {
                    cGalaxy = true;
                    galaxies.add(i + " " + j);
                }
            }
            if (!cGalaxy) columnsExpansion.add(j);
        }

        long total = 0;

        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                String[] xyA = galaxies.get(i).split(" ");
                String[] xyB = galaxies.get(j).split(" ");

                int add = 0;

                for (int r : rowsExpansion) {
                    if ((r > Integer.parseInt(xyA[0]) && r < Integer.parseInt(xyB[0])) || (r < Integer.parseInt(xyA[0]) && r > Integer.parseInt(xyB[0]))) add += 999999;
                }
                for (int c : columnsExpansion) {
                    if ((c > Integer.parseInt(xyA[1]) && c < Integer.parseInt(xyB[1])) || (c < Integer.parseInt(xyA[1]) && c > Integer.parseInt(xyB[1]))) add += 999999;
                }

                total += Math.abs(Integer.parseInt(xyA[0])-Integer.parseInt(xyB[0])) + Math.abs(Integer.parseInt(xyA[1])-Integer.parseInt(xyB[1])) + add;
            }
        }

        System.out.println(total);
    }
}
