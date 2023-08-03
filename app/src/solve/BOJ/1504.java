import java.io.*;
import java.util.*;

class Main {

    private static final int MAX = 100000000;

    static class Pair implements Comparable<Pair> {
        int x, cost;
        public Pair(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair o) {
            return cost - o.cost;
        }
    }

    private static int n, e;
    private static List<ArrayList<Pair>> graph;

    private static int dijkstra(int a, int b) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, MAX);

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        dist[a] = 0;
        pq.add(new Pair(a, 0));
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            for (Pair nxt : graph.get(cur.x)) {
                int nxtDist = cur.cost + nxt.cost;
                if (nxtDist < dist[nxt.x]) {
                    dist[nxt.x] = nxtDist;
                    pq.add(new Pair(nxt.x, nxtDist));
                }
            }
        }

        return dist[b];
    }

    private static int go(int[] path) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            int a = path[i];
            int b = path[i + 1];
            if (a == b) continue;
            int len = dijkstra(a, b);
            if (len >= MAX) {
                sum = -1;
                break;
            } else sum += len;
        }
        return sum;
    }
    private static void solve(BufferedReader br) throws IOException {
        int[] inp = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();
        n = inp[0]; e = inp[1];
        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < e; i++) {
            int[] p = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
            graph.get(p[0]).add(new Pair(p[1], p[2]));
            graph.get(p[1]).add(new Pair(p[0], p[2]));
        }
        int[] vertex = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();
        int v1 = vertex[0];
        int v2 = vertex[1];

        int[] path = {1, v1, v2, n};
        int[] path2 = {1, v2, v1, n};
        int minPath = Math.min(go(path), go(path2));
        res.append(minPath);
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
