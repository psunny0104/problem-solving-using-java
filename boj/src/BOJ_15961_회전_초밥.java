import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15961_회전_초밥 {

    static int N, D, K, C;
    static int[] belt;
    static int[] numberCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        belt = new int[N + K - 1];
        numberCnt = new int[D + 1];
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());

        }
        for (int i = N; i < N + K - 1; i++) {
            belt[i] = belt[i - N];
        }

        int stIdx = 0;
        int edIdx = K - 1;
        int curCnt = 0;
        int maxCnt = 1;
        for (int i = 0; i < K; i++) {
            numberCnt[belt[i]]++;
            if (numberCnt[belt[i]] == 1) {
                curCnt++;
            }
        }

        numberCnt[C]++;
        if (numberCnt[C] == 1) {
            curCnt++;
        }

        while (stIdx <= N - 1 && edIdx < N + K - 2) {
            int stValue = belt[stIdx];
            if (numberCnt[stValue] > 0) {
                numberCnt[stValue]--;
                if (numberCnt[stValue] == 0) {
                    curCnt--;
                }
            }

            stIdx++;
            edIdx++;

            numberCnt[belt[edIdx]]++;
            if (numberCnt[belt[edIdx]] == 1) {
                curCnt++;
            }

            maxCnt = Math.max(maxCnt, curCnt);
        }

        System.out.println(maxCnt);
    }

}
