import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236_아기_상어 {

    static int N;
    static int[][] map, dist;
    static Shark shark;
    static List<Fish> fish = new ArrayList<>();
    static PriorityQueue<Fish> orderedFish = new PriorityQueue<>();
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Shark(i, j);
                } else if (map[i][j] != 0) {
                    fish.add(new Fish(i, j, map[i][j]));
                }
            }
        }

        int cnt = 0;
        while (true) {
            // 현재 상어의 위치와 각 fish의 distance 최신화 하기
            renewDistance(shark);
            // pq에 담기
            orderedFish.clear();
            for (Fish cur : fish) {
                if (!cur.isAlive) {
                    continue;
                }
                ;
                if (cur.size >= shark.size) {
                    continue;
                }
                if (cur.distance == -1) {
                    continue;
                }
                orderedFish.offer(cur);
            }
            // 먹을 수 있는지 확인
            // 먹을 수 없으면 종료
            // 먹을 수 있으면 이동, 먹기
            if (orderedFish.size() == 0) {
                break;
            } else {
                Fish cur = orderedFish.poll();
                eatFish(cur);
                cnt += cur.distance;
            }
        }
        System.out.println(cnt);
    }

    private static void eatFish(Fish fish) {
        move(fish);
        fish.isAlive = false;
        shark.fishCnt += 1;
        if (shark.fishCnt == shark.size) {
            shark.fishCnt = 0;
            shark.size += 1;
        }

    }

    private static void move(Fish fish) {
        map[shark.r][shark.c] = 0;
        shark.r = fish.r;
        shark.c = fish.c;
        map[shark.r][shark.c] = 9;
    }

    private static void renewDistance(Shark shark) {
        markDistanceToMap(shark);
        for (Fish cur : fish) {
            if (!cur.isAlive) {
                continue;
            }
            cur.distance = dist[cur.r][cur.c];
        }
    }

    private static void markDistanceToMap(Shark shark) {
        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }
        ;
        dist[shark.r][shark.c] = 0;
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(shark);

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;
            for (int dir = 0; dir < 4; dir++) {
                int nr = cr + dr[dir];
                int nc = cc + dc[dir];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }
                if (dist[nr][nc] != -1) {
                    continue;
                }
                if (map[nr][nc] > shark.size) {
                    continue;
                }

                dist[nr][nc] = dist[cr][cc] + 1;
                q.offer(new Pair(nr, nc));
            }
        }
    }

    private static class Pair {

        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static class Shark extends Pair {

        int size = 2;
        int fishCnt = 0;

        public Shark(int r, int c) {
            super(r, c);
        }
    }

    private static class Fish extends Pair implements Comparable<Fish> {

        int size;
        int distance = 0;
        boolean isAlive = true;

        public Fish(int r, int c, int size) {
            super(r, c);
            this.size = size;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.distance != o.distance) {
                return this.distance - o.distance;
            }

            if (this.r != o.r) {
                return this.r - o.r;
            }

            return this.c - o.c;
        }
    }
}
