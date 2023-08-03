import java.io.*;
import java.util.Arrays;
import java.util.Stack;

class Main {

    // import java.util.*;
    class Solution {
        private boolean isCorrect(String s) {
            Stack<Character> stack = new Stack<>();
            for (char c: s.toCharArray()) {
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) return false;
                    if (c == ')' && stack.peek() != '(') return false;
                    if (c == ']' && stack.peek() != '[') return false;
                    if (c == '}' && stack.peek() != '{') return false;
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }
        public int solution(String s) {
            int answer = 0;
            StringBuilder sb = new StringBuilder(s);
            if (isCorrect(sb.toString())) answer++;
            for (int i = 0; i < sb.length(); i++) {
                sb.append(sb.charAt(0));
                sb.deleteCharAt(0);
                System.out.print(sb.toString() + ' ');
                if (isCorrect(sb.toString())) answer++;
            }

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
