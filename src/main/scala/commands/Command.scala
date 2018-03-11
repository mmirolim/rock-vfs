package commands

import filesystem.State

trait Command {
  def apply(state: State): State
}

object Command {
  def unknownCmd(name: String) =
    new Command {
      override def apply(state: State): State =
        state.withMsg(name + "unknown command")
    }

  def from(in: String): Command = {
    val tokens = in.trim.split(" ")
    // TODO return commands
    unknownCmd(tokens(0))
  }
}

