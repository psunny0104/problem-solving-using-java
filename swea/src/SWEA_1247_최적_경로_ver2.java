import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247_최적_경로_ver2 {

    static int T, customerCnt, answer = Integer.MAX_VALUE;
    static Pair start, destination;
    static boolean[] isSelected;
    static Pair[] customerPairs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            customerCnt = Integer.parseInt(br.readLine());
            in = new StringTokenizer(br.readLine());

            customerPairs = new Pair[customerCnt];
            isSelected = new boolean[customerCnt];

            start = new Pair(Integer.parseInt(in.nextToken()), Integer.parseInt(in.nextToken()));
            destination = new Pair(Integer.parseInt(in.nextToken()),
                    Integer.parseInt(in.nextToken()));
            for (int i = 0; i < customerCnt; i++) {
                customerPairs[i] = new Pair(Integer.parseInt(in.nextToken()),
                        Integer.parseInt(in.nextToken()));
            }

            answer = Integer.MAX_VALUE;
            search(start, 0, 0);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static void search(Pair cur, int cnt, int sum) {
        if (sum > answer) {
            return;
        }

        if (cnt == customerCnt) {
            answer = Math.min(answer, sum + getDistance(cur, destination));
            return;
        }

        for (int i = 0; i < customerCnt; i++) {
            if (isSelected[i]) {
                continue;
            }

            isSelected[i] = true;
            Pair next = customerPairs[i];
            search(next, cnt + 1, sum + getDistance(cur, next));
            isSelected[i] = false;
        }
    }

    private static int getDistance(Pair start, Pair end) {
        return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
    }

    private static class Pair {

        int y;
        int x;

        public Pair(int x, int y) {
            this.y = y;
            this.x = x;
        }
    }
}
