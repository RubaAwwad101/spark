object example_Aplhapatic {

  def main(args: Array[String]): Unit = {
    val alpha ='a' to 'z'
    for(l <- alpha){
      println(s"$l hash in ${l.hashCode()}")
    }
  }
}
