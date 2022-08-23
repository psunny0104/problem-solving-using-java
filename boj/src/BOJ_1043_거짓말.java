import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1043_거짓말 {

    static int N, M, peopleKnowTruth, answer;
    static int[] peopleKnowTruthIdx, parents;
    static int[][] parties;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        peopleKnowTruth = Integer.parseInt(st.nextToken());
        peopleKnowTruthIdx = new int[peopleKnowTruth + 1];
        for (int i = 1; i <= peopleKnowTruth; i++) {
            peopleKnowTruthIdx[i] = Integer.parseInt(st.nextToken());
        }

        makeSet();

        parties = new int[M + 1][];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int peopleInParty = Integer.parseInt(st.nextToken());
            parties[i] = new int[peopleInParty];

            for (int j = 0; j < peopleInParty; j++) {
                parties[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeUnionInParty();

        for (int i = 1; i <= M; i++) {
            if (check(i)) answer++;
        }

        System.out.println(answer);
    }

    private static void makeUnionInParty() {
        for (int p = 1; p <= M; p++) {
            int[] targets = parties[p];
            int size = targets.length;
            for (int i = 0; i < size; i++) {
                if (i + 1 >= size) {
                    continue;
                }

                for (int j = i + 1; j < size; j++) {
                    union(targets[i], targets[j]);
                }
            }
        }
    }

    private static void makeSet() {
        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static boolean check(int idx) {
        for (int i = 0; i < parties[idx].length; i++) {
            int inPartyIdx = parties[idx][i];
            for (int j = 1; j <= peopleKnowTruth; j++) {
                int knowTruthIdx = peopleKnowTruthIdx[j];
                if (find(inPartyIdx) == find(knowTruthIdx)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }

    private static int find(int a) {
        if (parents[a] == a) {
            return a;
        } else {
            return parents[a] = find(parents[a]);
        }
    }
}
