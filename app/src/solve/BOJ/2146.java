package solve;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    static int n;
    static int mp[][];
    static int vst[][];
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};
    static int minLen = Integer.MAX_VALUE;

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void checkBridge(int i, int j) {
        vst = new int[n][n];
        for (int v = 0; v < n; v++) {
            Arrays.fill(vst[v], -1);
        }
        int num = mp[i][j];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(i, j));
        vst[i][j] = 0;

        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (mp[nx][ny] == num || vst[nx][ny] >= 0) continue;
                if (mp[nx][ny] != 0) {
                    minLen = Math.min(minLen, vst[p.x][p.y]);
                    return;
                }
                queue.add(new Pair(nx, ny));
                vst[nx][ny] = vst[p.x][p.y] + 1;
            }
        }
    }

    private static void solve(BufferedReader br) throws IOException {
        n = Integer.parseInt(br.readLine());
        mp = new int[n][n];
        vst = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                mp[i][j] = Integer.parseInt(line[j]);
            }
        }

        // 대륙마다 숫자로 표시해주기
        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (vst[i][j] > 0 || mp[i][j] == 0) continue;
                Queue<Pair> queue = new LinkedList<>();       // 새로 알게 된 부분
                queue.add(new Pair(i, j));
                vst[i][j] = num;
                while (!queue.isEmpty()) {
                    Pair p = queue.poll();

                    for (int d = 0; d < 4; d++) {
                        int nx = p.x + dx[d];
                        int ny = p.y + dy[d];
                        if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                        if (vst[nx][ny] > 0 || mp[nx][ny] == 0) continue;
                        queue.add(new Pair(nx, ny));
                        vst[nx][ny] = num;
                    }
                }
                num++;
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mp[i][j] = vst[i][j];
            }
        }

        // 다리길이 재기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mp[i][j] == 0) continue;
                checkBridge(i, j);
            }
        }
        
        res.append(minLen);
    }

    private static StringBuilder res;
    private static void print(BufferedWriter bw) throws IOException {
        bw.write(res.toString());
        bw.flush();
    }

    public static void main(String args[]) throws Exception {
        //fileInputOutput();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            res = new StringBuilder();
            solve(br);
            print(bw);
        }
    }

    private static String INPUT_PATH = "D:/Projects/coding-test-training-cpp/input.txt";
    private static String OUTPUT_PATH = "D:/Projects/coding-test-training-cpp/output.txt";

    private static void fileInputOutput() throws IOException {
        System.setIn(new FileInputStream(INPUT_PATH));
        System.setOut(
            new PrintStream(
                new BufferedOutputStream(
                    new FileOutputStream(OUTPUT_PATH)
            )));
    }
}
