import java.io.*;
import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42583
class Main {

    private static void solve(BufferedReader br) throws IOException {
        int bridge_length = 5;
        int weight = 5;
        int[] truck_weights = new int[]{2,2,2,2,1,1,1,1,1};

        int time = 0;
        int truckIdx = 0;
        int bridgeWeight = 0;
        Queue<Integer> bridge = new LinkedList<>();

        while (truckIdx < truck_weights.length) {
            time++;
            if (bridge.size() >= bridge_length) {
                bridgeWeight -= bridge.poll();
            }
            int truck = truck_weights[truckIdx];
            if (bridgeWeight + truck <= weight) {
                bridge.add(truck);
                bridgeWeight += truck;
                truckIdx++;
            } else {
                bridge.add(0); // 더미
            }
        }
        time += bridge_length;
        System.out.println(time);

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
