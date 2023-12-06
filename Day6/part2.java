package Day6;

import java.util.*;
import java.io.*;

public class part2 {
    public static void main(String[] args) throws IOException {
        File f = new File("Day6/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        long time = Long.parseLong(br.readLine().replaceAll("\s+", "").split(":")[1]);
        long distance = Long.parseLong(br.readLine().replaceAll("\s+", "").split(":")[1]);

        long slow = (long) Math.ceil((-time + Math.sqrt(time*time - 4 * distance))/(-2));
        long fast = (long) Math.floor((-time - Math.sqrt(time*time - 4 * distance))/(-2));

        System.out.println(fast - slow + 1);
    }
}
