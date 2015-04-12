package filer;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.Ignore;
import org.junit.Test;

public class HumanReadableDurationTest
{
  @Test
  @Ignore
  public void shouldReportTimeInSeconds() {
    final LocalDateTime startTime = LocalDateTime.now();
    final LocalDateTime endTime = startTime.plusSeconds(4);
    assertEquals("4s", Duration.between(startTime, endTime).toString());
  }
}
