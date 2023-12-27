import heapq
inp = open('input2.txt', 'r')
out = open('output2.txt', 'w')

n, m = list(map(int, inp.readline().split()))

adj_list = [[] for i in range(n+1)]

for i in range(m):
    u, v, e = list(map(int, inp.readline().split()))
    adj_list[u].append((v, e))
s1, s2 = list(map(int, inp.readline().split()))
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


arr1 = Dijkstra(adj_list, s1)
arr2 = Dijkstra(adj_list, s2)
print(arr1, arr2)
min_time = 99999
min_node = None
for i in range(len(arr1)):
    if min_time > max(arr1[i], arr2[i]):
        min_time = max(arr1[i], arr2[i])
        min_node = i+1


if min_time == 99999 or min_node is None:
    out.write('Impossible')
else:
    out.write(f'Time {min_time}\nNode {min_node}')