import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14248_점프_점프 {

    static int N, S, cnt;
    static int[] dist;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dist = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }
        S = Integer.parseInt(br.readLine());

        bfs();

        System.out.println(cnt);
    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(S);
        isVisited = new boolean[N + 1];
        isVisited[S] = true;
        cnt++;

        while (!q.isEmpty()) {
            int cur = q.poll();

            int plus = cur + dist[cur];
            int minus = cur - dist[cur];

            isValid(q, plus);
            isValid(q, minus);
        }
    }

    private static void isValid(Queue<Integer> q, int nc) {
        if (nc < 1 || nc > N) {
            return;
        }

        if (isVisited[nc]) {
            return;
        }

        cnt++;
        q.offer(nc);
        isVisited[nc] = true;
    }

}
