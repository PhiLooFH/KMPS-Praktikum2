# XML Parser in Scala

This Scala program is a simple XML parser that converts a `List[Char]` containing XML data into a `List[String]` where each string represents a full XML tag. It uses pattern matching to identify and extract XML tags from the character list.

## How to Use

1. Clone the repository or copy the code into your own Scala project.

2. To parse XML data, use the `parseXML` method of the `XMLParser` object. Pass a `List[Char]` as input to this method, and it will return a `List[String]` containing each full XML tag.

   ```scala
   val xmlChars = List('<', 't', 'a', 'g', '>', 'c', 'o', 'n', 't', 'e', 'n', 't', '<', '/', 't', 'a', 'g', '>', 'm', 'o', 'r', 'e', '<', 't', 'a', 'g', '>', 'd', 'a', 't', 'a', '<', '/', 't', 'a', 'g', '>', '<', '/', 'r', 'o', 'o', 't', '>')
   val xmlTags = XMLParser.parseXML(xmlChars)
   xmlTags.foreach(println)


## Example
1. Suppose you have the following XML data as a List[Char]:
    ```scala
    val xmlChars = List('<', 'root', '>', '<', 'tag', '>', 'content', '<', '/', 'tag', '>', 'more', '<', 'tag', '>', 'data', '<', '/', 'tag', '>', '<', '/', 'root', '>')
2. When you parse it using the provided code, you'll get the following result:
    ```xml
   <root>
     <tag>
     </tag>
       more
     <tag>
     </tag>
    </root>
