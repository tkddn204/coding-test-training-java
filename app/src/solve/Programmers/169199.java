import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static class Point {
        int x, y;
        Point (int x, int y) { this.x = x; this.y = y; }

        @Override
        public boolean equals(Object o) {
            if (o == null || this.getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return this.x == point.x && this.y == point.y;
        }
    }

    private static void solve(BufferedReader br) throws IOException {
        String[] board = new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};

        int n = board.length;
        int m = board[0].length();
        Queue<Point> que = new LinkedList<>();
        Point end = new Point(0, 0);

        int[][] vst = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(vst[i], -1);

        for (int i = 0; i < board.length; i++) {
            StringBuilder builder = new StringBuilder(board[i]);
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'G') {
                    builder.setCharAt(j, '.');
                    end = new Point(i, j);
                }
                if (board[i].charAt(j) == 'R') {
                    que.add(new Point(i, j));
                    vst[i][j] = 0;
                }
                board[i] = builder.toString();
            }
        }

        while (!que.isEmpty()) {
            Point cur = que.poll();

            if (cur.equals(end)) break;

            for (int d = 0 ; d < 4; d++) {
                int nx = cur.x;
                int ny = cur.y;
                while (true) {
                    nx += dx[d];
                    ny += dy[d];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                    if (board[nx].charAt(ny) == 'D') break;
                }
                nx -= dx[d];
                ny -= dy[d];
                if (vst[nx][ny] > -1) continue;
                que.add(new Point(nx, ny));
                vst[nx][ny] = vst[cur.x][cur.y] + 1;
            }
        }
        printVst(n, m, vst);
        int answer = vst[end.x][end.y];
        System.out.println(answer);
    }

    private static void printVst(int n, int m, int[][] vst) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                String s;
                if (vst[i][j] == -1) s = "- ";
                else s = vst[i][j] + " ";
                System.out.print(s);
            }
            System.out.println();
        }
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
