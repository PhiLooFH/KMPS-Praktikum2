# Scala XML Parser

This is a Scala application for parsing XML files representing albums and their tracks. It extracts the album details, including title, date, artist, and a list of tracks with their properties.

## Usage

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/PhiLooFH/KMPS-Praktikum2.git
   ```

2. **Compile and Run**:

   You can compile and run the Scala program or run it from within the CLI using the following commands:

   ```bash
   cd src
   scala P2.sc
   ```

   ```bash
   cd src
   scalac Main.scala
   scala Main.scala
   ```

3. **Input XML File**:

   Make sure you have an XML file with album and track information. By default, the program reads the file located at `src/resources/alben.xml`. You can replace this file with your own XML file.

## Description

This Scala program uses tail-recursive functions to parse an XML file and extract album information. It defines two main data structures: `Album` and `Track`.

- `Album`:
   - `title`: The title of the album.
   - `date`: The release date of the album.
   - `artist`: The artist or band associated with the album.
   - `tracks`: A list of `Track` objects representing the individual tracks on the album.

- `Track`:
   - `title`: The title of the track.
   - `length`: The length of the track.
   - `rating`: The rating of the track.
   - `features`: A list of featured artists or contributors.
   - `writers`: A list of writers or composers of the track.

The program reads the XML file, tokenizes it, and then recursively parses the tokens to build a list of `Album` objects. It prints the list of albums and their details.

## XML Structure

The XML file should follow this structure:

```xml
<album>
  <title>Album Title</title>
  <date>Release Date</date>
  <artist>Artist Name</artist>
  <track>
    <title>Track Title</title>
    <length>Track Length</length>
    <rating>Track Rating</rating>
    <feature>Feature 1</feature>
    <feature>Feature 2</feature>
    <writing>Writer 1</writing>
    <writing>Writer 2</writing>
  </track>
  <!-- Add more tracks as needed -->
</album>
<!-- Add more albums as needed -->
```

Please ensure that your XML file adheres to this structure.

## Example

Example of how to use the parsing Methods:

```scala
object Main {
...
  def main(args: Array[String]): Unit = {
    val fileSource = Source.fromFile("your-xml-file.xml")
    val xmlFile = fileSource.toList
    fileSource.close()
    
    val tokenList = Main.createTokenList(xmlFile, Nil)
    val albumList = Main.parseFile(tokenList, Nil)
    
    // Print the list of albums and their details
    Main.printList(albumList)
  }
}
