import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_5644_무선_충전 {

    static int[] dr = {0, -1, 0, 1, 0}; // 이동X, 상, 우, 하, 좌
    static int[] dc = {0, 0, 1, 0, -1};

    static int T, time, bcTotalCnt;
    static User userA, userB;
    static ArrayList<BC> bcs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            in = new StringTokenizer(br.readLine());
            time = Integer.parseInt(in.nextToken());
            bcTotalCnt = Integer.parseInt(in.nextToken());

            userA = new User(1, 1);
            userB = new User(10, 10);
            bcs.clear();

            in = new StringTokenizer(br.readLine());
            for (int i = 0; i < time; i++) {
                userA.dirOrder.add(Integer.parseInt(in.nextToken()));
            }
            in = new StringTokenizer(br.readLine());
            for (int i = 0; i < time; i++) {
                userB.dirOrder.add(Integer.parseInt(in.nextToken()));
            }

            for (int i = 0; i < bcTotalCnt; i++) {
                in = new StringTokenizer(br.readLine());
                bcs.add(new BC(Integer.parseInt(in.nextToken()),
                        Integer.parseInt(in.nextToken()),
                        Integer.parseInt(in.nextToken()),
                        Integer.parseInt(in.nextToken())
                ));
            }

            ArrayList<Integer> aCoveredBCIdx = checkInBCArea(userA);
            ArrayList<Integer> bCoveredBCIdx = checkInBCArea(userB);
            charge(aCoveredBCIdx, bCoveredBCIdx);
            for (int idx = 0; idx < time; idx++) {
                move(userA, idx);
                move(userB, idx);
                aCoveredBCIdx = checkInBCArea(userA);
                bCoveredBCIdx = checkInBCArea(userB);
                charge(aCoveredBCIdx, bCoveredBCIdx);
            }

            sb.append("#").append(tc).append(" ").append(userA.charge + userB.charge).append("\n");
        }
        System.out.print(sb);
    }

    private static void charge(ArrayList<Integer> aCoveredBCIdx, ArrayList<Integer> bCoveredBCIdx) {
        int aSize = aCoveredBCIdx.size();
        int bSize = bCoveredBCIdx.size();
        int powerA = 0;
        int powerB = 0;

        if (aSize != 0 && bSize != 0) {
            int curPowerSum = 0;
            for (int b = 0; b < bSize; b++) {
                int bIdx = bCoveredBCIdx.get(b);
                for (int a = 0; a < aSize; a++) {
                    int aIdx = aCoveredBCIdx.get(a);

                    int curPowerA = bcs.get(aIdx).power;
                    int curPowerB = bcs.get(bIdx).power;
                    if (aIdx == bIdx) {
                        curPowerA /= 2;
                        curPowerB /= 2;
                    }
                    if (curPowerSum < curPowerA + curPowerB) {
                        powerA = curPowerA;
                        powerB = curPowerB;
                        curPowerSum = powerA + powerB;
                    }
                }
            }
            userA.charge += powerA;
            userB.charge += powerB;
        } else {
            for (Integer coveredBCIdx : aCoveredBCIdx) {
                powerA = Math.max(powerA, bcs.get(coveredBCIdx).power);
            }
            userA.charge += powerA;
            for (Integer coveredBCIdx : bCoveredBCIdx) {
                powerB = Math.max(powerB, bcs.get(coveredBCIdx).power);
            }
            userB.charge += powerB;
        }
    }

    private static ArrayList<Integer> checkInBCArea(User user) {
        ArrayList<Integer> coveredBCIdx = new ArrayList<>();

        for (int i = 0; i < bcTotalCnt; i++) {
            BC bc = bcs.get(i);
            int distance = Math.abs(bc.r - user.r) + Math.abs(bc.c - user.c);
            if (distance <= bc.range) {
                coveredBCIdx.add(i);
            }
        }

        return coveredBCIdx;
    }

    private static void move(User user, int time) {
        user.r = user.r + dr[user.dirOrder.get(time)];
        user.c = user.c + dc[user.dirOrder.get(time)];
    }

    private static class BC {

        int r;
        int c;
        int range;
        int power;

        public BC(int c, int r, int range, int power) {
            this.r = r;
            this.c = c;
            this.range = range;
            this.power = power;
        }
    }

    private static class User {

        int r;
        int c;
        int charge;
        ArrayList<Integer> dirOrder = new ArrayList<>();

        public User(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
