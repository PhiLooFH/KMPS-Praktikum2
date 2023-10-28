import scala.annotation.tailrec
import scala.io.Source

case class Track(title: String, length: String, rating:
Int, features: List[String], writers: List[String])

case class Album(title: String, date: String, artist:
String, tracks: List[Track])

object Main {

  @tailrec
  private def createTokenList(charList: List[Char], tokenList: List[String]): List[String] = charList match {
    case Nil => tokenList
    case '\r'::xs  =>
      createTokenList(xs, tokenList)
    case '<'::xs =>
      val newTokenList = tokenList :+ getTagAsString(xs, "")
      createTokenList(xs, newTokenList)
    case _::xs =>
      createTokenList(xs, tokenList)
  }

  @tailrec
  private def getTagAsString(charList: List[Char], returnString: String) : String = charList match {
    case Nil => returnString
    case '>'::tail => returnString
    case x::tail =>
      val builtString = returnString.concat(x.toString)
      getTagAsString(tail, builtString)
  }

  def main(args: Array[String]): Unit = {
    val fileSource = Source.fromFile("resources/alben.xml")
    val xmlFile = fileSource.toList; fileSource.close()
    val tokenList: List[String] = Nil
    println(createTokenList(xmlFile, tokenList))
  }
}