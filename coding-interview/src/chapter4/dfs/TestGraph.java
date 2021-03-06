package chapter4.dfs;

import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGraph {

	@Test
	public void TestDfsWithoutCycles() {
		Graph g = new Graph(4);
		g.addEdge(0, 1); g.addEdge(0, 2); 
		g.addEdge(2, 1);
		
		assertTrue(g.dfs(0)); assertTrue(g.dfs(1));
		assertTrue(g.dfs(2));
		assertTrue(!g.dfs(3));
	}
	
	@Test
	public void TestDfsWithCycles() {
		Graph g = new Graph(4);
		g.addEdge(0, 1); g.addEdge(0, 2); 
		g.addEdge(1, 0); g.addEdge(1, 2);
		g.addEdge(2, 1);
		
		assertTrue(g.dfs(0)); assertTrue(g.dfs(1));
		assertTrue(g.dfs(2)); assertTrue(!g.dfs(3));
	}
	
	@Test
	public void TestBfsWithoutCycles() {
		Graph g = new Graph(4);
		g.addEdge(0, 1); g.addEdge(0, 2); 
		g.addEdge(2, 1);
		
		assertTrue(g.bfs(0)); assertTrue(g.bfs(1));
		assertTrue(g.bfs(2));
		assertTrue(!g.bfs(3));
	}
	
	@Test
	public void TestBfsWithCycles() {
		Graph g = new Graph(4);
		g.addEdge(0, 1); g.addEdge(0, 2); 
		g.addEdge(1, 0); g.addEdge(1, 2);
		g.addEdge(2, 1);
		
		assertTrue(g.bfs(0)); assertTrue(g.bfs(1));
		assertTrue(g.bfs(2)); assertTrue(!g.bfs(3));
	}

	public void TestHasPath() {
		Graph g = new Graph(5);
		g.addEdge(0, 1); g.addEdge(1, 2); g.addEdge(2, 3); 
		
		assertTrue(g.hasPath(0, 1)); assertTrue(g.hasPath(0, 2)); assertTrue(g.hasPath(0, 3));
		assertFalse(g.hasPath(0, 4));
	}

	@Test
	public void TestGetNodesAtDepth() {
		Graph g = new Graph(7);
		g.addEdge(0, 1); g.addEdge(0, 1); g.addEdge(0, 4);
		g.addEdge(1, 3);
		g.addEdge(2, 3);
		g.addEdge(4, 5 ); g.addEdge(4, 6);
		
		Integer[] expected = new Integer[] {3,5,6};
		Set<Integer> nodes = g.getNodesAtDepth(2);
		
		for(Integer node : expected) {
			assertTrue(nodes.contains(node));
		}
		
	}

	@Test
	public void TestFindCommonAncestor() {
		testNoCommonAncestor();
		testAncestorIsSelf();
		testAncestorIsDeep();
	}
	
	private void testNoCommonAncestor() {
		Graph g = new Graph(2);
		assertEquals(-1, g.findCommonAncestor(0, 1));
	}
	
	private void testAncestorIsSelf() {
		Graph g = new Graph(2);
		g.addEdge(0, 1);
		
		assertEquals(0, g.findCommonAncestor(0, 1));
	}
	
	private void testAncestorIsDeep() {
		Graph g = new Graph(7);
		g.addEdge(0, 1); g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 4); g.addEdge(2, 5);
		g.addEdge(4, 6);
		g.addEdge(5, 6);
		
		assertEquals(0, g.findCommonAncestor(3, 6));
	}
}
