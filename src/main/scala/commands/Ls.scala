package commands

import datastructures.List
import filesystem.{ DirEntry, State }

class Ls extends Command {
  override def apply(state: State): State = {
    state.withMsg(getOutput(state.wd.contents))
  }

  def getOutput(content: List[DirEntry]): String = {
    if (content.isEmpty) ""
    else content.head.name + "[" + content.head.getType + "]\n" + getOutput(content.tail)
  }
}
