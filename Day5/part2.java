package Day5;

import java.io.*;
import java.util.*;

public class part2 {
    public static void main (String[] args) throws IOException {
        File f = new File("Day5/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        String[] s = br.readLine().replaceAll("\s+", " ").split(" ");
        ArrayList<Long> data = new ArrayList<>();
        for (int i = 1; i < s.length; i++) {
            data.add(Long.parseLong(s[i]));
        }

        boolean mapping = false;
        ArrayList<Long> mapped = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            if (line.contains(":")) {
                mapping = true;
                continue;
            }

            if (line.isEmpty() && mapping) {
                mapping = false;
                mapped.addAll(data);
                data = new ArrayList<>(mapped);
                mapped = new ArrayList<>();
                continue;
            } else if (line.isEmpty()) continue;

            String[] l = line.split(" ");
            long startD = Long.parseLong(l[0]);
            long startS = Long.parseLong(l[1]);
            long range = Long.parseLong(l[2]);

            ArrayList<Long> tx = new ArrayList<>();

            for (int i = 0; i < data.size(); i += 2) {
                ArrayList<Long> temp = split(data.get(i), data.get(i+1), startS, range);
                if (temp.size() == 2) {
                    mapped.add(startD + temp.get(0) - startS);
                    mapped.add(temp.get(1));
                } else if (temp.size() > 2) {
                    mapped.add(startD + temp.remove(0) - startS);
                    mapped.add(temp.remove(0));

                    tx.addAll(temp);
                } else {
                    tx.add(data.get(i));
                    tx.add(data.get(i+1));
                }
            }

            data = new ArrayList<>(tx);
        }

        mapped.addAll(data);
        System.out.println(mapped);
        long min = mapped.get(0);

        for (int i = 2; i < mapped.size(); i += 2) {
            if (mapped.get(i) < min) min = mapped.get(i);
        }

        System.out.println(min);
    }

    public static ArrayList<Long> split(long s, long r, long sourceStart, long range) throws IOException { //ERROR negative numbers
        ArrayList<Long> ret = new ArrayList<>();

        if (s >= sourceStart && s + r <= sourceStart + range) { //split 1
            ret.add(s);
            ret.add(r);
        } else if (s < sourceStart && s + r > sourceStart + range) { //split 3
            ret.add(sourceStart);
            ret.add(range);
            ret.add(s);
            ret.add(sourceStart - s);
            ret.add(sourceStart + range);
            ret.add(s + r - sourceStart - range);
        } else if (s + r >= sourceStart && s + r <= sourceStart + range){ //split 2
            ret.add(sourceStart);
            ret.add(s + r - sourceStart);
            ret.add(s);
            ret.add(sourceStart - s);
        } else if (s >= sourceStart && s <= sourceStart + range){ //split 2
            ret.add(s);
            ret.add(sourceStart+range-s);
            ret.add(sourceStart + range);
            ret.add(s + r - sourceStart - range);
        }

        return ret;
    }
}
