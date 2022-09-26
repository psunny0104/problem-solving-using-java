import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10815_숫자_카드 {

    static int N, M;
    static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cards = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int answer = 0;
            int target = Integer.parseInt(st.nextToken());

            int start = 0;
            int end = N - 1;
            while (start <= end) {
                int mid = (start + end) / 2;

                if (cards[mid] == target) {
                    answer = 1;
                    break;
                } else if (cards[mid] > target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            sb.append(answer).append(" ");
        }

        System.out.print(sb);
    }

}
