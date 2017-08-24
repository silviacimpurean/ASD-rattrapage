package ads.graph;

/**
 * A class for undirected graph
 */
public class UnDiGraph extends AbstractGraph {

    /**
     * builds an undirected graph with n vertices
     */
    public UnDiGraph(int n) {
        super(n);
    }

    @Override
    public void addEdge(int u, int v) {
        if ( ! adjacencyList.get(u).contains(v) ) {
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
            nbEdges++;
        }
    }

    @Override
    public void removeEdge(int u, int v) {
        if ( adjacencyList.get(u).remove(v) != null ) {
            adjacencyList.get(v).remove(u);
            nbEdges--;
        }
    }

    @Override
    public int degree(int u) {
        return adjacencyList.get(u).size();
    }
}
