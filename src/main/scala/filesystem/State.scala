package filesystem

class State(val root: Dir, val wd: Dir, val out: String) {

  def showShell: Unit = print(State.shellToken)

  def show: Unit = {
    println(out)
    // TODO should print dir name
    println(wd.name)
    showShell
  }

  // build a new state
  def withMsg(msg: String): State = new State(root, wd, msg)

}

object State {
  val shellToken = "$"
  def apply(root: Dir, wd: Dir, out: String = "") = new State(root, wd, out)
}
