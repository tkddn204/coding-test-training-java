package solve;
import java.io.*;

class Main {

    static enum Color {BLUE, WHITE, MIXED};

    static int n;
    static int paper[][];
    static int whiteCnt, blueCnt;

    private static Color colorCheck(int x, int y, int dv) {
        int white = 0, blue = 0;
        for (int i = x; i < x + dv; i++) {
            for (int j = y; j < y + dv; j++) {
                if (paper[i][j] == 0) white++;
                if (paper[i][j] == 1) blue++;
            }
        }
        
        if (white == 0) return Color.BLUE;
        else if (blue == 0) return Color.WHITE;
        return Color.MIXED;
    }

    private static void go(int x, int y, int dv) {
        Color color = colorCheck(x, y, dv);
        if (color != Color.MIXED) {
            if (color == Color.WHITE) whiteCnt++;
            if (color == Color.BLUE) blueCnt++;
            return;
        

        go(x, y, dv / 2);
        go(x, y + dv / 2, dv / 2);
        go(x + dv / 2, y, dv / 2);
        go(x + dv / 2, y + dv / 2, dv / 2);
    }

    private static void solve(BufferedReader br) throws IOException {
        n = Integer.parseInt(br.readLine());
        paper = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(line[j]);
            }
        }
        go(0, 0, n);
        res.append(whiteCnt + "\n");
        res.append(blueCnt);
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
