package Day8;

import java.io.*;
import java.util.*;

public class part2 {
    public static void main(String[] args) throws IOException {
        File f = new File("Day8/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        char[] instructions = br.readLine().toCharArray();
        br.readLine();

        HashMap<String, String[]> mapping = new HashMap<>();
        ArrayList<String> nodes = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            String key = line.substring(0,3);
            String[] values = line.substring(7,line.length()-1).split(", ");
            mapping.put(key, values);
            if (key.endsWith("A")) {
                nodes.add(key);
            }
        }

        long[] instructionCounts = new long[nodes.size()];

        int moves = 0;
        boolean complete = false;

        while (!complete) {
            for (int i = 0; i < instructions.length; i++) {
                moves++;
                complete = true;
                for (int j = 0; j < nodes.size(); j++) {
                    if (nodes.get(j).endsWith("Z")) continue;
                    String position = nodes.get(j);
                    if (instructions[i] == 'L') {
                        position = mapping.get(position)[0];
                    } else {
                        position = mapping.get(position)[1];
                    }
                    nodes.set(j, position);
                    complete = false;
                    if (nodes.get(j).endsWith("Z")) {
                        instructionCounts[j] = moves;
                    }
                }
                if (complete) {
                    break;
                }
            }
        }

        long total = 0;

        for (int i = 0; i < instructionCounts.length; i++) {
            if (total == 0) {
                total = instructionCounts[i];
                continue;
            }
            total = lcm(total, instructionCounts[i]);
        }

        System.out.println(total);
    }

    public static long lcm(long a, long b) {
        return (a*b)/gcd(a,b);
    }
    public static long gcd(long a, long b)
    {
        if (a == 0)
            return b;

        return gcd(b % a, a);
    }
}
