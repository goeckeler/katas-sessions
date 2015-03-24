package filer;

import java.io.IOException;

/**
 * File copy test driver
 */
public class Application
{
  public static void main(final String[] args)
    throws IOException
  {
    if (args.length != 2) {
      System.out.println("Usage: java -jar <filer.jar> <sourceDirectory> <targetDirectory>");
      System.exit(1);
    }

    FilerUtil.copy(args[0], args[1]);
  }
}
