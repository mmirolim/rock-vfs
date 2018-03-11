package filesystem

import datastructures.{List, Cons, Nil}

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

  
}

object Dir {
  val separator: String = "/"
  val rootPath: String = "/"
  def empty(pPath: String, name: String): Dir = {
    new Dir(pPath, name, Nil)
  }
  def newRoot: Dir = Dir.empty("","")
}
