import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17281_야구 {

    static int inning, MAX = 9, gameScore = Integer.MIN_VALUE;
    static int[][] results; // 이닝, 선수 번호
    static int[] selectedPlayers, numbers = {2, 3, 4, 5, 6, 7, 8, 9};
    static boolean[] isSelected;
    static Queue<Integer> order = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        inning = Integer.parseInt(br.readLine());
        results = new int[inning][MAX + 1];

        isSelected = new boolean[MAX - 1];
        selectedPlayers = new int[MAX - 1];
        for (int i = 0; i < inning; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= MAX; j++) {
                results[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        perm(0);
        System.out.println(gameScore);
    }

    private static void perm(int cnt) {
        if (cnt == MAX - 1) {
            int curScore = 0;

            setOrder();
            for (int i = 0; i < inning; i++) {
                curScore += play(i);
            }

            gameScore = Math.max(gameScore, curScore);
            return;
        }

        for (int i = 0; i < MAX - 1; i++) {
            if (isSelected[i]) {
                continue;
            }

            isSelected[i] = true;
            selectedPlayers[cnt] = numbers[i];
            perm(cnt + 1);
            isSelected[i] = false;
        }
    }

    private static int play(int curInning) {
        int outCnt = 0;
        int run = 0;
        int[] bases = new int[3]; // 0, 1, 2 -> 1, 2, 3
        Arrays.fill(bases, 0); // 0: 주자 없음, 1: 있음

        while (outCnt != 3) {
            order.offer(order.peek());
            int curPlayer = order.poll();
            int action = results[curInning][curPlayer];
            switch (action) {
                case 0:
                    outCnt++;
                    break;
                case 1:
                    run += hit(bases);
                    break;
                case 2:
                    run += doubleHit(bases);
                    break;
                case 3:
                    run += tripleHit(bases);
                    break;
                case 4:
                    run += homeRun(bases);
                    break;
            }
        }
        return run;
    }

    private static int hit(int[] bases) {
        int cnt = 0;

        if (bases[2] == 1) {
            cnt += 1;
            bases[2] = 0;
        }
        for (int i = 2; i >= 1; i--) {
            if (bases[i - 1] == 1) {
                bases[i] = 1;
                bases[i - 1] = 0;
            }
        }
        bases[0] = 1;

        return cnt;
    }

    private static int doubleHit(int[] bases) {
        int cnt = 0;

        for (int i = 1; i <= 2; i++) {
            if (bases[i] == 1) {
                cnt += 1;
                bases[i] = 0;
            }
        }
        if (bases[0] == 1) {
            bases[2] = 1;
            bases[0] = 0;
        }
        bases[1] = 1;

        return cnt;
    }

    private static int tripleHit(int[] bases) {
        int cnt = 0;

        for (int i = 0; i <= 2; i++) {
            if (bases[i] == 1) {
                cnt += 1;
                bases[i] = 0;
            }
        }
        bases[2] = 1;

        return cnt;
    }

    private static int homeRun(int[] bases) {
        int cnt = 1;
        for (int i = 0; i < bases.length; i++) {
            cnt += bases[i];
            bases[i] = 0;
        }
        return cnt;
    }


    private static void setOrder() {
        order.clear();
        for (int i = 0; i < 3; i++) {
            order.offer(selectedPlayers[i]);
        }
        order.offer(1);
        for (int i = 3; i < 8; i++) {
            order.offer(selectedPlayers[i]);
        }
    }

}
