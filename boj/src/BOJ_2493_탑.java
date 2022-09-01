import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493_탑 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        Stack<Pair> stack = new Stack<>();

        in = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int curHeight = Integer.parseInt(in.nextToken());

            while (!stack.isEmpty()) {
                Pair top = stack.peek();
                if (curHeight < top.height) {
                    sb.append(top.idx).append(" ");
                    break;
                } else {
                    stack.pop();
                }
            }

            if (stack.isEmpty()) {
                sb.append("0").append(" ");
            }

            stack.push(new Pair(i, curHeight));
        }
        System.out.println(sb);
    }

    private static class Pair {

        int idx;
        int height;

        public Pair(int idx, int value) {
            this.idx = idx;
            this.height = value;
        }
    }
}
