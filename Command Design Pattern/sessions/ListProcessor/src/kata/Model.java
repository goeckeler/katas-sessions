package kata;

import java.util.LinkedList;
import java.util.List;

public class Model
{
  private static final Model instance = new Model();

  private final List<String> items = new LinkedList<>();

  private Model() {
  }

  public static final Model model() {
    return instance;
  }

  public boolean add(String item) {
    return items.add(item);
  }

  public boolean remove(String item) {
    return items.remove(item);
  }

  public void clear() {
    items.clear();
  }

  public List<String> list() {
    return items;
  }
}
