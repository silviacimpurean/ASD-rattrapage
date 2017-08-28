package ads.test;

import ads.graph.Edge;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EdgeTest {

    private Edge a;
    private Edge b;

    @Before
    public void setUp() throws Exception {
        a = new Edge(0,2);
        b = new Edge(2,0);

    }

    @Test
    public void equals() throws Exception {
        assertEquals(a,b);
    }

}