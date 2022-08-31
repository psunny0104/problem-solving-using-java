import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458_시험_감독 {

    static int room, regular, vice;
    static int[] people;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        room = Integer.parseInt(br.readLine());
        people = new int[room];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < room; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        regular = Integer.parseInt(st.nextToken());
        vice = Integer.parseInt(st.nextToken());

        long answer = room;
        for (int person : people) {
            int target = person - regular;
            if (target > 0) {
                int cnt = (target) % vice;
                answer += cnt == 0 ? (target) / vice : (target / vice) + 1;
            }
        }

        System.out.println(answer);
    }
}
