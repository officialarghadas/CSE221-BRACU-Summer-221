def check(li, val):
    for i in li:
        if val in i:
            return i

def greedy(friends, tasks):
    with open(f'output3.txt', 'a+') as f:
        f.truncate(0)
    for i in tasks:
        f1, f2 = i
        group1 = check(friends, f1)
        group2 = check(friends, f2)
        if group1 == group2:
            friends.remove(group1)
        else:
            friends.remove(group1)
            friends.remove(group2)
        friends.append(list(set(group1+group2)))

        with open(f'output3.txt', 'a+') as f:
            f.write(f"{len(set(group1+group2))}\n")


if __name__ == '__main__':
    with open(f'input3.txt', 'r') as f:
        N, M = [int(i) for i in f.readline().split()]
        friend = [[i] for i in range(N)]
        tasks = []
        for _ in range(M):
            start, end = [int(i) for i in f.readline().split()]
            tasks.append((start, end))

    greedy(friend, tasks)