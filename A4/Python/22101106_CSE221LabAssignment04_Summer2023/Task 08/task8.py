def bfs(graph, start, visited):
    queue = [start]
    visited[start] = 1
    while queue:
        current: int = queue.pop(0)
        for node in graph[current]:
            if visited[node] == -1:
                visited[node] = 1 - visited[current]
                queue.append(node)
            elif visited[node] == visited[current]:
                return False
    return True


with open("input8_1.txt") as f:
    N = int(f.readline())
    cases = ""
    for t in range(N):
        n = int(f.readline())
        graph = [[] for i in range(20001)]
        visited = [-1] * 20001
        for i in range(n):
            u, v = map(int, f.readline().split())
            graph[u].append(v)
            graph[v].append(u)
        ans = 0
        for i in range(1, 20001):
            if visited[i] == -1 and graph[i]:
                if bfs(graph, i, visited):
                    ans += max(visited.count(0), visited.count(1))
                else:
                    ans = -1
                    break
        cases += f"Case {t+1}: {ans}\n"

with open("output8_1.txt", "a+") as out:
    out.truncate(0)
    out.write(cases)
