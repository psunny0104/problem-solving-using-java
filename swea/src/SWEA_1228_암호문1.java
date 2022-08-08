import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1228_암호문1 {
    static int len, orderCnt;
    static List<String> linkedList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;

        for (int tc = 1; tc <= 10; tc++) {
            len = Integer.parseInt(br.readLine());
            in = new StringTokenizer(br.readLine());

            linkedList = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                linkedList.add(in.nextToken());
            }

            orderCnt = Integer.parseInt(br.readLine());
            in = new StringTokenizer(br.readLine());
            for (int i = 0; i < orderCnt; i++) {
                char c = in.nextToken().charAt(0);
                int offset = Integer.parseInt(in.nextToken());
                int targetCnt = Integer.parseInt(in.nextToken());

                for (int j = 0; j < targetCnt; j++) {
                    linkedList.add(offset++, in.nextToken());
                }
            }

            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < 10; i++) {
                sb.append(linkedList.get(i)).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
