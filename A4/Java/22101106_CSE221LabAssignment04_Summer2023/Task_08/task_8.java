package Task_08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class task_8 {

    public static boolean dfs(Map<Integer, List<Integer>> graph, int start, int[] colors) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        colors[start] = 0;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            int currentColor = colors[current];

            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (colors[neighbor] == -1) {
                    colors[neighbor] = 1 - currentColor;
                    stack.push(neighbor);
                } else if (colors[neighbor] == currentColor) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 2; i++) {
            String inputFileName = "Task_08/input8_" + i + ".txt";
            String outputFileName = "Task_08/output8_" + i + ".txt";

            try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
                 BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName))) {

                int N = Integer.parseInt(br.readLine());
                StringBuilder cases = new StringBuilder();

                for (int t = 0; t < N; t++) {
                    int n = Integer.parseInt(br.readLine());
                    Map<Integer, List<Integer>> graph = new HashMap<>();
                    int[] colors = new int[n + 1];

                    for (int j = 0; j < n; j++) {
                        String[] uv = br.readLine().trim().split(" ");
                        int u = Integer.parseInt(uv[0]);
                        int v = Integer.parseInt(uv[1]);

                        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
                        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
                    }

                    int ans = 0;
                    boolean isValid = true;

                    for (int j = 1; j <= n; j++) {
                        if (colors[j] == -1) {
                            if (dfs(graph, j, colors)) {
                                int group0 = countOccurrences(colors, 0);
                                int group1 = countOccurrences(colors, 1);
                                ans += Math.max(group0, group1);
                            } else {
                                isValid = false;
                                break;
                            }
                        }
                    }

                    if (!isValid) {
                        ans = -1;
                    }

                    cases.append("Case ").append(t + 1).append(": ").append(ans).append("\n");
                }

                out.write(cases.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int countOccurrences(int[] arr, int target) {
        int count = 0;
        for (int num : arr) {
            if (num == target) {
                count++;
            }
        }
        return count;
    }
}