import java.io.*;

class Programmers {

    // import java.util.*;
    class Solution {
        // 문제 삽입
        public int solution() {
            int answer = 0;
            return answer;
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
            new Programmers().solve(br);
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
