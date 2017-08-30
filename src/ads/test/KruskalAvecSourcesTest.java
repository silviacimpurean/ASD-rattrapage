package ads.test;

import ads.PrimAvecSources;
import ads.graph.Edge;
import ads.graph.WeightedUnDiGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class KruskalAvecSourcesTest {

    private WeightedUnDiGraph G;
    private List<Integer> list;

    private Set<Edge> fcm;
    private Set<Edge> expected ;

    @Before
    public void setUp() throws Exception {
        G = new WeightedUnDiGraph(7);

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

        list = new ArrayList<>();
        list.add(2);
        list.add(5);


        fcm = PrimAvecSources.fcm(G,list);

        expected = new HashSet<>();

        expected.add(new Edge(1,4));
        expected.add(new Edge(5,1));
        expected.add(new Edge(0,6));
        expected.add(new Edge(2,0));
        expected.add(new Edge(3,0));

    }

    @Test
    public void fcm() throws Exception {
        assertEquals(fcm, expected);
        System.out.println(fcm);
        System.out.println(expected);
    }

}