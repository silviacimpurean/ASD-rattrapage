package ads.graph;

/**
 * A class for weighted edges
 */
public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {

    private double weight; // the weight of the edge

    /**
     * builds an edge (x,y) of weight w
     */
    public WeightedEdge(int x, int y, double w) {
        super(x,y);
        weight = w;
    }

    /**
     * builds an edge similar to e of weight w
     */
    public WeightedEdge(Edge e, double w) {
        this(e.origin(),e.destination(),w);
    }

    /**
     * returns the weight of the edge
     */
    public double weight() {
        return weight;
    }

    /**
     * Compares two edges after their weight
     */
    public int compareTo(WeightedEdge e) {
        if ( weight < e.weight )
            return -1;
        if ( weight > e.weight )
            return 1;
        return 0;
    }
}
