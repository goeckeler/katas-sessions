package api;


public class StringNode extends Tree<String> {
	private String text;

	public StringNode() {
		this("");
	}
	
	public StringNode(String text) {
		this.text = text;
	}
	
	@Override
	public String get() {
		return text;
	}

	@Override
	public void set(String object) {
		this.text = object;
	}
}
