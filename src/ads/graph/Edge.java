package ads.graph;

/**
 * A class for the edges of a graph.
 */
public class Edge {

    private int x;
    private int y;

    /**
     * builds the edge (x,y)
     */
    public Edge(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * returns the origin of the edge
     */
    public int origin() {
        return x;
    }

    /**
     * returns the destination of the edge
     */
    public int destination() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if(this!=null && o!=null){

            Edge e = (Edge) o;
            return ((x == e.x && y == e.y) || (x == e.y && y == e.x));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return x + y;
    }
}
