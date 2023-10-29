import scala.annotation.tailrec
import scala.io.Source

case class Track(title: String, length: String, rating:
Int, features: List[String], writers: List[String])

case class Album(title: String, date: String, artist:
String, tracks: List[Track])

@tailrec
private def getStringContent(charList: List[Char], returnString: String) : String = charList match {
  case Nil => returnString
  case '>'::xs => returnString //text starts with < and ends with > - Tag
  case '<'::xs => returnString //text starts with > and ends with < - Content
  case x::tail =>
    val builtString = returnString + x
    getStringContent(tail, builtString)
}

@tailrec
private def createTokenList(charList: List[Char], tokenList: List[String]): List[String] = charList match {
  case '<'::xs =>
    val newTokenList = tokenList :+ getStringContent(xs, "")
    createTokenList(xs, newTokenList)
  case '>'::x::xs =>
    x match {
      case '\r' => createTokenList(xs , tokenList) //exclude whitespaces /r or /n depending on the system
      case _ =>
        val newTokenList = tokenList :+ getStringContent(x :: xs, "")
        createTokenList(xs, newTokenList)
    }
  case Nil => tokenList
  case _::xs => createTokenList(xs, tokenList)
}

@tailrec
private def parseFile(tokenList: List[String], albumList: List[Album]): List[Album] = tokenList match {
  case Nil => albumList
  case "album" :: xs => parseFile(xs, albumList :+ parseAlbum(xs, Album("", "", "", Nil)))
  case _ :: xs => parseFile(xs, albumList)
}

@tailrec
private def parseAlbum(tokenList: List[String], album: Album): Album = tokenList match {
  case Nil => album
  case "/album" :: _ => album
  case "track" :: xs => parseAlbum(xs, album.copy(tracks = album.tracks :+ parseTrack(xs, Track("", "", 0, Nil, Nil))))
  case "date" :: xs => parseAlbum(xs, album.copy(date = xs.head))
  case "artist" :: xs => parseAlbum(xs, album.copy(artist = xs.head))
  case "title" :: xs => parseAlbum(xs, album.copy(title = xs.head))
  case _ :: xs => parseAlbum(xs, album)
}

@tailrec
private def parseTrack(tokenList: List[String], track: Track): Track = tokenList match {
  case Nil => track
  case "/track" :: _ => track
  case "writing" :: xs => parseTrack(xs, track.copy(writers = track.writers :+ xs.head))
  case "feature" :: xs => parseTrack(xs, track.copy(features = track.features :+ xs.head))
  case "title" :: xs => parseTrack(xs, track.copy(title = xs.head))
  case "length" :: xs => parseTrack(xs, track.copy(length = xs.head))
  case "rating" :: xs => parseTrack(xs, track.copy(rating = xs.head.toInt))
  case _ :: xs => parseTrack(xs, track)
}

@tailrec
private def printList(saidList: List[Any]): Unit = saidList match {
  case Nil =>
  case x :: xs => println(x); printList(xs)
}

def main(args: Array[String]): Unit = {
  val fileSource = Source.fromFile("src/resources/alben.xml")
  val xmlFile = fileSource.toList;
  fileSource.close()
  val tokenList = createTokenList(xmlFile, Nil)
  val albumList = parseFile(tokenList, Nil)
  println(albumList)
  printList(albumList)
}
