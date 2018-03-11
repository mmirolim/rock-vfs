package filesystem

class File(
  override val pPath: String,
  override val name: String,
  val contents: String,
) extends DirEntry(pPath, name)

