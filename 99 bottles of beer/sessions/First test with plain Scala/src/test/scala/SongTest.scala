/**
 * Created by tgo on 24.06.2014.
 */

import java.util

import org.junit.runner.RunWith
import org.scalatest.{Matchers, FlatSpec, FunSuite}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SongTest extends FlatSpec with Matchers {
  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new util.Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be (2)
    stack.pop() should be (1)
  }

  "Song" should "start with title" in {
     Song.sing shall startWith ("The \"99 bottles of beer\" song")
  }
 }
