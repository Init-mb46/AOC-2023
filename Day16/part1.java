package Day16;

import java.util.*;
import java.io.*;

public class part1 {
    public static void main(String[] args) throws IOException {
        File f = new File("Day16/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        ArrayList<char[]> map = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            map.add(line.toCharArray());
        }

        ArrayList<int[]> instructions = new ArrayList<>();
        ArrayList<String> usedInstructions = new ArrayList<>();

        ArrayList<int[]> dirCheck = findNext(map, 0, 0, 0, 1);
        for (int i = 0; i < dirCheck.size(); i++) {
            int[] e = new int[4];
            e[0] = 0; e[1] = 0;
            e[2] = dirCheck.get(i)[0]; e[3] = dirCheck.get(i)[1];
            instructions.add(e);
        }

        int energized = 1;
        int[][] energizedMap = new int[map.size()][map.get(0).length];
        energizedMap[0][0] = 1;

        while (!instructions.isEmpty()) {

            int[] cur = instructions.remove(0);
            if (usedInstructions.contains(Arrays.toString(cur))) continue;
            usedInstructions.add(Arrays.toString(cur));

            int xdir = cur[2];
            int ydir = cur[3];
            int posx = cur[0];
            int posy = cur[1];

            if (posx + xdir >= 0 && posx + xdir < map.size() && posy + ydir >= 0 && posy + ydir < map.get(0).length) {
                if (energizedMap[posx + xdir][posy + ydir] == 0) {
                    energized++;
                    energizedMap[posx + xdir][posy + ydir] += 1;
                }

                posx += xdir;
                posy += ydir;

                ArrayList<int[]> res = findNext(map, posx, posy, xdir, ydir);
                for (int j = 0; j < res.size(); j++) {
                    int[] entry = new int[4];
                    entry[0] = posx; entry[1] = posy;
                    entry[2] = res.get(j)[0]; entry[3] = res.get(j)[1];
                    instructions.add(entry);
                }
            }

        }
        System.out.println(energized);
    }

    public static ArrayList<int[]> findNext (ArrayList<char[]> map, int posx, int posy, int dirx, int diry) {
        ArrayList<int[]> dirs = new ArrayList<>();
        int[] d2 = new int[2];
        char c = map.get(posx)[posy];
        switch (c) {
            case ('/'):
                d2[0] = -diry;
                d2[1] = -dirx;
                dirs.add(d2);
                break;
            case ('\\'):
                d2[0] = diry;
                d2[1] = dirx;
                dirs.add(d2);
                break;
            default:
                if ((dirx == 0 && c == '-') || (diry == 0 && c == '|') || c == '.') {
                    d2[0] = dirx; d2[1] = diry;
                    dirs.add(d2);
                } else {
                    int[] d3 = new int[2];
                    d2[0] = diry;
                    d2[1] = dirx;
                    d3[0] = -diry;
                    d3[1] = -dirx;
                    dirs.add(d2); dirs.add(d3);
                }
                break;
        }
        return dirs;
    }
}
