import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10871_X보다_작은_수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(in.nextToken());
        int x = Integer.parseInt(in.nextToken());

        in = new StringTokenizer(br.readLine(), " ");
        while (n-- > 0){
            int target = Integer.parseInt(in.nextToken());
            if(target < x)
                sb.append(target).append(" ");
        }

        System.out.println(sb);
    }
}
