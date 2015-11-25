package kata;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExceptionTests
{
  @Rule
  public ExpectedException exception = ExpectedException.none();

  Thrower thrower = new Thrower();

  // custom assertion
  private void assertInstanceOf(Object object, Class<?> clazz) {
    Assert.assertNotNull(String.format("Object is null, but it should subclass of %s", clazz), object);
    String message = String.format("Object should be of type %s, but was %s", clazz, object.getClass());
    Assert.assertTrue(message, object.getClass().isAssignableFrom(clazz));
  }

  @Test
  public void shouldThrowMyRuntimeExceptionUsingTryCatch() {
    try {
      thrower.throwsRuntime();
      fail();
    } catch (Exception e) {
      // custom assertion, initial variant
      assertInstanceOf(e, MyRuntimeException.class);
      // Hamcrest assertion, later variant
      assertThat(e, instanceOf(MyRuntimeException.class));
    }
  }

  @Test(expected = MyRuntimeException.class)
  public void shouldThrowMyRuntimeExceptionUsingAnnotation() {
    thrower.throwsRuntime();
  }

  @Test
  public void shouldThrowMyRuntimeExceptionWithCauseUsingRule() {
    exception.expect(MyRuntimeException.class);
    exception.expectCause(isA(IllegalStateException.class));
    exception.expectMessage(containsString("custom"));
    
    thrower.throwsRuntimeWithCause();
  }
  
  @Test
  public void demonstratesHamcrestMatcher() {
    assertThat(Math.pow(1, 1), equalTo(1.0));
  }
}
