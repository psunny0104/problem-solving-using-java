import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇_청소기 {

    static int[] dr = {0, -1, 0, 1}; // 북->서, 동->북, 남->동, 서->남
    static int[] dc = {-1, 0, 1, 0};
    static int R, C, cnt;
    static int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
    static Robot robot;
    static int[][] map;
    static boolean[][] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        isVisited = new boolean[R][C];
        st = new StringTokenizer(br.readLine());
        robot = new Robot(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));

        while (true) {
            // cleanCurLocation
            cleanCurLocation();
            // search
            search();
            // check
        }

    }
    // 서, 남, 동, 북
    private static void search() {
        while (true) {
            int nr = robot.r + dr[robot.dir];
            int nc = robot.c + dc[robot.dir];
            if (!isVisited[nr][nc]) {
                // 그 방향으로 회전
//                rotate(getDir(dr[robot.dir], dc[robot.dir]));
                // 1칸 전진 후 처음부터
                move(nr, nc);
            }
        }

    }

//    private static Object getDir(int dr, int dc) {
//        if (dr == 0 && dc == -1) {
//            return
//        }
//    }

    private static void move(int nr, int nc) {
        robot.r = nr;
        robot.c = nc;
    }

    private static void rotate() {
//        switch (robot.dir) {
//            case NORTH:
//                break;
//            case
//
//        }
    }

    private static void cleanCurLocation() {
        isVisited[robot.r][robot.c] = true;
        cnt++;
    }

    private static class Robot {
        int r;
        int c;
        int dir; // 북, 동, 남, 서

       public Robot(int r, int c, int dir) {
           this.r = r;
           this.c = c;
           switch (dir) {
               case 0:
                   this.dir = NORTH;
                   break;
               case 1:
                   this.dir = EAST;
                   break;
               case 2:
                   this.dir = SOUTH;
                   break;
               case 3:
                   this.dir = WEST;
                   break;
           }
       }
   }
}
