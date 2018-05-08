package filesystem

abstract class DirEntry(val pPath: String, val name: String) {
  def getType: String
  def isRoot: Boolean
  def isDir: Boolean
  def isFile: Boolean
  def asDir[S >: DirEntry]: S

  def path: String = {
    val separator =
      if (Dir.rootPath.equals(pPath)) ""
      else Dir.separator

    pPath + separator + name
  }
}
