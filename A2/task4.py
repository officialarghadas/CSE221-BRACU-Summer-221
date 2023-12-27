# -*- coding: utf-8 -*-
"""task4

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1SGcARNYe9hkemRS3sX8WFffvIf2nLk8d
"""

#task4

def max_n(arr, s, e):
    if s == e:
        return arr[s]

    mid = (s + e) // 2
    max_l = max_n(arr, s, mid)
    max_r = max_n(arr, mid + 1, e)

    return max(max_l, max_r)

with open("input4.txt", "r") as file:
    N = int(file.readline())
    arr = list(map(int, file.readline().split()))

maxValue = max_n(arr, 0, N - 1)

with open("output4.txt", "w") as file:
    file.write(str(maxValue))