def greedy(tasks):
    tasks.sort(key=lambda task: task[1])  # Sort tasks by end times
    done_task = []
    end_time = -1
    done_count = 0

    for start, end in tasks:
        if start >= end_time:
            done_count += 1
            end_time = end
            done_task.append((start, end))

    return done_count, done_task


if __name__ == '__main__':

    with open(f'output1.txt', 'a+') as out:
        out.truncate(0)
    with open(f'input1.txt', 'r') as f:
        N = int(f.readline())
        tasks = []
        for i in range(N):
            start, end = [int(i) for i in f.readline().split()]
            tasks.append((start, end))

        done_count, done_task = greedy(tasks)

        with open(f'output1.txt', 'a+') as out:
            out.write(f"{done_count}\n")
            for start, end in done_task:
                out.write(f"{start} {end}\n")
