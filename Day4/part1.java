package Day4;

import java.util.*;
import java.io.*;

public class part1 {
    public static void main (String[] args) throws IOException {
        File f = new File("Day4/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        int total = 0;
        String line;

        while ((line = br.readLine()) != null) {
            String[] cards = line.replaceAll("\s+", " ").split(" ");
            ArrayList<Integer> winners = new ArrayList<>();
            int wins = 0;

            boolean bar = false;

            for (int i = 2; i < cards.length; i++) {
                if (!cards[i].equals("|") && !bar) winners.add(Integer.parseInt(cards[i]));
                if (cards[i].equals("|")) {
                    bar = true;
                    continue;
                }
                if (bar) {
                    if (winners.contains(Integer.parseInt(cards[i]))) wins++;
                }
            }

            total += (int) Math.pow(2, wins-1);
        }
        System.out.println(total);
    }
}
