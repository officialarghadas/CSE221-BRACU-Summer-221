def quickselect(arr, k):
    pivot = arr[-1]
    arr_L = []
    arr_R = []

    for i in range(len(arr)-1):
        if arr[i] < pivot:
            arr_L.append(arr[i])
        else:
            arr_R.append(arr[i])

    if len(arr_L) == k-1:
        return pivot
    elif len(arr_L) >= k:
        return quickselect(arr_L, k)
    else:
        return quickselect(arr_R, k - (len(arr_L)+1))


with open("input4.txt") as f:
    n = int(f.readline().strip("\n"))
    array = [int(i) for i in f.readline().strip("\n").split()]
    smallest = quickselect(array, 3)
    times = int(f.readline().strip("\n"))

    with open("output4.txt", "a+") as out:
        out.truncate(0)
        for i in range(times):
            k = int(f.readline().strip("\n"))
            smallest = quickselect(array, k)
            out.write(f"{smallest}\n")

