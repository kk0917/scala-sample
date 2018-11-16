object StringSearch extends App {

  // Simple search
  val text1: Seq[Char] = "カワカドカドカドドワンゴカドカドンゴドワドワンゴドワカワカドンゴドワ".toSeq
  val pattern1: Seq[Char] = "ドワンゴ".toSeq
  val matchIndexes1 = search(text1, pattern1)

  def search(text1: Seq[Char], pattern1: Seq[Char]): Seq[Int] = {
    var matchIndexes1 = Seq[Int]()
    for (i <- text1.indices) {
      val partText: Seq[Char] = text1.slice(i, i + pattern1.length)
      if (isMatch(partText, pattern1)) matchIndexes1 :+= i
    }
    matchIndexes1
  }

  def isMatch(partText: Seq[Char], pattern1: Seq[Char]): Boolean = {
    var isMatch = true
    var i = 0
    while (isMatch && i < pattern1.length) {
      if (partText.length < pattern1.length || partText(i) != pattern1(i)) isMatch = false
      i += 1
    }
    isMatch
  }

  println(s"出現場所: $matchIndexes1")

  // Regex search
  val text2 = "カワカドカドカドドワンゴカドカドンゴドワドワンゴドワカワカドンゴドワ"
  val pattern2 = "ドワンゴ"
  val matchIndexes2 = pattern2.r.findAllIn(text2).matchData.map(_.start).toList
  println(s"出現場所: $matchIndexes2")

  // Java's String type method
  println(text2.indexOf(pattern2))
  println(text2.lastIndexOf(pattern2))
}