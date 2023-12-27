def adj_matrix_creator(n, m, arr):
    matrix = [[0 for i in range(n+1)]for j in range(n+1)]
    for i in range(m):
        matrix[arr[i][0]][arr[i][1]] = arr[i][2]
    return matrix

for i in range(1, 3):
    with open(f"input1a_{i}.txt") as f:
        n, m = [int(i) for i in f.readline().strip("\n").split()]
        InputArr = [[int(i) for i in f.readline().strip("\n").split()] for i in range(m)]
        adj_matrix = adj_matrix_creator(n, m, InputArr)
        with open(f"output1a_{i}.txt", "a+") as out:
            out.truncate(0)
            [out.write(f"{i}\n") for i in adj_matrix]
