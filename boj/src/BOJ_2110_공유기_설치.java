import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110_공유기_설치 {

    static int N, C;
    static int[] locations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        locations = new int[N];
        for (int i = 0; i < N; i++) {
            locations[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(locations);

        long start = 1;
        long end = locations[N - 1] - locations[0];

        while (start < end) {
            long mid = (start + end + 1) / 2;

            int cnt = 1;
            int idx = 0;
            int prev = idx;
            while (idx < N - 1) {
                if (locations[idx + 1] - locations[prev] >= mid) {
                    cnt++;
                    prev = idx + 1;
                }
                idx++;
            }

            if (cnt >= C) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(start);

    }

}
