import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_15665_N과_M_11 {
    // 중복 순열 -> 결과 중복 제거
    static int N, M;
    static int[] numbers, inputs;
    static Set<String> hashSet = new LinkedHashSet<>();
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

        perm(0);
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (String s : hashSet) {
            sb.append(s);
        }
        System.out.print(sb);
    }

    private static void perm(int cnt) {
        StringBuilder sb = new StringBuilder();
        if (cnt == M) {
            for (int number : numbers) {
                sb.append(number).append(" ");
            }
            sb.append("\n");
            hashSet.add(sb.toString());
            return;
        }

        for (int i = 0; i < N; i++) {
            numbers[cnt] = inputs[i];
            perm(cnt + 1);
        }
    }
}
