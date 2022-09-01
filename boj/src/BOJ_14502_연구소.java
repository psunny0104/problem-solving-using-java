import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {

    static int Y, X, cmbTotalCnt, answer, TARGET_COUNT = 3;
    static int[][] ogMap, cpMap;
    static boolean[][] isVisited;
    static ArrayList<Pair> candidates = new ArrayList<>();
    static ArrayList<Pair> virusList = new ArrayList<>();
    static int[] selIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        Y = Integer.parseInt(in.nextToken());
        X = Integer.parseInt(in.nextToken());
        ogMap = new int[Y][X];

        for (int i = 0; i < Y; i++) {
            in = new StringTokenizer(br.readLine());
            for (int j = 0; j < X; j++) {
                ogMap[i][j] = Integer.parseInt(in.nextToken());
                if (ogMap[i][j] == 0) {
                    candidates.add(new Pair(i, j));
                } else if (ogMap[i][j] == 2) {
                    virusList.add(new Pair(i, j));
                }
            }
        }

        selIdx = new int[TARGET_COUNT];
        cmbTotalCnt = candidates.size();
        cmb(0, 0);
        System.out.println(answer);
    }

    private static void cmb(int cnt, int start) {
        if (cnt == TARGET_COUNT) {
            // 벽 배치
            buildWalls();
            // 바이러스 확산
            spreadVirus();
            // 카운트
            countArea();
            return;
        }

        for (int i = start; i < cmbTotalCnt; i++) {
            selIdx[cnt] = i;
            cmb(cnt + 1, i + 1);
        }
    }

    private static void buildWalls() {
        cpMap = new int[Y][X];
        for (int i = 0; i < Y; i++) {
            cpMap[i] = Arrays.copyOf(ogMap[i], X);
        }
        for (int idx : selIdx) {
            int cy = candidates.get(idx).y;
            int cx = candidates.get(idx).x;
            cpMap[cy][cx] = 1;
        }
    }

    private static void spreadVirus() {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};

        Queue<Pair> q = new LinkedList<>();
        isVisited = new boolean[Y][X];
        for (Pair virus : virusList) {
            q.offer(new Pair(virus.y, virus.x));
            isVisited[virus.y][virus.x] = true;
        }

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int cy = cur.y;
            int cx = cur.x;

            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];

                if (ny < 0 || nx < 0 || ny >= Y || nx >= X) {
                    continue;
                }
                if (isVisited[ny][nx]) {
                    continue;
                }
                if (cpMap[ny][nx] != 0) {
                    continue;
                }

                q.offer(new Pair(ny, nx));
                isVisited[ny][nx] = true;
                cpMap[ny][nx] = 2;
            }
        }
    }

    private static void countArea() {
        int areaCount = 0;
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if (cpMap[i][j] == 0) {
                    areaCount++;
                }
            }
        }
        answer = Math.max(answer, areaCount);
    }

    private static void print() {
        System.out.println("=====================");
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                System.out.print(cpMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=====================");
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
