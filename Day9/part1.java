package Day9;

import java.util.*;
import java.io.*;

public class part1 {
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
            int next = findNextVal(n);
            t += next;
        }

        System.out.println(t);
    }

    public static int findNextVal(int[] seq) {
        ArrayList<Integer> finalVals = new ArrayList<>();
        int[] diff = seq.clone();
        finalVals.add(seq[seq.length - 1]);
        while (!allZero(diff)) {
            int[] temp = new int[diff.length - 1];
            for (int i = 1; i < diff.length; i++) {
                temp[i - 1] = diff[i] - diff[i - 1];
            }
            diff = temp;
            finalVals.add(diff[diff.length - 1]);
        }
        int x = 0;
        for (int i = finalVals.size() - 1; i >= 0; i--) {
            x += finalVals.get(i);
        }
        return x;
    }

    public static boolean allZero(int[] a) {
        for (int ax : a) if (ax != 0) return false;
        return true;
    }
}
