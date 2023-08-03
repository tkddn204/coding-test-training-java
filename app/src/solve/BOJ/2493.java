import java.io.*;
import java.util.*;

class Main {

    private static int[] top;

    private static void solve(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String[] inp = br.readLine().split(" ");
        top = Arrays.stream(inp).mapToInt(Integer::parseInt).toArray();

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && top[stack.peek()] < top[i]) stack.pop();
            if (stack.isEmpty()) res.append(0).append(' ');
            else res.append(stack.peek() + 1).append(' ');
            stack.push(i);
        }
    }

    private static StringBuilder res;
    public static void main(String args[]) throws Exception {
        if (args.length > 1) fileInputOutput(args[0], args[1]);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            res = new StringBuilder();
            solve(br);
            bw.write(res.toString());
        }
    }

    private static void fileInputOutput(String inputPath, String outputPath) throws IOException {
        System.setIn(new FileInputStream(inputPath));
        System.setOut(
            new PrintStream(
                new BufferedOutputStream(
                    new FileOutputStream(outputPath)
            )));
    }
}
