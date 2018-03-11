package filesystem

import datastructures.List

class Dir(
  override val pPath: String,
  override val name: String,
  val contents: List[DirEntry],
) extends DirEntry(pPath, name)
