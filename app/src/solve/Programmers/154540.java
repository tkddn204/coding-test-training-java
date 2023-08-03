import java.io.*;
import java.util.*;

class Main {

    // import java.util.*;
    class Solution {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        class Pair {
            int x, y;
            Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        public int[] solution(String[] maps) {
            ArrayList<Integer> answerList = new ArrayList<>();
            int n = maps.length;
            int m = maps[0].length();
            boolean[][] vst = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (vst[i][j] || maps[i].charAt(j) == 'X') continue;
                    Queue<Pair> que = new LinkedList<>();
                    que.add(new Pair(i, j));
                    vst[i][j] = true;
                    int sum = maps[i].charAt(j) - '0';
                    while (!que.isEmpty()) {
                        Pair cur = que.poll();

                        for (int d = 0; d < 4; d++) {
                            int nx = cur.x + dx[d];
                            int ny = cur.y + dy[d];
                            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                            if (vst[nx][ny] || maps[nx].charAt(ny) == 'X') continue;
                            sum += maps[nx].charAt(ny) - '0';
                            que.add(new Pair(nx, ny));
                            vst[nx][ny] = true;
                        }
                    }
                    answerList.add(sum);
                }
            }
            if (answerList.isEmpty()) answerList.add(-1);
            else Collections.sort(answerList);
            return answerList.stream().mapToInt(Integer::intValue).toArray();
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
