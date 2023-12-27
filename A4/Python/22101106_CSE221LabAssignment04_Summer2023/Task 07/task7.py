def dfs(graph, start, visited):

    visited[start] = True
    max_distance = 0
    farthest_city = start

    for neighbor in graph[start]:
        if not visited[neighbor]:
            distance, city = dfs(graph, neighbor, visited)
            if distance + 1 > max_distance:
                max_distance = distance + 1
                farthest_city = city

    return max_distance, farthest_city


def find_maximum_path(graph, N):

    visited = [False] * (N + 1)
    _, city_a = dfs(graph, 1, visited)

    visited = [False] * (N + 1)
    _, city_b = dfs(graph, city_a, visited)

    return city_a, city_b


for i in range(1, 4):
    with open(f"input7_{i}.txt") as f:
        N = int(f.readline())
        graph = {i: [] for i in range(1, N + 1)}

        for _ in range(N - 1):
            u, v = map(int, f.readline().split())
            graph[u].append(v)
            graph[v].append(u)

        city_A, city_B = find_maximum_path(graph, N)

        with open(f"output7_{i}.txt", "a+") as out:
            out.truncate(0)
            out.write(str(city_A) + " " + str(city_B))
