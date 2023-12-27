def merge(data, data1):
    arr, inv = data
    arr1, inv1 = data1
    n = len(arr) + len(arr1)
    array = [0] * n
    count = 0
    inversions = inv+inv1

    while len(arr) > 0 and len(arr1) > 0:
        if arr[0] <= arr1[0]:
            array[count] = arr[0]
            arr = arr[1:]
        else:
            array[count] = arr1[0]
            arr1 = arr1[1:]
            inversions += len(arr)

        count += 1

    while len(arr) > 0:
        array[count] += arr[0]
        arr = arr[1:]

    while len(arr1) > 0:
        array[count] += arr1[0]
        arr1 = arr1[1:]

    return (array, inversions)


def mergeSort_inversion_check(arr):
    if len(arr) <= 1:
        return (arr, 0)
    else:
        mid = len(arr) // 2
        inv_L = mergeSort_inversion_check(arr[:mid])  # write the parameter
        inv_R = mergeSort_inversion_check(arr[mid:])  # write the parameter
        return merge(inv_L, inv_R)


with open("input1.txt") as f:
    n = int(f.readline().strip("\n"))
    array = [int(i) for i in f.readline().strip("\n").split()]
    re = mergeSort_inversion_check(array)

    with open("output1.txt", "a+") as out:
        out.truncate(0)
        out.write(f"{re[1]} ")






