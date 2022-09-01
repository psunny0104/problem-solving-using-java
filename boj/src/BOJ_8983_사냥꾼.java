import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_8983_사냥꾼 {

    static int arrowsCnt, animalsCnt, range, answer;
    static ArrayList<Integer> arrows = new ArrayList<>();
    static ArrayList<Pair> animals = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        arrowsCnt = Integer.parseInt(in.nextToken());
        animalsCnt = Integer.parseInt(in.nextToken());
        range = Integer.parseInt(in.nextToken());

        in = new StringTokenizer(br.readLine());
        for (int i = 0; i < arrowsCnt; i++) {
            arrows.add(Integer.parseInt(in.nextToken()));
        }

        for (int i = 0; i < animalsCnt; i++) {
            in = new StringTokenizer(br.readLine());
            animals.add(
                    new Pair(Integer.parseInt(in.nextToken()), Integer.parseInt(in.nextToken())));
        }

        Collections.sort(arrows);

        for (Pair animal : animals) {
            int curY = animal.y;
            int curX = animal.x;

            binarySearch(curY, curX);
        }

        System.out.println(answer);
    }

    private static void binarySearch(int curY, int curX) {
        int st = 0;
        int ed = arrowsCnt - 1;

        while (st <= ed) {
            int mid = (st + ed) / 2;
            int dist = Math.abs(arrows.get(mid) - curX) + curY;

            if (dist <= range) {
                answer++;
                break;
            }

            if (arrows.get(mid) < curX) {
                st = mid + 1;
            } else {
                ed = mid - 1;
            }
        }
    }

    private static class Pair {

        int y;
        int x;

        public Pair(int x, int y) {
            this.y = y;
            this.x = x;
        }
    }
}

