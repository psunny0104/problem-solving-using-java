import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_1021_회전하는_큐 {

    static int N, M;
    static Deque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            deque.offerLast(i);
        }

        int cnt = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());

            Deque<Integer> cpDeque = new ArrayDeque<>();
            for (Integer integer : deque) {
                cpDeque.offer(integer);
            }

            int lastCnt = 0;
            int firstCnt = 0;

            while (true) {
                if (cpDeque.peekFirst() == target) {
                    cpDeque.pollFirst();
                    break;
                } else {
                    cpDeque.offerFirst(cpDeque.pollLast());
                    firstCnt++;
                }
            }

            while (true) {
                if (deque.peekFirst() == target) {
                    deque.pollFirst();
                    break;
                } else {
                    deque.offerLast(deque.pollFirst());
                    lastCnt++;
                }
            }

            if (firstCnt > lastCnt) {
                cnt += lastCnt;
            } else {
                cnt += firstCnt;
                deque = cpDeque;
            }
        }
        System.out.println(cnt);
    }

}
