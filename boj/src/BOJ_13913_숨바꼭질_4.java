import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13913_숨바꼭질_4 {

    static int N, K;
    static int[] dist;
    static int[] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[100001];
        prev = new int[100001];

        if (N == K) {
            sb.append(0).append("\n");
            sb.append(N);
        } else {
            bfs();

            sb.append(dist[K]).append("\n");

            Deque<Integer> stk = new ArrayDeque<>();
            stk.offer(K);
            int cur = prev[K];
            while (cur != N) {
                stk.offer(cur);
                cur = prev[cur];
            }
            stk.offer(N);

            while (!stk.isEmpty()) {
                sb.append(stk.pollLast()).append(" ");
            }
        }

        System.out.print(sb);
    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        Arrays.fill(dist, -1);
        dist[N] = 0;
        prev[N] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == K) {
                break;
            }

            for (int d = 0; d < 3; d++) {
                int nc;
                
                if (d == 0) {
                    nc = cur * 2;
                } else if (d == 1) {
                    nc = cur + 1;
                } else {
                    nc = cur - 1;
                }

                if (nc < 0 || nc > 100000) {
                    continue;
                }

                if (dist[nc] != -1) {
                    continue;
                }

                dist[nc] = dist[cur] + 1;
                prev[nc] = cur;
                q.offer(nc);
            }
        }
    }

}
