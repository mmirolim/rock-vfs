package filesystem

import datastructures.{List, Cons, Nil}
import scala.annotation.tailrec

class Dir(
  override val pPath: String,
  override val name: String,
  val contents: List[DirEntry],
) extends DirEntry(pPath, name) {

  def isRoot: Boolean = pPath.isEmpty

  def isDir: Boolean = true

  def isFile: Boolean = false

  def getType: String = "Directory"

  def asDir: Dir = this

  def asFile: File = throw new FileSystemException("a directory cannot be converted to a file")

  def addEntry(newEntry: DirEntry): Dir = new Dir(pPath, name, contents ++ new Cons(newEntry, Nil))

  def hasEntry(name: String): Boolean = findEntry(name) != null

  def findEntry(name: String): DirEntry = {
    @tailrec
    def walk(name: String, contents: List[DirEntry]): DirEntry = {
      if (contents.isEmpty) null
      else if (contents.head.name.equals(name)) contents.head
      else walk(name, contents.tail)
    }

    walk(name, contents)
  }

  def getAllDirNamesInPath: List[String] = {
    // /a/b/c/d => List["a","b", "c", "d"]
    List.toList(path.substring(1).split(Dir.separator))
  }

  def findDescendant(path: List[String]): Dir = ???
}

object Dir {
  val separator: String = "/"
  val rootPath: String = "/"
  def empty(pPath: String, name: String): Dir = {
    new Dir(pPath, name, Nil)
  }
  def newRoot: Dir = Dir.empty("","")

  // TODO use regex
  def validateName(name: String): Boolean = !(name.contains(".") || name.contains("/"))
}
