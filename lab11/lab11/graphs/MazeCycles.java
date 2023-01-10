package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        DFSCycleCheck();
    }

    // Helper methods go here
    private void DFSCycleCheck() {
        int v = maze.xyTo1D(1, 1);
        int last = -1;
        Stack<Integer> stack = new Stack<>();
        stack.push(v);

        while (!stack.isEmpty()){
            v = stack.pop();
            marked[v] = true;
            announce();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    announce();
                    distTo[w] = distTo[v] + 1;
                    stack.push(w);
                } else if (w == last){
                    continue;
                } else {
                    edgeTo[w] = v;
                    announce();
                    return;
                }
            }
            last = v;
        }
    }
}

