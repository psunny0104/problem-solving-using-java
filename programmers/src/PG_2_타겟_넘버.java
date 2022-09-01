public class PG_2_타겟_넘버 {

    static int N, answer;

    public int solution(int[] numbers, int target) {

        N = numbers.length;

        search(0, 0, numbers, target);
        return answer;
    }


    private static void search(int cnt, int sum, int[] numbers, int target) {
        if (cnt == N) {
            if (sum == target) {
                answer++;
            }

            return;
        }

        search(cnt + 1, sum + numbers[cnt], numbers, target);
        search(cnt + 1, sum - numbers[cnt], numbers, target);
    }

}
