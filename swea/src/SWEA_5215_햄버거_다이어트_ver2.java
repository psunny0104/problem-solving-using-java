import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215_햄버거_다이어트_ver2 {

    static int N, LIMIT;
    static int[][] dp;
    static Ingredient[] ingredients;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            LIMIT = Integer.parseInt(st.nextToken());

            ingredients = new Ingredient[N + 1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int calorie = Integer.parseInt(st.nextToken());
                ingredients[i] = new Ingredient(score, calorie);
            }

            dp = new int[N + 1][LIMIT + 1];

            for (int i = 1; i <= N; i++) {
                for (int k = 1; k <= LIMIT; k++) {
                    int pivot = k - ingredients[i].calorie;
                    if (pivot < 0) {
                        dp[i][k] = dp[i - 1][k];
                    } else {
                        dp[i][k] = Math.max(dp[i - 1][k-ingredients[i].calorie] + ingredients[i].score, dp[i - 1][k]);
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dp[N][LIMIT]).append("\n");
        }
        System.out.print(sb);
    }

    private static class Ingredient {

        int score, calorie;

        public Ingredient(int score, int calorie) {
            this.score = score;
            this.calorie = calorie;
        }
    }
}
