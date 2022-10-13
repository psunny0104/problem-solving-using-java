import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5607_조합 {

    static int T, N, R, P = 1234567891;
    static long[] factorial;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        setFactorial();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            // nCr % p = n!/r!(n-r)! % p
            // = ((n! % p) * (r!(n-r)!)^-1 % p ) % p
            // = ((n! % p) * (r!(n-r)!)^(p-2) ) % p

            long dividend = factorial[N];
            long divisor = (factorial[R] * factorial[N - R]) % P;
            divisor = dq(divisor, P - 2);
            long answer = (dividend * divisor) % P;

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    private static long dq(long base, int exp) {
        if (exp == 0) {
            return 1;
        }

        if (exp % 2 == 0) {
            long value = dq(base, exp / 2);
            return (value * value) % P;
        } else {
            long value = dq(base, exp - 1) % P;
            return (value * base) % P;
        }

    }

    private static void setFactorial() {
        factorial = new long[10000001];
        factorial[0] = 1;
        for (int i = 1; i <= 10000000; i++) {
            factorial[i] = (factorial[i - 1] * i) % P;
        }
    }

}
