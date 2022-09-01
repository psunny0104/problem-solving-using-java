import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15655_N과_M_6 {

    // 조합
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] numbers, inputs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        N = Integer.parseInt(in.nextToken());
        M = Integer.parseInt(in.nextToken());

        numbers = new int[M];
        inputs = new int[N];

        in = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(in.nextToken());
        }

        Arrays.sort(inputs);
        comb(0, 0);
        System.out.print(sb);
    }

    private static void comb(int cnt, int start) {
        if (cnt == M) {
            for (int number : numbers) {
                sb.append(number).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < N; i++) {
            numbers[cnt] = inputs[i];
            comb(cnt + 1, i + 1);
        }
    }
}
