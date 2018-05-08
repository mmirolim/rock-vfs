package commands

import filesystem.State


class Cd(name: String) extends Command {
  override def apply(state: State): State = {
    val newWd = state.wd.findEntry(name)
    if (newWd == null) state.withMsg("no such directory " + name)
    else State.apply(state.wd, newWd.asDir)
  }
}
