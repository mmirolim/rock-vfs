package commands

import filesystem.State

trait Command {
  def apply(state: State): State
}

object Command {
  val LS = "ls"
  val MKDIR = "mkdir"
  val CD = "cd"

  def unknownCmd(name: String) =
    new Command {
      override def apply(state: State): State =
        state.withMsg(name + "unknown command")
    }

  def incompleteCmd(cmdName: String): Command = new Command {
    override def apply(state: State): State = {
      state.withMsg(cmdName + ": incomplete command")
    }
  }
  
  def from(in: String): Command = {
    val tokens = in.trim.split(" ")
    if (in.isEmpty || tokens.isEmpty) emptyCmd
    else if (LS.equals(tokens(0))) {
      new Ls
    } else if (MKDIR.equals(tokens(0))){
      if (tokens.length < 2) incompleteCmd(MKDIR)
      else new Mkdir(tokens(1))
    } else if (CD.equals(tokens(0))){
           new Cd(tokens(1))
    } else unknownCmd(tokens(0)) 
  }

  def emptyCmd: Command = new Command{
    override def apply(state: State): State = state
  }



}

