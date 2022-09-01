import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_3190_뱀 {

    static int N, K, L;
    static int[][] map;
    static Snake snake;
    static HashMap<Integer, Character> orders = new HashMap<>();
    static int[] dr = {0, 1, 0, -1}; // 동, 남, 서, 북
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        snake = new Snake(1, 1);
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        map[1][1] = 1;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 2;
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char order = st.nextToken().charAt(0);
            orders.put(time, order);
        }

        int cnt = 1;
        while (true) {
            if (!move()) {
                break;
            }
            if (orders.containsKey(cnt)) {
                changeDir(orders.get(cnt));
            }
            cnt++;
        }
        System.out.println(cnt);
    }

    private static boolean move() {
        Pair head = snake.body.peekFirst();
        Pair tail = snake.body.peekLast();
        int nr = head.r + dr[snake.dir];
        int nc = head.c + dc[snake.dir];

        if (nr < 1 || nc < 1 || nr > N || nc > N) {
            return false;
        }
        if (map[nr][nc] == 1) {
            return false;
        }

        if (map[nr][nc] == 0) {
            map[tail.r][tail.c] = 0;
            snake.body.pollLast();
        }

        snake.body.offerFirst(new Pair(nr, nc));
        map[nr][nc] = 1;

        return true;
    }

    private static void changeDir(Character dir) {
        switch (dir) {
            case 'L':
                snake.dir = (snake.dir + 3) % 4;
                break;
            case 'D':
                snake.dir = (snake.dir + 1) % 4;
                break;
        }
    }

    private static class Snake {

        int dir = 0;
        Deque<Pair> body = new ArrayDeque<>();

        public Snake(int r, int c) {
            body.offer(new Pair(r, c));
        }
    }

    private static class Pair {

        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
