object ReadLinesFromFile extends App {

  import scala.io.Source

  def widthOfLength(s: String) = s.length.toString.length

  if (args.length > 0) {
    val lines = Source.fromFile(args(0)).getLines().toList
    val longestLine = lines.reduceLeft((a, b) => if (a.length > b.length) a else b)
    var maxWidth = widthOfLength(longestLine)

    for (line <- lines) {
      val numSpaces = maxWidth - widthOfLength(line)
      val padding = " " * numSpaces
      println(padding + line.length + " | " + line)
    }
  } else {
    Console.err.println("Please enter fileName.")
  }

  // Execute "scala ReadLinesFromFile.scala [targetFile]" on shell
}