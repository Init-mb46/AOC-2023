package Day9;

import java.util.*;
import java.io.*;

public class part2 {
    public static void main(String[] args) throws IOException {
        File f = new File("Day9/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        ArrayList<int[]> seq = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            String[] ns = line.split(" ");
            int[] n = new int[ns.length];
            for (int i = 0; i < ns.length; i++) {
                n[i] = Integer.parseInt(ns[i]);
            }
            seq.add(n);
        }

        int t = 0;
        for (int i = 0; i < seq.size(); i++) {
            int[] n = seq.get(i);
            int previous = findPreviousVal(n);
            t += previous;
            System.out.println(previous);
        }

        System.out.println(t);
    }

    public static int findPreviousVal(int[] seq) {
        ArrayList<Integer> firstVals = new ArrayList<>();
        int[] diff = seq.clone();
        firstVals.add(seq[0]);
        while (!allZero(diff)) {
            int[] temp = new int[diff.length - 1];
            for (int i = 1; i < diff.length; i++) {
                temp[i - 1] = diff[i] - diff[i - 1];
            }
            diff = temp;
            firstVals.add(diff[0]);
        }
        int x = firstVals.get(firstVals.size()-1);
        for (int i = firstVals.size() - 2; i >= 0; i--) {
            x = firstVals.get(i) - x;
        }
        return x;
    }

    public static boolean allZero(int[] a) {
        for (int ax : a) if (ax != 0) return false;
        return true;
    }
}
