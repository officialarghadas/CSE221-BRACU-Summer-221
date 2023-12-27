from typing import List, Tuple


def shortest_path(
    graph, start, destination):
    num_cities: int = len(graph)
    distances: List[float] = [float("inf")] * num_cities
    distances[start] = 0
    visited: List[bool] = [False] * num_cities
    previous: List[int] = [-1] * num_cities

    for _ in range(num_cities):
        min_distance: float = float("inf")
        min_city: int = -1

        for city in range(1, num_cities):
            if not visited[city] and distances[city] < min_distance:
                min_distance = distances[city]
                min_city = city

        if min_city == -1:
            break

        visited[min_city] = True

        for neighbor in graph[min_city]:
            new_distance: int = distances[min_city] + 1
            if new_distance < distances[neighbor]:
                distances[neighbor] = new_distance
                previous[neighbor] = min_city

    path: List[int] = []
    city: int = destination
    while city != -1:
        path.append(city)
        city = previous[city]

    path = path[::-1]
    return distances[destination], path


for i in range(1, 6):
    with open(f"input5_{i}.txt") as f:
        N, M, D = map(int, f.readline().split())
        graph = [[] for _ in range(N + 1)]
        for _ in range(M):
            u, v = map(int, f.readline().split())
            graph[u].append(v)
            graph[v].append(u)
        min_time, path = shortest_path(graph, 1, D)

        with open(f"output5_{i}.txt", "a+") as out:
            out.truncate(0)
            out.write("Time: " + str(min_time) + "\n")
            out.write("Shortest Path: " + " ".join(map(str, path)))
