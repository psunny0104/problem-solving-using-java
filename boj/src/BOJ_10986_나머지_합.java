import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10986_나머지_합 {

    static int N, M;
    static int[] accSum, numCnts;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        N = Integer.parseInt(in.nextToken());
        M = Integer.parseInt(in.nextToken());
        accSum = new int[N + 1];
        numCnts = new int[M];

        in = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int input = Integer.parseInt(in.nextToken());
            accSum[i] = (input % M + accSum[i - 1]) % M;
            numCnts[accSum[i]]++;
        }

        answer += numCnts[0];
        for (int i = 0; i < M; i++) {
            answer += ((long) numCnts[i] * (numCnts[i] - 1)) / 2;
        }

        System.out.println(answer);
    }
}
