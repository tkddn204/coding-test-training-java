import java.io.*;
import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/176962
class Main{
  static class Plan {
    String name;
    int h, m;
    int play;
    Plan(String name, int h, int m, int play) {
      this.name = name;
      this.h = h;
      this.m = m;
      this.play = play;
    }
  }

  private static int comparePlan(Plan a, Plan b) {
    if (a.h > b.h) return 1;
    else if (a.h < b.h) return -1;
    else {
      if  (a.m > b.m) return 1;
      else if (a.m < b.m) return -1;
    }
    return 0;
  }

  private static void solve(BufferedReader br) throws IOException {
    String [][] plans = {{"science", "12:40", "50"},
        {"music", "12:20", "40"},
        {"history", "14:00", "40"},
        {"computer", "12:30", "100"}
    };
    ArrayList<Plan> planList = new ArrayList<>();
    ArrayList<String> answer = new ArrayList<>();

    // Plan 객체를 만들어서 정렬
    for (int i = 0; i < plans.length; i++) {
      String[] time = plans[i][1].split(":");
      planList.add(new Plan(plans[i][0],
          Integer.parseInt(time[0]),
          Integer.parseInt(time[1]),
          Integer.parseInt(plans[i][2])));
    }
    planList.sort(Main::comparePlan);

    // 시작
    int idx = 0;
    int t = -1;
    Stack<Plan> stack = new Stack<>();
    Plan proc = null;
    while (t++ < (60 * 24)) {
      int h = t / 60, m = t % 60;

      Plan plan = null;
      boolean checkNewTime = false;
      if (idx < plans.length) {
        plan = planList.get(idx);
        checkNewTime = plan.h == h && plan.m == m;
      }
      if (proc != null) {
        proc.play--;
        if (proc.play <= 0) {
          answer.add(proc.name);
          proc = null;
          if (idx == plans.length) break;
          else if (!stack.isEmpty()) {
            proc = stack.pop();
          }
        }
        if (checkNewTime) {
          if (proc != null) stack.push(proc);
          proc = plan;
          idx++;
        }
      } else if (checkNewTime) {
        proc = plan;
        idx++;
      }
    }
    if (proc != null) {
      answer.add(proc.name);
    }
    while (!stack.isEmpty()) {
      answer.add(stack.pop().name);
    }
    for (String name : answer) {
      System.out.println(name);
    }
//    return answer.toArray(new String[0]);
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

  private static String INPUT_PATH = "D:/Projects/coding-test-training-java/app/src/main/resources/input.txt";
  private static String OUTPUT_PATH = "D:/Projects/coding-test-training-java/app/src/main/resources/output.txt";

  private static void fileInputOutput() throws IOException {
    System.setIn(new FileInputStream(INPUT_PATH));
    System.setOut(
        new PrintStream(
            new BufferedOutputStream(
                new FileOutputStream(OUTPUT_PATH)
            )));
  }
}
