import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_9007_카누_선수 {

    static int T, K, N, LIMIT;
    static int[][] ABCD;
    static int[] AB, CD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            LIMIT = N * N;

            ABCD = new int[4][N];

            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    ABCD[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            AB = new int[LIMIT];
            CD = new int[LIMIT];

            int idx = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    AB[idx] = ABCD[0][i] + ABCD[1][j];
                    CD[idx++] = ABCD[2][i] + ABCD[3][j];
                }
            }

//            Arrays.sort(AB);
            Arrays.sort(CD);

            int minSum = Integer.MAX_VALUE;
            boolean isFindK = false;

            for (int i = 0; i < LIMIT; i++) {
                int left = 0;
                int right = LIMIT - 1;

                while (left <= right) {
                    int mid = (left + right) / 2;
                    int curSum = AB[i] + CD[mid];

                    int curDiff = Math.abs(K - curSum);
                    int minDiff = Math.abs(K - minSum);
                    if (minDiff > curDiff) {
                        minSum = curSum;
                    } else if (minDiff == curDiff) {
                        minSum = Math.min(minSum, curSum);
                    }

                    if (curSum == K) {
                        isFindK = true;
                        minSum = curSum;
                        break;
                    } else if (curSum > K) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                if (isFindK) {
                    break;
                }
            }
            sb.append(minSum).append("\n");
        }
        System.out.print(sb);
    }
}
