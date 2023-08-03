import java.io.*;
import java.util.*;

class Main {

    private static int[] stones;
    private static int sum;

    static class Pair {
        int a, b;
        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    private static int findSame() {
        boolean[][] vst = new boolean[1501][1501];

        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(stones[0], stones[1]));
        vst[stones[0]][stones[1]] = true;

        while (!que.isEmpty()) {
            Pair cur = que.poll();

            int[] nxt = {cur.a, cur.b, sum - cur.a - cur.b};
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (nxt[i] < nxt[j]) {
                        int[] tmp = Arrays.copyOf(nxt, 3);
                        tmp[i] += nxt[i];
                        tmp[j] -= nxt[i];
                        if (tmp[j] < 0) continue;
                        if (vst[tmp[0]][tmp[1]]) continue;
                        que.add(new Pair(tmp[0], tmp[1]));
                        vst[tmp[0]][tmp[1]] = true;
                    }
                }
            }
        }
        return vst[sum / 3][sum / 3] ? 1 : 0;
    }
    private static void solve(BufferedReader br) throws IOException {
        stones = Arrays.stream(
            br.readLine().split(" ")
        ).mapToInt(Integer::parseInt).toArray();
        sum = Arrays.stream(stones).reduce(Integer::sum).orElse(0);
        if (sum % 3 != 0) res.append(0);
        else res.append(findSame());
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
