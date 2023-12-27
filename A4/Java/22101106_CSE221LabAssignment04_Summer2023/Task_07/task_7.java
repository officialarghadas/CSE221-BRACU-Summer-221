package Task_07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class task_7 {

    public static class Pair {
        int distance;
        int city;

        public Pair(int distance, int city) {
            this.distance = distance;
            this.city = city;
        }
    }

    public static Pair dfs(Map<Integer, List<Integer>> graph, int start, boolean[] visited) {
        visited[start] = true;
        int maxDistance = 0;
        int farthestCity = start;

        for (int neighbor : graph.get(start)) {
            if (!visited[neighbor]) {
                Pair pair = dfs(graph, neighbor, visited);
                int distance = pair.distance;
                if (distance + 1 > maxDistance) {
                    maxDistance = distance + 1;
                    farthestCity = pair.city;
                }
            }
        }

        return new Pair(maxDistance, farthestCity);
    }

    public static Pair findMaximumPath(Map<Integer, List<Integer>> graph, int N) {
        boolean[] visited = new boolean[N + 1];
        Pair pairA = dfs(graph, 1, visited);

        visited = new boolean[N + 1];
        Pair pairB = dfs(graph, pairA.city, visited);

        return new Pair(pairA.city, pairB.city);
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            String inputFileName = "Task_07/input7_" + i + ".txt";
            String outputFileName = "Task_07/output7_" + i + ".txt";

            try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
                 BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName))) {

                int N = Integer.parseInt(br.readLine());
                Map<Integer, List<Integer>> graph = new HashMap<>();

                for (int j = 1; j <= N; j++) {
                    graph.put(j, new ArrayList<>());
                }

                for (int j = 1; j <= N - 1; j++) {
                    String[] uv = br.readLine().trim().split(" ");
                    int u = Integer.parseInt(uv[0]);
                    int v = Integer.parseInt(uv[1]);
                    graph.get(u).add(v);
                    graph.get(v).add(u);
                }

                Pair result = findMaximumPath(graph, N);
                out.write(result.city + " " + result.distance);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
