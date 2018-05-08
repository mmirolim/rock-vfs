package filesystem

class State(val root: Dir, val wd: Dir, val out: String) {


  def show: Unit = {
    println(out)
    print(wd.name)
    print(State.shellToken)
  }

  // build a new state
  def withMsg(msg: String): State = new State(root, wd, msg)

}

object State {
  val shellToken = "$"
  def apply(root: Dir, wd: Dir, out: String = "") = new State(root, wd, out)
}
