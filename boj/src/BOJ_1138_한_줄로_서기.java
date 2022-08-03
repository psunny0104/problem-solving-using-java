import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1138_한_줄로_서기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] leftPeopleCnts = new int[n + 1];
        ArrayList<Integer> order = new ArrayList<>();

        in = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            leftPeopleCnts[i] = Integer.parseInt(in.nextToken());
        }

        for(int i = n; i >= 1; i--){
            order.add(leftPeopleCnts[i], i);
        }

        for(int i = 0; i < n; i++){
            sb.append(order.get(i)).append(" ");
        }

        System.out.println(sb);
    }
}
