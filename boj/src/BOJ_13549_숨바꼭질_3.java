import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_13549_숨바꼭질_3 {

    static int N, K;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[100001];

        bfs();
        System.out.println(dist[K]);
    }

    private static void bfs() {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(N);
        Arrays.fill(dist, -1);
        dist[N] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == K) {
                return;
            }

            for (int i = 0; i < 3; i++) {
                int nr;
                if (i == 0) {
                    nr = cur * 2;
                } else if (i == 1) {
                    nr = cur + 1;
                } else {
                    nr = cur - 1;
                }

                if (nr < 0 || nr > 100000) {
                    continue;
                }

                if (dist[nr] != -1) {
                    continue;
                }

                if (i == 0) {
                    dist[nr] = dist[cur];
                    q.offerFirst(nr);
                } else {
                    dist[nr] = dist[cur] + 1;
                    q.offer(nr);
                }
            }
        }
    }

}
