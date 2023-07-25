import java.io.*;
import java.util.Arrays;

class Main {

    static int[] memo;

    static class Time {
        int sh, sm;
        int eh, em;
        Time(int sh, int sm, int eh, int em) {
            this.sh = sh;
            this.sm = sm;
            this.eh = eh;
            this.em = em;
        }
    }

    private static void solve(BufferedReader br) throws IOException {
        String[][] book_time = new String[3][3];
        Time[] times = new Time[1001];
        for (int i = 0; i < book_time.length; i++) {
            String[] startTime = book_time[i][0].split(":");
            String[] endTime = book_time[i][1].split(":");
            times[i] = new Time(
                Integer.parseInt(startTime[0]),
                Integer.parseInt(startTime[1]),
                Integer.parseInt(endTime[0]),
                Integer.parseInt(endTime[1])
            );
        }
        memo = new int[1500];
        Arrays.fill(memo, 0);
        for (int i = 0; i < book_time.length; i++) {
            int st = times[i].sh * 60 + times[i].sm;
            int en = times[i].eh * 60 + times[i].em;
            memo[st]++;
            memo[en + 10]--;
        }
        int maxVal = 0;
        for (int i = 1; i < 1500; i++) {
            memo[i] += memo[i - 1];
            maxVal = Math.max(maxVal, memo[i]);
        }
//        return maxVal;
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
