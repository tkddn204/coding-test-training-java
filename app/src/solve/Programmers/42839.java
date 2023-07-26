import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Main {

    // import java.util.*;
    class Solution {
        final int MAX = 10000000;
        boolean[] prime = new boolean[MAX]; // 소수 미리 저장
        Map<Integer, Boolean> chk; // 백트래킹 시 숫자 중복 방지용 체크 맵
        boolean[] vst; // 백트래킹 방문 배열
        int answer = 0;
        void backtracking(String numStr, String numbers) {
            int num = Integer.parseInt(numStr);
            if (!chk.containsKey(num) && prime[num]) {
                chk.put(num, true);
                System.out.println(num);
                answer++;
            }
            for (int i = 0; i < numbers.length(); i++) {
                if (vst[i]) continue;
                vst[i] = true;
                backtracking(numStr + numbers.charAt(i), numbers);
                vst[i] = false;
            }
        }

        public int solution(String numbers) {
            // 소수 생성(에라토스테네스의 체)
            Arrays.fill(prime, true);
            prime[0] = prime[1] = false;
            for (int i = 2; i * i < MAX; i++) {
                if (!prime[i]) continue;
                for (int j = i * i; j < MAX; j += i) {
                    prime[j] = false;
                }
            }

            chk = new HashMap<>();
            vst = new boolean[numbers.length()];
            Arrays.fill(vst, false);
            for (int i = 0; i < numbers.length(); i++) {
                vst[i] = true;
                backtracking(String.valueOf(numbers.charAt(i)), numbers);
                vst[i] = false;
            }

            return answer;
        }
    }

    private void solve(BufferedReader br) throws IOException {
        String input = br.readLine();
        String[] splits = input.split("\t");

        // 인풋 삽입

        new Solution().solution(input);
    }

    public static void main(String args[]) throws Exception {
        fileInputOutput();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            new Main().solve(br);
        }
    }

    private static String INPUT_PATH = "D:\\Projects\\coding-test-training-java\\app\\src\\main\\resources\\input.txt";
    private static String OUTPUT_PATH = "D:\\Projects\\coding-test-training-java\\app\\src\\main\\resources\\output.txt";

    private static void fileInputOutput() throws IOException {
        System.setIn(new FileInputStream(INPUT_PATH));
        System.setOut(
            new PrintStream(
                new BufferedOutputStream(
                    new FileOutputStream(OUTPUT_PATH)
            )));
    }
}
