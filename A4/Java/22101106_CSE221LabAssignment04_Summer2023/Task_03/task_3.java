package Task_03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class task_3 {

    public static List<List<Integer>> createAdjList(int n, List<Pair> arr) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (Pair pair : arr) {
            adjList.get(pair.source).add(pair.destination);
            adjList.get(pair.destination).add(pair.source);
        }
        return adjList;
    }

    public static List<Integer> dfs(int n, List<List<Integer>> adjList) {
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfsUtil(i, adjList, visited, res);
            }
        }
        return res;
    }

    public static void dfsUtil(int v, List<List<Integer>> adjList, boolean[] visited, List<Integer> res) {
        visited[v] = true;
        res.add(v);

        for (int i : adjList.get(v)) {
            if (!visited[i]) {
                dfsUtil(i, adjList, visited, res);
            }
        }
    }

    static class Pair {
        int source;
        int destination;

        public Pair(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 5; i++) {
            String inputFileName = "Task_03\\input3_" + i + ".txt";
            String outputFileName = "Task_03\\output3_" + i + ".txt";

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

                List<List<Integer>> adjList = createAdjList(n, inputArr);
                List<Integer> dfsRes = dfs(n, adjList);
                out.write(String.join(" ", dfsRes.stream().map(String::valueOf).toArray(String[]::new)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
