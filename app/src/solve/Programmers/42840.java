import java.io.*;
import java.util.*;

class Main {

    class Solution {

        private int check(int[] answers, int[] student) {
            int idx = 0;
            int correct = 0;
            for (int i = 0; i < answers.length; i++) {
                if (student[idx] == answers[i]) correct++;
                idx = (idx + 1) % student.length;
            }
            return correct;
        }

        // 문제 삽입
        public int[] solution(int[] answers) {
            ArrayList<Integer> list = new ArrayList<>();
            int[] one = {1, 2, 3, 4, 5};
            int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
            int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

            list.add(check(answers, one));
            list.add(check(answers, two));
            list.add(check(answers, three));

            int max = Collections.max(list);

            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == max) res.add(i + 1);
            }

            Integer[] arr = res.toArray(new Integer[0]);
            return Arrays.stream(arr).mapToInt(Integer::intValue).toArray();
        }
    }

    private void solve(BufferedReader br) throws IOException {
        String input = br.readLine();
        String[] splits = input.split("\t");

        // 인풋 삽입

        new Solution().solution();
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
