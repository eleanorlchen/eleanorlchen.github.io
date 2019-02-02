This program tests the Nearest neighbor and Brute force algorithms to solve traveling salesman problem,
comparing the running time of each one.
Submitted files: Graph.java, Display.java (because I want to change the ms to ns)
To compile the program: javac Graph.java, javac Display.java
To run the program: java Display.java; then click virtual desktop to display the GUI.
Choose the number of nodes, then click “Generate Random Graph” button to generate graph.
Then click Nearest Neighbor button to get result of Nearest neighbor algorithm.
Then click Brute Force to get result of Brute Force algorithm.
 
To improve the performance, I created a two dimensional array to store all edges’ distance, so that I can use
an array of running time O(1) to look up each edge distance later.
I ran the node numbers 4,6,8; each for 3 times and wrote down the distance and time.
Also because of the circular path, the permutation becomes a circular permutation, you only need to permute (N-1)! 
and keep the first position unchanged, this will lower the runtime by factor of N.

N = 2:
First run                 Distance       Time
nearest neighbor:         117.10         205889 ns
brute force:              117.10         229335 ns

N = 2:
Second run                Distance       Time
nearest neighbor:         37.58         123702 ns
brute force:              37.58         125215 ns

N = 2:
Third run                 Distance       Time
nearest neighbor:         28.64         117987 ns
brute force:              28.64         172205 ns

Average Neighbor: 149192.6667
Average Brute Force: 175585
_____________________
N = 3:
First run                 Distance       Time
nearest neighbor:         90.24         132677 ns
brute force:              90.24         165087 ns

N = 3:
Second run                 Distance       Time
nearest neighbor:         242.65         147397 ns
brute force:              242.65         122563 ns

N = 3:
Third run                 Distance       Time
nearest neighbor:         97.99         125865 ns
brute force:              97.99         154971 ns

Average Neighbor: 135313
Average Brute Force: 147540.3333
_____________________
N = 4:
First run                 Distance       Time
nearest neighbor:         199.35         123906 ns
brute force:              199.35         131558 ns

N = 4:
Second run                 Distance       Time
nearest neighbor:         247.59         140089 ns
brute force:              245.90         160481 ns

N = 4:
Third run                 Distance       Time
nearest neighbor:         253.63         146909 ns
brute force:              235.96         133702 ns

Average Neighbor: 136968
Average Brute Force: 141913.6667
_____________________
N = 5:
First run                 Distance       Time
nearest neighbor:         210.22         119286 ns
brute force:              195.30         267506 ns

N = 5:
Second run                 Distance       Time
nearest neighbor:         231.01         126191 ns
brute force:              215.25         205669 ns

N = 5:
Third run                 Distance       Time
nearest neighbor:         232.73         123771 ns
brute force:              200.40         191368 ns

Average Neighbor: 123082.6667
Average Brute Force: 221514.3333
_____________________
N = 6:
First run                 Distance       Time
nearest neighbor:         255.42         149363 ns
brute force:              245.56         433721 ns

N = 6:
Second run                 Distance       Time
nearest neighbor:         220.24         120368 ns
brute force:              189.78         353287 ns

N = 6:
Third run                 Distance       Time
nearest neighbor:         298.58         115939 ns
brute force:              260.80         171222 ns

Average Neighbor: 128556.6667
Average Brute Force: 319410
_____________________
N = 7:
First run                 Distance       Time
nearest neighbor:         271.83         124233 ns
brute force:              249.62         407536 ns

N = 7:
Second run                 Distance       Time
nearest neighbor:         257.65         125654 ns
brute force:              218.88         337023 ns

N = 7:
Third run                 Distance       Time
nearest neighbor:         260.82         104906 ns
brute force:              260.82         342253 ns

Average Neighbor: 118264.3333
Average Brute Force: 362270.6667
_____________________
N = 8:
First run                 Distance       Time
nearest neighbor:         309.15         180322 ns
brute force:              309.15         2110013 ns

N = 8:
Second run                 Distance       Time
nearest neighbor:         302.52         139416 ns
brute force:              294.80         541461 ns

N = 8:
Third run                 Distance       Time
nearest neighbor:         290.59         145788 ns
brute force:              243.35         841733 ns

Average Neighbor: 155175.3333
Average Brute Force: 1164402.333

So, when the n is small, the running time of the nearest neighbor is close to the running time of brute force.
But as n becomes larger and larger, the running time of brute force grows much faster than the running time
of nearest neighbor. When n=7, brute force is more than 3x slower than nearest neighbor. When n=8, brute
force is more than 8x slower.