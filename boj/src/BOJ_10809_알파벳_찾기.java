import java.io.*;
import java.util.Arrays;

public class BOJ_10809_알파벳_찾기 {
    public static void main(String[] args) throws IOException {
        firstSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[] location = new int[26];
        Arrays.fill(location, -1);

        String word = br.readLine();
        int order = -1;

        for (char alphabet : word.toCharArray()) {
            order += 1;
            if (location[alphabet - 'a'] != -1)
                continue;

            location[alphabet - 'a'] = order;
        }

        for (int cnt : location) {
            sb.append(cnt).append(' ');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
