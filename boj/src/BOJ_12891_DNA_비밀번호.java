import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_12891_DNA_비밀번호 {
    // 순열 -> 결과 중복 제거
    static int S, P, answer;
    static int[] condition, curState;
    static char[] target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        S = Integer.parseInt(in.nextToken());
        P = Integer.parseInt(in.nextToken());
        target = br.readLine().toCharArray();
        condition = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        curState = new int[4];

        int st = 0;
        int ed = st + P - 1;

        init();

        while (true) {
            if (isValid()) {
                answer++;
            }
            ed++;
            if (ed >= S) {
                break;
            }
            update(st++, ed);
        }
        System.out.println(answer);
    }


    private static void init() {
        for (int i = 0; i < P; i++) {
            plusEd(i);
        }
    }

    private static boolean isValid() {
        boolean isOk = true;

        for (int i = 0; i < 4; i++) {
            if (curState[i] < condition[i]) {
                isOk = false;
                break;
            }
        }

        return isOk;
    }

    private static void update(int st, int ed)  {
        subtractSt(st);
        plusEd(ed);
    }
    private static void plusEd(int ed) {
        switch (target[ed]) {
            case 'A':
                curState[0]++;
                break;
            case 'C':
                curState[1]++;
                break;
            case 'G':
                curState[2]++;
                break;
            case 'T':
                curState[3]++;
                break;
        }
    }
    private static void subtractSt(int st) {
        switch (target[st]) {
            case 'A':
                curState[0]--;
                break;
            case 'C':
                curState[1]--;
                break;
            case 'G':
                curState[2]--;
                break;
            case 'T':
                curState[3]--;
                break;
        }
    }
}
