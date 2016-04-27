package com.gildedrose;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

public class Loader
{
  private static final Charset UTF8 = Charset.forName("UTF-8");

  public static List<String> readFromFile(String directory, String file)
    throws IOException
  {
    String root = System.getProperty("user.dir");
    return Files.readAllLines(FileSystems.getDefault().getPath(root, directory, file), UTF8);
  }

  public static String readAsStringFromFile(String directory, String file)
    throws IOException
  {
    StringBuilder string = new StringBuilder();
    for (String text : readFromFile(directory, file)) {
      string.append(text).append("\n");
    }

    return string.toString();
  }
}
