import java.io.*;
import java.util.*;

class Main {

    private static void solve(BufferedReader br) throws IOException {
        String[] inp = br.readLine().split(" ");
        int n = Integer.parseInt(inp[0]);
        int m = Integer.parseInt(inp[1]);
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) deque.addLast(i);

        inp = br.readLine().split(" ");
        int oper = 0;
        for (String s : inp) {
            int k = Integer.parseInt(s);
            if (deque.isEmpty()) break;
            if (k == deque.peekFirst()) {
                deque.pollFirst();
                continue;
            }
            Iterator<Integer> ascIter = deque.iterator();
            int l = 0;
            while (ascIter.hasNext() && ascIter.next() != k) l++;
//            res.append(l).append(",").append(r).append("\n");
            if (l > deque.size() / 2) { // 오른쪽 이동
                while (deque.peekFirst() != k) {
                    deque.addFirst(deque.pollLast());
                    oper++;
                }
            } else { // 왼쪽 이동
                while (deque.peekFirst() != k) {
                    deque.addLast(deque.pollFirst());
                    oper++;
                }
            }
            deque.pollFirst();
        }
        res.append(oper);
    }

    private static StringBuilder res;
    private static void print(BufferedWriter bw) throws IOException {
        bw.write(res.toString());
        bw.flush();
    }

    public static void main(String args[]) throws Exception {
        fileInputOutput();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            res = new StringBuilder();
            solve(br);
            print(bw);
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
