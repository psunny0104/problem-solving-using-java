import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BOJ_7662_이중_우선순위_큐 {

    static int T, K;
    static PriorityQueue<Integer> naturalOrderPQ = new PriorityQueue<>();
    static PriorityQueue<Integer> reverseOrderPQ = new PriorityQueue<>(Comparator.reverseOrder());
    static Map<Integer, Integer> numberCnt = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            K = Integer.parseInt(br.readLine());
            naturalOrderPQ.clear();
            reverseOrderPQ.clear();
            numberCnt.clear();
            for (int i = 0; i < K; i++) {
                String[] input = br.readLine().split(" ");
                String commands = input[0];
                int number = Integer.parseInt(input[1]);
                if (commands.equals("I")) {
                    reverseOrderPQ.offer(number);
                    naturalOrderPQ.offer(number);
                    numberCnt.put(number, numberCnt.getOrDefault(number, 0) + 1);
                } else {
                    if (number == 1) {
                        int target;
                        while (!reverseOrderPQ.isEmpty()) {
                            target = reverseOrderPQ.poll();
                            if (numberCnt.containsKey(target)) {
                                if (numberCnt.get(target) - 1 == 0) {
                                    numberCnt.remove(target);
                                } else {
                                    numberCnt.put(target, numberCnt.get(target) - 1);
                                }
                                break;
                            }
                        }
                    } else {
                        int target;
                        while (!naturalOrderPQ.isEmpty()) {
                            target = naturalOrderPQ.poll();
                            if (numberCnt.containsKey(target)) {
                                if (numberCnt.get(target) - 1 == 0) {
                                    numberCnt.remove(target);
                                } else {
                                    numberCnt.put(target, numberCnt.get(target) - 1);
                                }
                                break;
                            }
                        }

                    }

                }
            }
            print(sb);
        }
        System.out.print(sb);
    }

    private static void print(StringBuilder sb) {
        if (numberCnt.isEmpty()) {
            sb.append("EMPTY");
        } else {
            int min = 0;
            int max = 0;
            boolean isMaxSelected = false;
            boolean isMinSelected = false;
            while (!reverseOrderPQ.isEmpty()) {
                if (numberCnt.containsKey(reverseOrderPQ.peek())) {
                    max = reverseOrderPQ.poll();
                    isMaxSelected = true;
                    break;
                }
                reverseOrderPQ.poll();
            }

            while (!naturalOrderPQ.isEmpty()) {
                if (numberCnt.containsKey(naturalOrderPQ.peek())) {
                    min = naturalOrderPQ.poll();
                    isMinSelected = true;
                    break;
                }
                naturalOrderPQ.poll();
            }

            if (isMaxSelected && isMinSelected) {
                sb.append(max).append(" ").append(min);
            } else if (!isMaxSelected && isMinSelected) {
                sb.append(min).append(" ").append(min);
            } else if (isMaxSelected && !isMinSelected) {
                sb.append(max).append(" ").append(max);
            } else {
                sb.append("EMPTY");
            }
        }
        sb.append("\n");
    }
}


