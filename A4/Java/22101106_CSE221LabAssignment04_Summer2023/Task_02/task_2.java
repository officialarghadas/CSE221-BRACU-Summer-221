package Task_02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class task_2 {

    static class Pair {
        int source;
        int destination;

        public Pair(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    public static List<List<Integer>> createGraph(int n, int m, List<Pair> arr) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (Pair pair : arr) {
            graph.get(pair.source).add(pair.destination);
            graph.get(pair.destination).add(pair.source);
        }
        return graph;
    }

    public static List<Integer> bfs(int n, List<List<Integer>> graph) {
        List<Integer> bfsPath = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int s = queue.poll();
            bfsPath.add(s);

            for (int i : graph.get(s)) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
        return bfsPath;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 5; i++) {
            String inputFileName = "Task_02\\input2_" + i + ".txt";
            String outputFileName = "Task_02\\output2_" + i + ".txt";

            try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
                 BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName))) {

                String[] nm = br.readLine().trim().split(" ");
                int n = Integer.parseInt(nm[0]);
                int m = Integer.parseInt(nm[1]);

                List<Pair> inputArr = new ArrayList<>();
                for (int j = 0; j < m; j++) {
                    String[] values = br.readLine().trim().split(" ");
                    int source = Integer.parseInt(values[0]);
                    int destination = Integer.parseInt(values[1]);
                    inputArr.add(new Pair(source, destination));
                }

                List<List<Integer>> graph = createGraph(n, m, inputArr);
                List<Integer> bfsPath = bfs(n, graph);
                out.write(String.join(" ", bfsPath.stream().map(String::valueOf).toArray(String[]::new)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}