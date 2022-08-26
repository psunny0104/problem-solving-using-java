import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1927_최소_힙 {

    static int N, input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            input = Integer.parseInt(br.readLine());
            switch (input) {
                case 0:
                    if (pq.isEmpty()) {
                        sb.append(0);
                    } else {
                        sb.append(pq.poll());
                    }
                    sb.append("\n");
                    break;
                default:
                    pq.offer(input);
                    break;
            }
        }
        System.out.print(sb);
    }
}
