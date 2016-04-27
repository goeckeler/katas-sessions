package com.gildedrose;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GildedRoseTest
{

  @Test
  public void foo() {
    Item[] items = new Item[] { new Item("foo", 0, 0)
    };
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertThat(app.items[0].name, equalTo("foo"));
  }
}
