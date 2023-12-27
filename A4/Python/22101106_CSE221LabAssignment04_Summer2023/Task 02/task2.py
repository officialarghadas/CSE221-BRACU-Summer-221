from typing import List, Tuple

InputArr = List[Tuple[int, int]]
Graph = List[List[int]]


def graph(n, m, arr):
    graph = [[] for _ in range(n + 1)]
    for i in range(m):
        graph[arr[i][0]].append(arr[i][1])
        graph[arr[i][1]].append(arr[i][0])
    return graph


def bfs(n, graph):

    queue = [1]
    visited: List[bool] = [False] * (n + 1)
    visited[1] = True
    bfs_path: List[int] = []
    while queue:
        s = queue.pop(0)
        bfs_path.append(s)
        for i in graph[s]:
            if visited[i] is False:
                queue.append(i)
                visited[i] = True
    return bfs_path


for i in range(1, 5):
    with open(f"input2_{i}.txt") as f:
        n, m = map(int, f.readline().split())
        input_arr: InputArr = [tuple(map(int, f.readline().split())) for _ in range(m)]
        with open(f"output2_{i}.txt",  "a+") as out:
            out.truncate(0)
            out.write(" ".join(map(str, bfs(n, graph(n, m, input_arr)))))
