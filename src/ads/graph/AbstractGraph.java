package ads.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A class for abstract graph. An abstract graph is the common part
 * between directed graph (DiGraph) and undirected graph (UnDiGraph)
 */
public abstract class AbstractGraph {

    // the total number of edges
    protected int nbEdges;

    // the adjacency list
    protected List<List<Integer>> adjacencyList;

    /**
     * builds an abstract graph with n vertices
     */
    public AbstractGraph(int n) {
        nbEdges = 0;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adjacencyList.add(new LinkedList<>());
    }

    /**
     * returns the number of vertices
     */
    public int nbVertices() {
        return adjacencyList.size();
    }

    /**
     * returns the number of edges
     */
    public int nbEdges() {
        return nbEdges;
    }

    /**
     * returns an iterable object over
     * the vertices adjacent to u
     */
    public Iterable<Integer> adjacents(int u) {
        return adjacencyList.get(u);
    }

    /**
     * returns an iterable object over
     * the edges incident to u
     */
    public Iterable<Edge> incidents(int u) {
        return new EdgeIterator(u);
    }


    /**
     * adds the edge e to the graph
     */
    public void addEdge(Edge e) {
        addEdge(e.origin(), e.destination());
    }

    /**
     * removes the edge e from the graph
     */
    public void removeEdge(Edge e) {
        removeEdge(e.origin(), e.destination());
    }

    /**
     * adds the edge (u,v) to the graph
     */
    public abstract void addEdge(int u, int v);

    /**
     * removes the edge (u,v) from the graph
     */
    public abstract void removeEdge(int u, int v);

    /**
     * returns the total degree of u
     */
    public abstract int degree(int u);

    // an inner class to iterate over the incident edges
    private class EdgeIterator implements Iterable<Edge>, Iterator<Edge> {

        int origin;
        Iterator<Integer> adjacents;

        EdgeIterator(int u) {
            origin = u;
            adjacents = adjacencyList.get(u).iterator();
        }

        public Iterator<Edge> iterator() {
            return this;
        }

        public boolean hasNext() {
            return adjacents.hasNext();
        }

        public Edge next() {
            return new Edge(origin, adjacents.next());
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

