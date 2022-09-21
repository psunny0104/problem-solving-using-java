import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7453_합이_0인_네_정수 {

    static int N, LIMIT;
    static long[] A, B, C, D, sumAB, sumCD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        LIMIT = N * N;
        A = new long[N];
        B = new long[N];
        C = new long[N];
        D = new long[N];
        sumAB = new long[LIMIT];
        sumCD = new long[LIMIT];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Long.parseLong(st.nextToken());
            B[i] = Long.parseLong(st.nextToken());
            C[i] = Long.parseLong(st.nextToken());
            D[i] = Long.parseLong(st.nextToken());
        }

        int idx = 0;
        for (int first = 0; first < N; first++) {
            for (int second = 0; second < N; second++) {
                sumAB[idx] = A[first] + B[second];
                sumCD[idx++] = C[first] + D[second];
            }
        }

        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        int start = 0;
        int end = LIMIT - 1;
        long cnt = 0;
        while (start < LIMIT && end >= 0) {
            long curSum = sumAB[start] + sumCD[end];

            if (curSum == 0) {
                long abCnt = 0;
                long cdCnt = 0;
                int abPivot = start;
                int cdPivot = end;
                while (sumAB[abPivot] == sumAB[start]) {
                    abCnt++;
                    start++;
                    if (start == LIMIT) {
                        break;
                    }
                }
                while (sumCD[cdPivot] == sumCD[end]) {
                    cdCnt++;
                    end--;
                    if (end == -1) {
                        break;
                    }
                }
                cnt += abCnt * cdCnt;
            } else if (curSum > 0) {
                end--;
            } else {
                start++;
            }
        }
        System.out.println(cnt);
    }

}
