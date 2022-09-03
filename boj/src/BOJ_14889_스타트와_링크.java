import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와_링크 {

    static int N, K, answer = Integer.MAX_VALUE;
    static boolean[] isSelected;
    static int[][] scores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = N / 2;

        scores = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isSelected = new boolean[N + 1];
        cmb(1, 1);
        System.out.println(answer);
    }

    private static void cmb(int cnt, int start) {
        if (cnt == K + 1) {
            List<Integer> starts = new ArrayList<>();
            List<Integer> links = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                if (isSelected[i]) {
                    starts.add(i);
                } else {
                    links.add(i);
                }
            }

            int startScore = getScores(starts);
            int linkScore = getScores(links);

            answer = Math.min(answer, Math.abs(startScore - linkScore));
            return;
        }

        for (int i = start; i <= N; i++) {
            isSelected[i] = true;
            cmb(cnt + 1, i + 1);
            isSelected[i] = false;
        }
    }

    private static int getScores(List<Integer> team) {
        int score = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                score += scores[team.get(i)][team.get(j)];
            }
        }

        return score;
    }

}
