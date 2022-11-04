import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1946_신입_사원 {

    static int T, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            PriorityQueue<Score> pq = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int dc = Integer.parseInt(st.nextToken());
                int iv = Integer.parseInt(st.nextToken());
                pq.offer(new Score(dc, iv));
            }

            int cnt = 1;
            Score pivot = pq.poll();
            while (!pq.isEmpty()) {
                Score cur = pq.poll();
                if (cur.iv < pivot.iv) {
                    cnt++;
                    pivot = cur;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }

    private static class Score implements Comparable<Score>{
        int dc;
        int iv;

        public Score(int dc, int iv) {
            this.dc = dc;
            this.iv = iv;
        }

        @Override
        public int compareTo(Score o) {
            return this.dc == o.dc ? this.iv - o.iv : this.dc - o.dc;
        }

    }
}
