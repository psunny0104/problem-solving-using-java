import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA_1225_암호생성기 {

    static int T;
    static Deque<Integer> numberDeq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;

        for (int tc = 1; tc <= 10; tc++) {
            T = Integer.parseInt(br.readLine());
            numberDeq = new ArrayDeque<>(8);

            in = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; i++) {
                numberDeq.offer(Integer.parseInt(in.nextToken()));
            }

            while (true) {
                if (cycle()) {
                    break;
                }
            }

            sb.append("#").append(tc).append(" ");
            for (Integer number : numberDeq) {
                sb.append(number).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static boolean cycle() {
        for (int i = 1; i <= 5; i++) {
            if (move(i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean move(int idx) {
        boolean isEnd = false;
        int curFirst = numberDeq.pollFirst();
        int target = curFirst - idx;

        if (target <= 0) {
            target = 0;
            isEnd = true;
        }
        numberDeq.offerLast(target);
        return isEnd;
    }

}
