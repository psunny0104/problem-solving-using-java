import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_15664_N과_M_10 {

    // 조합 -> 결과 중복 제거
    static int N, M;
    static int[] numbers, prevNumbers, inputs;
    static Set<String> linkedHashSet = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        N = Integer.parseInt(in.nextToken());
        M = Integer.parseInt(in.nextToken());

        numbers = new int[M];
        prevNumbers = new int[M];
        inputs = new int[N];

        in = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(in.nextToken());
        }

        Arrays.sort(inputs);
        comb(0, 0);
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (String s : linkedHashSet) {
            sb.append(s);
        }
        System.out.print(sb);
    }

    private static void comb(int cnt, int start) {
        StringBuilder sb = new StringBuilder();
        if (cnt == M) {
            for (int number : numbers) {
                sb.append(number).append(" ");
            }
            sb.append("\n");
            linkedHashSet.add(sb.toString());
            return;
        }

        for (int i = start; i < N; i++) {
            numbers[cnt] = inputs[i];
            comb(cnt + 1, i + 1);
        }
    }
}
