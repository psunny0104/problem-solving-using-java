import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15686_치킨_배달 {

    static int N, targetStoreCnt, totalStoreCnt, answer = Integer.MAX_VALUE;
    static ArrayList<Pair> storeList = new ArrayList<>();
    static ArrayList<Pair> homeList = new ArrayList<>();
    static Pair[] selectedStoreList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        N = Integer.parseInt(in.nextToken());
        targetStoreCnt = Integer.parseInt(in.nextToken());

        for (int i = 0; i < N; i++) {
            in = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int location = Integer.parseInt(in.nextToken());
                if (location == 1) {
                    homeList.add(new Pair(i, j));
                } else if (location == 2) {
                    storeList.add(new Pair(i, j));
                }
            }
        }

        totalStoreCnt = storeList.size();
        selectedStoreList = new Pair[targetStoreCnt];
        cmb(0, 0);
        System.out.println(answer);
    }

    private static void cmb(int cnt, int start) {
        if (cnt == targetStoreCnt) {
            // 거리 탐색
            calTotalDist();
            return;
        }

        for (int i = start; i < totalStoreCnt; i++) {
            selectedStoreList[cnt] = storeList.get(i);
            cmb(cnt + 1, i + 1);
        }
    }

    private static void calTotalDist() {
        int totalDist = 0;
        for (Pair home : homeList) {
            int homeDist = Integer.MAX_VALUE;

            for (Pair store : selectedStoreList) {
                int tempDist = Math.abs(store.y - home.y) + Math.abs(store.x - home.x);
                homeDist = Math.min(homeDist, tempDist);
            }

            totalDist += homeDist;
        }
        answer = Math.min(answer, totalDist);
    }

    private static class Pair {

        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
