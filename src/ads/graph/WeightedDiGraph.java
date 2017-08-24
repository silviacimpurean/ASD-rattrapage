package ads.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * A class for weighted undirected graphs
 */
public class WeightedDiGraph extends DiGraph {

    private static final String NEWLINE = System.getProperty("line.separator");

    // to map the edges to their weight
    private Map<Edge,Double> weights;

    /**
     * builds an undirected weighted graph with n vertices
     */
    public WeightedDiGraph(int n) {
        super(n);
        weights = new HashMap<Edge,Double>();
    }

    /**
     * adds the edge (u,v) of weight w to the graph
     */
    public void addEdge(int u, int v, double w) {
        if ( ! adjacencyList.get(u).contains(v) ) {
            adjacencyList.get(u).add(v);
            nbEdges++;
            weights.put(new Edge(u,v),w);
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
        Double d = weights.get(new Edge(x,y));
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

    public Iterable<WeightedEdge> weighedIncidents(int u) {
        return new WeightedEdgeIterator(u);
    }


    public static WeightedDiGraph reverse (WeightedDiGraph G) {
        int numVertices = G.nbVertices();
        int f,t;
        double w;
        WeightedDiGraph Gr = new WeightedDiGraph(numVertices);
        for (int u = 0; u < numVertices; u++)
            for (WeightedEdge e: G.weighedIncidents(u)) {
                f = e.origin();
                t = e.destination();
                w = e.weight();
                WeightedEdge er = new WeightedEdge(t,f,w);
                Gr.addEdge(er);
            }
        return Gr;
        }

    private class WeightedEdgeIterator implements Iterable<WeightedEdge>, Iterator<WeightedEdge> {

        int origin;
        Iterator<Integer> adjacents;

        WeightedEdgeIterator(int u) {
            origin = u;
            adjacents = adjacencyList.get(u).iterator();
        }

        public Iterator<WeightedEdge> iterator() {
            return this;
        }

        public boolean hasNext() {
            return adjacents.hasNext();
        }

        public WeightedEdge next() throws NoSuchElementException{
            int next = adjacents.next();
            return new WeightedEdge(origin, next, weight(origin, next));
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(nbVertices() + " " + nbEdges() + NEWLINE);
        for (int v = 0; v < nbVertices(); v++) {
            s.append(v + ": ");
            for (WeightedEdge e : weighedIncidents(v)) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


    public static void main(String[] args) {
        WeightedDiGraph g = new WeightedDiGraph(3);
        WeightedEdge e1 = new WeightedEdge(0, 1, 01.10);
        g.addEdge(e1);
        WeightedEdge e2 = new WeightedEdge(1, 2, 12.21);
        g.addEdge(e2);
        WeightedEdge e3 = new WeightedEdge(2, 0, 20.02);
        g.addEdge(e3);
        System.out.println(g.toString());


        WeightedDiGraph gr = reverse(g);
        System.out.println(gr.toString());
    }

}


