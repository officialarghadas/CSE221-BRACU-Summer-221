package Task_06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class task_6 {

    public static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static boolean canMove(int row, int col, char[][] grid, boolean[][] visited) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        return (row < 0 || row >= numRows || col < 0 || col >= numCols || grid[row][col] == '#' || visited[row][col]);
    }

    public static int dfs(int row, int col, char[][] grid, boolean[][] visited) {
        int diamonds = 0;

        if (canMove(row, col, grid, visited)) {
            return 0;
        }

        visited[row][col] = true;

        if (grid[row][col] == 'D') {
            diamonds += 1;
        }

        int[] deltaRow = {0, 1, 0, -1};
        int[] deltaCol = {1, 0, -1, 0};

        for (int i = 0; i < 4; i++) {
            int newRow = row + deltaRow[i];
            int newCol = col + deltaCol[i];
            diamonds += dfs(newRow, newCol, grid, visited);
        }

        return diamonds;
    }

    public static int maximumDiamonds(int rows, int cols, char[][] grid) {
        int maxDiamonds = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '.') {
                    boolean[][] visited = new boolean[rows][cols];
                    for (int k = 0; k < rows; k++) {
                        Arrays.fill(visited[k], false);
                    }
                    maxDiamonds = Math.max(maxDiamonds, dfs(i, j, grid, visited));
                }
            }
        }

        return maxDiamonds;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 7; i++) {
            String inputFileName = "Task_06/input6_" + i + ".txt";
            String outputFileName = "Task_06/output6_" + i + ".txt";

            try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
                 BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName))) {

                String[] rh = br.readLine().trim().split(" ");
                int R = Integer.parseInt(rh[0]);
                int H = Integer.parseInt(rh[1]);

                char[][] treasureMap = new char[R][H];
                for (int j = 0; j < R; j++) {
                    treasureMap[j] = br.readLine().trim().toCharArray();
                }

                out.write(String.valueOf(maximumDiamonds(R, H, treasureMap)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}