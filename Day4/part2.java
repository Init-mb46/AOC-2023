package Day4;

import java.util.*;
import java.io.*;

public class part2 {
    public static ArrayList<String> cards = new ArrayList<>();
    public static void main (String[] args) throws IOException {
        File f = new File("Day4/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;

        int t = 0;

        while ((line = br.readLine()) != null) {
            cards.add(line);
        }

        for (int i = 0; i < cards.size(); i ++) {
            t += processCards(cards.get(i));
        }

        System.out.println(t);
    }

    public static int processCards(String Scard) throws IOException {
        String[] card = Scard.replaceAll("\s+", " ").split(" ");

        int processed = 1;
        int cardn = Integer.parseInt(card[1].substring(0,card[1].length()-1));

        ArrayList<Integer> winners = new ArrayList<>();
        int i = 2;
        while (!card[i].equals("|")) {
            winners.add(Integer.parseInt(card[i]));
            i++;
        }
        int n = 0;
        for (int j = i + 1; j < card.length; j++) {
            if (winners.contains(Integer.parseInt(card[j]))) {
                n++;
                processed += processCards(cards.get(cardn+n-1));
            }
        }

        return processed;
    }
}
