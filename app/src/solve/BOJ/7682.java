import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Main {

    static boolean isCellsSame(String s, char turn) {
        for (char c : s.toCharArray())
            if (c != turn) return false;
        return true;
    }

    static boolean existWin(String[] board, char turn) {
        // 가로
        for (String row: board) {
            if (isCellsSame(row, turn)) return true;
        }
        // 세로
        for (int i = 0; i < 3; i++) {
            StringBuilder col = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                col.append(board[j].charAt(i));
            }
            if (isCellsSame(col.toString(), turn)) return true;
        }
        // 왼쪽 대각선
        StringBuilder diag = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            diag.append(board[i].charAt(i));
        }
        if (isCellsSame(diag.toString(), turn)) return true;

        // 오른쪽 대각선
        StringBuilder diag2 = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            diag2.append(board[i].charAt(2 - i));
        }
        if (isCellsSame(diag2.toString(), turn)) return true;

        return false;
    }

    private static boolean isOrderCorrect(String[] board) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('O', 0); map.put('X', 0); map.put('.', 0);
        for (String s : board) {
            for (char c : s.toCharArray()) {
                map.put(c, map.get(c) + 1);
            }
        }

        int ONum = map.get('O');
        int XNum = map.get('X');
        if (XNum - ONum > 1) return false;
        if (ONum > XNum) return false;

        boolean OWin = existWin(board, 'O');
        boolean XWin = existWin(board, 'X');
        if (OWin && XWin) return false;
        if (XWin && ONum >= XNum) return false;
        if (OWin && ONum < XNum) return false;

        if (!(OWin || XWin)) {
            for (String s : board) {
                for (char c : s.toCharArray()) {
                    if (c == '.') return false;
                }
            }
        }

        return true;
    }



    private static void solve(BufferedReader br) throws IOException {
        while (true) {
            String inp = br.readLine();
            if (inp.equals("end")) break;

            String[] board = new String[3];
            for (int i = 0; i < 3; i++) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 3; j++) {
                    builder.append(inp.charAt(i * 3 + j));
                }
                board[i] = builder.toString();
            }

            if (isOrderCorrect(board)) res.append("valid");
            else res.append("invalid");
            res.append('\n');
        }
    }

    private static StringBuilder res;
    private static void print(BufferedWriter bw) throws IOException {
        bw.write(res.toString());
        bw.flush();
    }

    public static void main(String args[]) throws Exception {
//        fileInputOutput();
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
