import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_11003_최솟값_찾기 {

    static int N, L;
    static int[] D;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(in.nextToken());
        L = Integer.parseInt(in.nextToken());
        Deque<Pair> deque = new ArrayDeque<>();

        in = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int curValue = Integer.parseInt(in.nextToken());
            while (!deque.isEmpty() && deque.peekLast().value > curValue) {
                deque.pollLast();
            }
            deque.offerLast(new Pair(i, curValue));

            if (deque.peekFirst().idx < i - L + 1) {
                deque.pollFirst();
            }

            sb.append(deque.peekFirst().value).append(" ");
        }

        System.out.println(sb);
    }

    private static class Pair {

        int idx;
        int value;

        public Pair(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
}
