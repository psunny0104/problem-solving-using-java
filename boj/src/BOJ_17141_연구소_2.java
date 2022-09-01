import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17141_연구소_2 {

    static int N, virusTargetNumber, virusTotalCnt, answer = Integer.MAX_VALUE;
    static int[][] ogMap, cpMap;
    static int[][] ogDist, cpDist;
    static ArrayList<Pair> virusList = new ArrayList<>();
    static ArrayList<Pair> selVirusList = new ArrayList<>();

    static int[] selIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        N = Integer.parseInt(in.nextToken());
        virusTargetNumber = Integer.parseInt(in.nextToken());
        ogMap = new int[N][N];
        ogDist = new int[N][N];

        for (int i = 0; i < N; i++) {
            in = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ogMap[i][j] = Integer.parseInt(in.nextToken());
                if (ogMap[i][j] == 2) {
                    virusList.add(new Pair(i, j));
                    ogMap[i][j] = 0;
                } else if (ogMap[i][j] == 1) {
                    ogDist[i][j] = -1;
                }
            }
        }

        virusTotalCnt = virusList.size();
        selIdx = new int[virusTargetNumber];
        cmb(0, 0);
        answer = answer == Integer.MAX_VALUE ? -1 : answer - 1;
        System.out.println(answer);
    }

    private static void cmb(int cnt, int start) {
        if (cnt == virusTargetNumber) {
            // 바이러스 배치
            fixVirusPos();
            // 바이러스 확산
            spreadVirus();
            // 카운트
            getArea();
            return;
        }

        for (int i = start; i < virusTotalCnt; i++) {
            selIdx[cnt] = i;
            cmb(cnt + 1, i + 1);
        }
    }

    private static void fixVirusPos() {
        cpMap = new int[N][N];
        selVirusList.clear();
        for (int i = 0; i < N; i++) {
            cpMap[i] = Arrays.copyOf(ogMap[i], N);
        }
        for (int idx : selIdx) {
            Pair cur = virusList.get(idx);
            cpMap[cur.y][cur.x] = 2;
            selVirusList.add(new Pair(cur.y, cur.x));
        }
    }

    private static void spreadVirus() {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};

        cpDist = new int[N][N];
        for (int i = 0; i < N; i++) {
            cpDist[i] = Arrays.copyOf(ogDist[i], N);
        }

        Queue<Pair> q = new LinkedList<>();
        for (Pair virus : selVirusList) {
            q.offer(new Pair(virus.y, virus.x));
            cpDist[virus.y][virus.x] = 1;
        }

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int cy = cur.y;
            int cx = cur.x;

            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                    continue;
                }
                if (cpDist[ny][nx] > 0) {
                    continue;
                }
                if (cpMap[ny][nx] != 0) {
                    continue;
                }

                q.offer(new Pair(ny, nx));
                cpDist[ny][nx] = cpDist[cy][cx] + 1;
                cpMap[ny][nx] = 2;
            }
        }
    }

    private static void getArea() {
        int areaCount = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cpDist[i][j] == 0) {
                    areaCount = Integer.MAX_VALUE;
                    break;
                }
                areaCount = Math.max(areaCount, cpDist[i][j]);
            }
            if (areaCount == Integer.MAX_VALUE) {
                break;
            }
        }
        answer = Math.min(answer, areaCount);
    }

    private static void print() {
        System.out.println("=====================");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(cpDist[i][j] + " ");
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
