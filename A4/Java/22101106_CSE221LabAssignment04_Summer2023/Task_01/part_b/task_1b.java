package Task_01.part_b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class task_1b {

    public static List<List<Pair>> adjListCreator(int n, int m, int[][] inputArr) {
        List<List<Pair>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int source = inputArr[i][0];
            int destination = inputArr[i][1];
            int weight = inputArr[i][2];
            adjList.get(source).add(new Pair(destination, weight));
        }
        return adjList;
    }

    static class Pair {
        int destination;
        int weight;

        public Pair(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 4; i++) {
            String inputFileName = "Task_01\\part_b\\input1b_" + i + ".txt";
            String outputFileName = "Task_01\\part_b\\output1b_" + i + ".txt";

            try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
                 BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName))) {

                String[] nm = br.readLine().trim().split(" ");
                int n = Integer.parseInt(nm[0]);
                int m = Integer.parseInt(nm[1]);

                int[][] inputArr = new int[m][3];
                for (int j = 0; j < m; j++) {
                    String[] values = br.readLine().trim().split(" ");
                    for (int k = 0; k < 3; k++) {
                        inputArr[j][k] = Integer.parseInt(values[k]);
                    }
                }

                List<List<Pair>> adjList = adjListCreator(n, m, inputArr);
                for (int j = 0; j <= n; j++) {
                    out.write(j + " :");
                    for (Pair pair : adjList.get(j)) {
                        out.write(" (" + pair.destination + ", " + pair.weight + ")");
                    }
                    out.write("\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}