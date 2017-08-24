package ads.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * A class for weighted undirected graphs
 */
public class WeightedUnDiGraph extends UnDiGraph {

    // to map the edges to their weight
    private Map<Edge,Double> weights;

    /**
     * builds an undirected weighted graph with n vertices
     */
    public WeightedUnDiGraph(int n) {
        super(n);
        weights = new HashMap<Edge,Double>();
    }

    /**
     * adds the edge (u,v) of weight w to the graph
     */
    public void addEdge(int u, int v, double w) {
        if ( ! adjacencyList.get(u).contains(v) ) {
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
            nbEdges++;
            if ( u < v )
                weights.put(new Edge(u,v),w);
            else
                weights.put(new Edge(v,u),w);
        }
    }

    /**
     * adds the weighted edge e to the graph
     */
    public void addEdge(WeightedEdge e) {
        addEdge(e.origin(),e.destination(),e.weight());
    }

    /**
     * returns the weight of the edge (x,y)
     * or 0 if the edge (x,y) is not in the graph
     */
    public double weight(int x, int y) {
        Double d = x < y ? weights.get(new Edge(x,y)) : weights.get(new Edge(y,x));
        if ( d == null )
            return 0.0;
        return d;
    }

    /**
     * returns the weight of the edge e
     * or 0 if the edge e is not in the graph
     */
    public double weight(Edge e) {
        return weight(e.origin(),e.destination());
    }
}
