package Day16;

import java.util.*;
import java.io.*;

public class part2 {
    public static void main(String[] args) throws IOException {
        File f = new File("Day16/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        ArrayList<char[]> map = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            map.add(line.toCharArray());
        }

        int maxEnergized = 0;
        ArrayList<int[]> startConfigs = new ArrayList<>();
        for (int i = 0 ; i < map.get(0).length; i++) {
            int[] o1 = new int[4];
            int[] o2 = new int[4];

            o1[0] = 0; o1[1] = i; o1[2] = 1; o1[3] = 0;
            o2[0] = map.size()-1; o2[1] = i; o2[2] = -1; o2[3] = 0;

            startConfigs.add(o1); startConfigs.add(o2);
        }

        for (int i = 0 ; i < map.size(); i++) {
            int[] o1 = new int[4];
            int[] o2 = new int[4];

            o1[0] = i; o1[1] = 0; o1[2] = 0; o1[3] = 1;
            o2[0] = i; o2[1] = map.get(0).length - 1; o2[2] = 0; o2[3] = -1;

            startConfigs.add(o1); startConfigs.add(o2);
        }

        for (int[] startConfig : startConfigs) {
            ArrayList<int[]> instructions = new ArrayList<>();
            ArrayList<String> usedInstructions = new ArrayList<>();

            int x = startConfig[0];
            int y = startConfig[1];
            int d1 = startConfig[2];
            int d2 = startConfig[3];

            ArrayList<int[]> dirCheck = findNext(map, x, y, d1, d2);
            for (int i = 0; i < dirCheck.size(); i++) {
                int[] e = new int[4];
                e[0] = x; e[1] = y;
                e[2] = dirCheck.get(i)[0]; e[3] = dirCheck.get(i)[1];
                instructions.add(e);
            }

            int energized = 1;
            int[][] energizedMap = new int[map.size()][map.get(0).length];
            energizedMap[x][y] = 1;

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
                        energizedMap[posx+xdir][posy+ydir]++;
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
            if (energized > maxEnergized) maxEnergized = energized;
        }

        System.out.println(maxEnergized);
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
