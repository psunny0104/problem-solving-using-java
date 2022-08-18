import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247_최적_경로 {

    static int T, customerCnt, answer = Integer.MAX_VALUE;
    static Pair start, destination;
    static boolean[] isSelected;
    static Pair[] customerPairs, selectedCustomers;
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
            selectedCustomers = new Pair[customerCnt];

            start = new Pair(Integer.parseInt(in.nextToken()), Integer.parseInt(in.nextToken()));
            destination = new Pair(Integer.parseInt(in.nextToken()), Integer.parseInt(in.nextToken()));
            for (int i = 0; i < customerCnt; i++) {
                customerPairs[i] = new Pair(Integer.parseInt(in.nextToken()), Integer.parseInt(in.nextToken()));
            }

            answer = Integer.MAX_VALUE;
            perm(0);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static void perm(int cnt) {
        if (cnt == customerCnt) {
            // 회사 - 선택된 순서의 고객위치 - 집
            // 각 최단거리 더해서 합하기
            int curSum = getDistance(start.y, start.x, selectedCustomers[0].y, selectedCustomers[0].x);
            for (int i = 0; i < customerCnt - 1; i++) {
                if (curSum > answer)
                    return;
                curSum += getDistance(selectedCustomers[i].y, selectedCustomers[i].x, selectedCustomers[i + 1].y, selectedCustomers[i + 1].x);
            }
            curSum += getDistance(selectedCustomers[customerCnt - 1].y, selectedCustomers[customerCnt - 1].x, destination.y, destination.x);

            if (curSum < answer) {
                answer = curSum;
            }
            return;
        }

        for (int i = 0; i < customerCnt; i++) {
            if (isSelected[i]) {
                continue;
            }

            isSelected[i] = true;
            selectedCustomers[cnt] = customerPairs[i];
            perm(cnt + 1);
            isSelected[i] = false;
        }
    }

    private static int getDistance(int stY, int stX, int edY, int edX) {
        return Math.abs(stX - edX) + Math.abs(stY - edY);
    }

    private static class Pair{
        int y;
        int x;

        public Pair(int x, int y) {
            this.y = y;
            this.x = x;
        }
    }
}
