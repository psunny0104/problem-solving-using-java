import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11729_하노이_탑_이동_순서 {

    static StringBuilder sb = new StringBuilder();
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        move(N, 1, 2, 3);
        sb.insert(0, cnt + "\n");
        System.out.println(sb);
    }

    private static void move(int n, int start, int mid, int end) {
        cnt++;

        if(n == 1) {
            sb.append(start).append(" ").append(end).append("\n");
            return;
        }

        move(n - 1, start, end, mid);

        sb.append(start).append(" ").append(end).append("\n");

        move(n - 1, mid, start, end);

    }
}
