import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17298_오큰수 {

    static int N;
    static Stack<Integer> ogStk = new Stack<>();
    static Stack<Integer> mvStk = new Stack<>();
    static Stack<Integer> answer = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ogStk.push(Integer.parseInt(st.nextToken()));
        }

        mvStk.push(ogStk.pop());
        answer.push(-1);
        while (!ogStk.isEmpty()) {
            while (true) {
                if (mvStk.isEmpty()) {
                    answer.push(-1);
                    mvStk.push(ogStk.pop());
                    break;
                }
                if (ogStk.peek() < mvStk.peek()) {
                    answer.push(mvStk.peek());
                    mvStk.push(ogStk.pop());
                    break;
                } else {
                    mvStk.pop();
                }
            }
        }
        while (!answer.isEmpty()) {
            sb.append(answer.pop()).append(" ");
        }
        System.out.println(sb);
    }
}
