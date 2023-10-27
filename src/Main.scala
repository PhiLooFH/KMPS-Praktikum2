import scala.annotation.tailrec
import scala.io.Source

case class Track(title: String, length: String, rating:
Int, features: List[String], writers: List[String])

case class Album(title: String, date: String, artist:
String, tracks: List[Track])

object Main {

  def createTokenList(charList: List[Char]): String = charList match {
    case Nil => "not found"
    case '<'::tail => tail match {

      case head::'>'::xs => createTokenList(xs)

    }
    case _::tail => createTokenList(tail)
  }

  def main(args: Array[String]): Unit = {
    val fileSource = Source.fromFile("resources/alben.xml")
    val xmlFile = fileSource.toList
    fileSource.close()
    for (element <- createTokenList(xmlFile)) {
      println(element)
    }
  }
}