def greedy(tasks, M):
    tasks.sort()
    assigned = [0] * M
    done_count = 0

    for start, end in tasks:
        assigned_man = -1

        for i in range(M):
            if assigned[i] <= start:
                assigned_man = i
                break

        if assigned_man != -1:
            assigned[assigned_man] = end
            done_count += 1

    return done_count



if __name__ == '__main__':

    with open(f'output2.txt', 'a+') as out:
        out.truncate(0)
    with open(f'input2.txt', 'r') as f:
        N, K = [int(i) for i in f.readline().split()]
        tasks = []
        for i in range(N):
            start, end = [int(i) for i in f.readline().split()]
            tasks.append((start, end))

        done_count = greedy(tasks, K)

        with open(f'output2.txt', 'a+') as out:
            out.write(f"{done_count}\n")




