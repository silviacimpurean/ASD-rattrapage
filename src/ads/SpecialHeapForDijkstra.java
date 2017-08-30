package ads;


/**
 * A class for special binary heap used in the Dijkstra algorithm
 */
public class SpecialHeapForDijkstra {

    private int[] A;       // to store the heap (the vertices)
    private double[] cost; // to store the cost of the vertices
    private int[] ref;     // to store the location of each vertex in A
    private int size;      // the number of elements in the heap

    /**
     * Build a special heap of capacity n.
     * The cost array stores the cost of
     * each vertex.
     * The heap is empty.
     * Complexity: THETA(1)
     */
    public SpecialHeapForDijkstra(int n, double[] cost) {
        this.A = new int[n];
        this.ref = new int[n];
        this.cost = cost;
        size = 0;
    }

    ///////////// Private methods

    /**
     * Swap values in the arrays A
     * and ref at indexes i and j
     * Complexity: THETA(1)
     */
    private void swap(int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
        ref[A[i]] = j;
        ref[A[j]] = i;
    }

    /**
     * Return the number of the left
     * node of node number n.
     * Complexity: THETA(1)
     */
    private int left(int n) {
        return 2*n + 1;
    }

    /**
     * Return the number of the right
     * node of node number n.
     * Complexity: THETA(1)
     */
    private int right(int n) {
        return 2*(n + 1);
    }

    /**
     * Return the number of the parent
     * node of node number n.
     * Complexity: THETA(1)
     */
    private int parent(int n) {
        return (n - 1)/2;
    }

    /**
     * Percolate down the element à node number n
     * Complexity: O(log(size))
     */
    private void percolateDown(int n) {
        int g = left(n);
        int d = right(n);
        int k = n;
        if(g < size && cost[g] < cost[n])
            k = g;
        if(d < size && cost[d] < cost[k])
            k = d;
        if(k != n) {
            swap(k, n);
            percolateDown(k);
        }
    }

    /**
     * Percolate up the vertex at node number n
     * We call that method after we decreased the
     * cost of vertex at node n
     * Complexity: O(log(size))
     */
    private void percolateUp(int n) {
        while(n > 0 && cost[A[n]] < cost[A[parent(n)]]) {
            swap(n, parent(n));
            n = parent(n);
        }
    }

    ///////////// Public methods

    /**
     * Check if the heap is empty.
     * Complexity: THETA(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Empty the heap
     * Complexity: THETA(1)
     */
    public void reset() {
        size = 0;
    }

    /**
     * Return and delete the extreme vertex
     * Complexity: O(log(size))
     */
    public int deleteExtreme() {
        int extreme = A[0];
        A[0] = A[--size];
        if ( size > 0 )
            percolateDown(0);
        return extreme;
    }

    /**
     * Add a new vertex in the heap
     * Complexity: O(log(size))
     */
    public void add(int v) {
        A[size++] = v;
        ref[v] = size-1;
        percolateUp(ref[v]);
    }

    /**
     * Percolate up the vertex v
     * We call that method after we decreased the
     * cost of vertex v
     * Complexity: O(log(size))
     */
    public void decreaseCost(int v) {
        percolateUp(ref[v]);
    }
}
