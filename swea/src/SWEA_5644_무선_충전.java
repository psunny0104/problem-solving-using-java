import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
            userB = new User(10,10);
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

            // 충전 범위 인지 확인
                // 중복인지 확인
                // 중복이면 나누기
            // 이동

            ArrayList<Integer> aCoveredBCIdx;
            ArrayList<Integer> bCoveredBCIdx;
            for (int idx = 0; idx < time; idx++) {
                print();
                System.out.println(idx);
                aCoveredBCIdx = checkInBCArea(userA);
                bCoveredBCIdx = checkInBCArea(userB);
                charge(aCoveredBCIdx, bCoveredBCIdx);
                move(userA, idx);
                move(userB, idx);
                System.out.println(userA.charge);
                System.out.println(userB.charge);
                System.out.println("===============");
            }

            print();
            aCoveredBCIdx = checkInBCArea(userA);
            bCoveredBCIdx = checkInBCArea(userB);
            charge(aCoveredBCIdx, bCoveredBCIdx);
            System.out.println(userA.charge);
            System.out.println(userB.charge);
            System.out.println("===============");
            sb.append("#").append(tc).append(" ").append(userA.charge + userB.charge).append("\n");
        }
        System.out.print(sb);
    }

    private static void charge(ArrayList<Integer> aCoveredBCIdx, ArrayList<Integer> bCoveredBCIdx) {
        int aSize = aCoveredBCIdx.size();
        int bSize = bCoveredBCIdx.size();
        if ((aSize == 0 && bSize != 0) || (aSize != 0 && bSize == 0)) {
            int aGauge = 0;
            for (Integer coveredBCIdx : aCoveredBCIdx) {
                aGauge = Math.max(aGauge, bcs.get(coveredBCIdx).power);
            }
            userA.charge += aGauge;
            int bGauge = 0;
            for (Integer coveredBCIdx : bCoveredBCIdx) {
                bGauge = Math.max(bGauge, bcs.get(coveredBCIdx).power);
            }
            userB.charge += bGauge;
            System.out.println("a: "+aGauge +" / b: "+bGauge);
        } else {
            int aGauge = 0;
            int bGauge = 0;
            int tempSumGauge = 0;
            for (int a = 0; a < aSize; a++) {
                int aIdx = aCoveredBCIdx.get(a);
                int tempA = bcs.get(aIdx).power;
                for (int b = 0; b < bSize; b++) {
                    int bIdx = bCoveredBCIdx.get(b);
                    int tempB = bcs.get(bIdx).power;
                    if (aIdx == bIdx) {
                        tempA /= 2;
                        tempB /= 2;
                    }
                    if (tempSumGauge < tempA + tempB) {
                        aGauge = tempA;
                        bGauge = tempB;
                        tempSumGauge = aGauge + bGauge;
                    }
                }
            }
            userA.charge += aGauge;
            userB.charge += bGauge;
            System.out.println("a: "+aGauge +" / b: "+bGauge);
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
        ArrayList<User> users = new ArrayList<>();

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
    static char map[][];
    private static void print() {
        map = new char[11][11];
        for (int i = 1; i <= 10; i++) {
            Arrays.fill(map[i], '.');
        };

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10 ; j++) {
                for (int k = 0; k < bcTotalCnt; k++) {
                    BC bc = bcs.get(k);
                    int distance = Math.abs(bc.r - i) + Math.abs(bc.c - j);
                    if (distance <= bc.range) {
                        int tmp = map[i][j] == '.' ? 0 :  bcs.get(map[i][j] - '0' - 1).power;
                        if (bcs.get(k).power > tmp)
                            map[i][j] = (char)(k + 1 + '0');
                    }
                }
            }
        }

        map[userA.r][userA.c] = 'A';
        map[userB.r][userB.c] = 'B';

        System.out.println("============================");
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("============================");
    }
}
