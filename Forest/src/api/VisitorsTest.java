package api;

import static org.junit.Assert.*;

import org.junit.Test;

public class VisitorsTest {

	@Test
	public void shouldFoldTreeAsString() {
		ToStringVisitor<String> asString = new ToStringVisitor<String>();
		
		Sample.sampleForrest().visit(asString);
		assertEquals("a, b, d, e, f, c, g, h, i, j", asString.toString());
	}

	@Test
	public void shouldPrintTreeAsString() {
		ToTreeStringVisitor<String> asString = new ToTreeStringVisitor<String>();
		Sample.sampleForrest().visit(asString);
		assertEquals("a\n  b\n    d\n    e\n    f\n  c\n    g\n    h\ni\n  j\n", asString.toString());
	}

}
