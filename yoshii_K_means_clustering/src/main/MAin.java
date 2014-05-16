package main;
import java.io.*;
import java.util.*;

    public class MAin {
    private static ArrayList<String> linesCollection;
    private static BufferedReader in;

    public static void collectLines() throws EOFException, IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                throw new EOFException();
            }
            linesCollection.add(line);
        }
    }

    public static void main(String[] args) {
        linesCollection = new ArrayList<String>();
        in = new BufferedReader(new InputStreamReader(System.in));
        try {
            collectLines();
        } catch (EOFException e) {
        } catch (IOException e) {
            System.err.println("入出力に関する例外発生");
            return;
        }

        for (int i = (linesCollection.size() - 1); !(linesCollection.isEmpty()) && i >= 0; i--) {
            System.out.println(linesCollection.get(i));
        }
    }
}
