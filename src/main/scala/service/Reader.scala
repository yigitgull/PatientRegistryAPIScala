package service

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

class Reader {
  def getDocument(fileName: String) = try Files.lines(Path.of(fileName)).collect(Collectors.joining(""))
  catch {
    case e: IOException =>
      System.out.println("File was not read")
      throw new RuntimeException(e)
  }
}