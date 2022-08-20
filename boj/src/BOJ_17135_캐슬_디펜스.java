import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17135_캐슬_디펜스 {

    static int R, C, LIMIT, K = 3, archerR, answer = Integer.MIN_VALUE;
    static int[] selectedIdx;
    static Archer[] archers;
    static List<ArrayList<Enemy>> enemies = new ArrayList<>();
    static HashSet<Enemy> phaseCandidates = new HashSet<>();
    static PriorityQueue<Enemy> phaseCandidatesPerArcher = new PriorityQueue<>(
            (o1, o2) -> o1.distance == o2.distance ? o1.c - o2.c:o1.distance - o2.distance
    );

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        R = Integer.parseInt(in.nextToken());
        C = Integer.parseInt(in.nextToken());
        LIMIT = Integer.parseInt(in.nextToken());
        selectedIdx = new int[K];
        archers = new Archer[K];
        archerR = R;

        for (int i = 0; i < K; i++) {
            archers[i] = new Archer(archerR, 0);
        }

        for (int i = 0; i < R; i++) {
            in = new StringTokenizer(br.readLine());
            ArrayList<Enemy> cur = new ArrayList<>();
            for (int j = 0; j < C; j++) {
                if (Integer.parseInt(in.nextToken()) == 1) {
                    cur.add(new Enemy(i, j));
                }
            }
            enemies.add(cur);
        }

        // C 중 3자리 배치하기
        comb(0, 0);
        System.out.println(answer);
    }

    private static void comb(int cnt, int start) {
        if (cnt == K) {
            int phaseKilledCnt = getPhaseKilledCnt();
            answer = Math.max(answer, phaseKilledCnt);

            return;
        }

        for (int i = start; i < C; i++) {
            selectedIdx[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    private static int getPhaseKilledCnt() {
        archerR = R;
        int phaseKilledCnt = 0;

        for (ArrayList<Enemy> enemy : enemies) {
            for (Enemy cur : enemy) {
                cur.isAlive = true;
                cur.distance = 0;
            }
        }

        for (int i = 0; i < K; i++) {
            archers[i].c = selectedIdx[i];
        }

        while (archerR > 0) {
            // 궁수 위치 조정
            for (int i = 0; i < K; i++) {
                archers[i].r = archerR;
            }
            // 가장 가까우면서, 왼쪽에 있는 적 최대 3명 제거
            phaseKilledCnt += killEnemies();
            // 궁수 행 이동
            archerR--;
        }

        return phaseKilledCnt;
    }

    private static int killEnemies() {
        int phaseCnt = 0;
        phaseCandidates.clear();
        // 행 -> 궁수 / 궁수 선택 -> 행 제거
        for (Archer archer : archers) {
            phaseCandidatesPerArcher.clear();
            int targetR = archerR - 1;

            while (targetR >= 0) {
                int targetRowEnemiesCnt = enemies.get(targetR).size();
                if (targetRowEnemiesCnt == 0) {
                    targetR--;
                    continue;
                }
                for (int i = 0; i < targetRowEnemiesCnt; i++) {

                    Enemy curEnemy = enemies.get(targetR).get(i);
                    if (!curEnemy.isAlive) {
                        continue;
                    }

                    int dist = getDistance(archer, curEnemy);
                    if (dist <= LIMIT) {
                        curEnemy.distance = dist;
                        phaseCandidatesPerArcher.offer(curEnemy);
                    }
                }
                targetR--;
            }

            if (phaseCandidatesPerArcher.size() > 0) {
                phaseCandidates.add(phaseCandidatesPerArcher.peek());
            }
        }
        for (Enemy phaseCandidate : phaseCandidates) {
            phaseCandidate.isAlive = false;
            phaseCnt++;
        }

        return phaseCnt;
    }

    private static int getDistance(Pair start, Pair end) {
        return Math.abs(start.r - end.r) + Math.abs(start.c - end.c);
    }

    private static class Pair{
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static class Archer extends Pair {

        public Archer(int r, int c) {
            super(r, c);
        }
    }

    private static class Enemy extends Pair {
        int distance;
        boolean isAlive;

        public Enemy(int r, int c) {
            super(r, c);
            isAlive = true;
        }
    }
}