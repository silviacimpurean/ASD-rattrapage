package ads.graph;

/**
 * A class for directed graph
 */
public class DiGraph extends AbstractGraph {

    // inDegree[u] is the in-degree of u
    protected int inDegree[];

    /**
     * builds a directed graph with n vertices
     */
    public DiGraph(int n) {
        super(n);
        inDegree = new int[n];
        for ( int i = 0; i < n; i++ )
            inDegree[i] = 0;
    }

    @Override
    public void addEdge(int u, int v) {
        if ( ! adjacencyList.get(u).contains(v) ) {
            adjacencyList.get(u).add(v);
            nbEdges++;
            inDegree[v]++;
        }
    }

    @Override
    public void removeEdge(int u, int v) {
        if ( adjacencyList.get(u).remove(v) != null ) {
            nbEdges--;
            inDegree[v]--;
        }
    }

    /**
     * returns the out-degree of u
     */
    public int outDegree(int u) {
        return adjacencyList.get(u).size();
    }

    /**
     * returns the in-degree of u
     */
    public int inDegree(int u) {
        return inDegree[u];
    }

    @Override
    public int degree(int u) {
        return outDegree(u) + inDegree(u);
    }
}
