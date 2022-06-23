import java.io.*;
import java.util.HashSet;

public class BOJ_3052_나머지 {
    public static void main(String[] args) throws IOException {
        firstSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        HashSet<Integer> hs = new HashSet<Integer>();
        for (int i = 1; i <= 10; i++) {
            int number = Integer.parseInt(br.readLine()) % 42;
            hs.add(number);
        }

        sb.append(hs.size());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
