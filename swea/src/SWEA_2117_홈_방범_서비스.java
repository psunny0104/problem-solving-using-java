import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_2117_홈_방범_서비스 {

    static int T, citySize, costPerHome, LIMIT_K, curHomeCnt, answer;
    static ArrayList<Pair> homes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            in = new StringTokenizer(br.readLine());
            citySize = Integer.parseInt(in.nextToken());
            costPerHome = Integer.parseInt(in.nextToken());

            homes.clear();
            for (int i = 0; i < citySize; i++) {
                in = new StringTokenizer(br.readLine());
                for (int j = 0; j < citySize; j++) {
                    int input = Integer.parseInt(in.nextToken());
                    if (input == 1) {
                        homes.add(new Pair(i, j));
                    }
                }
            }

            LIMIT_K = citySize % 2 == 0 ? citySize + 1 : citySize;
            answer = 0;
            for (int i = 0; i < citySize; i++) {
                for (int j = 0; j < citySize; j++) {
                    for (int k = 1; k <= LIMIT_K; k++) {
                        int curProfit = getProfit(new Pair(i, j), k);
                        if (curProfit >= 0) {
                            answer = Math.max(answer, curHomeCnt);
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static int getProfit(Pair start, int k) {
        return getSales(start, k) - getCost(k);
    }

    private static int getSales(Pair start, int k) {
        curHomeCnt = getHomeCntInArea(start, k - 1);
        return costPerHome * curHomeCnt;
    }

    private static int getHomeCntInArea(Pair start, int k) {
        int cnt = 0;

        for (Pair home : homes) {
            if (getDistance(home, start) <= k) {
                cnt++;
            }
        }

        return cnt;
    }

    private static int getDistance(Pair start, Pair destination) {
        return Math.abs(destination.y - start.y) + Math.abs(destination.x - start.x);
    }

    private static int getCost(int k) {
        return k * k + (k - 1) * (k - 1);
    }

    private static class Pair{
        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
