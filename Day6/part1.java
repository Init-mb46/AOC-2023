package Day6;

import java.util.*;
import java.io.*;

public class part1 {
    public static void main(String[] args) throws IOException {
        File f = new File("Day6/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<Integer> times = new ArrayList<>();
        ArrayList<Integer> distances = new ArrayList<>();

        String[] line1 = br.readLine().replaceAll("\s+", " ").split(" ");
        String[] line2 = br.readLine().replaceAll("\s+", " ").split(" ");

        for (int i = 1; i < line1.length; i++) times.add(Integer.valueOf(line1[i]));
        for (int i = 1; i < line2.length; i++) distances.add(Integer.valueOf(line2[i]));

        int total = 1;

        for (int i = 0; i < times.size(); i++) {
            int time = times.get(i);
            int distance = distances.get(i);
            int slowest = 0;
            int fastest = 0;

            for (int speed = 1; speed <= time; speed++) {
                if (slowest != 0 && (time - speed) * speed <= distance) {
                    fastest = speed - 1;
                    break;
                } else if (slowest == 0) {
                    if ((time - speed) * speed > distance) {
                        slowest = speed;
                    }
                }
            }

            total *= (fastest - slowest + 1);
        }

        System.out.println(total);
    }
}
