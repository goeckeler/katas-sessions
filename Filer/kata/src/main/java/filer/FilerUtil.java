package filer;

import static filer.TimeUtils.durationForHumans;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.EnumSet;

public class FilerUtil
{
  private static Filer filer = null;

  public static void copy(final String sourceDirectory, final String targetDirectory)
    throws IOException
  {
    final Path sourcePath = FileSystems.getDefault().getPath(sourceDirectory);
    final Path targetPath = FileSystems.getDefault().getPath(targetDirectory);

    final LocalDateTime startTime = LocalDateTime.now();

    Files.walkFileTree(sourcePath, EnumSet.noneOf(FileVisitOption.class), 1, new SimpleFileVisitor<Path>()
    {
      @Override
      public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs)
        throws IOException
      {
        final Path targetdir = targetPath.resolve(sourcePath.relativize(dir));

        if (Files.isDirectory(dir) && Files.notExists(targetdir)) {
          Files.createDirectory(targetdir);
        }

        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
        throws IOException
      {
        final Path target = targetPath.resolve(sourcePath.relativize(file));
        filer.copy(file, target);
        return FileVisitResult.CONTINUE;
      }
    });

    final LocalDateTime endTime = LocalDateTime.now();

    System.out.println("Copy took " + durationForHumans(Duration.between(startTime, endTime)));
  }
}
