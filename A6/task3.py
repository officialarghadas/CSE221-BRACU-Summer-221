task3_input=open('input3.txt', 'r')
task3_output=open('output3.txt', 'w')
inpt=task3_input.read().split('\n')
dicc={}
for i in range(1,int(inpt[0].split(' ')[0])+1):
  dicc[int(i)]=[]
for i in range(int(inpt[0].split(' ')[1])):
  temp=inpt[i+1].split(' ')
  dicc[int(temp[0])].append([int(temp[1]), int(temp[2])])

d=[float('inf')]*len(dicc.keys())
pie=[None]*len(dicc.keys())
src=1
d[src-1]=0
sett=[]
from queue import PriorityQueue
q = PriorityQueue()
for i in dicc:
  q.put((d[i-1],i))
while(q.empty()==False):
  u=q.get()
  for v,w in dicc[u[1]]:
    new=max(u[0],w)
    if new<d[v-1]:
       d[v-1]=new
       pie[v-1]=u
       q.put((new, v))
task3_output.write(str(d[int(inpt[0].split(' ')[0])-1]))
