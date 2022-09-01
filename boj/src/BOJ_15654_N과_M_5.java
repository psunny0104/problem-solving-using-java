import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15654_N과_M_5 {

    // 순열
    static int N, M;
    static int[] inputs, numbers;
    static boolean[] isSelected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        N = Integer.parseInt(in.nextToken());
        M = Integer.parseInt(in.nextToken());

        inputs = new int[N];
        isSelected = new boolean[N];
        numbers = new int[M];

        in = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(in.nextToken());
        }

        Arrays.sort(inputs);
        perm(0);
        System.out.print(sb);
    }

    private static void perm(int cnt) {
        if (cnt == M) {
            for (int number : numbers) {
                sb.append(number).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSelected[i]) {
                continue;
            }

            isSelected[i] = true;
            numbers[cnt] = inputs[i];
            perm(cnt + 1);
            isSelected[i] = false;
        }
    }
}
