package Day1;

import java.io.*;
import java.util.*;

class part1 {
    public static void main (String args[]) throws IOException {
        ArrayList<String> Lines = getInput();
        int t = 0;

        for (String line : Lines) {
            int num = 0;
            String n = "";
            boolean first = true;
            for (char c : line.toCharArray()) {
                if (Character.isDigit(c)) {
                    if (first) {
                        first = false;
                        num += Integer.parseInt(String.valueOf(c));
                    }
                    n = String.valueOf(c);
                }
            }
            num = Integer.parseInt(String.valueOf(num)+n);
            t += num;
        }

        System.out.println(t);
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
