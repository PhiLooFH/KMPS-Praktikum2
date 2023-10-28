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
    case '<'::xs =>
      val newTokenList = tokenList :+ getStringContent(xs, "")
      createTokenList(xs, newTokenList)
    case '>'::x::xs => x match {
      case '\r' => createTokenList(xs , tokenList) //exclude whitespaces /r or /n depending on the system
      case _ =>
        val newTokenList = tokenList :+ getStringContent(x :: xs, "")
        createTokenList(xs, newTokenList)
    }
    case _::xs => 
      createTokenList(xs, tokenList)
  }

  @tailrec
  private def getStringContent(charList: List[Char], returnString: String) : String = charList match {
    case '>'::tail => returnString //text starts with < and ends with > - Tag
    case '<'::tail => returnString //text starts with > and ends with < - Content
    case x::tail =>
      val builtString = returnString + x
      getStringContent(tail, builtString)
  }

  def main(args: Array[String]): Unit = {
    val fileSource = Source.fromFile("resources/alben.xml")
    val xmlFile = fileSource.toList; fileSource.close()
    val tokenList = createTokenList(xmlFile, Nil)
    for(element <- tokenList) {
      println(element)
    }
  }
}