package Day10;

import java.util.*;
import java.io.*;

public class part1 {
    public static void main(String[] args) throws IOException {
        File f = new File("Day10/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        ArrayList<char[]> pipeMap = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            pipeMap.add(line.toCharArray());
        }

        int X = 0;
        int Y = 0;

        for (int i = 0; i < pipeMap.size(); i++) {
            for (int j = 0; j < pipeMap.get(i).length; j++) {
                if (pipeMap.get(i)[j] == 'S') {
                    Y = j;
                    X = i;
                }
            }
        }

        int prevX = -1;
        int prevY = -1;
        int pipeDistance = 0;

        char currentPipe = pipeMap.get(X)[Y];

        for (int i = X - 1; i <= X + 1; i++){
            if (i == X) continue;
            if (i < 0 || i >= pipeMap.size()) continue;
            if (pipeMap.get(i)[Y] == '.') continue;

            if (direction(pipeMap.get(i)[Y])[1] == 1 && X > i) {
                prevX = X;
                prevY = Y;
                X = i;
                currentPipe = pipeMap.get(i)[Y];
                break;
            } else if (direction(pipeMap.get(i)[Y])[0] == -1) {
                prevX = X;
                prevY = Y;
                X = i;
                currentPipe = pipeMap.get(i)[Y];
                break;
            }
        }

        if (prevX == -1) {
            for (int i = Y - 1; i <= Y + 1; i++){
                if (i == Y) continue;
                if (i < 0 || i >= pipeMap.get(X).length) continue;
                if (pipeMap.get(X)[i] == '.') continue;

//                System.out.println(pipeMap);
                if (direction(pipeMap.get(X)[i])[2] == 1 && Y > i) {
                    prevX = X;
                    prevY = Y;
                    Y = i;
                    currentPipe = pipeMap.get(X)[i];
                    break;
                } else if (direction(pipeMap.get(X)[i])[3] == -1) {
                    prevX = X;
                    prevY = Y;
                    Y = i;
                    currentPipe = pipeMap.get(X)[i];
                    break;
                }
            }
        }

        pipeDistance++;

        while (currentPipe != 'S') {
            int[] directions = direction(currentPipe);
            boolean altered = false;

            for (int i = 0; i < 2; i++) {
                if (directions[i] == 0) continue;
                if (directions[i] + X < pipeMap.size() && directions[i] + X >= 0 && directions[i] + X != prevX) {
                    if (pipeMap.get(directions[i]+X)[Y] == '.') continue;
                    prevX = X;
                    prevY = Y;
                    X = X + directions[i];
                    currentPipe = pipeMap.get(X)[Y];
                    altered = true;
                    break;
                }
            }
            for (int i = 2; i < 4; i++) {
                if (altered) break;
                if (directions[i] == 0) continue;
                if (directions[i] + Y < pipeMap.get(X).length && directions[i] + Y >= 0 && directions[i] + Y != prevY) {
                    if (pipeMap.get(X)[Y+directions[i]] == '.') continue;
                    prevX = X;
                    prevY = Y;
                    Y = Y + directions[i];
                    currentPipe = pipeMap.get(X)[Y];
                    break;
                }
            }

            if (currentPipe != 'S') pipeDistance++;
        }

        System.out.println(pipeDistance + " ---> " + (1 + pipeDistance/2));
    }

    public static int[] direction (char C) {
        int[] directions = new int[4]; // north -1  south 1  east 1  west -1
        switch (C) {
            case '|':
                directions[0] = -1;
                directions[1] = 1;
                break;
            case '-':
                directions[2] = 1;
                directions[3] = -1;
                break;
            case 'L':
                directions[0] = -1;
                directions[2] = 1;
                break;
            case 'J':
                directions[0] = -1;
                directions[3] = -1;
                break;
            case '7':
                directions[1] = 1;
                directions[3] = -1;
                break;
            default: //F
                directions[2] = 1;
                directions[1] = 1;
                break;
        }
        return directions;
    }
}
