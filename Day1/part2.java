package Day1;

import java.io.*;
import java.util.*;

public class part2 {
    public static HashMap<String,Integer> numbers = new HashMap<>();

    public static void main (String args[]) throws IOException{
        ArrayList<String> input = getInput();

        int t = 0;

        {// initialize map
            numbers.put("one", 1);
            numbers.put("two", 2);
            numbers.put("three", 3);
            numbers.put("four", 4);
            numbers.put("five", 5);
            numbers.put("six", 6);
            numbers.put("seven", 7);
            numbers.put("eight", 8);
            numbers.put("nine", 9);
        }

        for (String line : input) {
            t += num(line);
        }

        System.out.println(t);
    }

    public static int num(String line) throws IOException {
        String n1 = ""; int t1 = -1;
        String n2 = ""; int t2 = -1;
        String num3 = ""; int t3 = -1;
        String num4 = ""; int t4 = -1;

        boolean first = true;
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isDigit(c)) {
                if (first) {
                    first = false;
                    n1 = String.valueOf(c);
                    t1 = i;
                }
                n2 = String.valueOf(c);
                t2 = i;
            }
        }

        first = true;

        for (int i = 0; i < line.length(); i++) {
            for (String v : numbers.keySet()) {
                int x = line.indexOf(v, i);
                if (x == -1) continue;
                if (first) {
                    first = false;
                    num3 = String.valueOf(numbers.get(v));
                    t3 = x;
                    num4 = String.valueOf(numbers.get(v));
                    t4 = x;
                }
                if (x < t3) {
                    t3 = x;
                    num3 = String.valueOf(numbers.get(v));
                } else if (x > t4) {
                    t4 = x;
                    num4 = String.valueOf(numbers.get(v));
                }
            }
        }

        String res = "";

        if (t1 != -1 && t3 != -1) {
            res += (t3 < t1) ? num3 : n1;
        } else if (t1 != -1) {
            res += n1;
        } else {
            res += num3;
        }

        if (t2 != -1 && t4 != -1) {
            res += (t4 > t2) ? num4 : n2;
        } else if (t2 != -1) {
            res += n2;
        } else {
            res += num4;
        }

        System.out.println(res);
        return Integer.parseInt(res);
    }
    public static ArrayList<String> getInput() throws IOException {
        File f = new File("Day1/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line;
        ArrayList<String> lines = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
}
