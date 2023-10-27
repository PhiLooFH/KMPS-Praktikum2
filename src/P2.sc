import scala.io.Source

case class Track(title: String, length: String, rating:
Int, features: List[String], writers: List[String])

case class Album(title: String, date: String, artist:
String, tracks: List[Track])

val fileSource = Source.fromFile("/Users/philipp/Documents/Uni/KMPS/Praktikum/P2/resources/alben.xml")
val xmlFile = fileSource.toList
fileSource.close()
println(xmlFile)