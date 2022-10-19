import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3980_선발_명단 {

    static int T, maxSum;
    static int[][] stats;
    static boolean[] isSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        stats = new int[11][11];

        for (int tc = 1; tc <= T; tc++) {
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    stats[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxSum = Integer.MIN_VALUE;
            isSet = new boolean[11];
            search(0, 0);
            System.out.println(maxSum);
        }


    }

    private static void search(int cnt, int sum) {
        if (cnt == 11) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (stats[cnt][i] == 0) {
                continue;
            }

            if (isSet[i]) {
                continue;
            }

            isSet[i] = true;
            search(cnt + 1, sum + stats[cnt][i]);
            isSet[i] = false;
        }
    }

}
