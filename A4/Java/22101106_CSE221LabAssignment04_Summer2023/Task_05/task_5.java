package Task_05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class task_5 {

    public static class Pair {
        int vertex;
        int edge;

        public Pair(int vertex, int edge) {
            this.vertex = vertex;
            this.edge = edge;
        }
    }

    public static List<Integer> shortestPath(List<List<Integer>> graph, int start, int destination) {
        int numCities = graph.size();
        int[] distances = new int[numCities];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        boolean[] visited = new boolean[numCities];
        int[] previous = new int[numCities];
        Arrays.fill(previous, -1);

        for (int i = 0; i < numCities; i++) {
            int minDistance = Integer.MAX_VALUE;
            int minCity = -1;

            for (int city = 1; city < numCities; city++) {
                if (!visited[city] && distances[city] < minDistance) {
                    minDistance = distances[city];
                    minCity = city;
                }
            }

            if (minCity == -1) {
                break;
            }

            visited[minCity] = true;

            for (int neighbor : graph.get(minCity)) {
                int newDistance = distances[minCity] + 1;
                if (newDistance < distances[neighbor]) {
                    distances[neighbor] = newDistance;
                    previous[neighbor] = minCity;
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int city = destination;
        while (city != -1) {
            path.add(city);
            city = previous[city];
        }

        int minTime = distances[destination];
        path.add(minTime);

        return path;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            String inputFileName = "Task_05/input5_" + i + ".txt";
            String outputFileName = "Task_05/output5_" + i + ".txt";

            try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
                 BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName))) {

                String[] nmd = br.readLine().trim().split(" ");
                int N = Integer.parseInt(nmd[0]);
                int M = Integer.parseInt(nmd[1]);
                int D = Integer.parseInt(nmd[2]);

                List<List<Integer>> graph = new ArrayList<>();
                for (int j = 0; j <= N; j++) {
                    graph.add(new ArrayList<>());
                }

                for (int j = 0; j < M; j++) {
                    String[] uv = br.readLine().trim().split(" ");
                    int u = Integer.parseInt(uv[0]);
                    int v = Integer.parseInt(uv[1]);
                    graph.get(u).add(v);
                    graph.get(v).add(u);
                }

                List<Integer> path = shortestPath(graph, 1, D);

                out.write("Time: " + path.get(path.size() - 1) + "\n");
                out.write("Shortest Path: " + String.join(" ", path.stream().map(Object::toString).toArray(String[]::new)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

