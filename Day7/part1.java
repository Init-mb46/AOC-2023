package Day7;

import java.io.*;
import java.util.*;

public class part1 {

    public static void main(String[] args) throws IOException {
        File f = new File("Day7/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        ArrayList<String> cards = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            cards.add(line);
            System.out.println(classify(line));
        }

        sort(cards);
        long total = 0;

        for (int i = 1; i <= cards.size(); i++) {
            int bid = Integer.parseInt(cards.get(i-1).split(" ")[1]);
            total += (long) bid * i;
            System.out.println(i + " " + Arrays.toString(getValuesArray(cards.get(i - 1))));
        }

        System.out.println(total);
    }

    public static int classify(String card) {
        char[] hand = card.split(" ")[0].toCharArray();
        int mostMatch = 0;
        int mostMatch2 = 0;

        for (int i = 0; i < hand.length; i++) {
            int matches = 1;
            if (hand[i] == 'X') continue;
            for (int j = 0; j < hand.length; j++) {
                if (i == j) continue;
                if (hand[i] == hand[j]) {
                    matches++;
                    hand[j] = 'X';
                }
            }
            if (matches > mostMatch) {
                mostMatch2 = mostMatch;
                mostMatch = matches;
            } else if (matches > mostMatch2) {
                mostMatch2 = matches;
            }
        }

        if (mostMatch == 5) {
            return 1; //five of a kind
        } else if (mostMatch == 4) {
            return 2; //four of a kind
        } else if (mostMatch == 3 && mostMatch2 == 2) {
            return 3; //full house
        } else if (mostMatch == 3) {
            return 4; //three of a kind
        } else if (mostMatch == 2 && mostMatch2 == 2) {
            return 5; //two pair
        } else if (mostMatch == 2) {
            return 6; //one pair
        }
        return 7; //high card
    }

    public static boolean isHigher(String c2, String c1) { //true if c2 is higher than c1
        int c1V = classify(c1);
        int c2V = classify(c2);
        if (c1V < c2V) {
            return false;
        } else if (c1V > c2V) {
            return true;
        } else {
            int[] card1 = getValuesArray(c1);
            int[] card2 = getValuesArray(c2);
            for (int i = 0; i < 5; i++) {
                if (card1[i] > card2[i]) {
                    return false;
                } else if (card1[i] < card2[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int[] getValuesArray(String card) {
        int[] values = new int[5];
        char[] hand = card.split(" ")[0].toCharArray();

        for (int i = 0; i < hand.length; i++) {
            if (Character.isDigit(hand[i])) {
                values[i] = Integer.parseInt(String.valueOf(hand[i]));
            } else {
                switch (hand[i]) {
                    case 'A':
                        values[i] = 14;
                        break;
                    case 'K':
                        values[i] = 13;
                        break;
                    case 'Q':
                        values[i] = 12;
                        break;
                    case 'J':
                        values[i] = 11;
                        break;
                    default:
                        values[i] = 10;
                        break;
                }
            }
        }
        return values;
    }

    public static void sort(ArrayList<String> cards) //least to greatest
    {
        for (int i = 1; i < cards.size(); ++i) {
            String c1 = cards.get(i);
            int j = i - 1;

            while (j >= 0 && isHigher(cards.get(j), c1)) {
                cards.set(j+1, cards.get(j));
                j = j - 1;
            }
            cards.set(j + 1, c1);
        }
    }
}
