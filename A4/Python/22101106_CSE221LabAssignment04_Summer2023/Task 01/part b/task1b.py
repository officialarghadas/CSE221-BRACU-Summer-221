def adj_list_creator(n, m, InputArr):
    adj_list = [[] for i in range(n+1)]
    [adj_list[InputArr[i][0]].append((InputArr[i][1], InputArr[i][2])) for i in range(m)]
    return adj_list


for i in range(1, 4):
    with open(f"input1b_{i}.txt") as f:
        n, m = [int(i) for i in f.readline().strip("\n").split()]
        InputArr = [[int(i) for i in f.readline().strip("\n").split()] for i in range(m)]
        adj_list = adj_list_creator(n, m, InputArr)
        with open(f"output1b_{i}.txt", "a+") as out:
            out.truncate(0)
            for i in range(n+1):
                out.write(f"{i} :")
                for j in adj_list[i]:
                    out.write(f"{j}")
                out.write("\n") if i != n else out.write("")