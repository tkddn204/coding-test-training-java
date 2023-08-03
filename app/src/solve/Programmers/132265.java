import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Main {

    // import java.util.*;
    class Solution {
        public int solution(int[] topping) {
            int answer = 0;
            int n = topping.length;
            Map<Integer, Integer> AMap = new HashMap<>();
            Map<Integer, Integer> BMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                BMap.put(topping[i], BMap.getOrDefault(topping[i], 0) + 1);
            }
            for (int key: BMap.keySet()) {
                System.out.println(key + " : " + BMap.get(key));
            }
            for (int i = 0; i < n; i++) {
                AMap.put(topping[i], AMap.getOrDefault(topping[i], 0) + 1);
                BMap.put(topping[i], BMap.get(topping[i]) - 1);
                if (BMap.get(topping[i]).equals(0)) BMap.remove(topping[i]);
                if (AMap.size() == BMap.size()) answer++;
            }
            return answer;
        }
    }

    private void solve(BufferedReader br) throws IOException {
        String input = br.readLine();
        String[] splits = input.split("\t");

        // 인풋 삽입

//        new Solution().solution();
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
