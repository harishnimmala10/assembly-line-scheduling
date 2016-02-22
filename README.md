# assembly-line-scheduling
Algorithm for a assembly line scheduling problem to minimize the overall load on the system

In this project, we are interested in the assembly scheduling problem. There are n tasks to execute using m processors. All the processors are identical, but tasks are not. Each task has a processing time pi.
The problem is to assign for each processor j an interval of tasks Ij = [bj ; ej ]
Since it is an assembly line, the throughput of the system will be limited by the most loaded processor. 
The load of processor j is Lj = sum of processing times of the tasks executed by processor j
The most loaded processor has a load of L = maximum Lj where j=1 to m.
The point of the project is to design and implement algorithms to minimize L.

Four algorithms were implemented in this project
  1) Brute Force algorithm
  2) Dynamic Programming
  
  3) Greedy algorithm that decides whether there is a solution with a load <= Ltarget and constructs such a solution if there is one.
      This has an extra input LTarget.
  4)Heuristic algorithm to find a non-optimal, but reasonable solution, in a much shorter time.

File format: The first line of the file indicates the total number of tasks.The remaining lines has the processing times of these tasks. An instance with 3 tasks of processing time 10, 11 and 12 will be:
3
10
11
12
