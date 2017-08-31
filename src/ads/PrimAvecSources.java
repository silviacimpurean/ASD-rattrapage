

package ads;

import ads.graph.Edge;
import ads.graph.WeightedEdge;
import ads.graph.WeightedUnDiGraph;
import ads.heap.*;

import java.util.*;

/**
 * A class for the Prim algorithm with sources
 */
public class PrimAvecSources {

    /**
     * returns the set all edges of a MINIMUM SPANNING FOREST of the graph G connected to a list of sources
     * @param G the weighted undirected graph containing all the houses (vertices) that have to be linked to the electricity network
     * @param sources the houses (vertices) that are directly connected to the electric sources
     * @complexity: O(E) E being the number of edges.
     */
    public static Set<Edge> fcm(WeightedUnDiGraph G, List<Integer> sources) throws FullHeapException, EmptyHeapException {

        Set<Edge> fcm = new HashSet<Edge>(); // the edges of the MSF

        // to make a minimum-heap of weighted edges

        Comparator<WeightedEdge> c = new Comparator<WeightedEdge>() {
            public int compare(WeightedEdge e1, WeightedEdge e2) {
                return e2.compareTo(e1);
            }
        };

        // the minimum-heap of weighted edges
        BinaryHeap<WeightedEdge> minHeap = new BinaryHeap<WeightedEdge>(G.nbEdges(),c);

        // known[u] == true <==> u is known (the house u is connected to electricity)
        boolean known[] = new boolean[G.nbVertices()];

        // the number of known vertices (the number of houses connected to the electric network), which is equal to the number of sources that we receive as a parameter
        int knownVertices = sources.size();
        // complexity ? |S|
        for(int m = 0 ; m < sources.size(); m++) {

            // we initialize the list of known vertices from the sources' list
            known[sources.get(m)] = true;

            // we add the edges incident to the sources in the heap (the houses that can be directly connected to the sources)
            // complexity : |e(s)|
            for (Edge e : G.incidents(sources.get(m)))
                minHeap.add(new WeightedEdge(e, G.weight(e)));
        }
        // while not all vertices are known (i.e. connected)
        // complexity ? |S|
        while ( knownVertices < G.nbVertices() ) {

            // we select the edge with the lowest cost and we delete it from the heap
            WeightedEdge min = minHeap.deleteExtreme();

            // we get the destination of the selected edge
            int v = min.destination();

            // in case that the destination vertex is yet unknown
            if ( ! known[v] ) {

                // we add the edge to the output
                fcm.add(min);

                //we mark the destination vertex as known
                known[v] = true;

                // we increase the number of known vertices (of connected houses)
                knownVertices++;

                // we add the edges incident to the recently known vertex to the heap, with the condition that the destination is still "unknown'
                for ( Edge e : G.incidents(v) )
                    if ( ! known[e.destination()] )
                        minHeap.add(new WeightedEdge(e,G.weight(e)));
            }
        }

        return fcm;
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

        Set<Edge> fcm = fcm(G,list);

        for ( Edge e : fcm )
            System.out.print(e + " ");
        System.out.println();
    }
    // expected output (the edges could show up in a different order)
    //
    // (1, 4) (0, 2) (1, 5) (0, 6) (0, 3)
}
