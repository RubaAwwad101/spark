object example_1 {
  def main(args: Array[String]): Unit = {
    val numbers=(0 to 12).toArray
    val evenCount = numbers.count(_%2==0)
    println(s" there are $evenCount and ${numbers.mkString(",")}")
    val strings = Array("A", "B", "C", "D", "E", "A","f","j","h")
    val queries = Array("A", "C", "E")
    val results = queries.map(qr => strings.count(_==qr) )
    println(results.mkString(","))

    val grouped = strings.grouped(3).toArray
    grouped.foreach(el => println(el.mkString(",")))


    val groupBy = strings.groupBy(_.hashCode)
    groupBy.foreach(el => println(el))

    val aplha = 'A' to 'Z'
    for(letter <- aplha){
      println(s"$letter hashes to ${letter.hashCode()}")
    }

  }
}
