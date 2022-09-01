import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17142_연구소_3 {

    static int N, virusTargetCnt, virusTotalCnt, answer = Integer.MAX_VALUE;
    static int[][] ogMap, cpMap, ogDist;
    static ArrayList<Pair> virusList = new ArrayList<>();
    static int[] selIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        N = Integer.parseInt(in.nextToken());
        virusTargetCnt = Integer.parseInt(in.nextToken());

        ogMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            in = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ogMap[i][j] = Integer.parseInt(in.nextToken());
                if (ogMap[i][j] == 2) {
                    virusList.add(new Pair(i, j));
                    ogMap[i][j] = 3;
                }
            }
        }

        virusTotalCnt = virusList.size();
        selIdx = new int[virusTargetCnt];
        cmb(0, 0);
        answer = answer == Integer.MAX_VALUE ? -1 : answer - 1;
        System.out.println(answer);
    }

    private static void cmb(int cnt, int start) {
        if (cnt == virusTargetCnt) {
            // 바이러스 선택
            fixVirusPos();
            // 바이러스 확산
            spreadVirus();
            // 비활성 바이러스 벽 표기
            markUnSelectedVirus();
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
        ogDist = new int[N][N];
        for (int i = 0; i < N; i++) {
            cpMap[i] = Arrays.copyOf(ogMap[i], N);
            ogDist[i] = Arrays.copyOf(ogMap[i], N);
        }
        for (int idx : selIdx) {
            Pair virus = virusList.get(idx);
            cpMap[virus.y][virus.x] = 2;
        }
        // dist 배열 미리 세팅
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (ogDist[i][j] == 1) {
                    ogDist[i][j] = -1;
                } else if (ogDist[i][j] == 3) {
                    ogDist[i][j] = -2;
                }
            }
        }
    }

    private static void spreadVirus() {
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};
        Queue<Pair> queue = new LinkedList<>();
        for (int idx : selIdx) {
            Pair virus = virusList.get(idx);
            queue.offer(virus);
            ogDist[virus.y][virus.x] = 1;
        }

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            int cy = cur.y;
            int cx = cur.x;

            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                    continue;
                }
                if (cpMap[ny][nx] == 1 || ogDist[ny][nx] > 0) {
                    continue;
                }

                queue.offer(new Pair(ny, nx));
                ogDist[ny][nx] = ogDist[cy][cx] + 1;
            }
        }
    }

    private static void markUnSelectedVirus() {
        for (int i = 0; i < virusTotalCnt; i++) {
            boolean isSelected = false;
            for (int idx : selIdx) {
                if (idx == i) {
                    isSelected = true;
                }
            }
            if (isSelected) {
                continue;
            }
            Pair virus = virusList.get(i);
            ogDist[virus.y][virus.x] = -1;
        }
    }

    private static void getArea() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (ogDist[i][j] == 0) {
                    return;
                }
                count = Math.max(count, ogDist[i][j]);
            }
        }
        answer = Math.min(answer, count);
    }

    private static void print() {
        System.out.println("=====================");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(ogDist[i][j] + " ");
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
