object Song extends App {
  def main(args: Array[String]) {
    println sing()
  }

  def sing(bottles: Int = 99) : String = {
    "The \"99 bottles of beer\" song"
  }
}