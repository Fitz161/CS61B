package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        edu.princeton.cs.algs4.Queue<Integer> queue = new edu.princeton.cs.algs4.Queue<Integer>();
        queue.enqueue(maze.xyTo1D(1, 1));
        while (!queue.isEmpty()){
            int v = queue.dequeue();
            marked[v] = true;
            announce();
            if (v == t){
                targetFound = true;
            }
            if (targetFound){
                return;
            }
            for (int w : maze.adj(v)){
                if (!marked[w]){
                    marked[w] = true;
                    edgeTo[w] = v;
                    announce();
                    distTo[w] = distTo[v] + 1;
                    queue.enqueue(w);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

