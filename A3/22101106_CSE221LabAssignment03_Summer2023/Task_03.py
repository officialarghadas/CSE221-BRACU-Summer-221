def partition(arr, low, high):
    pivot = arr[high]
    i = low - 1

    for j in range(low, high):
        if arr[j] <= pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]

    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1


def quicksort(arr, low, high):
    if low < high:
        pivot_index: int = partition(arr, low, high)

        quicksort(arr, low, pivot_index - 1)
        quicksort(arr, pivot_index + 1, high)



with open("input3.txt") as f:
    n = int(f.readline().strip("\n"))
    array = [int(i) for i in f.readline().strip("\n").split()]
    quicksort(array, 0, n-1)

    with open("output3.txt", "a+") as out:
        for i in range(n):
            if i == 0:
                out.truncate(0)
            out.write(f"{array[i]}")

