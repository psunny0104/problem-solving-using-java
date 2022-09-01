import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15649_N과_M_1 {

    // 순열
    static int N, M;
    static boolean[] isSelected;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());
        N = Integer.parseInt(in.nextToken());
        M = Integer.parseInt(in.nextToken());

        isSelected = new boolean[N + 1]; // 1 ~ N
        numbers = new int[M];  // 1 ~ M

        perm(0);
        System.out.print(sb);
    }

    private static void perm(int cnt) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(numbers[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (isSelected[i]) {
                continue;
            }

            isSelected[i] = true;
            numbers[cnt] = i;
            perm(cnt + 1);
            isSelected[i] = false;
        }
    }
}
