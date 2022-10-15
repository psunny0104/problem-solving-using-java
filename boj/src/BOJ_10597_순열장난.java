import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ_10597_순열장난 {

    static int LIMIT;
    static char[] N;
    static boolean[] numberCnt = new boolean[51];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = br.readLine().toCharArray();
        LIMIT = N.length;
        Deque<Integer> cur = new ArrayDeque<>();

        search(0, cur);
    }

    private static void search(int cnt, Deque<Integer> cur) {
        if (cnt == LIMIT) {

            int max = 0;
            for (Integer integer : cur) {
                max = Math.max(max, integer);
            }

            for (int i = 1; i <= max; i++) {
                if (!numberCnt[i]) {
                    return;
                }
            }

            StringBuilder sb = new StringBuilder();
            while (!cur.isEmpty()) {
                sb.append(cur.poll()).append(" ");
            }
            System.out.print(sb);

            exit(0);
        }

        int target = N[cnt] - '0';

        if (target > 50) {
            return;
        }

        if (target >= 1 && !numberCnt[target]) {
            numberCnt[target] = true;
            cur.offer(target);
            search(cnt + 1, cur);
            numberCnt[target] = false;
            cur.pollLast();
        }

        if (cnt + 1 < LIMIT) {
            String newTarget = N[cnt] - '0' + String.valueOf(N[cnt + 1] - '0');
            target = Integer.parseInt(newTarget);

            if (target > 50) {
                return;
            }

            if (!numberCnt[target]) {
                numberCnt[target] = true;
                cur.offer(target);
                search(cnt + 2, cur);
                numberCnt[target] = false;
                cur.pollLast();
            }
        }


    }

}
