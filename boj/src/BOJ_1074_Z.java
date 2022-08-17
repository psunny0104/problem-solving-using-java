import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074_Z {

    static int N, R, C, LEN;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        N = Integer.parseInt(in.nextToken());
        R = Integer.parseInt(in.nextToken());
        C = Integer.parseInt(in.nextToken());

        LEN = (int)Math.pow(2, N);
        dq(0, 0, LEN);
    }

    private static void dq(int r, int c, int n) {
        if (r < 0 || c < 0 || r >= LEN || c >= LEN) {
            return;
        }
        if (n == 1) {
            System.out.println(cnt);
        } else if (n > 1) {
            int newN = n/2;
            if (R < r + newN && C < c + newN) {
                cnt += 0;
                dq(r, c, newN);
            } else if (R < r + newN && C < c + n) {
                cnt += newN * newN * 1;
                dq(r, c + newN, newN);
            } else if (R < r + n && C < c + newN) {
                cnt += newN * newN * 2;
                dq(r + newN, c, newN);
            } else if (R < r + n && C < c + n) {
                cnt += newN * newN * 3;
                dq(r + newN, c + newN, newN);
            }
        }
    }
}
