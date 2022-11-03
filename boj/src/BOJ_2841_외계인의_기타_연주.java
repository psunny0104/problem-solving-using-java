import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_2841_외계인의_기타_연주 {

    static int N, P, cnt;
    static HashMap<Integer, Deque<Integer>> gt = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int line = Integer.parseInt(st.nextToken());
            int fret = Integer.parseInt(st.nextToken());

            if (gt.containsKey(line)) {
                Deque<Integer> cur = gt.get(line);
                while (true) {
                    if (cur.isEmpty()) {
                        cur.offer(fret);
                        cnt++;
                        break;
                    }
                    int top = cur.peekLast();
                    if (top > fret) {
                        cur.pollLast();
                        cnt++;
                    } else {
                        if (top != fret) {
                            cur.offerLast(fret);
                            cnt++;
                        }
                        break;
                    }
                }
            } else {
                Deque<Integer> stk = new ArrayDeque<>();
                stk.offer(fret);
                gt.put(line, stk);
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
