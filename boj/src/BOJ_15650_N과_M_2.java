import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15650_N과_M_2 {

    // 조합
    static int N, M;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());
        N = Integer.parseInt(in.nextToken());
        M = Integer.parseInt(in.nextToken());

        numbers = new int[M];  // 1 ~ M

        comb(0, 1);
        System.out.print(sb);
    }

    private static void comb(int cnt, int start) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(numbers[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= N; i++) {
            numbers[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }
}
