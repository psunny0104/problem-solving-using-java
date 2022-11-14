import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15565_귀여운_라이언 {

    static int N, K;
    static int[] dolls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dolls = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            dolls[i] = Integer.parseInt(st.nextToken());
        }

        int start = 1;
        int end = 1;
        int totalCnt = 1;
        int lionCnt = 0;
        if (dolls[start] == 1) {
            lionCnt++;
        }
        int answer = Integer.MAX_VALUE;

        if (lionCnt == K) {
            answer = Math.min(answer, totalCnt);
        }

        while (start <= N && end <= N) {
            if (lionCnt < K) {
                end++;
                if (end <= N) {
                    totalCnt++;
                    if (dolls[end] == 1) {
                        lionCnt++;
                    }
                }
            } else {
                if (lionCnt == K) {
                    answer = Math.min(answer, totalCnt);
                }
                if (dolls[start] == 1) {
                    lionCnt--;
                }
                totalCnt--;
                start++;
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        
        System.out.println(answer);
    }

}
