from typing import List, Tuple

InputArr = List[Tuple[int, int]]


def adj_list(n, arr):

    lst = {i: [] for i in range(1, n + 1)}
    for i in arr:
        lst[i[0]].append(i[1])
        lst[i[1]].append(i[0])
    return lst


def dfs(n, adj_lst):

    visited: List[bool] = [False] * (n + 1)
    res = []

    def dfs_util(v: int):

        visited[v] = True
        res.append(v)
        for i in adj_lst[v]:
            if not visited[i]:
                dfs_util(i)

    for i in range(1, n + 1):
        if not visited[i]:
            dfs_util(i)
    return res


for i in range(1, 5):
    with open(f"input3_{i}.txt") as f:
        n, m = map(int, f.readline().split())
        input_arr: InputArr = [tuple(map(int, f.readline().split())) for _ in range(m)]
        with open(f"output3_{i}.txt", "a+") as out:
            out.truncate(0)
            out.write(" ".join(map(str, dfs(n, adj_list(n, input_arr)))))
