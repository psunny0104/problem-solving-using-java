import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17143_낚시왕 {

    static int R, C, M;
    static King king;
    static Map<Integer, Shark> sharks = new HashMap<>();
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int sharkIdx = 1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharks.put(sharkIdx, new Shark(sharkIdx++, r, c, s, d, z));
        }

        king = new King(0, 0, 0);

        while (king.c <= C) {
            kingMove();
            hunt();
            sharksMove();
            sharkEat();
        }

        System.out.println(king.sharkSizeSum);

    }

    private static void sharkEat() {

        ArrayList<ArrayList<ArrayList<Integer>>> list = new ArrayList<>();
        for (int i = 0; i <= R; i++) {
            list.add(new ArrayList<>());
            for (int j = 0; j <= C; j++) {
                list.get(i).add(new ArrayList<>());
            }
        }

        for (int sharkIdx = 1; sharkIdx <= M; sharkIdx++) {
            Shark curShark = sharks.get(sharkIdx);
            if (curShark == null) {
                continue;
            }
            list.get(curShark.r).get(curShark.c).add(sharkIdx);
        }

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <=C; j++) {
                int curSize = list.get(i).get(j).size();
                if (curSize > 1) {
                    int maxSharkIdx = 0;
                    int maxSharkSize = 0;
                    for (int k = 0; k < curSize; k++) {
                        Shark cur = sharks.get(list.get(i).get(j).get(k));
                        if (cur.size > maxSharkSize) {
                            maxSharkSize = cur.size;
                            maxSharkIdx = cur.idx;
                        }
                    }
                    for (int k = 0; k < curSize; k++) {
                        Shark cur = sharks.get(list.get(i).get(j).get(k));
                        if (maxSharkIdx != cur.idx) {
                            sharks.remove(cur.idx);
                        }
                    }
                }
            }
        }
    }

    private static void sharksMove() {
        for (int sharkIdx = 1; sharkIdx <= M; sharkIdx++) {
            Shark curShark = sharks.get(sharkIdx);

            if (curShark == null) {
                continue;
            }

            int moveTarget = curShark.speed;
            if (curShark.dir == 1) {
                int diff = curShark.r - 1;
                if (diff < moveTarget) {
                    curShark.r = 1;
                    moveTarget -= diff;
                    curShark.dir = 2;
                } else {
                    curShark.r -= moveTarget;
                    if (curShark.r == 1) {
                        curShark.dir = 2;
                    }
                    continue;
                }

                int quotient = (moveTarget) / (R-1);
                int remainder = (moveTarget) % (R-1);

                if (quotient % 2 == 0) {
                    curShark.r += remainder;
                } else {
                    curShark.dir = 1;
                    curShark.r = R - remainder;
                }
            } else if (curShark.dir == 2) {
                int diff = R - curShark.r;
                if (diff < moveTarget) {
                    curShark.r = R;
                    moveTarget -= diff;
                    curShark.dir = 1;
                } else {
                    curShark.r += moveTarget;
                    if (curShark.r == R) {
                        curShark.dir = 1;
                    }
                    continue;
                }

                int quotient = (moveTarget) / (R-1);
                int remainder = (moveTarget) % (R-1);

                if (quotient % 2 == 0) {
                    curShark.r -= remainder;
                } else {
                    curShark.dir = 2;
                    curShark.r = 1 + remainder;
                }
            } else if (curShark.dir == 3) {
                int diff = C - curShark.c;
                if (diff < moveTarget) {
                    curShark.c = C;
                    moveTarget -= diff;
                    curShark.dir = 4;
                } else {
                    curShark.c += moveTarget;
                    if (curShark.c == C) {
                        curShark.dir = 4;
                    }
                    continue;
                }

                int quotient = (moveTarget) / (C-1);
                int remainder = (moveTarget) % (C-1);

                if (quotient % 2 == 0) {
                    curShark.c -= remainder;
                } else {
                    curShark.dir = 3;
                    curShark.c = 1 + remainder;
                }
            } else if (curShark.dir == 4) {
                int diff = curShark.c - 1;
                if (diff < moveTarget) {
                    curShark.c = 1;
                    moveTarget -= diff;
                    curShark.dir = 3;
                } else {
                    curShark.c -= moveTarget;
                    if (curShark.c == 1) {
                        curShark.dir = 3;
                    }
                    continue;
                }

                int quotient = (moveTarget) / (C-1);
                int remainder = (moveTarget) % (C-1);

                if (quotient % 2 == 0) {
                    curShark.c += remainder;
                } else {
                    curShark.dir = 4;
                    curShark.c = C - remainder;
                }

            }
        }
    }

    private static void hunt() {
        PriorityQueue<Shark> pq = new PriorityQueue<>();
        for (Shark shark : sharks.values()) {
            pq.offer(shark);
        }

        while (!pq.isEmpty()) {
            Shark curShark = pq.poll();
            if (curShark.c == king.c) {
                curShark.isAlive = false;
                king.sharkSizeSum += curShark.size;
                sharks.remove(curShark.idx);
                break;
            }
        }
    }

    private static void kingMove() {
        king.c++;
    }


    private static class Shark implements Comparable<Shark> {

        int idx, r, c, speed, dir, size;
        boolean isAlive;

        public Shark(int idx, int r, int c, int speed, int dir, int size) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
            this.isAlive = true;
        }

        public Shark(int idx, int r, int c, int speed, int dir, int size, boolean isAlive) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
            this.isAlive = isAlive;
        }


        @Override
        public int compareTo(Shark o) {
            return this.r == o.r ? this.c - o.c : this.r - o.r;
        }
    }

    private static class Point {

        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static class King extends Point {

        int sharkSizeSum;

        public King(int r, int c, int sharkSizeSum) {
            super(r, c);
            this.sharkSizeSum = sharkSizeSum;
        }
    }

}
