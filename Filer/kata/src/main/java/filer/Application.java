package filer;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * File copy test driver
 */
public class Application
{
  public static void main(final String[] args)
    throws IOException
  {
    CommandLine commandLine = Arguments.parse(args);
    
    if (commandLine.getArgs().length != 2) {
      System.out.println("Usage: java -jar <filer.jar> <sourceDirectory> <targetDirectory>");
      System.exit(1);
    }

    FilerUtil.copy(args[0], args[1]);
  }
}

class Arguments
{
  private static Options options = new Options();

  static {
    options.addOption("i", "implementation", true,
        "Define which implementation to use i.e. 'channel', 'files', 'reader'");
  }

  public static CommandLine parse(final String[] args) {
    CommandLine commandLine = null;
    try {
      CommandLineParser parser = new PosixParser();
      commandLine = parser.parse(options, args);
    } catch (ParseException exp) {
      System.out.println("Invalid options: " + exp.getMessage());
      return null;
    }
    return commandLine;
  }
}
