import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1931_회의실_배정 {

    static int N;
    static Meeting[] meetings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in;
        N = Integer.parseInt(br.readLine());
        meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            in = new StringTokenizer(br.readLine());
            meetings[i] = new Meeting(Integer.parseInt(in.nextToken()),
                    Integer.parseInt(in.nextToken()));
        }

        Arrays.sort(meetings,
                ((o1, o2) -> o1.end != o2.end ? o1.end - o2.end : o1.start - o2.start));

        int cnt = 1;
        int selectedEnd = meetings[0].end;
        for (int i = 1; i < N; i++) {
            if (meetings[i].start >= selectedEnd) {
                cnt++;
                selectedEnd = meetings[i].end;
            }
        }
        System.out.println(cnt);
    }

    private static class Meeting {

        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
