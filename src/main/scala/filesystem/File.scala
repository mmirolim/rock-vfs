package filesystem

class File(
  override val pPath: String,
  override val name: String,
  val contents: String,
) extends DirEntry(pPath, name) {

  override def getType: String = "File"
  override def isRoot: Boolean = throw new UnsupportedOperationException
  override def isDir: Boolean = false
  override def isFile: Boolean = true
}

