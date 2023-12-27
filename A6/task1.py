import heapq
inp = open('input1.txt', 'r')
out = open('output1.txt', 'w')

n, m = list(map(int, inp.readline().split()))

adj_list = [[] for i in range(n+1)]

for i in range(m):
    u, v, e = list(map(int, inp.readline().split()))
    adj_list[u].append((v, e))
source = int(inp.readline())
print(adj_list)

def Dijkstra(G,s):
    distance = [99999]*len(G)
    parent = [None]*len(G)
    colour = ['White']*len(G)
    distance[s] = 0


    priority_Q = []

    heapq.heapify(priority_Q)
    heapq.heappush(priority_Q, (0, s))
    while len(priority_Q) != 0:
        dist, curr = heapq.heappop(priority_Q)
        for i in G[curr]:
            if colour[i[0]] == 'White':
                if (dist + i[1]) < distance[i[0]]:
                    distance[i[0]] = distance[curr] + i[1]
                    parent[i[0]] = curr
                    heapq.heappush(priority_Q, (distance[i[0]], i[0]))
        colour[curr] = "Black"

    return distance[1:]


ans = Dijkstra(adj_list, source)

for i in range(len(ans)):
    temp = '' if i == len(ans)-1 else ' '
    if ans[i] == 99999:
        out.write(f"-1{temp}")
    else:
        out.write(f"{ans[i]}{temp}")

