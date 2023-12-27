from typing import List

Matrix = List[List[int]]
Visited = List[List[bool]]


def can_move(row, col, grid, visited):

    return (
        row < 0
        or row >= len(grid)
        or col < 0
        or col >= len(grid[0])
        or grid[row][col] == "#"
        or visited[row][col]
    )


def dfs(row, col, grid, visited):

    if can_move(row, col, grid, visited):
        return 0

    visited[row][col] = True
    diamonds = 0

    if grid[row][col] == "D":
        diamonds += 1

    for delta_row, delta_col in ((0, 1), (1, 0), (0, -1), (-1, 0)):
        diamonds += dfs(row + delta_row, col + delta_col, grid, visited)

    return diamonds


def maximum_diamonds(rows, cols, grid):
    max_diamonds = 0

    for i in range(rows):
        for j in range(cols):
            if grid[i][j] == ".":
                visited = [[False for _ in range(cols)] for _ in range(rows)]
                max_diamonds = max(max_diamonds, dfs(i, j, grid, visited))

    return max_diamonds


for i in range(1, 8):
    with open(f"input6_{i}.txt", "r", encoding="utf-8") as f:
        R, H = map(int, f.readline().split())
        treasure_map = [list(f.readline().strip()) for _ in range(R)]

        with open(f"output6_{i}.txt", "w", encoding="utf-8") as out:
            out.truncate()
            out.write(str(maximum_diamonds(R, H, treasure_map)))
