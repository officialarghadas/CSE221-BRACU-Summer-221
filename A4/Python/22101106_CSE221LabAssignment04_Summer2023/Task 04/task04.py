from typing import List, Tuple

InputArr = List[Tuple[int, int]]
Graph = List[List[int]]


def has_cycle(graph, start, visited) :
    visited[start] = True

    for neighbor in graph[start]:
        if visited[neighbor]:
            return True
        if has_cycle(graph, neighbor, visited):
            return True

    visited[start] = False
    return False


def check_cycle(n, arr):

    graph: Graph = [[] for _ in range(n + 1)]
    for vertex, edge in arr:
        graph[vertex].append(edge)

    visited: List[bool] = [False] * (n + 1)

    for city in range(1, n + 1):
        if has_cycle(graph, city, visited):
            return "YES"

    return "NO"


for i in range(1, 6):
    with open(f"input4_{i}.txt") as f:
        n, m = map(int, f.readline().split())
        input_arr: InputArr = [tuple(map(int, f.readline().split())) for _ in range(m)]

        with open(f"output4_{i}.txt", "a+") as out:
            out.truncate(0)
            out.write(check_cycle(n, input_arr))
