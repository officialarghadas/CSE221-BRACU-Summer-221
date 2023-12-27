package Task_04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class task_4 {

    public static class Pair {
        int vertex;
        int edge;

        public Pair(int vertex, int edge) {
            this.vertex = vertex;
            this.edge = edge;
        }
    }

    public static boolean hasCycle(List<List<Integer>> graph, int start, boolean[] visited) {
        visited[start] = true;

        for (int neighbor : graph.get(start)) {
            if (visited[neighbor]) {
                return true;
            }
            if (hasCycle(graph, neighbor, visited)) {
                return true;
            }
        }

        visited[start] = false;
        return false;
    }

    public static String checkCycle(int n, List<Pair> arr) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (Pair pair : arr) {
            graph.get(pair.vertex).add(pair.edge);
        }

        boolean[] visited = new boolean[n + 1];

        for (int city = 1; city <= n; city++) {
            if (hasCycle(graph, city, visited)) {
                return "YES";
            }
        }

        return "NO";
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            String inputFileName = "Task_04/input4_" + i + ".txt";
            String outputFileName = "Task_04/output4_" + i + ".txt";

            try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
                 BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName))) {

                String[] nm = br.readLine().trim().split(" ");
                int n = Integer.parseInt(nm[0]);
                int m = Integer.parseInt(nm[1]);

                List<Pair> inputArr = new ArrayList<>();
                for (int j = 0; j < m; j++) {
                    String[] values = br.readLine().trim().split(" ");
                    int vertex = Integer.parseInt(values[0]);
                    int edge = Integer.parseInt(values[1]);
                    inputArr.add(new Pair(vertex, edge));
                }

                out.write(checkCycle(n, inputArr));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}