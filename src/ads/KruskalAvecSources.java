package ads;

import ads.graph.Edge;
import ads.graph.WeightedEdge;
import ads.graph.WeightedUnDiGraph;
import ads.heap.*;

import java.util.*;

/**
 * A class for the Kruskal algorithm with multiple sources
 */

public class KruskalAvecSources {


    /**
     * adds all the weighted edges of graph G to the minimum heap minHeap
     */
    private static void fillHeap(BinaryHeap<WeightedEdge> minHeap, WeightedUnDiGraph G) throws FullHeapException {
        for ( int u = 0; u < G.nbVertices(); u++ ) {
            for ( Integer a : G.adjacents(u) ) {
                if ( u < a )
                    minHeap.add(new WeightedEdge(u,a,G.weight(u, a)));
            }
        }
    }

    /**
     * returns the set all edges of a MINIMUM SPANNING FOREST of the graph G connected to a list of sources
     * @param G the weighted undirected graph containing all the houses (vertices) that have to be linked to the electricity network
     * @param sources the houses (vertices) that are directly connected to the electric sources
     */
    public static Set<Edge> fcm(WeightedUnDiGraph G, List<Integer> sources) throws FullHeapException, EmptyHeapException {

        Set<Edge> mst = new HashSet<>(); // the edges of the MST

        // to make a minimum-heap of weighted edges
        Comparator<WeightedEdge> c = new Comparator<WeightedEdge>() {
            public int compare(WeightedEdge e1, WeightedEdge e2) {
                return e2.compareTo(e1);
            }
        };

        // a minimum-heap
        BinaryHeap<WeightedEdge> minHeap = new BinaryHeap<WeightedEdge>(G.nbEdges(),c);
        // fill the minimum-heap with all the weighted edges from the graph G
        fillHeap(minHeap,G);
        // disjoint sets of all the vertices of the graph G
        DisjointSets ds = new DisjointSets(G.nbVertices());

        // while there are more edges to check
        // complextiy Number of edged
        while ( ! minHeap.isEmpty() ) {

            // select the edge with minimum cost
            WeightedEdge min = minHeap.deleteExtreme();
            int u = min.origin();
            int v = min.destination();

            // check if both nods of the selected edge are in the same set
            // complexity number of edges
            int ru = ds.find(u);
            int rv = ds.find(v);

            //  check if both nodes of the selected edge are already connected to a source
            boolean isEdgeConnected = false;

            for (int n = 0; n < sources.size(); n++) {
                for (int m = 0; m < sources.size(); m++) {
                    if (ds.find(sources.get(n)) == ru && ds.find(sources.get(m)) == rv)
                        isEdgeConnected = true;
                }
            }

            if (( ru != rv ) && !isEdgeConnected){
                mst.add(min);
                ds.union(ru, rv);
            }
        }
        return mst;
    }
    /**
     * for testing
     */
    public static void main(String args[]) throws FullHeapException, EmptyHeapException {
        WeightedUnDiGraph G = new WeightedUnDiGraph(7);
        G.addEdge(0,2,2);
        G.addEdge(0,3,3);
        G.addEdge(0,4,8);
        G.addEdge(0,6,5);

        G.addEdge(1,4,2);
        G.addEdge(1,3,4);
        G.addEdge(1,5,3);
        G.addEdge(1,6,7);

        G.addEdge(2,4,5);

        G.addEdge(3,5,7);
        G.addEdge(3,6,6);

        G.addEdge(4,6,6);


        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(5);

        Set<Edge> mst = fcm(G, list);

        for ( Edge e : mst )
            System.out.print(e + " ");
        System.out.println();
    }
    // expected output (the edges could show up in a different order)
    //
    // (1, 4) (0, 2) (1, 5) (0, 6) (0, 3)
}