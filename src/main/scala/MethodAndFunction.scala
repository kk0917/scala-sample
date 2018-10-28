object MethodAndFunction extends App {

  // method
  def isAlphamericFDef(str: String): Boolean = str.matches("[a-z]+")
  println(isAlphamericFDef("hello"))

  // function
  val isAlphamericF = (str: String) => str.matches("[a-z]+")
  val words = Seq("scala", "2,12")
  println(words.filter(isAlphamericF))
}