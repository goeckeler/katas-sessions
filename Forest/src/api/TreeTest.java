package api;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreeTest {

	@Test
	public void shouldHaveSingleNode() {
		StringNode tree = new StringNode("a");
		assertEquals("a", tree.get());
	}
	
	@Test
	public void shouldHaveSingleKid() {
		StringNode tree = new StringNode("a");
		tree.add(new StringNode("b"));
		assertNotNull(tree.children());
		assertEquals(1, tree.children().size());
		assertEquals("b", tree.children().iterator().next().get());
	}
}
