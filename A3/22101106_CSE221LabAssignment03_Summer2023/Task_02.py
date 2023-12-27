def calc(i, j):
    return i + j**2

def algorithm_n(arr):
    max_sum = float("-inf")
    max_num = float("-inf")

    for i in range(len(arr)):
        if arr[i] > max_num:
            max_num = arr[i]
            max_idx = i

    for i in range(len(arr)):
        if i != max_idx:
            if calc(max_num, arr[i]) > max_sum:
                max_sum = calc(max_num, arr[i])
            if arr[i] < 0 and calc(arr[i], max_num) > max_sum:
                max_sum = calc(arr[i], max_num)

    return max_sum


with open("input2.txt") as f:
    n1 = int(f.readline().strip("\n"))
    array1 = [int(i) for i in f.readline().strip("\n").split()]
    re = algorithm_n(array1)

    with open("output2.txt", "a+") as out:
        out.truncate(0)
        out.write(f"{re} ")