object example_0 {
  def main(args: Array[String]): Unit = {
    val strings = Array("A", "B", "C", "D", "E", "A")
    val queries = Array("A", "C", "E")

    println(strings.count(el => el == "A"))

    def getStringMatches(strings: Array[String], queries: Array[String]): Array[Int] = {
      val arrayMatches = queries.map(qr => strings.count(entry => entry == qr))
      arrayMatches
    }
    val results = getStringMatches(strings , queries)
    println(results.mkString(", "))
  }

}

