import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11000_강의실_배정 {

    static int N;
    static Class[] classes;
    static PriorityQueue<Class> pq = new PriorityQueue<>(
            (o1, o2) -> o1.edT != o2.edT ? o1.edT - o2.edT : o1.stT - o2.stT);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        classes = new Class[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            classes[i] = new Class(from, to);
        }

        Arrays.sort(classes);

        pq.offer(classes[0]);
        for (int i = 1; i < N; i++) {
            Class next = classes[i];
            Class cur = pq.peek();
            if (cur.edT <= next.stT) {
                pq.poll();
            }
            pq.offer(next);
        }

        System.out.println(pq.size());

    }

    private static class Class implements Comparable<Class> {

        int stT, edT;

        public Class(int stT, int edT) {
            this.stT = stT;
            this.edT = edT;
        }

        @Override
        public int compareTo(Class o) {
            return this.stT != o.stT ? this.stT - o.stT : this.edT - o.edT;
        }
    }
}
