import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_11286_절댓값_힙 {

    static int N;
    static Queue<Integer> pq = new PriorityQueue<>((o1, o2)
            -> Math.abs(o1) == Math.abs(o2) ? o1 - o2 : Math.abs(o1) - Math.abs(o2));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 절대값 -> 음, 양
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());

            if (input == 0) {
                printMinValue();
            } else {
                pq.offer(input);
            }
        }
        System.out.print(sb);
    }

    private static void printMinValue() {
        if (pq.isEmpty()) {
            sb.append(0).append("\n");
        } else {
            sb.append(pq.poll()).append("\n");
        }
    }
}