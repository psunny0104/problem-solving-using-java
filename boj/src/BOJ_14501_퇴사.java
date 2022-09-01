import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사 {

    // 가지치기 없는 완전탐색도 시간복잡도 만족
    static int N, answer = 0; // 상담을 하나도 하지 않는 경우 -> 0
    static Schedule[] schedules;
    static boolean[] isSelected;
    static List<Schedule> candidates = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        schedules = new Schedule[N];
        isSelected = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            schedules[i] = new Schedule(i+1,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        search(0);
        System.out.println(answer);
    }

    private static void search(int cnt) {
        if (cnt == N) {
            candidates.clear();

            for (int i = 0; i < N; i++) {
                if (isSelected[i]) {
                    candidates.add(schedules[i]);
                }
            }

            if (candidates.size() == 0) {
                return;
            }

            int phaseCnt = check();
            if (phaseCnt != -1) {
                answer = Math.max(answer, phaseCnt);
            }

            return;
        }

        isSelected[cnt] = true;
        search(cnt + 1);
        isSelected[cnt] = false;
        search(cnt + 1);
    }

    private static int check() {
        int cnt = 0;
        if (candidates.get(0).day + candidates.get(0).periodFromToday > N + 1) {
            return -1;
        }

        cnt += candidates.get(0).amount;
        int candidatesSize = candidates.size();
        for (int i = 1; i < candidatesSize; i++) {
            Schedule curDay = candidates.get(i);
            Schedule prevDay = candidates.get(i-1);
            if (curDay.day - prevDay.day + 1 <= prevDay.periodFromToday) {
                return -1;
            }
            if (curDay.day + curDay.periodFromToday > N + 1) {
                return -1;
            }
            cnt += curDay.amount;
        }

        return cnt;
    }

    private static class Schedule {

        int day;
        int periodFromToday;
        int amount;

        public Schedule(int day, int periodFromToday, int amount) {
            this.day = day;
            this.periodFromToday = periodFromToday;
            this.amount = amount;
        }
    }
}
