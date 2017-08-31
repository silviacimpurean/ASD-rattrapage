package ads;

import ads.graph.WeightedDiGraph;

import java.util.ArrayList;
import java.util.List;


/**
 * A class to find the shortest path from all the vertices of a graph to a specific target (vertex)
 */
public class DijkstraAvecCible {

    // the weighted graph
    private WeightedDiGraph G;

    // the special heap
    private SpecialHeapForDijkstra heap;

    // the cost for the vertices
    private double[] cost;

    // the previous vertex in the
    // shortest path for each vertex
    private int[] previous;


    /**
     * Build a Dijkstra object for the graph G
     */
    public DijkstraAvecCible(WeightedDiGraph G) {
        this.G = G;
        this.previous = new int[G.nbVertices()];
        this.cost = new double[G.nbVertices()];
        this.heap = new SpecialHeapForDijkstra(G.nbVertices(), cost);
    }

    /**
     * Compute the shortest paths from the source
     * vertex to all other vertices
     */
    public void shortestPath(int source) {
        previous[source] = -1;
        for (int i = 0; i < cost.length; i++)
            cost[i] = Double.MAX_VALUE;
        cost[source] = 0;
        fillHeap();

        while (!heap.isEmpty()) {
            int node = heap.deleteExtreme();
            for (int adj : G.adjacents(node))
                if (cost(node) + G.weight(node, adj) < cost(adj)) {
                    cost[adj] = cost(node) + G.weight(node, adj);
                    heap.decreaseCost(adj);
                    previous[adj] = node;
                }
        }
    }


    /**
     * Return the cost of the shortest path
     * from the source to the vertex v
     */
    public double cost(int v) {
        return cost[v];
    }

    /**
     *
     * Display the shortest path from
     * the source to the vertex v
     */
    public List<Integer> displayShortestPath(List nodesList ,int v) {

        if (previous[v] == -1) {
            nodesList.add(v);
            //System.out.println(v);
        }
        else {
            if(previous[v] != 0) {
                displayShortestPath(nodesList,previous[v]);

                nodesList.add(v);
                //System.out.println(" " + v);
            }
        }
        return nodesList;
    }

    public static void printReverseArray(List liste ) {

        for(int i=liste.size()-1; i>=0;i--){
            System.out.println(liste.get(i));
        }

    }


    // fill the special heap with all the vertices
    private void fillHeap() {
        for (int i = 0; i < cost.length; i++) {
            heap.add(i);
        }
    }



    /**
     * For testing: the graph G is the one from
     * part 1 of lab #11
     */
    public static void main(String[] args) {
        WeightedDiGraph G = new WeightedDiGraph(12);

        G.addEdge(0, 1, 2.0);
        G.addEdge(0, 4, 13.0);
        G.addEdge(0, 11, 10.0);
        G.addEdge(0, 10, 7.0);
        G.addEdge(1, 6, 1.0);
        G.addEdge(2, 4, 1.0);
        G.addEdge(2, 7, 1.0);
        G.addEdge(2, 9, 2.0);
        G.addEdge(3, 5, 8.0);
        G.addEdge(3, 7, 4.0);
        G.addEdge(4, 9, 5.0);
        G.addEdge(6, 3, 5.0);
        G.addEdge(6, 7, 2.0);
        G.addEdge(6, 11, 3.0);
        G.addEdge(7, 5, 10.0);
        G.addEdge(7, 8, 4.0);
        G.addEdge(7, 9, 7.0);
        G.addEdge(8, 5, 5.0);
        G.addEdge(9, 5, 1.0);
        G.addEdge(9, 8, 9.0);
        G.addEdge(10, 2, 3.0);
        G.addEdge(10, 4, 8.0);
        G.addEdge(10, 11, 5.0);
        G.addEdge(11, 7, 12.0);

        // We reverse the edges of the graph G
        WeightedDiGraph GReversed = WeightedDiGraph.reverse(G);
        DijkstraAvecCible d = new DijkstraAvecCible(GReversed);


        int source = 5;

        // We treat the destination as the start node (the source becomes the target)
        d.shortestPath(source);

        // We print the REVERSED path from the source to the rest of the nodes in the new REVERSED graph
        for ( int v = 0; v < G.nbVertices(); v++ ) {
            System.out.println("Shortest path from " + v + " to " + source + " (cost: " + d.cost(v) + "): ");

            List nodesList = new ArrayList();
            printReverseArray(d.displayShortestPath(nodesList,v));
        }
    }

}
