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
    Options options = Options.parse(args);
    FilerUtil fileUtils = new FilerUtil().includeFolderLevels(options.getLevels()); 
    fileUtils.copy(options.getSource(), options.getTarget());
  }
}
