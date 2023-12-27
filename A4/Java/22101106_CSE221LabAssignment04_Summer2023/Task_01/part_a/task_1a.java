package Task_01.part_a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class task_1a{

    public static int[][] adjMatrixCreator(int n, int m, int[][] arr) {
        int[][] matrix = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            matrix[arr[i][0]][arr[i][1]] = arr[i][2];
        }
        return matrix;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 3; i++) {
            String inputFileName = "Task_01\\part_a\\input1a_" + i + ".txt";
            String outputFileName = "Task_01\\part_a\\output1a_" + i + ".txt";

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

                int[][] adjMatrix = adjMatrixCreator(n, m, inputArr);
                for (int[] row : adjMatrix) {
                    for (int val : row) {
                        out.write(val + " ");
                    }
                    out.write("\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}