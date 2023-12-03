package Day3;

import java.util.*;
import java.io.*;

public class part2 {
    public static void main (String[] args) throws IOException {
        ArrayList<String> inputs = new ArrayList<>();

        File f = new File("Day3/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            inputs.add(line);
        }

        int total = 0;

        for (int i = 0; i < inputs.size(); i++) {
            String l = inputs.get(i);
            for (int j = 0; j < l.length(); j++) {
                if (l.charAt(j) == '*') {
                    //symbol
                    ArrayList<Integer> t = new ArrayList<>();

                    for (int a = i - 1; a <= i + 1; a++) {
                        for (int b = j - 1; b <= j + 1; b++) {
                            if (a == i && b == j) continue;
                            if (a < 0 || b < 0) continue;
                            if (Character.isDigit(inputs.get(a).charAt(b))) {
                                //part number found
                                int x = b-1;
                                int y = b+1;

                                String n = "" + inputs.get(a).charAt(b);
                                while (Character.isDigit(inputs.get(a).charAt(x))) {
                                    n = inputs.get(a).charAt(x) + n;
                                    x--;
                                    if (x < 0) break;
                                }

                                while (y < inputs.get(a).length() && Character.isDigit(inputs.get(a).charAt(y))) {
                                    n += inputs.get(a).charAt(y);
                                    y++;
                                }

                                if (t.contains(Integer.parseInt(n))) continue;
                                t.add(Integer.parseInt(n));
                            }
                        }
                    }

                    if (t.size() == 2) total += t.get(0) * t.get(1);
                }
            }
        }
        System.out.println(total);
    }
}
